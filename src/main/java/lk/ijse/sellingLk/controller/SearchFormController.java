package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.controller.barController.SearchResultBarController;
import lk.ijse.sellingLk.dto.SearchDto;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.dto.WebVehicleDto;
import lk.ijse.sellingLk.model.VehicleModel;
import lk.ijse.sellingLk.util.WebSearch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchFormController {
    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtPriceMin;

    @FXML
    private JFXComboBox<Integer> txtYearMin;

    @FXML
    private JFXTextField txtPriceMax;

    @FXML
    private JFXComboBox<Integer> txtYearMax;

    @FXML
    private Pane dbResultPane;

    @FXML
    private VBox vBox;

    @FXML
    private JFXToggleButton tglWeb;

    @FXML
    private JFXTextField txtModel;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXComboBox<String> cmbFuelType;


    private VehicleModel model = new VehicleModel();

    private String[] types = {"Car", "Van", "SUV/Jeep", "Motor Cycle", "Crew Cab", "Pickup / Double Cab", "Bus", "Lorry", "Three Wheel", "Other"};
    private String[] fuelTypes = {"Petrol", "Diesel", "Hybrid", "Electric"};

    public void initialize() {
        vBox.getChildren().clear();
        //vBoxWeb.getChildren().clear();
        loadYearData();
        loadTypes();
        loadFuelTypes();
    }

    private void loadFuelTypes() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (String data : fuelTypes) list.add(data);
        cmbFuelType.setItems(list);
    }

    private void loadTypes() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (String data : types) list.add(data);
        cmbType.setItems(list);
    }

    private void loadYearData() {

        int minYear = 1980;
        int maxYear = 2023;


        ObservableList<Integer> yearList = FXCollections.observableArrayList();
        for (int i = minYear; i <= maxYear; i++) {
            yearList.add(i);
        }
        txtYearMax.setItems(yearList);
        txtYearMin.setItems(yearList);

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String brand = txtBrand.getText();
        String type = (cmbType.getSelectionModel().getSelectedIndex()<0 ? "":cmbType.getValue());
        String vModel = txtModel.getText();
        String fuelType = (cmbFuelType.getSelectionModel().getSelectedIndex()<0 ? "":cmbFuelType.getValue());
        int priceMin = (txtPriceMin.getText().isEmpty() ? 0 : Integer.parseInt(txtPriceMin.getText()));
        int priceMax = (txtPriceMax.getText().isEmpty() ? 0 : Integer.parseInt(txtPriceMax.getText()));
        int yearMin = (txtYearMin.getSelectionModel().getSelectedIndex()<0 ? 0 :txtYearMin.getValue());
        int yearMax = (txtYearMax.getSelectionModel().getSelectedIndex()<0 ? 0 :txtYearMax.getValue());

        var serchDto = new SearchDto(brand, vModel, type, fuelType, priceMin, priceMax, yearMin, yearMax);

        vBox.getChildren().clear();
        if (tglWeb.isDisable()) {
            try {
                List<VehicleDto> result = model.search(serchDto);

                for (int i = 0; i < result.size(); i++) {
                    setData(result.get(i));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            WebSearch searchModel = new WebSearch();
            searchModel.search(serchDto);
        }
    }

    private void setData(VehicleDto vehicleDto) {
        try {
            FXMLLoader loader = new FXMLLoader(SearchResultBarController.class.getResource("/bar/searchResultBar.fxml"));
            Parent root = loader.load();
            SearchResultBarController controller = loader.getController();
            controller.setData(vehicleDto);
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void tglWebOnAction(ActionEvent event) {

    }

}
