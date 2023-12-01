package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.sellingLk.controller.barController.CartBarController;
import lk.ijse.sellingLk.dto.*;
import lk.ijse.sellingLk.model.*;
import lk.ijse.sellingLk.util.DateTimeUtil;
import lk.ijse.sellingLk.util.EmailUtil;
import lk.ijse.sellingLk.util.Navigation;
import lk.ijse.sellingLk.util.ValidateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class BuyOrderFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label lblWarning;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private VBox vBox;

    @FXML
    private Text lblTime;


    @FXML
    private Text txtTotalPrice;

    @FXML
    private Text lblDate;

    @FXML
    private JFXTextField txtSellerName ,txtSellerContact;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private ImageView imgCart;

    @FXML
    private ImageView imgPlaceOrder;

    private ArrayList<String> cart = new ArrayList<>();
    private int netTotal = 0;
    private VehicleDto dto;
    private boolean vehicleSectionIsEnable = false;

    public void initialize() {
        loadVehicleIds();
        vBox.getChildren().clear();
        time();
    }

    private void vehicleSection() {
        if(vehicleSectionIsEnable) {
            cmbItemId.setDisable(true);
            txtDescription.setDisable(true);
            btnAddToCart.setDisable(true);
        }else {
            cmbItemId.setDisable(false);
            txtDescription.setDisable(false);
            btnAddToCart.setDisable(false);
        }
    }

    private void time() {
//        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lblTime.setText(DateTimeUtil.timeNow());
            lblDate.setText(DateTimeUtil.dateNow());
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    private void loadVehicleIds() {
        VehicleModel vehicleModel = new VehicleModel();
        try {
            List<String> allVehicle = vehicleModel.getNotGetVehicle();
            ObservableList<String> vehicleIds = FXCollections.observableArrayList();
            for (String id : allVehicle) {
                vehicleIds.add(id);
            }
            cmbItemId.setItems(vehicleIds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNewSellerOnAction(ActionEvent event) {
        Navigation.popupNavigation("newSeller-form.fxml","Add New Seller");
    }

    @FXML
    void btnNewVehicleOnAction(ActionEvent event) {
        Navigation.popupNavigation("newVehicle-form.fxml","Add New Vehicle");
    }

    @FXML
    void btnItemIdOnAction(ActionEvent event) {
        try {
            dto = new VehicleModel().getVehicleInfo(cmbItemId.getValue());
            txtDescription.setText(dto.getBrand() + " " + dto.getModel() + " " + dto.getYear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
            String vehicleId = cmbItemId.getValue();
            cart.add(vehicleId);

            calculateTotal();

            //this is for set total price in Rs. 4 124 475 540.00 format
            String strPrice = String.valueOf(netTotal);
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

            txtTotalPrice.setText("Rs. " + txt + ".00");
            addRow();
            cmbItemId.requestFocus();
            txtDescription.clear();
    }

    private void calculateTotal() {
        cart.forEach(id -> {
            try {
                VehicleDto vehicleInfo = new VehicleModel().getVehicleInfo(id);
                netTotal += vehicleInfo.getPrice();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void addRow() {
        try {
            FXMLLoader loader = new FXMLLoader(CartBarController.class.getResource("/bar/cartBar.fxml"));
            Parent root = loader.load();
            CartBarController controller = loader.getController();
            controller.setData(dto);
            System.out.println("added row to cart");
            vBox.getChildren().add(root);

        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnSellOrderOnAction(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(this.getClass().getResource("/view/sellOrder-form.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String id = null;
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        if (ValidateUtil.validatePhone(txtSellerContact.getText(),txtSellerContact)) {
            try {
                SellerDto sellerInfo = new SellerModel().getSellerInfo(txtSellerContact.getText());
                id = new BuyOrderModel().generateNextOrderId();
                var placeOrderModel = new PlaceOrderModel();
                var pdto = new PlaceOrderDto(
                        id,
                        sellerInfo.getId(),
                        cart,
                        netTotal,
                        date,
                        time
                );


                boolean isSaved = placeOrderModel.saveBuyOrder(pdto);
                if (isSaved) {
                    generateReport(pdto);
                    String email = new BuyerModel().getEmail(pdto.getCusId());
                    //sendMail("Thank you for choosing our service !", " ", email);
                    cart.clear();
                    vBox.getChildren().clear();
                    loadVehicleIds();
                    txtSellerContact.requestFocus();
                    txtSellerContact.clear();
                    txtDescription.clear();
                    txtTotalPrice.setText("");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private boolean sendMail(String title, String message, String gmail) {
        try {
            new EmailUtil().sendMail(title, message, gmail);
            return true;
        } catch (IOException | MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void generateReport(PlaceOrderDto placeOrderDto) throws SQLException {
        String description = dto.getBrand() + " " + dto.getModel() + " " + dto.getYear();
        String custName=new BuyerModel().getBuyerName(placeOrderDto.getCusId());
        String userName=new UserModel().getUserName(SignInFormController.uname,SignInFormController.pword);
        HashMap map = new HashMap();

        try {
            InputStream stream = getClass().getResourceAsStream("/reports/Invoice.jrxml");
            JasperDesign load = JRXmlLoader.load(stream);
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, map, new JREmptyDataSource());
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void txtSellerContactKeyPressed(KeyEvent event) {
        ValidateUtil.validatePhone(txtSellerContact.getText(),txtSellerContact);
        if (lblWarning.isVisible()  && txtSellerContact.getText().length()<9){
            lblWarning.setVisible(false);
        }
    }

    @FXML
    void txtSellerContactOnAction(ActionEvent event) {
        try {
            SellerDto info = new SellerModel().getSellerInfo(txtSellerContact.getText());
            if (info != null) {
                txtSellerName.setText(info.getName());
                cmbItemId.requestFocus();
                lblWarning.setVisible(false);
            } else {
                lblWarning.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
