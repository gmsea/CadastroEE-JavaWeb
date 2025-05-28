package cadastrobd.model.util;

import java.sql.*;

public class ConectorBD {
    private static final String URL =
      "jdbc:sqlserver://localhost:1433;databaseName=SistemaLoja;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "loja";
    private static final String PASS = "loja";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static PreparedStatement getPrepared(Connection c, String sql) throws SQLException {
        return c.prepareStatement(sql);
    }

    public static ResultSet getSelect(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    public static void close(AutoCloseable ac) {
        try {
            if (ac != null) ac.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
