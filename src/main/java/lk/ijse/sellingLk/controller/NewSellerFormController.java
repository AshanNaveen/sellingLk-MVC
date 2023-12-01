package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class NewSellerFormController {
    @FXML
    private Pane sellerDetailPane;

    @FXML
    private JFXTextField txtSellerName;

    @FXML
    private JFXTextField txtSellerContact;

    @FXML
    private JFXTextField txtSellerEmail;

    @FXML
    private JFXTextField txtSellerAddress;

    @FXML
    private JFXTextField txtSellerNic;

    @FXML
    void btnNewSellerOnAction(ActionEvent event) {

    }
}
