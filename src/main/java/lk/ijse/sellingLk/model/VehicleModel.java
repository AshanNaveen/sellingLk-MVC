package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    public boolean saveVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into item(" +
                "id,description,brand,model,year,fuelType,engineCapacity,mileage,price,contact) VALUES (?,?,?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getDescription(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice(),
                dto.getContact());
    }
    public boolean updateVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE item SET " +
                "description=?,brand=?,model=?,year=?,fuelType=?,engineCapacity=?,mileage=?,price=?,contact=? WHERE id=?",
                dto.getDescription(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice(),
                dto.getContact());
    }
    public boolean deleteVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM item WHERE id=?",dto.getId());
    }


    public List<VehicleDto> getAllVehile()throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM item");

        List<VehicleDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));
        }
        return dtoList;
    }
}
