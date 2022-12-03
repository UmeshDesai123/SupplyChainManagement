package com.example.supplychain;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage{
    ListView<HBox> products;
    ListView<HBox> showProductsbyName(String search) throws SQLException{
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("Select * from product");
        products= new ListView<>();

        Label name= new Label();
        Label price= new Label();
        Label id= new Label();

        HBox details= new HBox();

        name.setMinWidth(60.0);
        id.setMinWidth(30.0);
        price.setMinWidth(70.0);

        name.setText("Name");
        price.setText("Price");
        id.setText("ID");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while(res.next()) {
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())){
                Label productName = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();

                productName.setMinWidth(60.0);
                productID.setMinWidth(30.0);
                productPrice.setMinWidth(70.0);
                buy.setText("Buy");
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailid.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login first before placing the order");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {

                            try {
                                Order place = new Order();
                                place.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Clicked on buy");
                        }
                    }
                });

                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productID.setText("" + res.getString("productID"));

                productDetails.getChildren().addAll(productID, productName, productPrice, buy);
                productList.add(productDetails);
            }
        }
        if(productList.size()==2){
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No product is available for your search.");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("Select * from product");
        products= new ListView<>();

        Label name= new Label();
        Label price= new Label();
        Label id= new Label();

        HBox details= new HBox();

        name.setMinWidth(60.0);
        id.setMinWidth(30.0);
        price.setMinWidth(70.0);

        name.setText("Name");
        price.setText("Price");
        id.setText("ID");

        details.getChildren().addAll(id, name, price);
        productList.add(details);

        while(res.next()){
            Label productName= new Label();
            Label productPrice= new Label();
            Label productID= new Label();
            Button buy= new Button();
            HBox productDetails= new HBox();

            productName.setMinWidth(60.0);
            productID.setMinWidth(30.0);
            productPrice.setMinWidth(70.0);
            buy.setText("Buy");
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailid.equals("")){
                        Dialog<String> dialog= new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login first before placing the order");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else {

                        try {
                            Order place= new Order();
                            place.placeOrder(productID.getText());
                        }
                        catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Clicked on buy");
                    }
                }
            });

            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productID.setText(""+res.getString("productID"));

            productDetails.getChildren().addAll(productID, productName, productPrice, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
