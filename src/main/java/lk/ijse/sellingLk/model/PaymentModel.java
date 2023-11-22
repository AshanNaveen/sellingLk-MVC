package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.PaymentDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

    public String generateNextId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM payment ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9>=id && id > 0) return "P00" + id;
            else if (99>=id && id > 9) return "P0" + id;
            else if (id > 99) return "P"+id;
        }
        return "P001";
    }

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO payment VALUES(?,?,?,?,?,?)",paymentDto.getId(),paymentDto.getAmount(),paymentDto.getDate(),paymentDto.getSellerId(),paymentDto.getBuyerId(),paymentDto.getUId());
    }
}
