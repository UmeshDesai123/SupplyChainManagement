package com.example.supplychain;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    String SQLURL = "jdbc:mysql://localhost:3306/supply?useSSL=false";
    String userName = "root";
    String password= "Umesh@MySQL";
    Connection con = null;
    DatabaseConnection(){
        try {
            con = DriverManager.getConnection(SQLURL, userName, password);
            if(con!= null)
                System.out.println("Database connection has established!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String Query){
        ResultSet res= null;
        try {
            Statement statement = con.createStatement();
            res = statement.executeQuery(Query);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  res;
    }
    public int executeUpdate(String Query){
        int res=0;
        try {
            Statement statement = con.createStatement();
            res= statement.executeUpdate(Query);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
