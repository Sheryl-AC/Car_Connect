package exception;

public class VehicleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Explicitly declare serialVersionUID

    public VehicleNotFoundException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}