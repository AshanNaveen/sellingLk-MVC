package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.LoyalIdDto;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.LoyalIdModel;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.UserModel;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.sql.SQLException;
import java.util.List;

public class NewBuyerFormController {
    @FXML
    private Pane buyerDetailPane;

    @FXML
    private JFXTextField txtBuyerName;

    @FXML
    private JFXTextField txtBuyerContact;

    @FXML
    private JFXTextField txtBuyerEmail;

    @FXML
    private JFXTextField txtBuyerAddress;

    @FXML
    private JFXTextField txtBuyerNic;

    @FXML
    private Label lblSaved;

    @FXML
    private JFXComboBox<String> cmbLoyalId;

    public void initialize() {
        loadLoyalId();
    }

    private void loadLoyalId() {
        LoyalIdModel lmodel=new LoyalIdModel();
        ObservableList<String> obList= FXCollections.observableArrayList();
        try {
            List<LoyalIdDto> list = lmodel.getAllLoyalId();
            list.forEach(e->{
                obList.add(e.getId());
            });
            cmbLoyalId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNewBuyerOnAction(ActionEvent event) {
        String name = txtBuyerName.getText();
        String contact = txtBuyerContact.getText();
        String email = txtBuyerEmail.getText();
        String address = txtBuyerAddress.getText();
        String nic = txtBuyerNic.getText();

        if (ValidateUtil.validatePhone(contact, txtBuyerContact) &&
                ValidateUtil.validateMail(email, txtBuyerEmail) &&
                ValidateUtil.validateNic(nic, txtBuyerNic) &&
                ValidateUtil.validateAddress(address, txtBuyerAddress)) {
            try {
                BuyerModel model = new BuyerModel();
                String uID = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
                boolean isSaved = model.saveBuyer(new BuyerDto(model.generateNextBuyerId(), name, nic, email, address, contact, uID, "normal"));
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
    void txtBuyerAddressOnAction(ActionEvent event) {
        cmbLoyalId.requestFocus();
    }

    @FXML
    void txtBuyerContactOnAction(ActionEvent event) {
        txtBuyerEmail.requestFocus();
    }

    @FXML
    void txtBuyerEmailOnAction(ActionEvent event) {
        txtBuyerNic.requestFocus();
    }

    @FXML
    void txtBuyerNameOnAction(ActionEvent event) {
        txtBuyerContact.requestFocus();
    }

    @FXML
    void txtBuyerNicOnAction(ActionEvent event) {
        txtBuyerAddress.requestFocus();
    }

    @FXML
    void cmbLoyalIdOnAction(ActionEvent event) {
        btnNewBuyerOnAction(event);
    }
}
