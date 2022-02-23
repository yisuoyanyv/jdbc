package com.zjl.lesson03;

import com.zjl.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement st =null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();//获取数据库连接
            //区别
            //使用 ？ 占位符代替参数
            String sql=" delete from users where id=?" ;
            st=conn.prepareStatement(sql);//预编译SQl，先写SQL，不执行
            //手动给参数赋值
            st.setInt(1,6);//id
            final int i = st.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
