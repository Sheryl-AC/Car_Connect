package exception;

public class ReservationNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Explicitly declare serialVersionUID

    public ReservationNotFoundException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}
