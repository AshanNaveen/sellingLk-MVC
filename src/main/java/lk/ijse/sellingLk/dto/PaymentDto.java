package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class PaymentDto {
    private String id;
    private int amount;
    private Date date;
    private String sellerId;
    private String buyerId;
    private String uId;
}
