package exception;

public class AdminNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Explicitly declare serialVersionUID

    public AdminNotFoundException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}

