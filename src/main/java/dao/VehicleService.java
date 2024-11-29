package dao;
import entity.Vehicle;
import exception.VehicleNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleService implements IVehicleService {
    private static final Map<Integer, Vehicle> vehicleDatabase = new ConcurrentHashMap<>();

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        if (!vehicleDatabase.containsKey(vehicleId)) {
            throw new VehicleNotFoundException("Vehicle not found.");
        }
        return vehicleDatabase.get(vehicleId);
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDatabase.values()
                .stream()
                .filter(Vehicle::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (vehicleDatabase.containsKey(vehicle.getVehicleId())) { // Corrected getter name
            throw new VehicleNotFoundException("Vehicle ID already exists.");
        }
        vehicleDatabase.put(vehicle.getVehicleId(), vehicle); // Corrected getter name
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        if (!vehicleDatabase.containsKey(vehicle.getVehicleId())) { // Corrected getter name
            throw new VehicleNotFoundException("Vehicle not found.");
        }
        vehicleDatabase.put(vehicle.getVehicleId(), vehicle); // Corrected getter name
    }

    @Override
    public void removeVehicle(int vehicleId) {
        if (!vehicleDatabase.containsKey(vehicleId)) {
            throw new VehicleNotFoundException("Vehicle not found.");
        }
        vehicleDatabase.remove(vehicleId);
    }
}

