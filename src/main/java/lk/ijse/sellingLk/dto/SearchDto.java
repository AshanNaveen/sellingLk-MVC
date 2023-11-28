package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class SearchDto {
    private String brand;
    private String model;
    private String type;
    private String fuelType;
    private int priceMin;
    private int priceMax;
    private int yearMin;
    private int yearMax;

}
