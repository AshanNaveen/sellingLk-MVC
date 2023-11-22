package lk.ijse.sellingLk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"))));
        stage.getIcons().add(new Image("/assets/image/Frame 2 (12).png"));
        stage.setTitle("Selling LK");

        stage.show();
    }
}
