package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;
    @FXML
    public void Add(MouseEvent event) throws SQLException {
        ResultSet res= HelloApplication.connection.executeQuery("Select max(productID) from product");
        int productID= 0;
        if(res.next()){
            productID= res.getInt("max(productID)") + 1;
        }
        String query= String.format("Insert into product values('%s', '%s', '%s', '%s' )",productID, name.getText(), price.getText(), email.getText());
        int response= HelloApplication.connection.executeUpdate(query);
        Dialog<String> dialog= new Dialog<>();
        dialog.setTitle("Product Status");
        ButtonType type= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response > 0){
            dialog.setContentText("Product added successfully");
        }
        else{
            dialog.setContentText("Product insertion failed");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
