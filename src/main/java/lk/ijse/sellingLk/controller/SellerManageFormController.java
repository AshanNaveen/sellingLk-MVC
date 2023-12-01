package lk.ijse.sellingLk.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.controller.barController.BuyerbarController;
import lk.ijse.sellingLk.controller.barController.SellerBarController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SellerManageFormController {
    @FXML
    private AnchorPane SellerPane;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private VBox vBox;

    private SellerModel model = new SellerModel();

    @FXML
    void btnBuyerManageOnAction(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(this.getClass().getResource("/view/buyerManage-form.fxml"));
            SellerPane.getChildren().clear();
            SellerPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadData();
    }

    private void loadData() {
        vBox.getChildren().clear();
        try {
            List<SellerDto> list = model.getAllSeller();
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
        String nic = txtNic.getText();
        String phone = txtPhone.getText();

        try {
            String id = model.generateNextSellerId();
            String uId = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
            System.out.println(uId);
            if (model.saveSeller(new SellerDto(
                    id,
                    name,
                    nic,
                    email,
                    address,
                    phone,
                    uId
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

    public void setData(SellerDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(BuyerbarController.class.getResource("/bar/sellerBar.fxml"));
            Parent root = loader.load();
            SellerBarController controller = loader.getController();
            controller.setData(dto);
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
