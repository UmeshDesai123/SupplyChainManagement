package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    public void login(MouseEvent event) throws SQLException, IOException{
        String query= String.format("Select * from user where emailID= '%s' And pass= '%s'", email.getText(), password.getText());
        ResultSet res= HelloApplication.connection.executeQuery(query);
        if(res.next()){
            String UserType= res.getString("userType");
            HelloApplication.emailid = res.getString("emailID");
            if(UserType.equals("Buyer") || UserType.equals("buyer")){
                System.out.println("Logged in as Buyer");

                ProductPage products= new ProductPage();
                Header header= new Header();
                ListView<HBox> productList= products.showProducts();

                AnchorPane productPane= new AnchorPane();
                productPane.setLayoutX(50.0);
                productPane.setLayoutY(100.0);
                productPane.getChildren().add(productList);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root, productPane);
            }
            else{
                System.out.println("Logged in as Seller");
                AnchorPane sellerPage= FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                sellerPage.setLayoutY(100);
                HelloApplication.root.getChildren().add(sellerPage);
            }
        }
        else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login Failed!!!");
            alert.setContentText("Please try Again with correct email/password.");
            alert.showAndWait();
        }
    }
    @FXML
    TextField newemail;
    @FXML
    TextField name;
    @FXML
    TextField newpassword;
    @FXML
    TextField type;

    @FXML
    public void create(MouseEvent event) throws IOException, SQLException{
        String query= String.format("insert into user values('%S', '%s', '%s', '%s')", newemail.getText(), name.getText(), newpassword.getText(), type.getText());
        int response= HelloApplication.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New user created");
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Account created successfully");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
