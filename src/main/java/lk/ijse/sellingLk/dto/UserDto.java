package lk.ijse.sellingLk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class UserDto {
   private String id;
   private String role;
   private String email;
   private String password;
   private String username;
   private String name;
}
