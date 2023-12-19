package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.PreOrderDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreOrderModel {
    public boolean savePreOrder(PreOrderDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO preOrder VALUES(?,?,?,?,?)",dto.getId(),dto.getDescription(),dto.getYear(),dto.getDate(),dto.getStatus(),dto.getBuyerId());
    }
    public boolean updatePreOrder(PreOrderDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE preOrder SET description=?,year=?,date=?,status=?,buyerId=? WHERE id=?",dto.getDescription(),dto.getYear(),dto.getDate(),dto.getStatus(),dto.getBuyerId(),dto.getId());
    }
    public boolean deletePreOrder(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM preOrder WHERE id=?",id);
    }
    public List<PreOrderDto> getAllPreOrders() throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM preOrder WHERE month(date) = month(now())");
        List<PreOrderDto> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new PreOrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            ));
        }
        return list;
    }

}
