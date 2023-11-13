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
        return CrudUtil.crudUtil("INSERT into buyer(id,name,email,address,phone,uId,loyalId) VALUES (?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId());
    }

    public boolean updateBuyer(final BuyerDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE buyer SET name=? , email=? , address=? , phone=? , uId=? , loyalId=? WHERE id=?",
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId(),
                dto.getId());
    }

    public boolean deleteBuyer(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM buyer WHERE id=?",id);
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
                    resultSet.getString(7)
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
            if (id > 0) return "B00" + id;
            else if (id > 9) return "B0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "B001";
    }
}
