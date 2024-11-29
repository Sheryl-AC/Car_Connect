package car_connect;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    @Test
    void testCalculateSpeed_ValidInput() {
        // Create an instance of App
        App app = new App();
        
        // Call calculateSpeed with valid inputs
        int speed = app.calculateSpeed(100, 2);
        
        // Assert that the result is as expected
        assertEquals(50, speed, "Speed calculation failed for valid inputs");
    }

    @Test
    void testCalculateSpeed_DivisionByZero() {
        // Create an instance of App
        App app = new App();
        
        // Assert that an exception is thrown for division by zero
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.calculateSpeed(100, 0);
        });
        
        // Assert the exception message
        assertEquals("Time cannot be zero", exception.getMessage());
    }
}

