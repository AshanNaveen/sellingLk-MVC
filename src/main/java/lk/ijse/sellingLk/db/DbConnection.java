package lk.ijse.sellingLk.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class DbConnection {
    @Getter
    private Connection connection = null;
    private static DbConnection dbConnection=null;
    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sellingLk",
                "root",
                "Ijse1234"
        );
    }

    public static DbConnection getInstance() throws SQLException {
        return (dbConnection==null ? (dbConnection = new DbConnection()): dbConnection);
    }

}
