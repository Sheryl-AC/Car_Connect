package dao;
import entity.Vehicle;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Insert a new vehicle
    public void addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO Vehicle (Model, Make, Year, Color, registrationNumber, isAvailable, dailyRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vehicle.getModel());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getColor());
            preparedStatement.setString(5, vehicle.getRegistrationNumber());
            preparedStatement.setBoolean(6, vehicle.isAvailable());
            preparedStatement.setDouble(7, vehicle.getDailyRate());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a vehicle by ID
    public Vehicle getVehicleById(int vehicleId) {
        String query = "SELECT * FROM Vehicle WHERE VehicleID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vehicleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVehicle(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update vehicle details
    public void updateVehicle(Vehicle vehicle) {
        String query = "UPDATE Vehicle SET Model = ?, Make = ?, Year = ?, Color = ?, registrationNumber = ?, " +
                "isAvailable = ?, DailyRate = ? WHERE VehicleID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vehicle.getModel());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getColor());
            preparedStatement.setString(5, vehicle.getRegistrationNumber());
            preparedStatement.setBoolean(6, vehicle.isAvailable());
            preparedStatement.setDouble(7, vehicle.getDailyRate());
            preparedStatement.setInt(8, vehicle.getVehicleId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a vehicle by ID
    public void deleteVehicle(int vehicleId) {
        String query = "DELETE FROM Vehicle WHERE VehicleID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to Vehicle object
    private Vehicle mapResultSetToVehicle(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getInt("VehicleID"),
                resultSet.getString("Model"),
                resultSet.getString("Make"),
                resultSet.getInt("Year"),
                resultSet.getString("Color"),
                resultSet.getString("registrationNumber"),
                resultSet.getBoolean("isAvailable"),
                resultSet.getDouble("DailyRate")
        );
    }

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle";

        try (Connection connection = DBConnUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                vehicles.add(mapResultSetToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
