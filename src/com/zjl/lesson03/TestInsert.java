package com.zjl.lesson03;

import com.zjl.lesson02.utils.JdbcUtils;

import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement st =null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();//获取数据库连接
            //区别
            //使用 ？ 占位符代替参数
            String sql=" INSERT INTO `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`)" +
                    "VALUES(?,?,?,?,?)";
            st=conn.prepareStatement(sql);//预编译SQl，先写SQL，不执行
            //手动给参数赋值
            st.setInt(1,6);//id
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
            JdbcUtils.release(conn,st,rs);
        }
    }
}
