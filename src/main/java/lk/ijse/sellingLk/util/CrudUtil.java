package lk.ijse.sellingLk.util;

import lk.ijse.sellingLk.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T crudUtil(String sql, Object... arg) throws SQLException  {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < arg.length; i++) {
            statement.setObject((i + 1), arg[i]);
        }
        if (sql.startsWith("SELECT")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate() > 0);
        }
    }
    public static void setAutoCommit(boolean value) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        connection.setAutoCommit(value);
    }

    public static void commit() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        connection.commit();
    }

    public static void rollBack() {
        try {
            Connection connection=DbConnection.getInstance().getConnection();
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
