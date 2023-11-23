package lk.ijse.sellingLk.util;

import javafx.scene.layout.Pane;

import java.util.regex.Pattern;

public  class ValidateUtil {
    public static boolean validateMail(String mail, Pane pane) {
        if (Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b",mail)){
            pane.setStyle("-fx-border-color: #00ff00");
            return true;
        }else {
            pane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }
}
