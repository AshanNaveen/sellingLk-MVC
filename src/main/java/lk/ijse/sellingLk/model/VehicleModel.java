package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.dto.SearchDto;
import lk.ijse.sellingLk.dto.VehicleDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class VehicleModel {
    public boolean saveVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into vehicle(" +
                        "id,brand,model,type,year,fuelType,engineCapacity,mileage,price,status) VALUES (?,?,?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getType(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice(),
                dto.getStatus());
    }

    public boolean updateVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE vehicle SET " +
                        "brand=?,model=?,type=?,year=?,fuelType=?,engineCapacity=?,mileage=?,price=?,status=? WHERE id=?",
                dto.getBrand(),
                dto.getModel(),
                dto.getType(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice(),
                dto.getStatus());
    }

    public boolean deleteVehicle(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM vehicle WHERE id=?", id);
    }

    public boolean deleteVehicle(List<String> list) throws SQLException {
        for (String array : list) {
            if (!updateStatus(array)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateStatus(String list) throws SQLException {
        return CrudUtil.crudUtil("UPDATE vehicle SET status='Sold' WHERE id=?", list);
    }


    public List<VehicleDto> getAllVehile() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle");

        List<VehicleDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getString(10)
            ));
        }
        return dtoList;
    }

    public String getNextVehicleId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("V");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "V00" + id;
            else if (99 >= id && id > 9) return "V0" + id;
            else if (id > 99) return "V" + id;
        }
        return "V001";
    }


    public int getMinumunYear() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year LIMIT 1");
        while (resultSet.next()) {
            return resultSet.getInt(5);
        }
        return 2000;
    }

    public int getMaximumYear() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year DESC LIMIT 1");
        while (resultSet.next()) {
            return resultSet.getInt(5);
        }
        return 2000;
    }

    public List<VehicleDto> search(SearchDto searchDto) throws SQLException {
        List<VehicleDto> list = new ArrayList<>();
        String brand = searchDto.getBrand();
        String model = searchDto.getModel();

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE brand LIKE \'%"+brand+"%\' OR model LIKE \'%"+model+"%\' AND ?>year AND year>? AND fuelType LIKE \'%"+searchDto.getFuelType()+"%\' AND ?>price AND price>?",searchDto.getYearMax(),searchDto.getYearMin(),searchDto.getPriceMax(),searchDto.getPriceMin());
        while (resultSet.next()) {
            if (resultSet.getString(10).equals("On Hand")) {
                list.add(new VehicleDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10)
                ));
            }
        }
        if (list.size()>1) {
            return list;
        }


        resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE brand LIKE \'%"+brand+"%\' AND model LIKE \'%"+model+"%\'");
        l1:
        while (resultSet.next()) {
            if (resultSet.getString(10).equals("On Hand")) {
                for (VehicleDto dto : list) {
                    if (dto.getId().equals(resultSet.getString(1))) {
                        continue l1;
                    }
                }

                list.add(new VehicleDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10)
                ));
            }
        }
        return list;
    }

    public VehicleDto getVehicleInfo(String value) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE id=?", value);

        while (resultSet.next()) {
            return new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getString(10)
            );
        }
        return null;
    }

    public List<String> getNotGetVehicle() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.crudUtil("select id from vehicle where id not in (select vehicleId from sellorderdetail)");
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
