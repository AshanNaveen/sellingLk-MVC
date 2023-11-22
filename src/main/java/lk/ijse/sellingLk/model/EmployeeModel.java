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
                        "email,"+
                        "address," +
                        "phone," +
                        "role," +
                        "uId" +
                        ") VALUES (?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getUId());
    }
    public boolean updateEmployee(final EmployeeDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE staff SET " +
                "name=?," +
                "email=?,"+
                "address=?," +
                "phone=?," +
                "role=?" +
                " WHERE id=?",
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getId());
    }
    public boolean deleteEmployee(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM staff WHERE id=?",
                id);
    }


    public List<EmployeeDto> getAllEmployee()throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM staff");

        List<EmployeeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new EmployeeDto(
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

    public String getNextEmpId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM staff ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("E");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9>id && id > 0) return "E00" + id;
            else if (99>id && id > 9) return "E0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "E001";
    }
}
