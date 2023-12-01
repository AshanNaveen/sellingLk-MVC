package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyOrderDetailModel {
    public boolean saveOrderDetail(String orderId, List<String> list, Date date) throws SQLException {
        for (String cart : list) {
            if(!saveOrderDetail(orderId, cart,date)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetail(String orderId, String cart,Date date) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO buyorderdetail Values (?,?,?)",date,orderId,cart);
    }
}
