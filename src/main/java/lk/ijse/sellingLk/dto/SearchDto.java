package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class SearchDto {
    private String description;
    private String fuelType;
    private String priceMin;
    private String priceMax;
    private String yearMin;
    private String yearMax;
    private String millageMin;
    private String millageMax;

}