package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.WebVehicleDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WebSearchModel {

    public boolean save(List<WebVehicleDto> webVehicleDtos) throws SQLException {
        CrudUtil.crudUtil("TRUNCATE TABLE tempdata");
        for (WebVehicleDto dto : webVehicleDtos) {
                if (!save(dto)) {
                    return false;
                }
        }
        return true;
    }

    private boolean save(WebVehicleDto webVehicleDto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO tempdata VALUES(?,?,?,?,?,?,?,?,?,?)",
                webVehicleDto.getTitle(),
                webVehicleDto.getBrand(),
                webVehicleDto.getModel(),
                webVehicleDto.getContact(),
                webVehicleDto.getYear(),
                webVehicleDto.getPrice(),
                webVehicleDto.getFuelType(),
                webVehicleDto.getEngineCapacity(),
                webVehicleDto.getMileage(),
                webVehicleDto.getCity()
        );
    }

    public List<WebVehicleDto> getAllSearch() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM tempdata");
        List<WebVehicleDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new WebVehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));
        }
        return list;
    }
}
