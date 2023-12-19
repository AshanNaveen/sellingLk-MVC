package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.UserModel;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.sql.SQLException;

public class NewSellerFormController {
    @FXML
    private Pane sellerDetailPane;
    @FXML
    private Label lblSaved;

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
        String name = txtSellerName.getText();
        String contact = txtSellerContact.getText();
        String email = txtSellerEmail.getText();
        String address = txtSellerAddress.getText();
        String nic = txtSellerNic.getText();

        if (ValidateUtil.validatePhone(contact, txtSellerContact) &&
                ValidateUtil.validateMail(email, txtSellerEmail) &&
                ValidateUtil.validateNic(nic, txtSellerNic) &&
                ValidateUtil.validateAddress(address, txtSellerAddress)) {
            try {
                SellerModel model = new SellerModel();
                String uID = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
                boolean isSaved = model.saveSeller(new SellerDto(model.generateNextSellerId(), name, nic, email, address, contact, uID));
                if (isSaved) {
                    lblSaved.setText("Seller Saved Successfully !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                // Code to be executed at the end (500 millis)
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                } else {
                    lblSaved.setText("Try again !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtSellerAddressOnAction(ActionEvent event) {
        btnNewSellerOnAction(event);
    }

    @FXML
    void txtSellerContactOnAction(ActionEvent event) {
        txtSellerEmail.requestFocus();
    }

    @FXML
    void txtSellerNameOnAction(ActionEvent event) {
        txtSellerContact.requestFocus();
    }

    @FXML
    void txtSellerNicOnAction(ActionEvent event) {
        txtSellerAddress.requestFocus();
    }
    @FXML
    void txtSellerEmailOnAction(ActionEvent event) {
        txtSellerNic.requestFocus();
    }
}
