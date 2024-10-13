package infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void execute() throws SQLException {
        Connection connection = databaseConnection.getConnection(DatabaseConnectionType.POOLED_DATA_SOURCE);
        try (Statement statement = connection.createStatement()) {
            System.out.println("Creating tables");
            statement.executeUpdate(getCreateTasksTableCommand());
            System.out.println("Tables created");
        }
    }

    private String getCreateTasksTableCommand() {
        return """
            CREATE TABLE IF NOT EXISTS tasks (
                id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
                title VARCHAR(50) NOT NULL,
                description TINYTEXT NOT NULL,
                created_at DATETIME NOT NULL DEFAULT NOW(),
                PRIMARY KEY (id)
            )
            """;
    }
}
