package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class VehicleDto {
    private String id;
    private String description;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private String enginCapacity;
    private String mileage;
    private String price;
    private String contact;
}
