package org.smart4j.framework.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类操作工具类
 * @author WZ
 * @Class ClassUtil
 * @create 2017-08-10
 **/
public final class ClassUtil {

   private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
   public static ClassLoader getClassLoader(){
       //获取当前线程中的ClassLoader即可
       return Thread.currentThread().getContextClassLoader();
   }

    /**
     * 加载类，需要提供类名（全名，包含包名），与是否初始化标志，此处的初始化指的是是否执行类的静态代码块
     * 为了提高加载类的性能，可将loadClass方法的isInitialized参数设置为false
     * @param className
     * @param isInitialized
     * @return
     */
   public static Class<?> loadClass(String className, boolean isInitialized){
       Class<?> cls;
       try{
           cls = Class.forName(className, isInitialized, getClassLoader());
       }catch(ClassNotFoundException e){
           logger.error("load class failure", e);
           throw new RuntimeException(e);
       }
       return cls;
   }

    /**
     * 加载类，需提供类名（全名）
     * 不做类的初始化（此处的初始化指的是是否执行类的静态代码块）
     * @param className
     * @return
     */
   public static Class<?> loadClass(String className){
       return loadClass(className, false);
   }

    /**
     * 获取指定报名下的所有类
     * @param packageName
     * @return
     */
   public static Set<Class<?>> getClassSet(String packageName){
       Set<Class<?>> classSet = new HashSet<Class<?>>();
       try {
           Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
           while(urls.hasMoreElements()){
               URL url = urls.nextElement();
               if(url != null){
                   String protocol = url.getProtocol();
                   if(protocol.equals("file")){
                       String packagePath = url.getPath().replaceAll("%20","");
                       addClass(classSet, packagePath, packageName);
                   }else if(protocol.equals("jar")){
                       JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                       if(jarURLConnection != null){
                           JarFile jarFile = jarURLConnection.getJarFile();
                           if( jarFile != null){
                               Enumeration<JarEntry> jarEntries = jarFile.entries();
                               while( jarEntries.hasMoreElements()){
                                   JarEntry jarEntry = jarEntries.nextElement();
                                   String jarEntryName = jarEntry.getName();
                                   if(jarEntryName.endsWith(".class")){
                                       String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                       doAddClass(classSet, className);
                                   }
                               }
                           }
                       }
                   }
               }
           }
       } catch (Exception e) {
           logger.error("get class set failure",e);
           throw new RuntimeException(e);
       }
       return classSet;
   }

    /**
     * 递归遍历指定包下的文件，并通过文件名加载到相应的类，添加到classSet中
     * @param classSet
     * @param packagePath
     * @param packageName
     */
   private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName){
       File[] files = new File(packagePath).listFiles(new FileFilter(){
           @Override
           public boolean accept(File file) {
               return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
           }
       });
       for(File file : files){
           String fileName = file.getName();
           if( file.isFile()){
               String className = fileName.substring(0, fileName.lastIndexOf("."));
               if(StringUtils.isNotEmpty(className)){
                   className = packageName + "." + className;
               }
               doAddClass(classSet, className);
           }else{
               String subPackagePath = fileName;
               if(StringUtil.isNotEmpty(packagePath)){
                   subPackagePath = packagePath + "/" + subPackagePath;
               }
               String subPackageName = fileName;
               if(StringUtil.isNotEmpty(packageName)){
                   subPackageName = packageName + "." + subPackageName;
               }
               addClass(classSet, subPackagePath, subPackageName);
           }
       }
   }

    /**
     * 通过类名加载类，并添加到set集合中
     * @param classSet
     * @param className
     */
   private static void doAddClass(Set<Class<?>> classSet, String className){
       Class<?> cls = loadClass(className, false);
       classSet.add(cls);
   }
}
