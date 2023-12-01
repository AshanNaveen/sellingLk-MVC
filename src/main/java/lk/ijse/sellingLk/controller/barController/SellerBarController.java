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
    private JFXTextField txtEName;

    @FXML
    private JFXTextField txtEEmail;

    @FXML
    private JFXTextField txtEAddress;

    @FXML
    private JFXTextField txtENic;

    @FXML
    private JFXTextField txtEContact;

    @FXML
    private JFXButton btnUpdateSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtContact;

    @FXML
    private Text txtNic;

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
        txtENic.setText(txtNic.getText());
        txtEContact.setText(txtContact.getText());

        txtName.setVisible(false);
        txtEmail.setVisible(false);
        txtAddress.setVisible(false);
        txtNic.setVisible(false);
        txtContact.setVisible(false);

        txtEName.setVisible(true);
        txtEEmail.setVisible(true);
        txtEAddress.setVisible(true);
        txtENic.setVisible(true);
        txtEContact.setVisible(true);
        btnUpdate.setVisible(false);
        btnUpdateSave.setVisible(true);

    }

    @FXML
    void btnUpdateSaveOnAction(ActionEvent event) {

        txtName.setText(txtEName.getText());
        txtEmail.setText(txtEEmail.getText());
        txtAddress.setText(txtEAddress.getText());
        txtNic.setText(txtENic.getText());
        txtContact.setText(txtEContact.getText());

        txtName.setVisible(true);
        txtEmail.setVisible(true);
        txtAddress.setVisible(true);
        txtNic.setVisible(true);
        txtContact.setVisible(true);

        txtEName.setVisible(false);
        txtEEmail.setVisible(false);
        txtEAddress.setVisible(false);
        txtENic.setVisible(false);
        txtEContact.setVisible(false);


        try {
            if(model.updateSeller(new SellerDto(
                    txtId.getText(),
                    txtName.getText(),
                    txtNic.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtContact.getText(),
                    new UserModel().getUserId(SignInFormController.uname,SignInFormController.pword)
            ))) new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            btnUpdateSave.setVisible(false);
            btnUpdate.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setData(SellerDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());
        txtNic.setText(dto.getNic());
        txtContact.setText(dto.getPhone());
    }
}
