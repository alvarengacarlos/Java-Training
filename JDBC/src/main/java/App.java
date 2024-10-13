import entity.Task;
import infra.DatabaseConnection;
import infra.CreateTables;
import infra.DatabaseConnectionType;
import infra.PopulateTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Referênces:
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html#creating_statements
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
 */

public class App {
    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public static void main(String[] args) {
        try {
            new CreateTables().execute();
            new PopulateTables().execute();

            App app = new App();
            app.insertTask();
            List<Task> tasks = app.readTasks();
            tasks.forEach(System.out::println);
            Task nonExistentTask = app.readTaskById((short) 0);
            System.out.println(nonExistentTask);
            Task task = app.readTaskById((short) 1);
            System.out.println(task);

        } catch (SQLException exception) {
            System.err.println("Message: " + exception.getMessage());
            System.err.println("Standard code: " + exception.getSQLState());
            System.err.println("Error code: " + exception.getErrorCode());
        }
    }

    private void insertTask() throws SQLException {
        String sql = "INSERT INTO tasks (title, description) VALUES ('Buy apples', 'Buy 10 apples in the morning.')";
        Connection connection = databaseConnection.getConnection(DatabaseConnectionType.POOLED_DATA_SOURCE);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private List<Task> readTasks() throws SQLException {
        String sql = "SELECT * FROM tasks";
        Connection connection = databaseConnection.getConnection(DatabaseConnectionType.POOLED_DATA_SOURCE);
        List<Task> tasks = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                tasks.add(mapTabularTaskToObject(resultSet));
            }
            return tasks;
        }
    }

    private Task mapTabularTaskToObject(ResultSet resultSet) throws SQLException {
        return new Task(
                resultSet.getShort("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("created_at")
        );
    }

    private Task readTaskById(Short id) throws SQLException {
        Connection connection = databaseConnection.getConnection(DatabaseConnectionType.POOLED_DATA_SOURCE);
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (PreparedStatement prepareStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            prepareStatement.setShort(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (!resultSet.first()) {
                return null;
            }

            return mapTabularTaskToObject(resultSet);
        }
    }
}
