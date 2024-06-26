package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SellerDto {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String phone;
    private String uId;
}
