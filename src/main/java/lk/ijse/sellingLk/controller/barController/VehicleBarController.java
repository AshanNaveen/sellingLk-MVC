package lk.ijse.sellingLk.controller.barController;

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
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtFuelType;

    @FXML
    private Text txtPrice;
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        VehicleModel model = new VehicleModel();
        try {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ",ButtonType.YES,ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.YES){
                if (model.deleteVehicle(txtId.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(VehicleDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getBrand()+" "+dto.getModel()+" "+dto.getYear());
        txtFuelType.setText(dto.getFuelType());

        txtPrice.setText("Rs. "+dto.getPrice());
    }
}
