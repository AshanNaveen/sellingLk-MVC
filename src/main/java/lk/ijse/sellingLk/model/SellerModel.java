package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.SellerDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerModel {
    public boolean saveSeller(final SellerDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into seller(id,name,nic,email,address,phone,uId) VALUES (?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId());
    }

    public boolean updateSeller(final SellerDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE seller SET name=? ,nic=?, email=? , address=? , phone=? , uId=?  WHERE id=?",
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getId());
    }

    public boolean deleteSeller(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM seller WHERE id=?", id);
    }


    public List<SellerDto> getAllSeller() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller");

        List<SellerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new SellerDto(
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

    public String generateNextSellerId() throws SQLException {

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

    public String getSellerName(String value) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller WHERE id=?", value);
        if (resultSet.next())return resultSet.getString(2);
        return null;
    }

    public SellerDto getSellerInfo(String contact) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller WHERE phone=?",contact);
        if (resultSet.next())return new SellerDto(resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7));
        return null;
    }

    public String getEmail(String cusId) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT email FROM seller WHERE id=?", cusId);
        if (resultSet.next())return resultSet.getString(1);
        return null;
    }
}
