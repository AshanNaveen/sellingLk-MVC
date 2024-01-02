package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.PreOrderDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreOrderModel {
    public boolean savePreOrder(PreOrderDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO preOrder VALUES(?,?,?,?,?,?,?)",dto.getId(),dto.getYear(),dto.getBrand(),dto.getModel(),dto.getDate(),dto.getStatus(),dto.getBuyerId());
    }
    public boolean updatePreOrder(PreOrderDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE preOrder SET year=?,brand=?,model=?,date=?,status=?,buyerId=? WHERE id=?",dto.getYear(),dto.getBrand(),dto.getModel(), Date.valueOf(dto.getDate()),dto.getStatus(),dto.getBuyerId(),dto.getId());
    }
    public boolean deletePreOrder(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM preOrder WHERE id=?",id);
    }
    public List<PreOrderDto> getThisMonthPreOrders() throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM preOrder WHERE month(date) = month(now())");
        List<PreOrderDto> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new PreOrderDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }
        return list;
    }
    public String generateNextPreOrderId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id > 0) return "S00" + id;
            else if (id > 9) return "S0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "S001";
    }

    public List<PreOrderDto> getAllPreOrders() throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM preOrder");
        List<PreOrderDto> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new PreOrderDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }
        return list;
    }
}
