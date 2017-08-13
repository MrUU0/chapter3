package org.smart4j.chapter3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.helper.DBHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by wangz on 2017/8/8.
 */
@Service
public class CustomerService {

    private static  final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    /**
     * query by keyword
     * @param name
     * @return
     */
    public List<Customer> getCustomerList(String name){
        try {
            String sql = "SELECT * FROM CUSTOMER";
            if(name.isEmpty()){
                return DBHelper.queryList(Customer.class,sql);
            }else {
                return DBHelper.queryList(Customer.class,sql+" WHERE NAME LIKE ?", "%"+name+"%");
            }
        } catch (Exception e) {
            logger.error("[getCustomerList] execute sql failure", e);
            return null;
        }
    }

    /**
     * query customer by id
     * @param id
     * @return
     */
    public Customer getCustomer( long id){
        return DBHelper.queryEntity( Customer.class, "SELECT * FROM CUSTOMER WHERE ID = ?", id);
    }

    /**
     * create customer
     * @param params
     * @return
     */
    public boolean createCustomer(Map<String, Object> params){
        return DBHelper.insertEntity(Customer.class, params);
    }

    /**
     * update customer info
     * @param id
     * @param params
     * @return
     */
    public boolean updateCustomer( long id, Map<String, Object> params){
        params.put("ID",id);
        return DBHelper.updateEntity(Customer.class, params);
    }

    /**
     * delete customer by id
     * @param id
     * @return
     */
    public boolean deleteCustomer( long id){
        return DBHelper.deleteEntity(Customer.class, id);
    }

}
