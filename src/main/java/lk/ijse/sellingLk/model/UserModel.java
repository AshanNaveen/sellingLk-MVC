package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.dto.UserDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public boolean saveUser(final UserDto dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into user(id,role,email,password,username,name) VALUES (?,?,?,?,?,?)",
                dto.getId(),
                dto.getRole(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getName());
    }

    public boolean searchUser(String username, String password) throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM user WHERE username=? AND password=?", username, password);
        return rst.next();
    }

    public String getUserId(String username,String password) throws SQLException {
        System.out.println("Ia am come");
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM user WHERE username=? AND password=?", username,password);
        if (resultSet.next())return resultSet.getString(1);
        return null;
    }

    public String getUserName(String uname, String pword) throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT name FROM user WHERE username=? AND password=?", uname,pword);
        if (resultSet.next())return resultSet.getString(1);
        return null;
    }

    public String generateNextUserId() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM user ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return splitId(current);
        }
        return splitId(null);

    }

    private String splitId(String current) {
        if (current != null) {
            String[] split = current.split("U");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "U00" + id;
            else if (99 >= id && id > 9) return "U0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "U001";
    }
}
