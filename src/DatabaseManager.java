import java.sql.*;

public class DatabaseManager {
    
    private static final String URL = "jdbc:sqlite:matchmaking.db"; // path to database file

    public static void initialize() {
        // SQL command to create the players table
        String sql = "CREATE TABLE IF NOT EXISTS players (" +
                     "username TEXT PRIMARY KEY, " +
                     "elo REAL NOT NULL, " +
                     "total_games INTEGER NOT NULL" +
                     ");";
        
        try (Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()) {
            
            // execute table creation
            stmt.execute(sql);
            System.out.println("SQL: Connection established and table ready.");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

    }
}
