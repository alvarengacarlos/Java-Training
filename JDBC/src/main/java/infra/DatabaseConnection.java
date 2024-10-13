package infra;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;

import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * References:
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
 * - https://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html
 */
public final class DatabaseConnection {
    private final String host = "localhost";
    private final String port = "3306";
    private final String name = "jdbc_test";
    private final String user = "user";
    private final String password = "userpw";
    private static DatabaseConnection instance = null;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection(DatabaseConnectionType connectionType) throws SQLException {
        Connection connection = switch (connectionType) {
            case DatabaseConnectionType.DRIVER_MANAGER -> getDriverManagerConnection();
            case DatabaseConnectionType.SIMPLE_DATA_SOURCE -> getSimpleDataSourceConnection();
            case DatabaseConnectionType.POOLED_DATA_SOURCE -> getPooledDataSourceConnection().getConnection();
            case DatabaseConnectionType.DISTRIBUTED_TRANSACTION_DATA_SOURCE -> getDistributedTransactionDataSourceConnection().getConnection();
        };

        return connection;
    }

    private Connection getDriverManagerConnection() throws SQLException {
        return DriverManager.getConnection(getConnectionUrl());
    }

    private String getConnectionUrl() {
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://");
        url.append(host);
        url.append(":");
        url.append(port);
        url.append("/");
        url.append(name);
        url.append("?");
        url.append("user=");
        url.append(user);
        url.append("&password=");
        url.append(password);

        return url.toString();
    }

    private Connection getSimpleDataSourceConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        configureMysqlDataSource(dataSource);
        return dataSource.getConnection();
    }

    private void configureMysqlDataSource(MysqlDataSource dataSource) {
        dataSource.setServerName(host);
        dataSource.setPort(Integer.parseInt(port));
        dataSource.setDatabaseName(name);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    private PooledConnection getPooledDataSourceConnection() throws SQLException {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        configureMysqlDataSource(dataSource);
        return dataSource.getPooledConnection();
    }

    private XAConnection getDistributedTransactionDataSourceConnection() throws SQLException {
        MysqlXADataSource dataSource = new MysqlXADataSource();
        configureMysqlDataSource(dataSource);
        return dataSource.getXAConnection();
    }
}
