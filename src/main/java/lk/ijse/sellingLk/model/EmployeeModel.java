package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.EmployeeDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public boolean saveEmployee(final EmployeeDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into staff(" +
                        "id," +
                        "name," +
                        "address," +
                        "phone," +
                        "role," +
                        "uId" +
                        ") VALUES (?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getUId());
    }
    public boolean updateEmployee(final EmployeeDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE item SET " +
                "name=?," +
                "address=?," +
                "phone=?," +
                "role=?," +
                "uId=?" +
                " WHERE id=?",
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getUId(),
                dto.getId());
    }
    public boolean deleteEmployee(final EmployeeDto dto) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM item WHERE id=?",
                dto.getId());
    }


    public List<EmployeeDto> getAllEmployee()throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM item");

        List<EmployeeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return dtoList;
    }
}
