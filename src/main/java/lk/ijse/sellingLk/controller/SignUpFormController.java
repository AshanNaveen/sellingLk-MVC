package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.sellingLk.util.EmailUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public class SignUpFormController {

    @FXML
    private Pane signUpPane;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private ImageView imgEye;

    @FXML
    private JFXTextField txtUnHidePw;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private ImageView imgConfirmEye;

    @FXML
    private JFXTextField txtUnHideConfirmPw;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtOtp;

    private boolean hide = true;

    private boolean cHide=true;

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {

        try {
            EmailUtil email = new EmailUtil();
            int random= (int) Math.floor(Math.random()*100000);

        } catch (GeneralSecurityException | IOException e ) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        boolean isSuccess=validateUser();
    }

    private boolean validateUser() {
        String name = txtUsername.getText();
        String password= txtPassword.getText();
        Pattern.matches("[A-Za-z]", "abcd");
    return true;
    }

    @FXML
    void imgCPasswordEyeOnClicked(MouseEvent event) {

        if (cHide) {
            imgConfirmEye.setImage(new Image("/assets/icons/openEye.png"));
            txtUnHideConfirmPw.setText(txtConfirmPassword.getText());
            txtConfirmPassword.setVisible(false);
            txtUnHideConfirmPw.setVisible(true);
            cHide = false;
            return;
        }
        else{
            imgConfirmEye.setImage(new Image("/assets/icons/closedEye.png"));
            txtConfirmPassword.setText(txtUnHideConfirmPw.getText());
            txtConfirmPassword.setVisible(true);
            txtUnHideConfirmPw.setVisible(false);
            cHide = true;
            return;
        }
    }

    @FXML
    void imgPasswordEyeOnClicked(MouseEvent event) {
        if (hide) {
            imgEye.setImage(new Image("/assets/icons/openEye.png"));
            txtUnHidePw.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtUnHidePw.setVisible(true);
            hide = false;
            return;
        }
        else{
            imgEye.setImage(new Image("/assets/icons/closedEye.png"));
            txtPassword.setText(txtUnHidePw.getText());
            txtPassword.setVisible(true);
            txtUnHidePw.setVisible(false);
            hide = true;
            return;
        }
    }

}
