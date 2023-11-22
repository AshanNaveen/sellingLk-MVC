package lk.ijse.sellingLk.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.sellingLk.controller.barController.BuyerbarController;
import lk.ijse.sellingLk.controller.barController.CartBarController;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.PlaceOrderDto;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.model.*;
import lk.ijse.sellingLk.util.DateTimeUtil;
import lk.ijse.sellingLk.util.EmailUtil;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class SellOrderFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXTextField txtCount;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private VBox vBox;

    @FXML
    private Text lblTime;

    @FXML
    private JFXComboBox<String> cmbBuyerId;

    @FXML
    private Text txtTotalPrice;

    @FXML
    private Text lblDate;

    @FXML
    private JFXTextField txtBuyerName;

    @FXML
    private JFXTextField txtDescription;

    private ArrayList<String[]> cart = new ArrayList<>();
    private int netTotal = 0;

    VehicleDto dto;

    public void initialize() {
        loadCusIds();
        loadVehicleIds();
        vBox.getChildren().clear();
        //DateAndTime.setText(DateTimeUtil.timeNow());
        time();
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
            List<VehicleDto> allVehicle = vehicleModel.getAllVehile();
            ObservableList<String> vehicleIds = FXCollections.observableArrayList();
            for (VehicleDto vehicleDto : allVehicle) {
                vehicleIds.add(vehicleDto.getId());
            }
            cmbItemId.setItems(vehicleIds);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadCusIds() {
        BuyerModel buyerModel = new BuyerModel();
        try {
            List<BuyerDto> allBuyer = buyerModel.getAllBuyer();
            ObservableList<String> buyerIds = FXCollections.observableArrayList();
            for (BuyerDto buyerDto : allBuyer) {
                buyerIds.add(buyerDto.getId());
            }
            cmbBuyerId.setItems(buyerIds);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnBuyerIdOnAction(ActionEvent event) {
        try {
            txtBuyerName.setText(new BuyerModel().getBuyerName(cmbBuyerId.getValue()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void txtCountOnAction(ActionEvent event) {
        validateCount();
    }

    private boolean validateCount() {
        if (!Pattern.matches("(?<=^|\\s)[0-9]+(?=$|\\s)", txtCount.getText())) {
            txtCount.setStyle("-fx-border-color: #FA5252");
            return false;
        } else {
            txtCount.setStyle("-fx-border-color: transparent");
            return true;
        }
    }


    @FXML
    void btnItemIdOnAction(ActionEvent event) {
        try {
            dto = new VehicleModel().getVehicleInfo(cmbItemId.getValue());
            txtDescription.setText(dto.getBrand() + " " + dto.getModel() + " " + dto.getYear());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (validateCount()) {
            String vehicleId = cmbItemId.getValue();
            int count = Integer.parseInt(txtCount.getText());
            String[] data = {vehicleId, txtCount.getText()};
            cart.add(data);

            int total = (Integer.parseInt(dto.getPrice())) * count;
            netTotal += total;
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
        }

    }

    private void addRow() {
        try {
            FXMLLoader loader = new FXMLLoader(CartBarController.class.getResource("/bar/cartBar.fxml"));
            Parent root = loader.load();
            CartBarController controller = loader.getController();
            controller.setData(dto, Integer.parseInt(txtCount.getText()));
            System.out.println("add");
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnBuyOrderOnAction(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(this.getClass().getResource("/view/buyOrder-form.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewBuyerOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String id = null;
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        try {
            id = new SellOrderModel().generateNextOrderId();
            var placeOrderModel = new PlaceOrderModel();
            var pdto = new PlaceOrderDto(
                    id,
                    cmbBuyerId.getValue(),
                    cart,
                    netTotal,
                    date,
                    time
            );
            boolean isSaved = placeOrderModel.saveSellOrder(pdto);
            if (isSaved) {
                generateReport(pdto);
                String email=new BuyerModel().getEmail(pdto.getCusId());
                sendMail("Thank you for choosing our service !","Your Order Is Successfully .. ",email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        String[] list = placeOrderDto.getItems().get(0);
        String custName=new BuyerModel().getBuyerName(placeOrderDto.getCusId());
        String userName=new UserModel().getUserName(SignInFormController.uname,SignInFormController.pword);
        HashMap map = new HashMap();
        map.put("code", placeOrderDto.getOrderId());
        map.put("description", description);
        map.put("rate", dto.getPrice());
        map.put("qty","1");
        String tot= String.valueOf(netTotal);
        map.put("amount", tot);
        map.put("custName", custName);
        map.put("invoiceNum", placeOrderDto.getOrderId());
        map.put("username", userName);
        map.put("total", netTotal);
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
}