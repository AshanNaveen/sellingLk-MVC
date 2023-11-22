package lk.ijse.sellingLk.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.EmployeeDto;
import lk.ijse.sellingLk.model.EmployeeModel;
import lk.ijse.sellingLk.model.UserModel;

import java.sql.SQLException;

public class EmployeeBarController {
    @FXML
    private Text txtEmpCode;

    @FXML
    private Text txtEmail;

    @FXML
    private JFXTextField txtEAddress;

    @FXML
    private Text txtPhone;
    @FXML
    private Text txtAddress;

    @FXML
    private Text txtName;

    @FXML
    private Text txtRole;

    @FXML
    private JFXTextField txtEname;

    @FXML
    private JFXTextField txtEEmail;

    @FXML
    private JFXTextField txtEPhone;

    @FXML
    private JFXComboBox<String> cmbERole;

    @FXML
    private JFXButton btnUpdate;

    private EmployeeModel model  = new EmployeeModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ", ButtonType.OK,ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.YES){
                if (model.deleteEmployee(txtEmpCode.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Manager");
        roles.add("Security Officer");
        roles.add("Cashier");
        roles.add("Worker");
        cmbERole.setItems(roles);
    }
    @FXML
    void btnEditOnAction(ActionEvent event) {
        txtEname.setText(txtName.getText());
        txtEEmail.setText(txtEmail.getText());
        txtEAddress.setText(txtAddress.getText());
        txtEPhone.setText(txtPhone.getText());
        loadRoles();


        txtName.setVisible(false);
        txtEmail.setVisible(false);
        txtAddress.setVisible(false);
        txtPhone.setVisible(false);
        txtRole.setVisible(false);

        txtEname.setVisible(true);
        txtEEmail.setVisible(true);
        txtEAddress.setVisible(true);
        txtEPhone.setVisible(true);
        cmbERole.setVisible(true);
        btnUpdate.setVisible(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        txtName.setText(txtEname.getText());
        txtEmail.setText(txtEEmail.getText());
        txtAddress.setText(txtEAddress.getText());
        txtPhone.setText(txtEPhone.getText());
        txtRole.setText(cmbERole.getValue());

        txtName.setVisible(true);
        txtEmail.setVisible(true);
        txtAddress.setVisible(true);
        txtPhone.setVisible(true);
        txtRole.setVisible(true);

        txtEname.setVisible(false);
        txtEEmail.setVisible(false);
        txtEAddress.setVisible(false);
        txtEPhone.setVisible(false);
        cmbERole.setVisible(false);
        btnUpdate.setVisible(false);


        try {
            if(model.updateEmployee(new EmployeeDto(
                    txtEmpCode.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPhone.getText(),
                    txtRole.getText(),
                    new UserModel().getUserId(SignInFormController.uname,SignInFormController.pword)
            ))) new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            btnUpdate.setVisible(false);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setData(EmployeeDto dto) {
        txtEmpCode.setText(dto.getId());
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtAddress.setText(dto.getAddress());
        txtPhone.setText(dto.getPhone());
        txtRole.setText(dto.getRole());
    }

}
