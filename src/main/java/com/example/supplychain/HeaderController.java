package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginbutton;
    @FXML
    Label email;
    @FXML
    TextField searchtext;
    @FXML
    Button logoutButton;

    @FXML
    public void initialize(){
        if(!HelloApplication.emailid.equals("")){
            loginbutton.setOpacity(0);
            email.setText(HelloApplication.emailid);
        }
    }
    @FXML
    public void log(MouseEvent event) throws IOException{
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        loginpage.setLayoutY(100);
        HelloApplication.root.getChildren().add(loginpage);

    }
    @FXML
    public void search(MouseEvent event) throws IOException, SQLException {
        Header header= new Header();
        ProductPage product= new ProductPage();
        AnchorPane productpane= new AnchorPane();
        productpane.setLayoutX(100);
        productpane.setLayoutY(100);
        productpane.getChildren().add(product.showProductsbyName(searchtext.getText()));

        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root, productpane);

    }
    public void logout(MouseEvent even){
        if(logoutButton.getOpacity()==0){
            logoutButton.setOpacity(1);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailid= "";
                    logoutButton.setOpacity(0);
                    try {
                        Header header= new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else
            logoutButton.setOpacity(0);
    }
}
