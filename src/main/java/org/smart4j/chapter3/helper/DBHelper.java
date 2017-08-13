package org.smart4j.chapter3.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.PropsUtil;
import org.smart4j.framework.utils.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * database helper
 * Created by wangz on 2017/8/8.
 */
public class DBHelper {

    private static final Logger logger = LoggerFactory.getLogger(DBHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
    private static final BasicDataSource DATA_SOURCE;

    /**
     * init dbdb connection properties
     * and load jdbc driver
     */
    static{
        //read config.properties
        Properties conf = PropsUtil.loadProps("smart.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password =  conf.getProperty("jdbc.password");
        //init BaseDataSource
        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    /**
     * get connection
     * @return
     */
    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if( conn == null){
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                logger.error("get connection failure",e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    /**
     * excute sql file
     * @param filePath
     */
    public static void executeSqlFile(String filePath){
        if(StringUtil.isNotEmpty(filePath)){
            InputStream is = null;
            BufferedReader reader = null;
            try {
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
                reader = new BufferedReader(new InputStreamReader(is));
                String sql = null;
                while((sql = reader.readLine()) != null){
                    update(sql);
                }
            } catch (IOException e) {
                logger.error("[executeSqlFile] execute sql file failure", e);
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    logger.error("close reader failure", e);
                }
            }
        }
    }

    /**
     * query entity list
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryList(Class<T> clazz, String sql, Object... params){
        try{
            return QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(clazz), params);
        }catch( SQLException e) {
            logger.error("query entity list failure，sql:" + sql, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * query bean entity
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> clazz, String sql, Object... params){
        try {
            return QUERY_RUNNER.query( getConnection(), sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            logger.error("[queryEntity] query entity failure, sql：" + sql, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * excute sql for map list
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> query(String sql, Object... params){
        try{
            return QUERY_RUNNER.query( getConnection(), sql, new MapListHandler(), params);
        }catch(Exception e){
            logger.error("[query] execute query failure，sql：" + sql, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * execute update sql, return changet row num
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params){
        try {
           return QUERY_RUNNER.update(getConnection(),sql, params);
        } catch (SQLException e) {
            logger.error("[update] execute update failure, sql：" + sql, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * insert a entity
     * @param clazz
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> clazz, Map<String, Object> fieldMap){
        if( clazz == null){
            logger.error("can not insert entity which class is null");
            return false;
        }
        if(CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not insert entity : fieldMap is empty");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(clazz);
        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        Object[] params = new Object[fieldMap.size()];
        int k = 0;
        for(String field : fieldMap.keySet()){
            columns.append(field).append(",");
            values.append("?,");
            params[k++] = fieldMap.get(field);
        }
        sql += columns.replace( columns.lastIndexOf(","), columns.length(),") ")
                + " VALUES "
              + values.replace( values.lastIndexOf(","), values.length(), ") ");
        return update(sql, params) == 1;
    }

    /**
     * update entity by id
     * @param clazz
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> clazz, Map<String, Object> fieldMap){
        if(clazz == null){
            logger.error("can not update entity which class is null");
            return false;
        }
        if( CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not update entity : fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTableName(clazz) + " SET ";
        StringBuilder columns = new StringBuilder();
        Object[] params = new Object[fieldMap.size()+1];
        int k = 0;
        for(String field : fieldMap.keySet()){
            columns.append(field).append("=?,");
            params[k++] = fieldMap.get(field);
        }
        params[k] = fieldMap.get("ID");
        sql += columns.substring(0, columns.length()-1) + " WHERE ID = ?";
        return update(sql, params) == 1;
    }

    /**
     * delete entity by id
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> clazz, long id){
        String sql = "DELETE FROM " + getTableName(clazz) + " WHERE ID = ?";
        return update(sql, id) == 1;
    }
    /**
     * get table name by class simple name
     * @param clazz
     * @return
     */
    private static String getTableName(Class<?> clazz){
        return clazz.getSimpleName();
    }
}
