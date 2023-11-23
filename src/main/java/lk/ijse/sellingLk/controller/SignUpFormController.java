package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.sellingLk.controller.barController.EmployeeBarController;
import lk.ijse.sellingLk.util.EmailUtil;
import lk.ijse.sellingLk.util.ValidateUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public class SignUpFormController {
    @FXML
    private Pane signUpPane;

    @FXML
    private Label lblWarning;

    @FXML
    private Pane usernamePane;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Pane emailPane;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private Pane otpPane;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private JFXButton btnSendOtp;

    private String random;


    @FXML
    void btnNextOnAction(ActionEvent event) {
        if (ValidateUtil.validateMail(txtEmail.getText(), emailPane) && checkOtp() && !txtUsername.getText().equals(" ")) {
            try {
                String username = txtUsername.getText();
                String email=txtEmail.getText();
                FXMLLoader loader = new FXMLLoader(SignUpSecondFormController.class.getResource("/view/signUpSecond-form.fxml"));
                Parent root= loader.load();
                SignUpSecondFormController controller = loader.getController();
                controller.setData(username,email);
                signUpPane.getChildren().clear();
                signUpPane.getChildren().add(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkOtp() {
        if (random.equals(txtOtp.getText())) {
            otpPane.setStyle("-fx-border-color: #00ff00");
            return true;
        } else {
            otpPane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }


    @FXML
    void btnSendOtpOnAction(ActionEvent event) {
        if (ValidateUtil.validateMail(txtEmail.getText(), emailPane)) {
            try {
                EmailUtil email = new EmailUtil();
                random = String.valueOf((int)Math.floor(Math.random() * 100000));
                System.out.println(random);
                String usermail = txtEmail.getText();
                btnSendOtp.setText("Resend OTP");
                email.sendMail("Selling LK Verification", "Please use this code to reset the password for the account " + usermail + "." +
                        "\n\nHere is your code : " + random +
                        "\n\nThanks,\nSellingLK IT Team", usermail);
            } catch (GeneralSecurityException | IOException | MessagingException e) {
                e.printStackTrace();
            }
        } else {
            lblWarning.setVisible(true);
        }
    }


}
