package lk.ijse.sellingLk.controller.barController;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.dto.WebVehicleDto;

public class SearchResultBarController {

    @FXML
    private Text txtDescription;

    @FXML
    private Text txtYear;

    @FXML
    private Text txtPrice;

    @FXML
    private Text txtMileage;

    public void setData(VehicleDto vehicleDto){
        txtDescription.setText(vehicleDto.getBrand()+ " "+vehicleDto.getModel());
        txtYear.setText(String.valueOf(vehicleDto.getYear()));
        txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
        txtMileage.setText(String.valueOf(vehicleDto.getMileage()));
    }
    public void setData(WebVehicleDto vehicleDto){
        txtDescription.setText(vehicleDto.getBrand()+ " "+vehicleDto.getModel());
        txtYear.setText(vehicleDto.getYear());
        txtPrice.setText(vehicleDto.getPrice());
        txtMileage.setText(vehicleDto.getMileage());
    }
}
