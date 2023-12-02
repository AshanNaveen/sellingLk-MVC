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
    private String year;
    private String price;
    private String fuelType;
    private String engineCapacity;
    private String mileage;
    private String city;
}
