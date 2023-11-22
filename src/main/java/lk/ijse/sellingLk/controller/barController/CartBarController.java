package lk.ijse.sellingLk.controller.barController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.dto.VehicleDto;

import java.sql.SQLException;

public class CartBarController {
    @FXML
    private Text txtVehicleId;

    @FXML
    private Text txtCount;

    @FXML
    private Text txtPrice;

    @FXML
    private Text txtTotal;

    @FXML
    private Text txtDescription;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    public void setData(VehicleDto dto,int count){
        txtCount.setText(String.valueOf(count));
        txtDescription.setText(dto.getBrand()+" "+dto.getModel()+" "+dto.getYear());
        txtVehicleId.setText(dto.getId());
        txtPrice.setText("Rs. "+numberToString(Integer.parseInt(dto.getPrice())));
        int total = (Integer.parseInt(dto.getPrice()))*count;
        txtTotal.setText("Rs. "+numberToString(total));

    }

    public String numberToString(int num){
        String strPrice = String.valueOf(num);
        String[] split = strPrice.split("(?<=\\G.{" + 1 + "})");
        String txt = "";
        int round = 0;
        for (int i = split.length - 1; i > -1; i--) {
            txt = split[i] + txt;
            round += 1;
            if (round == 3) {
                txt = " " + txt;
                round = 0;
            }
        }
        return txt;
    }
}
