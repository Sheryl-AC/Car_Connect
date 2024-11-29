package exception;

public class AuthenticationException extends RuntimeException implements java.io.Serializable {
    private static final long serialVersionUID = 7L;

    public AuthenticationException(String message) {
        super(message);
    }
}
