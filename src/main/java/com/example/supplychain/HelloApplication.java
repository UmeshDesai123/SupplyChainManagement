package com.example.supplychain;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailid;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailid= "";
        connection= new DatabaseConnection();
        root= new Group();

        Header header= new Header();

        ProductPage products= new ProductPage();
        ListView<HBox> productList= products.showProducts();
        AnchorPane productPane= new AnchorPane();
        productPane.setLayoutX(50.0);
        productPane.setLayoutY(100.0);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(header.root, productPane);

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Supply Chain Management");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                System.out.println("Connection has Closed.");
                connection.con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}