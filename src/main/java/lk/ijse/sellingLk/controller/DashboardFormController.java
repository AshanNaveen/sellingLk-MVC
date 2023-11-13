package lk.ijse.sellingLk.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Pane loadingPane;

    @FXML
    private Pane btnSearch;

    @FXML
    private Pane btnPreOrder;

    @FXML
    private Pane btnStock;

    @FXML
    private Pane btnBAndS;

    @FXML
    private Pane btnPayment;

    @FXML
    private Pane btnEmployee;

    public void initialize() throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/overview-form.fxml")));
    }

    @FXML
    void btnBAndSOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/sellerManage-form.fxml")));
    }

    @FXML
    void btnEmployeeOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/employeeManage-form.fxml")));
    }

    @FXML
    void btnLogoOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/overview-form.fxml")));
    }

    @FXML
    void btnPaymentOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signIn-form.fxml")));
    }

    @FXML
    void btnPreOrderOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/preOrdersManage-form.fxml")));
    }

    @FXML
    void btnSearchOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/search-form.fxml")));
    }

    @FXML
    void btnStockOnMouseClicked(MouseEvent event) throws IOException {
        loadingPane.getChildren().clear();
        loadingPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/stockManage-form.fxml")));
    }
}
