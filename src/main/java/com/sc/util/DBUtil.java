package com.sc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getThreadCon() throws SQLException {
        //获取当前线程的连接
        Connection con = threadLocal.get();
        //判断当前线程上是否有连接
        if (con == null) {
            //如果没有连接就从连接池中获取连接
            con = dataSource.getConnection();
            //存入当前线程的threadLocal中
            threadLocal.set(con);
        }
        //返回当前线程上的连接
        return con;
    }

    //开启事务
    public static void startTransaction() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = getThreadCon();
        }
        //将自动提交事务改为false
        conn.setAutoCommit(false);
    }

    //回滚事务
    public static void rollBackTransaction() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn != null) {
            conn.rollback();
        }
    }

    //提交事务
    public static void commitTransaction() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn != null) {
            conn.commit();
        }
    }

    //关闭事务
    public static void closeConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 将连接从static的ThreadLocal中移除,否则ThreadLocal会越来越臃肿导致内存溢出。
            threadLocal.remove();
        }
    }

}
