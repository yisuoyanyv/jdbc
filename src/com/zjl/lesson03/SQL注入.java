package com.zjl.lesson03;

import com.zjl.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        PreparedStatement st =null;
        ResultSet rs = null;

        try{
            conn= JdbcUtils.getConnection();
            //PreparedStatement防止SQL注入的本质，把传递过来的参数当作字符
            //假设其中存在转义字符，比如说 ' 会被直接转义   Mybatis
            //SQL
            String sql="select * from users where name =? and password =? ";
            st  = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);

            rs = st.executeQuery();//查询完毕会返回一个结果集
            while (rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("=================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
