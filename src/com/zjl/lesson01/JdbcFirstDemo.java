package com.zjl.lesson01;

import java.sql.*;

/*
我的第一个JDBC程序
 */
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载程序
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
        //2.用户信息和url
        //useUnicode=true&characterEncoding=utf8&useSSL=true
        String url="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="000000";
        //3.连接成功，数据库对象  Connection 代表数据库
        final Connection connection = DriverManager.getConnection(url, username, password);
        //4.执行SQL的对象 Statement 执行sql的对象
        final Statement statement = connection.createStatement();
        statement.executeQuery("");//查询操作，返回ResultSet
        statement.execute(""); //执行任何SQL
        statement.executeUpdate("");//更新、插入、删除、都是用这个，返回一个受影响的行数
        //5.执行SQL的对象去执行SQL，可能存在结果，查看返回结果
        String sql="select * from users";

        final ResultSet resultSet = statement.executeQuery(sql);//返回的结果集，结果集封装了我们全部的查询出来的结果

        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("name="+resultSet.getObject("NAME"));
            System.out.println("pwd="+resultSet.getObject("PASSWORD"));
            System.out.println("email="+resultSet.getObject("email"));
            System.out.println("birthday="+resultSet.getObject("birthday"));
            System.out.println("==========================================");
        }

        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
