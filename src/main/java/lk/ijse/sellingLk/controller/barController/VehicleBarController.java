package lk.ijse.sellingLk.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.model.VehicleModel;

import java.sql.SQLException;

public class VehicleBarController {
    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtDescription;

    @FXML
    private Text txtFuelType;

    @FXML
    private Text txtPrice;

    @FXML
    private Text txtMileage;

    @FXML
    private Text txtEngineCapacity;

    @FXML
    private JFXTextField txtEBrand;

    @FXML
    private JFXTextField txtEYear;

    @FXML
    private JFXTextField txtEModel;

    @FXML
    private JFXTextField txtEFuelType;

    @FXML
    private JFXTextField txtEFEngineCapacity;

    @FXML
    private JFXTextField txtEMileage;

    @FXML
    private JFXTextField txtEPrice;

    @FXML
    private JFXTextField txtEDescription;
    private VehicleDto dto;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        dto.setId(txtId.getText());

        dto.setBrand(txtEBrand.getText());
        dto.setModel(txtEModel.getText());
        dto.setYear(Integer.parseInt(txtEYear.getText()));
        dto.setFuelType(txtEFuelType.getText());
        dto.setEnginCapacity(Integer.parseInt(txtEFEngineCapacity.getText()));
        dto.setMileage(Integer.parseInt(txtEMileage.getText()));
        dto.setPrice(Integer.parseInt(txtEPrice.getText()));

        try {
            boolean isUpdated = new VehicleModel().saveVehicle(dto);
            if (isUpdated)new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Updated Successfully").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    @FXML
    void btnEditOnAction(ActionEvent event) {
        txtDescription.setVisible(false);
        txtPrice.setVisible(false);
        txtFuelType.setVisible(false);
        txtMileage.setVisible(false);
        txtEngineCapacity.setVisible(false);

        txtEBrand.setText(dto.getBrand());
        txtEModel.setText(dto.getModel());
        txtEYear.setText(String.valueOf(dto.getYear()));
        txtEFuelType.setText(dto.getFuelType());
        txtEFEngineCapacity.setText(String.valueOf(dto.getEnginCapacity()));
        txtEMileage.setText(String.valueOf(dto.getMileage()));
        txtEPrice.setText(String.valueOf(dto.getPrice()));

        txtEDescription.setVisible(true);
        txtEBrand.setVisible(true);
        txtEModel.setVisible(true);
        txtEYear.setVisible(true);
        txtEFuelType.setVisible(true);
        txtEFEngineCapacity.setVisible(true);
        txtEMileage.setVisible(true);
        txtEPrice.setVisible(true);


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        VehicleModel model = new VehicleModel();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (model.deleteVehicle(txtId.getText())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(VehicleDto dto) {
        this.dto = dto;
        txtId.setText(this.dto.getId());
        txtDescription.setText(this.dto.getBrand() + " " + this.dto.getModel() + " " + this.dto.getYear());
        txtFuelType.setText(this.dto.getFuelType());
        txtPrice.setText("Rs. " + this.dto.getPrice());
        txtMileage.setText(String.valueOf(this.dto.getMileage()));
        txtEngineCapacity.setText(String.valueOf(this.dto.getEnginCapacity()));
    }
}
