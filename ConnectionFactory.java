import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://aws-0-us-west-2.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.yfbyuwmgekuluzoazeta";
    private static final String PASSWORD = "Hehe2505.26";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não foi encontrado!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}