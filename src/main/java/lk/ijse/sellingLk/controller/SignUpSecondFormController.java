package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.sellingLk.dto.UserDto;
import lk.ijse.sellingLk.model.UserModel;
import lk.ijse.sellingLk.util.Navigation;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpSecondFormController {
    @FXML
    private Label lblWarning;

    @FXML
    private Label lblWarning1;

    @FXML
    private Pane rolePane;

    @FXML
    private Pane namePane;

    @FXML
    private Pane signUpPane;

    @FXML
    private JFXTextField txtRole;

    @FXML
    private JFXTextField txtName;

    @FXML
    private Pane passwordPane;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUnHidePw;

    @FXML
    private ImageView imgEye;

    @FXML
    private Pane cPasswordPane;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtUnHideConfirmPw;

    @FXML
    private ImageView imgConfirmEye;

    private boolean hide = true;

    private boolean cHide=true;

    private String username;

    private String email;

    public void setData(String username,String email){
        this.username=username;
        this.email=email;
    }
    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if (validateName() && validatePassword() && validateConfirm() && validateRole()){
            UserModel model = new UserModel();
            try {
                boolean isSaved = model.saveUser(new UserDto(model.generateNextUserId(),txtRole.getText(),email,txtPassword.getText(),username,txtName.getText()));
                if (isSaved){
                    Navigation.switchNavigation("dashboard-form.fxml",event);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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

    @FXML
    void nameKeyPressed(KeyEvent event) {
        validateName();
    }

    private boolean validateName() {
        if (Pattern.matches("[A-z]{4,}",txtName.getText())){
            namePane.setStyle("-fx-border-color: #00ff00");
            return true;
        }
        else{
            namePane.setStyle("-fx-border-color: #FA5252;");
            return false;
        }
    }

    @FXML
    void passwordKeyPressed(KeyEvent event) {
        if(validatePassword()){
            lblWarning.setVisible(true);
            lblWarning1.setVisible(true);
        }else {
            lblWarning.setVisible(false);
            lblWarning1.setVisible(false);
        }
    }

    private boolean validatePassword() {
        if (Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",txtPassword.getText())){
            passwordPane.setStyle("-fx-border-color: #00ff00");
            return true;
        }

        else{
            passwordPane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }

    @FXML
    void confirmPasswordKeyPressed(KeyEvent event) {
        validateConfirm();
    }

    private boolean validateConfirm() {
        String cPassword = txtConfirmPassword.getText();
        String password = txtPassword.getText();

        if (password.equals(cPassword)){
            cPasswordPane.setStyle("-fx-border-color: #00ff00");
            return true;
        }
        else{
            cPasswordPane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }

    @FXML
    void roleKeyPressed(KeyEvent event) {
        validateRole();
    }

    private boolean validateRole() {
        if (Pattern.matches("[A-z]{4,}",txtRole.getText())){
            rolePane.setStyle("-fx-border-color: #00ff00");
            return true;
        }
        else{
            rolePane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }

}
