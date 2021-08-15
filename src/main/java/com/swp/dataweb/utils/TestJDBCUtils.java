package com.swp.dataweb.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestJDBCUtils {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/dataweb?serverTimezone=GMT";
        Connection conn = DriverManager.getConnection(url,"root","root");
        //System.out.println(conn);
        // 获取语句执行平台对象 Statement
        Statement smt = conn.createStatement();
        //  插入数据
        String sql_i = "select * from dataweb_task";
        smt.executeQuery(sql_i);
    }

    //定义一个方法，查询user表的数据将其封装为对象，然后装载集合，返回。
    public static List<UserBean> findAll(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<UserBean> list = null;
        try {
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "SELECT * FROM user";
            //获取执行sql的对象
            st = conn.createStatement();
            //执行sql
            rs = st.executeQuery(sql);
            UserBean bean = null;
            list = new ArrayList<UserBean>();
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                bean = new UserBean(id,username,password);
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,st,conn);
        }
        return list;
    }
}
