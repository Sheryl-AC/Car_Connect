package exception;

public class InvalidInputException extends RuntimeException implements java.io.Serializable {
    private static final long serialVersionUID = 6L;

    public InvalidInputException(String message) {
        super(message);
    }
}
