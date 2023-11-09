package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SignInFormController {
    @FXML
    private JFXTextField txtUsername;

    private Pane greenPane;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private Label txtSignUpFooter;

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    @FXML
    void imgPasswordEye(MouseEvent event) {

    }

    @FXML
    void txtSignInOnMouseClicked(MouseEvent event) {

        TranslateTransition tt = new TranslateTransition(Duration.millis(250), greenPane);
        tt.setFromY(greenPane.getLayoutY()-30);
        tt.setToY(-100);
        tt.playFromStart();
    }

    public void initData(Pane greenPane) {
        this.greenPane=greenPane;
    }
}
