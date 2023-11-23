package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.controller.barController.BuyerbarController;
import lk.ijse.sellingLk.controller.barController.VehicleBarController;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StockManageFormController {
    @FXML
    private JFXTextField txtModel;

    @FXML
    private JFXTextField txtYear;

    @FXML
    private JFXTextField txtFuelType;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtMileage;

    @FXML
    private JFXTextField txtPrice;


    @FXML
    private JFXTextField txtEngineCapacity;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private VBox vBox;

    private VehicleModel model = new VehicleModel();

    public void initialize() {
        loadData();
    }

    private void loadData() {
        vBox.getChildren().clear();
        try {
            List<VehicleDto> list = model.getAllVehile();
            for (int i = 0; i < list.size(); i++) {
                setData(list.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setData(VehicleDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(VehicleBarController.class.getResource("/bar/vehicleBar.fxml"));
            Parent root = loader.load();
            VehicleBarController controller = loader.getController();
            controller.setData(dto);
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {


        try {
            String id = model.getNextVehicleId();

            String vehicleModel = txtModel.getText();
            int year = Integer.parseInt(txtYear.getText());
            String fuelType = txtFuelType.getText();
            String brand = txtBrand.getText();
            String mileage = txtMileage.getText();
            String price = txtPrice.getText();
            String engineCapacity = txtEngineCapacity.getText();
            String description = txtDescription.getText();
            String status = "On Hand";

            VehicleDto vehicle = new VehicleDto(id,description,brand,vehicleModel,year,fuelType,engineCapacity,mileage,price,status);

            boolean isSaved=model.saveVehicle(vehicle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                loadData();
            }
            else new Alert(Alert.AlertType.WARNING,"Try Again").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
