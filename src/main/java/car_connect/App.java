package car_connect;

public class App {
	 int calculateSpeed(int distance, int time) {
	        if (time == 0) {
	            throw new IllegalArgumentException("Time cannot be zero");
	        }
	        return distance / time;
	    }
}
