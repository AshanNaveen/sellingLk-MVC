package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreOrderDto {
    private String id;
    private String description;
    private int year;
    private String date;
    private int Status;
    private String buyerId;
}
