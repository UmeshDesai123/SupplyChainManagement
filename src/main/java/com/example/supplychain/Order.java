package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order{

    void placeOrder(String productID) throws SQLException {
        ResultSet res= HelloApplication.connection.executeQuery("select max(orderID) from orders");
        int orderID= 0;
        if(res.next()){
            orderID= res.getInt("max(orderID)")+1;
        }

        String query= String.format("insert into orders values(%S, %s, '%s')", orderID, productID, HelloApplication.emailid);
        int response= HelloApplication.connection.executeUpdate(query);
        if(response>0){
            System.out.println("Order Placed");
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Order update");
            ButtonType type= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Order placed successfully");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
