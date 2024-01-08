package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.controller.LoginFormController;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.dto.BuyOrderDto;
import lk.ijse.sellingLk.dto.PaymentDto;
import lk.ijse.sellingLk.dto.PlaceOrderDto;
import lk.ijse.sellingLk.dto.SellOrderDto;
import lk.ijse.sellingLk.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class PlaceOrderModel {
    private VehicleModel vehicleModel = new VehicleModel();
    private PaymentModel paymentModel = new PaymentModel();

    private SellOrderModel sellOrderModel = new SellOrderModel();

    private BuyOrderModel buyOrderModel = new BuyOrderModel();

    private SellOrderDetailModel sellOrderDetailModel = new SellOrderDetailModel();
    private BuyOrderDetailModel buyOrderDetailModel = new BuyOrderDetailModel();

  public boolean saveBuyOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved = buyOrderModel.saveOrder(new BuyOrderDto(placeOrderDto.getOrderId(), placeOrderDto.getCusId()));
            if (isSaved) {
                String id = paymentModel.generateNextId();
                String uId = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
                boolean isPaid = paymentModel.savePayment(new PaymentDto(id, placeOrderDto.getAmount(), placeOrderDto.getDate(),  placeOrderDto.getCusId(),null, uId));
                if (isPaid) {
                    boolean isOrderDetailSaved = buyOrderDetailModel.saveOrderDetail(placeOrderDto.getOrderId(), placeOrderDto.getItems(), placeOrderDto.getDate());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        System.out.println("Hureee");
                        //generateReport(placeOrderDto);
                        result = true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    public boolean saveSellOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved = sellOrderModel.saveOrder(new SellOrderDto(placeOrderDto.getOrderId(), placeOrderDto.getCusId()));
            if (isSaved) {
                boolean isDeleted = vehicleModel.deleteVehicle(placeOrderDto.getItems());

                if (isDeleted) {
                    String id = paymentModel.generateNextId();
                    String uId = new UserModel().getUserId(SignInFormController.uname, SignInFormController.pword);
                    boolean isPaid = paymentModel.savePayment(new PaymentDto(id, placeOrderDto.getAmount(), placeOrderDto.getDate(), null, placeOrderDto.getCusId(), uId));
                    if (isPaid) {
                        boolean isOrderDetailSaved = sellOrderDetailModel.saveOrderDetail(placeOrderDto.getOrderId(), placeOrderDto.getItems(), placeOrderDto.getDate());
                        if (isOrderDetailSaved) {
                            connection.commit();
                            System.out.println("Hureee");
                            //generateReport(placeOrderDto);
                            result = true;
                        }

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }


}

