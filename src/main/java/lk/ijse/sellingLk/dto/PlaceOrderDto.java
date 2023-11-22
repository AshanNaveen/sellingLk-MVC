package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderDto {
    private String orderId;
    private String cusId;
    private ArrayList<String[]> items;
    private int amount;
    private Date date;
    private Time time;
}
