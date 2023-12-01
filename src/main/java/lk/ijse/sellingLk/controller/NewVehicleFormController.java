package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.UserModel;
import lk.ijse.sellingLk.model.VehicleModel;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.sql.SQLException;

public class NewVehicleFormController {
    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModel;

    @FXML
    private JFXTextField txtMileage;

    @FXML
    private JFXTextField txtEngineCapacity;

    @FXML
    private JFXTextField txtYear;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXComboBox<String> cmbFuelType;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private Label lblSaved;

    @FXML
    void btnNewVehicleOnAction(ActionEvent event) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String mileage = txtMileage.getText();
        String engineCapacity = txtEngineCapacity.getText();
        String year = txtYear.getText();
        String number = txtNumber.getText();
        String type = cmbType.getValue();
        String fuelType = cmbFuelType.getValue();
        String price = txtPrice.getText();
        if (ValidateUtil.validateVehicleNumber(number, txtNumber) &&
                ValidateUtil.validateYear(Integer.parseInt(year), txtYear) &&
                ValidateUtil.validateMileage(mileage, txtMileage) &&
                ValidateUtil.validateEngineCapacity(engineCapacity, txtEngineCapacity) &&
                ValidateUtil.validatePrice(price, txtPrice)) {
            try {
                VehicleModel vehicleModel = new VehicleModel();
                boolean isSaved = vehicleModel.saveVehicle(new VehicleDto(vehicleModel.getNextVehicleId(), type, brand, model, Integer.parseInt(year), fuelType, Integer.parseInt(engineCapacity), Integer.parseInt(mileage), number, Integer.parseInt(price), "On Hand"));
                if (isSaved) {
                    lblSaved.setText("Vehicle Saved Successfully !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                // Code to be executed at the end (500 millis)
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                } else {
                    lblSaved.setText("Try again !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void txtBrandKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtEngineCapacityKeyPressed(KeyEvent event) {
        ValidateUtil.validateEngineCapacity(txtEngineCapacity.getText(), txtEngineCapacity);
    }

    @FXML
    void txtMileageKeyPressed(KeyEvent event) {
        ValidateUtil.validateMileage(txtMileage.getText(), txtMileage);
    }

    @FXML
    void txtModelKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtNumberKeyPressed(KeyEvent event) {
        ValidateUtil.validateVehicleNumber(txtNumber.getText(), txtNumber);
    }

    @FXML
    void txtYearKeyPressed(KeyEvent event) {
        ValidateUtil.validateYear(Integer.parseInt(txtYear.getText()), txtYear);
    }
}
