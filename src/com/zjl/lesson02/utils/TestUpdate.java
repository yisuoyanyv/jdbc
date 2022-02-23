package com.zjl.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn =null;
        Statement st =null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();//获取数据库连接
            st=conn.createStatement();//获取SQL执行对象
            String sql="UPDATE USERS SET NAME='框上',email='dddd@qq.com' WHERE id=1;";
            final int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("更新成功");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
