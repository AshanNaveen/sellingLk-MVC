package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class PaymentDto {
    private String id;
    private String amount;
    private String date;
    private String sellerId;
    private String buyerId;
    private String uId;
}
