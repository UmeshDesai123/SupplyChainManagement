package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    Text clicked;

    @FXML
    protected void onHelloButtonClick() {
        clicked.setText("Project Intialised");
    }
}