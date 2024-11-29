package exception;

public class DatabaseConnectionException extends RuntimeException implements java.io.Serializable {
    private static final long serialVersionUID = 5L;

    public DatabaseConnectionException(String message) {
        super(message);
    }
}

