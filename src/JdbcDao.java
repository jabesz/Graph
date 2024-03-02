import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/grafico";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "jabes123";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";

    public boolean validate (String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            return connection;
        }catch(SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    public static void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
           if (ex instanceof SQLException) {
            e.printStackTrace(System.err);
            System.err.print("SQLState: " +((SQLException) e ) .getSQLState());
            System.err.print("Error code: " +((SQLException) e ) .getErrorCode());
            System.err.print("Message: " + ex.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.err.print("Cause: " + t);
                t = t.getCause();
            }
           } 
        }

    }
}
