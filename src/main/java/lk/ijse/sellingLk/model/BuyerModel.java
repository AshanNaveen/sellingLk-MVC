package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.dto.BuyerDto;
import lk.ijse.sellingLk.dto.UserDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerModel {
    public boolean saveBuyer(final BuyerDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into buyer(id,name,nic,email,address,phone,uId,loyalId) VALUES (?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId());
    }

    public boolean updateBuyer(final BuyerDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE buyer SET name=? , nic=? ,email=? , address=? , phone=? , uId=? , loyalId=? WHERE id=?",
                dto.getName(),
                dto.getEmail(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId(),
                dto.getId());
    }

    public boolean deleteBuyer(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM buyer WHERE id=?", id);
    }


    public List<BuyerDto> getAllBuyer() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer");

        List<BuyerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new BuyerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return dtoList;
    }

    public String generateNextBuyerId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("B");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "B00" + id;
            else if (99 >= id && id > 9) return "B0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "B001";
    }

    public String getBuyerName(String value) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer WHERE id=?", value);
        while (resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;
    }

    public String getEmail(String cusId) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer WHERE id=?", cusId);
        while (resultSet.next()) {
            return resultSet.getString(3);
        }
        return null;
    }

    public BuyerDto getBuyerInfo(String contact) throws SQLException {
        BuyerDto dto = null;
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer WHERE phone=?", contact);
        if (resultSet.next()) {
           dto= new BuyerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return dto;
    }


}
