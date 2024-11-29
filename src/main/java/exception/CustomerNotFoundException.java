package exception;

public class CustomerNotFoundException extends RuntimeException implements java.io.Serializable {
    private static final long serialVersionUID = 2L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
