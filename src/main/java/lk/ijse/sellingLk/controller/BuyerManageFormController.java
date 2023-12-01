package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.controller.barController.BuyerbarController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.LoyalIdDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.LoyalIdModel;
import lk.ijse.sellingLk.model.UserModel;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class BuyerManageFormController {
    @FXML
    private AnchorPane buyerPane;

    @FXML
    private JFXTextField txtEmail, txtNic;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbLoyalId;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private VBox vBox;

    @FXML
    private JFXTextField txtName;

    private BuyerModel model = new BuyerModel();

    public void initialize() {
        loadLoyalId();
        loadData();
    }

    private void loadLoyalId() {
        LoyalIdModel lmodel = new LoyalIdModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<LoyalIdDto> list = lmodel.getAllLoyalId();
            list.forEach(e -> {
                obList.add(e.getId());
            });
            cmbLoyalId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        vBox.getChildren().clear();
        try {
            List<BuyerDto> list = model.getAllBuyer();
            for (int i = 0; i < list.size(); i++) {
                setData(list.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String loyalId = cmbLoyalId.getValue();
        String nic = txtNic.getText();
        if (ValidateUtil.validateAddress(address, txtAddress) &&
                ValidateUtil.validateMail(email, txtEmail) &&
                ValidateUtil.validatePhone(phone, txtPhone) &&
                ValidateUtil.validateNic(nic, txtNic)) {
            try {
                String id = model.generateNextBuyerId();
                String uId = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
                System.out.println(uId);
                if (model.saveBuyer(new BuyerDto(
                        id,
                        name,
                        nic,
                        email,
                        address,
                        phone,
                        uId,
                        loyalId
                ))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                    loadData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try Again").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    @FXML
    void btnSellerManageOnAction(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(this.getClass().getResource("/view/sellerManage-form.fxml"));
            buyerPane.getChildren().clear();
            buyerPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setData(BuyerDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(BuyerbarController.class.getResource("/bar/buyerBar.fxml"));
            Parent root = loader.load();
            BuyerbarController controller = loader.getController();
            controller.setData(dto);
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void cmbLoyalIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressKeyReleased(KeyEvent event) {
        ValidateUtil.validateAddress(txtAddress.getText(), txtAddress);
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        cmbLoyalId.requestFocus();
    }

    @FXML
    void txtEmailKeyRealesed(KeyEvent event) {
        ValidateUtil.validateMail(txtEmail.getText(), txtEmail);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtNic.requestFocus();
    }

    @FXML
    void txtNicKeyRealeased(KeyEvent event) {
        ValidateUtil.validateNic(txtNic.getText(), txtNic);
    }

    @FXML
    void txtNicOnACtion(ActionEvent event) {
        txtEmail.requestFocus();

    }

    @FXML
    void txtPhoneKeyReleased(KeyEvent event) {
        ValidateUtil.validatePhone(txtPhone.getText(), txtPhone);
    }

    @FXML
    void txtPhoneOnAction(ActionEvent event) {
        cmbLoyalId.requestFocus();
    }

    public void txtNameKeyRealeased(KeyEvent keyEvent) {
        ValidateUtil.validateName(txtName.getText(), txtName);
    }
}
