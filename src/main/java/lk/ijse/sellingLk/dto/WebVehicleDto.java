package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebVehicleDto {
    private String title;
    private String brand;
    private String model;
    private String contact;
    private int year;
    private int price;
    private String fuelType;
    private int engineCapacity;
    private int mileage;
    private String city;
}
