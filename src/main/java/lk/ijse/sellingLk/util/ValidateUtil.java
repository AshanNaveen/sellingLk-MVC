package lk.ijse.sellingLk.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static boolean validateMail(String mail, Pane pane) {
        if (Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", mail)) {
            pane.setStyle("-fx-border-color: #00ff00");
            return true;
        } else {
            pane.setStyle("-fx-border-color: #FA5252");
            return false;
        }
    }

    public static boolean validateMail(String mail, JFXTextField field) {
        if (Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", mail)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    //validate phone number
    public static boolean validatePhone(String phone, JFXTextField field) {
        if (Pattern.matches("^07\\d{8}$", phone)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    public static boolean validatePhone(String phone) {
        if (Pattern.matches("^07\\d{8}$", phone)) return true;
        else return false;
    }

    public static boolean validateNic(String text, JFXTextField field) {
        if (Pattern.matches("^\\d{12}$|^\\d{10}[Vv]$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    public static boolean validateAddress(String text, JFXTextField field) {
        if (Pattern.matches("^\\b(?![!@#$%&*?])[\\w,/]+\\b$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    public static boolean validateVehicleNumber(String text, JFXTextField field) {
        if (Pattern.matches("^[A-Z]{2,3}\\d{4}$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    public static boolean validateYear(int year, JFXTextField field) {
        if (LocalDate.now().getYear() >= year && String.valueOf(year).length()==4 ) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }

    public static boolean validateYear(int year) {
        if (LocalDate.now().getYear() >= year)
            return true;
        else
            return false;
    }

    public static boolean validatePrice(String price, JFXTextField field) {
        if (Pattern.matches("^\\d{4,}$", price)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
    public static boolean validatePrice(String price) {
        if (Pattern.matches("^\\d{4,}$", price))
            return true;
        else
            return false;
    }

    public static boolean validateEngineCapacity(String text, JFXTextField field) {
        if (Pattern.matches("^\\d{3,}$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
    public static boolean validateEngineCapacity(String text) {
        if (Pattern.matches("^\\d{3,}$", text))
            return true;
        else
            return false;
    }

    public static boolean validateMileage(String text, JFXTextField field) {
        if (Pattern.matches("^\\d{3,}$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
    public static boolean validateMileage(String text) {
        if (Pattern.matches("^\\d{3,}$", text))
            return true;
        else
            return false;
    }

    public static boolean validateName(String text, JFXTextField field) {
        if (Pattern.matches("^[A-Za-z]+([ '-][A-Za-z]+)*$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
    public static boolean validateVehicleNum(String text, JFXTextField field) {
        if (Pattern.matches("^[A-Z]{2}\\d+$", text)) {
            field.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            field.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }


    public static boolean validateBrandAndModel(String text, JFXTextField textField) {
        if (Pattern.matches("^[a-zA-Z0-9\\s\\-_&@#!$%^*()+={}\\[\\]:;<>,.?~\\\\/]*$", text)) {
            textField.setStyle("-fx-text-fill:  #B1F041 ");
            return true;
        } else {
            textField.setStyle("-fx-text-fill: #FA5252");
            return false;
        }
    }
}
