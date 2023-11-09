package lk.ijse.sellingLk.model;

import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public boolean saveUser(final UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT into user(id,role,address,email,password,username) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getRole());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getPassword());
        pstm.setString(6, dto.getUsername());
        return pstm.executeUpdate() > 0;
    }
    public boolean updateUser(final UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE user SET role=?,address=?,email=?,password=?,username=? WHERE id=?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getRole());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getPassword());
        pstm.setString(5, dto.getUsername());
        pstm.setString(6, dto.getId());
        return pstm.executeUpdate() > 0;
    }
    public boolean deleteUser(final UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM user WHERE id=?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        return pstm.executeUpdate() > 0;
    }


    public List<UserDto> getAllUsers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<UserDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String role = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String password = resultSet.getString(5);
            String username = resultSet.getString(6);
            UserDto dto = new UserDto(id,role,address,email,password,username);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
