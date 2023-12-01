package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.LoyalIdDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoyalIdModel{
    public List<LoyalIdDto> getAllLoyalId() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM loyalid");
        List<LoyalIdDto> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new LoyalIdDto(
                    resultSet.getString(1),
                    resultSet.getInt(2)
            ));
        }
        return list;
    }
}
