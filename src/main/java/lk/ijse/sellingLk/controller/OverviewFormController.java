package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.PreOrderDto;
import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.PreOrderModel;
import lk.ijse.sellingLk.model.SellerModel;
import lk.ijse.sellingLk.model.VehicleModel;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class OverviewFormController {
    @FXML
    private VBox vBox;

    @FXML
    private LineChart<?, ?> grpSelling;

    @FXML
    private JFXSpinner loader;

    @FXML
    private Label lblPercentage;

    @FXML
    private Label lblVehicleCount;

    @FXML
    private Label lblBuyerCount;

    @FXML
    private Label lblSellerCount;

    public void initialize() throws InterruptedException {
        loader.setProgress(-1);
        Thread.sleep(1000);
        loadVehicle();
        loadBuyer();
        loadSeller();
        loadLoader();
        loadGraph();
    }

    private void loadGraph() {

    }

    private void loadSeller() {
        try {
            List<SellerDto> allSeller = new SellerModel().getAllSeller();
            lblSellerCount.setText(String.valueOf(allSeller.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBuyer() {
        try {
            List<BuyerDto> allBuyer = new BuyerModel().getAllBuyer();
            lblBuyerCount.setText(String.valueOf(allBuyer.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadVehicle() {
        try {
            List<VehicleDto> allVehile = new VehicleModel().getAllVehile();
            lblVehicleCount.setText(String.valueOf(allVehile.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadLoader() {
        try {
            List<PreOrderDto> dto = new PreOrderModel().getAllPreOrders();
            int total = dto.size();
            int count = 0;
            for (PreOrderDto preOrderDto : dto) {
                if (preOrderDto.getStatus() == 0) {
                    count++;
                }
            }
            int percentage = (count * 100) / total;
            lblPercentage.setText(percentage + "%");
            loader.setProgress(percentage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
