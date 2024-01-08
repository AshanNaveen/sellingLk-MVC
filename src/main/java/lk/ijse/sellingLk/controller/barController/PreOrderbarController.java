package lk.ijse.sellingLk.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.PreOrderDto;
import lk.ijse.sellingLk.model.BuyerModel;
import lk.ijse.sellingLk.model.PreOrderModel;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.sql.SQLException;
import java.time.LocalDate;

public class PreOrderbarController {
    @FXML
    private Text txtId;

    @FXML
    private Text txtYear;

    @FXML
    private Text txtBuyerName;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Text txtBrand;

    @FXML
    private JFXTextField txtEBrand;

    @FXML
    private JFXTextField txtEModel;

    @FXML
    private JFXTextField txtEYear;

    @FXML
    private JFXTextField txtEContact;

    @FXML
    private ImageView imgIsCompleted;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtModel;

    @FXML
    private DatePicker EdatePicker;

    @FXML
    private JFXButton btnUpdate;

    private int status = 0;

    private PreOrderModel preOrderModel = new PreOrderModel();
    private BuyerModel buyerModel = new BuyerModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are Your Sure ? ", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (preOrderModel.deletePreOrder(txtId.getText())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        String phone = null;
        try {
            phone = buyerModel.getBuyerInfo(txtBuyerName.getText()).getPhone();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtEBrand.setText(txtBrand.getText());
        txtEModel.setText(txtModel.getText());
        txtEYear.setText(txtYear.getText());
        txtEContact.setText(phone);
        EdatePicker.setValue(LocalDate.parse(txtDate.getText()));

        txtBrand.setVisible(false);
        txtModel.setVisible(false);
        txtDate.setVisible(false);
        txtBuyerName.setVisible(false);
        txtYear.setVisible(false);
        imgIsCompleted.setMouseTransparent(false);

        txtEBrand.setVisible(true);
        txtEModel.setVisible(true);
        txtEYear.setVisible(true);
        txtEContact.setVisible(true);
        EdatePicker.setVisible(true);
        btnEdit.setVisible(false);
        btnUpdate.setVisible(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = null;
        try {
            name = buyerModel.getBuyerInfo(txtEContact.getText()).getName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtBrand.setText(txtEBrand.getText());
        txtModel.setText(txtEModel.getText());
        txtYear.setText(txtEYear.getText());
        txtBuyerName.setText(name);
        txtDate.setText(String.valueOf(EdatePicker.getValue()));

        txtBrand.setVisible(true);
        txtModel.setVisible(true);
        txtYear.setVisible(true);
        txtBuyerName.setVisible(true);
        txtDate.setVisible(true);
        imgIsCompleted.setMouseTransparent(true);

        txtEBrand.setVisible(false);
        txtEModel.setVisible(false);
        txtEYear.setVisible(false);
        txtEContact.setVisible(false);
        EdatePicker.setVisible(false);
        btnEdit.setVisible(true);
        btnUpdate.setVisible(false);
    }

    @FXML
    void imgIsCompletedOnMouseClicked(MouseEvent event) {
        if (status == 0) {
            imgIsCompleted.setImage(new Image("/assets/icons/icons8-correct-96.png"));
            status = 1;
        } else {
            imgIsCompleted.setImage(new Image("/assets/icons/icons8-close-96.png"));
            status = 0;
        }
    }

    @FXML
    void txtKeyReleased(KeyEvent event) {
        if (event.getSource() instanceof JFXTextField) {
            JFXTextField textField = (JFXTextField) event.getSource();

            switch (textField.getId()) {
                case "txtEBrand":
                    ValidateUtil.validateBrandAndModel(textField.getText(), textField);
                    break;
                case "txtEContact":
                    ValidateUtil.validatePhone(txtEContact.getText(), txtEContact);
                    break;
                case "txtEModel":
                    ValidateUtil.validateBrandAndModel(txtEModel.getText(), txtEBrand);
                    break;
                case "txtEYear":
                    ValidateUtil.validateYear(Integer.parseInt(txtEYear.getText()), txtEYear);
                    break;
            }
        }
    }

    @FXML
    void txtOnAction(ActionEvent event) {
        if (event.getSource() instanceof JFXTextField) {
            JFXTextField textField = (JFXTextField) event.getSource();

            switch (textField.getId()) {
                case "txtEBrand":
                    txtEModel.requestFocus();
                    break;
                case "txtEContact":
                    EdatePicker.requestFocus();
                    break;
                case "txtEModel":
                    txtEYear.requestFocus();
                    break;
                case "txtEYear":
                    txtEContact.requestFocus();
                    break;
            }
        }
    }

    public void setData(PreOrderDto dto) {
        String name = null;
        try {
            name = buyerModel.getBuyerInfo(dto.getBuyerId()).getName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtId.setText(dto.getId());
        txtBrand.setText(dto.getBrand());
        txtModel.setText(dto.getModel());
        txtYear.setText(String.valueOf(dto.getYear()));
        txtDate.setText(dto.getDate());
        txtBuyerName.setText(name);

        if (dto.getStatus() == 0) {
            imgIsCompleted.setImage(new Image("/assets/icons/icons8-close-96.png"));
            status = 0;
        } else {
            imgIsCompleted.setImage(new Image("/assets/icons/icons8-correct-96.png"));
            status = 1;
        }
    }
}
