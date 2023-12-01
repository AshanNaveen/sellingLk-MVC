package lk.ijse.sellingLk.util;

import com.jfoenix.controls.JFXTextField;
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
    //validate phone number
    public static boolean validatePhone(String phone, JFXTextField pane) {
        if (Pattern.matches("^07\\d{8}$",phone)){
            pane.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        }else {
            pane.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
}
