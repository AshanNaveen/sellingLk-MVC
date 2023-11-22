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
        return CrudUtil.crudUtil("INSERT into user(id,role,address,email,password,username) VALUES (?,?,?,?,?,?)",
                dto.getId(),
                dto.getRole(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUsername());
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
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM user WHERE username=? AND password=?", uname,pword);
        if (resultSet.next())return resultSet.getString(1);
        return null;
    }
}
