package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.sellingLk.dto.UserDto;
import lk.ijse.sellingLk.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController {
    @FXML
    private Pane greenPane;

    @FXML
    private Label txtSignIn;

    @FXML
    private Pane lodingPane;

    @FXML
    private ImageView imgCarousel;

    @FXML
    private Label txtSignUp;

    @FXML
    private Label lblFooterOne;

    @FXML
    private Label lblFooterTwo;

    @FXML
    void txtSignInOnMouseClicked(MouseEvent event) throws IOException {
        txtSignUp.getStyleClass().add("active");
        txtSignIn.getStyleClass().remove("active");
        txtSignUp.setMouseTransparent(false);
        TranslateTransition tt = new TranslateTransition(Duration.millis(250), greenPane);
        tt.setFromX(125);
        tt.setToX(0);
        tt.playFromStart();
        txtSignIn.setMouseTransparent(true);
        lodingPane.getChildren().clear();
        lodingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signIn-form.fxml")));
        //set label id to sign in
        lblFooterOne.setText("You Haven't an Account?");
        lblFooterTwo.setText("Sign Up");
        lblFooterTwo.setOnMouseClicked(event1 -> {
            txtSignUp.getStyleClass().add("active");
            txtSignIn.getStyleClass().remove("active");
            txtSignUp.setMouseTransparent(false);
            TranslateTransition tt1 = new TranslateTransition(Duration.millis(250), greenPane);
            tt1.setFromX(greenPane.getLayoutY()-40);
            tt1.setToX(125);
            tt1.playFromStart();
            txtSignIn.setMouseTransparent(true);
            lodingPane.getChildren().clear();
            try {
                lodingPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/signUp-form.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //set label id to sign up
            lblFooterOne.setText("You Haven't an Account?");
            lblFooterTwo.setText("Sign Up");
            lblFooterTwo.setOnMouseClicked(event2 -> {
                try {
                    txtSignUpOnMouseClicked(event2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
    @FXML
    void txtSignUpOnMouseClicked(MouseEvent event) throws IOException {
        txtSignIn.getStyleClass().add("active");
        txtSignUp.getStyleClass().remove("active");
        txtSignIn.setMouseTransparent(false);
        TranslateTransition tt = new TranslateTransition(Duration.millis(250), greenPane);
        tt.setFromX(greenPane.getLayoutY()-40);
        tt.setToX(125);
        tt.playFromStart();
        txtSignUp.setMouseTransparent(true);
        lodingPane.getChildren().clear();
        lodingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signUp-form.fxml")));
        lblFooterOne.setText("You Have an Account?");
        lblFooterTwo.setText("Sign In");
        lblFooterTwo.setOnMouseClicked(event2 -> {
            try {
                txtSignInOnMouseClicked(event2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void initialize() throws IOException {
        lodingPane.getChildren().clear();
        lodingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signIn-form.fxml")));
        txtSignIn.setMouseTransparent(true);
        txtSignUp.getStyleClass().add("active");

        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("I am in carousel");
                try {
                    imgCarousel.setImage(new Image("/assets/image/carousel/image1.png"));
                    Thread.sleep(10000);
                    imgCarousel.setImage(new Image("/assets/image/carousel/image2.png"));
                    Thread.sleep(15000);
                    imgCarousel.setImage(new Image("/assets/image/carousel/image3.png"));
                    Thread.sleep(10000);
                    imgCarousel.setImage(new Image("/assets/image/carousel/image4.png"));
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }

}

