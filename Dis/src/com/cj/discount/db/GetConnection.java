package com.cj.discount.db;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection
{
  public static Connection getConnection()
  {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/discount?useUnicode=true&characterEncoding=UTF-8";
    String name = "root";
    String password = "123456";
    Connection con = null;
    try
    {
      Class.forName(driver);
      try
      {
        con = DriverManager.getConnection(url, name, password);
        System.out.println("已获得数据库连接");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
     
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return con;
  }
}
