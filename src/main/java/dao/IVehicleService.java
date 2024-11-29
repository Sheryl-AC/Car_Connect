package dao;

import entity.Vehicle;
import java.util.List;

public interface IVehicleService {
    Vehicle getVehicleById(int vehicleId);  // Retrieve vehicle by its ID
    List<Vehicle> getAvailableVehicles();   // Get a list of all available vehicles
    void addVehicle(Vehicle vehicle);       // Add a new vehicle to the database
    void updateVehicle(Vehicle vehicle);    // Update vehicle details
    void removeVehicle(int vehicleId);      // Remove a vehicle by its ID
}