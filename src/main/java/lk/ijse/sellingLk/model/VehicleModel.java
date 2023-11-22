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
                "id,description,brand,model,year,fuelType,engineCapacity,mileage,price) VALUES (?,?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getDescription(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice());
    }
    public boolean updateVehicle(final VehicleDto dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE vehicle SET " +
                "description=?,brand=?,model=?,year=?,fuelType=?,engineCapacity=?,mileage=?,price=? WHERE id=?",
                dto.getDescription(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getPrice());
    }

    public boolean deleteVehicle(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM vehicle WHERE id=?",id);
    }
    public boolean deleteVehicle(ArrayList<String[]> list) throws SQLException {
        for (String[] array : list) {
            if(!deleteVehicle(array)) {
                return false;
            }
        }
        return true;
    }
    public boolean deleteVehicle(String[] list) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM vehicle WHERE id=?",list[0]);
    }


    public List<VehicleDto> getAllVehile()throws SQLException {
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
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
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
            if (9>= id && id > 0) return "V00" + id;
            else if (99>=id && id > 9) return "V0" + id;
            else if (id > 99) return "V"+id;
        }
        return "V001";
    }


    public int getMinumunYear() throws SQLException {
        ResultSet resultSet =CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year LIMIT 1");
        while (resultSet.next()){
            return resultSet.getInt(5);
        }
        return 2000;
    }
    public int getMaximumYear() throws SQLException {
        ResultSet resultSet =CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year DESC LIMIT 1");
        while (resultSet.next()){
            return resultSet.getInt(5);
        }
        return 2000;
    }

    public List<VehicleDto> search(SearchDto searchDto) throws SQLException {
        List<VehicleDto> list= new LinkedList<>();
        String current=searchDto.getDescription();
        String[] temp = current.split(" ");
        System.out.println("Regex pahu kara");
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE description LIKE \'%"+current+"%\'");
        while (resultSet.next()){
            list.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        if (list.size()!=0) {
            return list;
        }
        System.out.println("Palaweni query eka iwrai");

        String description="description LIKE \'%"+current+"%\' OR description LIKE ";
        for (int i = 0; i < temp.length; i++) {
            description = description + "\'%" + temp[i]+"%\'";
            if (i==temp.length-1){
                break;
            }
            description = description +  " OR description LIKE ";
        }

        resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE "+description);
       l1: while (resultSet.next()){
            for (VehicleDto dto : list){
                if (dto.getId().equals(resultSet.getString(1))){
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
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return list;
    }

    public VehicleDto getVehicleInfo(String value) throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM vehicle WHERE id=?",value);

        while (resultSet.next()){
            return new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return null;
    }
}
