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
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.controller.barController.BuyerbarController;
import lk.ijse.sellingLk.controller.barController.EmployeeBarController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.EmployeeDto;
import lk.ijse.sellingLk.model.EmployeeModel;
import lk.ijse.sellingLk.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeManageFormController {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbRoleId;;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private VBox vBox;

    private EmployeeModel model=new EmployeeModel();

    public void initialize() {
        loadRoles();
        loadAllEmployees();
    }

    private void loadRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Manager");
        roles.add("Security Officer");
        roles.add("Cashier");
        roles.add("Worker");
        cmbRoleId.setItems(roles);
    }

    private void loadAllEmployees() {

        vBox.getChildren().clear();
        try {
            List<EmployeeDto> list = model.getAllEmployee();
            for (int i = 0; i < list.size(); i++) {
                setData(list.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setData(EmployeeDto employeeDto) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeBarController.class.getResource("/bar/employeeBar.fxml"));
            Parent root = loader.load();
            EmployeeBarController controller = loader.getController();
            controller.setData(employeeDto);
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String role = cmbRoleId.getValue();

        try {
            String id = model.getNextEmpId();
            String uId = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
            System.out.println(uId);
            if (model.saveEmployee(new EmployeeDto(
                    id,
                    name,
                    email,
                    address,
                    phone,
                    role,
                    uId
            ))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                loadAllEmployees();
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
