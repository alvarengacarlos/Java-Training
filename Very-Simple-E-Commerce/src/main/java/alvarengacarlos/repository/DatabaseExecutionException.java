package alvarengacarlos.repository;

public class DatabaseExecutionException extends Exception {
    public DatabaseExecutionException() {
        super("it was not possible execute this operation");
    }
}
