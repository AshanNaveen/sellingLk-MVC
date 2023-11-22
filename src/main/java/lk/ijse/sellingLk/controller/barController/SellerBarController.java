package lk.ijse.sellingLk.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.UserModel;

import java.sql.SQLException;

public class SellerBarController {
    @FXML
    private Text txtId;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtPhone;

    @FXML
    private Text txtName;

    @FXML
    private JFXTextField txtEName;

    @FXML
    private JFXTextField txtEEmail;

    @FXML
    private JFXTextField txtEAddress;

    @FXML
    private JFXTextField txtEPhone;

    @FXML
    private JFXButton btnUpdateSave;

    private SellerModel model = new SellerModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ", ButtonType.NO,ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.YES){
                if (model.deleteSeller(txtId.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"ok").show();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        txtEName.setText(txtName.getText());
        txtEEmail.setText(txtEmail.getText());
        txtEAddress.setText(txtAddress.getText());
        txtEPhone.setText(txtPhone.getText());

        txtName.setVisible(false);
        txtEmail.setVisible(false);
        txtAddress.setVisible(false);
        txtPhone.setVisible(false);

        txtEName.setVisible(true);
        txtEEmail.setVisible(true);
        txtEAddress.setVisible(true);
        txtEPhone.setVisible(true);
        btnUpdateSave.setVisible(true);
    }

    @FXML
    void btnUpdateSaveOnAction(ActionEvent event) {

        txtName.setText(txtEName.getText());
        txtEmail.setText(txtEEmail.getText());
        txtAddress.setText(txtEAddress.getText());
        txtPhone.setText(txtEPhone.getText());

        txtName.setVisible(true);
        txtEmail.setVisible(true);
        txtAddress.setVisible(true);
        txtPhone.setVisible(true);

        txtEName.setVisible(false);
        txtEEmail.setVisible(false);
        txtEAddress.setVisible(false);
        txtEPhone.setVisible(false);


        try {
            if(model.updateSeller(new SellerDto(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPhone.getText(),
                    new UserModel().getUserId(SignInFormController.uname,SignInFormController.pword)
            ))) new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            btnUpdateSave.setVisible(false);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void setData(SellerDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());
        txtPhone.setText(dto.getPhone());
    }
}
