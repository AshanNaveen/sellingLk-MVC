package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class VehicleDto {
    private String id;
    private String type;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private int enginCapacity;
    private int mileage;
    private String vehicleNumber;
    private int price;
    private String status;
}
