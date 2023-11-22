package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.SellOrderDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellOrderModel {
    public boolean saveOrder(SellOrderDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO sellorder VALUES (?,?)",dto.getId(),dto.getBuyerId());
    }
    public String generateNextOrderId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM sellOrder ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("O");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9>=id&&id > 0) return "O00" + id;
            else if (99>=id&&id > 9) return "O0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "O001";
    }
}
