package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.sellingLk.dto.UserDto;
import lk.ijse.sellingLk.model.UserModel;

import java.sql.SQLException;

public class SignUpFormController {

    @FXML
    private Pane signUpPane;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private Label txtSignInFooter;

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {

    }

    @FXML
    public void btnSignUpOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();

        if (password.equals(txtConfirmPassword.getText())) {
            UserDto dto = new UserDto(null, null, null, email, password, username);
            UserModel model = new UserModel();
            try {
                boolean isSaved = model.saveUser(dto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User Saved Successfully").show();
        }

    }

    @FXML
    void imgCPasswordEye(MouseEvent event) {

    }

    @FXML
    void imgPasswordEye(MouseEvent event) {

    }

    @FXML
    void txtSignInOnMouseClicked(MouseEvent event) {
        
    }
}
