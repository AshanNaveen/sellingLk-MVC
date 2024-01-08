package lk.ijse.sellingLk.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.sellingLk.util.Navigation;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private Pane loadingPane;

    @FXML
    private Pane btnSearch;

    @FXML
    private ImageView imgSearch;

    @FXML
    private Pane btnPreOrder;

    @FXML
    private ImageView imgPreOrders;

    @FXML
    private Pane btnStock;

    @FXML
    private ImageView imgStock;

    @FXML
    private Pane btnBAndS;

    @FXML
    private ImageView imgCustomers;

    @FXML
    private Pane btnPayment;

    @FXML
    private ImageView imgPayment;

    @FXML
    private Pane btnEmployee;

    @FXML
    private ImageView imgEmployees;

    @FXML
    private Label lblSearch;

    @FXML
    private Label lblPreOrders;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblSeller;

    @FXML
    private Label lblPayment;

    @FXML
    private Label lblEmployee;

    @FXML
    private Pane backPane;

    private int position=0;

    public void initialize() throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "overview-form.fxml");
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void btnBAndSOnMouseIn(MouseEvent event) {
        if (position!=3) {
            lblSeller.setVisible(true);
            imgCustomers.setImage(new Image("/assets/icons/ashan-light.png"));
        }
    }


    @FXML
    void btnBAndSOnMouseOut(MouseEvent event) {
        if (position!=3) {
            lblSeller.setVisible(false);
            imgCustomers.setImage(new Image("/assets/icons/ashan.png"));
        }
    }

    @FXML
    void btnEmployeeOnMouseIn(MouseEvent event) {
        if (position!=5) {
            lblEmployee.setVisible(true);
            imgEmployees.setImage(new Image("/assets/icons/icons8-employees-light.png"));
        }
    }
    @FXML
    void btnEmployeeOnMouseOut(MouseEvent event) {
        if (position!=5) {
            lblEmployee.setVisible(false);
            imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        }
    }

    @FXML
    void btnPaymentOnMouseIn(MouseEvent event) {
        if (position!=4) {
            lblPayment.setVisible(true);
            imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards-light.png"));
        }
    }

    @FXML
    void btnPaymentOnMouseOut(MouseEvent event) {
        if (position!=4) {
            lblPayment.setVisible(false);
            imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        }
    }

    @FXML
    void btnPreOrderOnMouseIn(MouseEvent event) {
        if (position!=1) {
            lblPreOrders.setVisible(true);
            imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order-light.png"));
        }
    }

    @FXML
    void btnPreOrderOnMouseOut(MouseEvent event) {
        if (position!=1) {
            lblPreOrders.setVisible(false);
            imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        }
    }

    @FXML
    void btnSearchOnMouseIn(MouseEvent event) {
        if (position!=0) {
            lblSearch.setVisible(true);
            imgSearch.setImage(new Image("/assets/icons/icons8-search-light.png"));
        }
    }

    @FXML
    void btnSearchOnMouseOut(MouseEvent event) {
        if (position!=0) {
            lblSearch.setVisible(false);
            imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        }
    }

    @FXML
    void btnStockOnMouseIn(MouseEvent event) {
        if(position!=2) {
            lblStock.setVisible(true);
            imgStock.setImage(new Image("/assets/icons/icons8-store-50-light.png"));
        }
    }

    @FXML
    void btnStockOnMouseOut(MouseEvent event) {
        if (position!=2) {
            lblStock.setVisible(false);
            imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        }
    }



    @FXML
    void btnBAndSOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "sellerManage-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,241,500);break;
            case 1 : transition(80,241,600);break;
            case 2 : transition(161,241,700);break;
            case 3 : transition(241,241,100);break;
            case 4 : transition(322,241,700);break;
            case 5 : transition(402,241,600);
        }
        position=3;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan-light.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
    }

    @FXML
    void btnEmployeeOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "employeeManage-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,402,300);break;
            case 1 : transition(80,402,400);break;
            case 2 : transition(161,402,500);break;
            case 3 : transition(241,402,600);break;
            case 4 : transition(322,402,700);break;
            case 5 : transition(402,402,100);
        }
        position=5;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees-light.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);
    }

    @FXML
    void btnLogoOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "overview-form.fxml");
        if(backPane.isVisible())backPane.setVisible(false);
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);
    }

    @FXML
    void btnPaymentOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "sellOrder-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,322,400);break;
            case 1 : transition(80,322,500);break;
            case 2 : transition(161,322,600);break;
            case 3 : transition(241,322,700);break;
            case 4 : transition(322,322,100);break;
            case 5 : transition(402,322,700);
        }
        position=4;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards-light.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);

    }

    @FXML
    void btnPreOrderOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "preOrdersManage-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,80,700);break;
            case 1 : transition(80,80,100);break;
            case 2 : transition(161,80,700);break;
            case 3 : transition(241,80,600);break;
            case 4 : transition(322,80,500);break;
            case 5 : transition(402,80,400);
        }
        position=1;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order-light.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);

    }

    @FXML
    void btnSearchOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "search-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,0,100);break;
            case 1 : transition(80,0,700);break;
            case 2 : transition(161,0,600);break;
            case 3 : transition(241,0,500);break;
            case 4 : transition(322,0,400);break;
            case 5 : transition(402,0,300);
        }
        position=0;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search-light.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblStock.isVisible())lblStock.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);
    }

    @FXML
    void btnStockOnMouseClicked(MouseEvent event) throws IOException {
        Navigation.onTheTopNavigation(loadingPane, "stockManage-form.fxml");
        if(!backPane.isVisible())backPane.setVisible(true);
        switch (position){
            case 0 : transition(0,161,600);break;
            case 1 : transition(80,161,700);break;
            case 2 : transition(161,161,100);break;
            case 3 : transition(241,161,700);break;
            case 4 : transition(322,161,600);break;
            case 5 : transition(402,161,500);
        }
        position=2;
        imgEmployees.setImage(new Image("/assets/icons/icons8-employees.png"));
        imgStock.setImage(new Image("/assets/icons/icons8-store-50-light.png"));
        imgSearch.setImage(new Image("/assets/icons/icons8-search.png"));
        imgPreOrders.setImage(new Image("/assets/icons/icons8-purchase-order.png"));
        imgPayment.setImage(new Image("/assets/icons/icons8-bank-cards.png"));
        imgCustomers.setImage(new Image("/assets/icons/ashan.png"));

        if (lblEmployee.isVisible())lblEmployee.setVisible(false);
        if (lblSearch.isVisible())lblSearch.setVisible(false);
        if (lblPreOrders.isVisible())lblPreOrders.setVisible(false);
        if (lblPayment.isVisible())lblPayment.setVisible(false);
        if (lblSeller.isVisible())lblSeller.setVisible(false);

    }

    private void transition(int from,int to,int time){
        TranslateTransition tt = new TranslateTransition(Duration.millis(time), backPane);
        tt.setFromX(from);
        tt.setToX(to);
        tt.playFromStart();
    }

}
