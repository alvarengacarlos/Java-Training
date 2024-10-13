package infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Reference:
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/tables.html
 */

public class PopulateTables {
    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void execute() throws SQLException {
        Connection connection = databaseConnection.getConnection(DatabaseConnectionType.POOLED_DATA_SOURCE);
        try (Statement statement = connection.createStatement()) {
            System.out.println("Inserting values");
            statement.executeUpdate(getPopulateTasksTableCommand());
            System.out.println("Values inserted");
        }
    }

    private String getPopulateTasksTableCommand() {
        return """            
            INSERT INTO tasks (
                title,
                description
            ) VALUES
                ("Buy apples", "Buy 10 apples in the morning."),
                ("Sell bananas", "Sell my 20 bananas in the afternoon."),
                ("Eat apples","Eat my 10 apples at night."
            )
            """;
    }
}
