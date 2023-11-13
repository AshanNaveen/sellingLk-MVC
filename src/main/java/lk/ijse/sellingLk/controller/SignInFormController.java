package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.sellingLk.model.UserModel;

import java.sql.SQLException;

public class SignInFormController {
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtOtp;
    @FXML
    private JFXTextField txtUnHidePassword;
    @FXML
    private Label txtSignUpFooter;

    @FXML
    private ImageView imgEye;

    private boolean hide = true;

    public static String uname;
    public static String pword;

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserModel model = new UserModel();

        try {
            boolean isIn = model.searchUser(username, password);
            if (isIn){
                try {
                    this.uname=username;
                    this.pword=username;
                    LoginFormController.t.stop();
                    Pane root = FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml"));
                    txtUsername.getScene().setRoot(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void imgPasswordEye(MouseEvent event) {
        if (hide) {
            imgEye.setImage(new Image("/assets/icons/openEye.png"));
            txtUnHidePassword.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtUnHidePassword.setVisible(true);
            hide = false;
            return;
        }
        else{
            imgEye.setImage(new Image("/assets/icons/closedEye.png"));
            txtPassword.setText(txtUnHidePassword.getText());
            txtUnHidePassword.setVisible(false);
            txtPassword.setVisible(true);
            hide = true;
            return;
        }
    }

}
