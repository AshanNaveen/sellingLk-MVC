package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SellOrderDetailDto {
    private String date;
    private String  qty;
    private String orderId;
    private String itemId;
}
