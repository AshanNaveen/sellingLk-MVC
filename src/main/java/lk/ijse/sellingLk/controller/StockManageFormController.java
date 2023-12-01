package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtMileage;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtEngineCapacity;

    @FXML
    private VBox vBox;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXComboBox<String> cmbFuelType;

    private VehicleModel model = new VehicleModel();
    private String[] types={"Car","Van","SUV/Jeep","Motor Cycle","Crew Cab","Pickup / Double Cab","Bus","Lorry","Three Wheel","Other"};
    private String[] fuelTypes={"Petrol","Diesel","Hybrid","Electric"};

    public void initialize() {
        loadData();
        loadTypes();
        loadFuelTypes();
    }

    private void loadFuelTypes() {
        ObservableList<String> list= FXCollections.observableArrayList();
        for (String data : fuelTypes)list.add(data);
        cmbFuelType.setItems(list);
    }

    private void loadTypes() {
        ObservableList<String> list= FXCollections.observableArrayList();
        for (String data : types)list.add(data);
        cmbType.setItems(list);
    }

    private void loadData() {
        vBox.getChildren().clear();
        try {
            List<VehicleDto> list = model.getAllVehile();
            for (int i = 0; i < list.size(); i++) setData(list.get(i));
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
            String brand = txtBrand.getText();
            String vehicleModel = txtModel.getText();
            String type=cmbType.getValue();
            int year = Integer.parseInt(txtYear.getText());
            String fuelType = cmbFuelType.getValue();
            int engineCapacity = Integer.parseInt(txtEngineCapacity.getText());
            int mileage = Integer.parseInt(txtMileage.getText());
            int price = Integer.parseInt(txtPrice.getText());
            String status = "On Hand";

            VehicleDto vehicle = new VehicleDto(id,brand,vehicleModel,type,year,fuelType,engineCapacity,mileage,price,status);

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
