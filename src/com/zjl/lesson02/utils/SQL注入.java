package com.zjl.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {
//        login("wangwu","123456");
        login(" 'or ' 1=1","123456");
    }

    //登录业务
    public static void login(String username,String password){
        Connection conn = null;
        Statement st =null;
        ResultSet rs = null;

        try{
            conn= JdbcUtils.getConnection();
            st  = conn.createStatement();
            //SQL
            String sql="select * from users where NAME='"+username+"' and PASSWORD='"+password+"'";

            rs = st.executeQuery(sql);//查询完毕会返回一个结果集
            while (rs.next()){
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
