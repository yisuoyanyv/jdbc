package com.zjl.lesson05.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestC3P0 {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement st =null;
        ResultSet rs=null;
        try{
            conn= JdbcUtis_C3P0.getConnection();//获取数据库连接 原来是自己实现的，现在用别人实现的
            //区别
            //使用 ？ 占位符代替参数
            String sql=" INSERT INTO `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`)" +
                    "VALUES(?,?,?,?,?)";
            st=conn.prepareStatement(sql);//预编译SQl，先写SQL，不执行
            //手动给参数赋值
            st.setInt(1,7);//id
            st.setString(2,"qinjing");
            st.setString(3,"12234");
            st.setString(4,"xxxx@qq.com");
            //注意点：
            // sql.Date  数据库    java.sql.Date()
            // util.Date  Java   new Date().getTime()获得时间戳
            st.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
            final int i = st.executeUpdate();
            if(i>0){
                System.out.println("插入成功");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtis_C3P0.release(conn,st,rs);
        }
    }
}
