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
import lk.ijse.sellingLk.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchFormController {
    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtFuelType;

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



    private VehicleModel model = new VehicleModel();
    public void initialize() {
        vBox.getChildren().clear();
        //vBoxWeb.getChildren().clear();
        loadYearData();
    }

    private void loadYearData() {
        try {
            int minYear=model.getMinumunYear();
            int maxYear=model.getMaximumYear();


            ObservableList<Integer> yearList = FXCollections.observableArrayList();
            for (int i = minYear; i <= maxYear; i++) {
                yearList.add(i);
            }
            txtYearMax.setItems(yearList);
            txtYearMin.setItems(yearList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String description = txtDescription.getText();
        String fuelType = txtFuelType.getText();
        String priceMin = txtPriceMin.getText();
        String priceMax = txtPriceMax.getText();
        int yearMin = txtYearMin.getValue();
        int yearMax = txtYearMax.getValue();
        vBox.getChildren().clear();
        try {
            List<VehicleDto> result=model.search(new SearchDto(description,fuelType,priceMin,priceMax,yearMin,yearMax));

            for (int i = 0; i < result.size(); i++) {
                setData(result.get(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
