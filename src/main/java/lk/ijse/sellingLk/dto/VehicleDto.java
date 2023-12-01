package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class VehicleDto {
    private String id;
    private String brand;
    private String model;
    private String type;
    private int year;
    private String fuelType;
    private int enginCapacity;
    private int mileage;
    private int price;
    private String status;
}
