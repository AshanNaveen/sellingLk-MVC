package lk.ijse.sellingLk.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.PreOrderDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.PreOrderModel;
import lk.ijse.sellingLk.model.UserModel;

import java.sql.SQLException;

public class PreOrderbarController {
    @FXML
    private Text txtPreOrderId ,txtEYear;

    @FXML
    private Text txtYear;

    @FXML
    private Text txtBuyerId;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Text txtDescription;

    @FXML
    private ImageView imgTick;

    @FXML
    private Text txtDate;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private DatePicker txtEDate;

    @FXML
    private JFXTextField txtEBuyerId;

    @FXML
    private JFXTextField txtEDescription;

    @FXML
    private JFXComboBox<?> cmbEYear;

    private int status=0;

    private PreOrderModel model = new PreOrderModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ", ButtonType.OK,ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.YES){
                if (model.deletePreOrder(txtPreOrderId.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        txtEDescription.setText(txtDescription.getText());
        txtEYear.setText(txtYear.getText());
        txtEBuyerId.setText(txtBuyerId.getText());

        txtDescription.setVisible(false);
        txtBuyerId.setVisible(false);
        txtDate.setVisible(false);
        txtYear.setVisible(false);

        txtEDescription.setVisible(true);
        txtEBuyerId.setVisible(true);
        txtEDate.setVisible(true);
        txtEYear.setVisible(true);
        btnEdit.setVisible(false);
        btnUpdate.setVisible(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        txtDescription.setText(txtEDescription.getText());
        txtYear.setText(txtEYear.getText());
        txtBuyerId.setText(txtEBuyerId.getText());
        txtDate.setText(String.valueOf(txtEDate.getValue()));

        txtDescription.setVisible(true);
        txtBuyerId.setVisible(true);
        txtDate.setVisible(true);

        txtYear.setVisible(true);

        txtEDescription.setVisible(false);
        txtEBuyerId.setVisible(false);
        txtEDate.setVisible(false);
        txtEYear.setVisible(false);
        btnEdit.setVisible(true);
        btnUpdate.setVisible(false);

        /*try {
            if(model.updatePreOrder(new PreOrderDto(
                    txtPreOrderId.getText(),
                    txtDescription.getText(),
                    txtYear.getText(),
                    txtDate.getText(),
                    (status==0)
            ))) new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    }

    public void setData(PreOrderDto dto){
        txtPreOrderId.setText(dto.getId());
        txtDescription.setText(dto.getDescription());
        txtYear.setText(String.valueOf(dto.getYear()));
        txtDate.setText(dto.getDate());
        txtBuyerId.setText(dto.getBuyerId());

        if(dto.getStatus()==0){
            imgTick.setImage(new Image("/assets/icons/icons8-close-96.png"));
        }else imgTick.setImage(new Image("/assets/icons/icons8-correct-96.png"));
    }
}
