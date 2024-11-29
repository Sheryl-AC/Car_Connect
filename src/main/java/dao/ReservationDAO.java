package dao;
import entity.Reservation;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // Create a new reservation
    public void createReservation(Reservation reservation) {
        String query = "INSERT INTO reservation (customerID, vehicleID, startDate, endDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getCustomerID());
            preparedStatement.setInt(2, reservation.getVehicleID());
            preparedStatement.setDate(3, Date.valueOf(reservation.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getEndDate()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a reservation by ID
    public Reservation getReservationById(int reservationId) {
        String query = "SELECT * FROM reservation WHERE reservationID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a reservation
    public void updateReservation(int reservationID, Reservation reservation) {
        String query = "UPDATE reservation SET customerID = ?, vehicleID = ?, startDate = ?, endDate = ? WHERE reservationID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservation.getCustomerID());
            preparedStatement.setInt(2, reservation.getVehicleID());
            preparedStatement.setDate(3, Date.valueOf(reservation.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getEndDate()));
            preparedStatement.setInt(5, reservationID);  // Use the reservationID as identifier
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("No reservation found with the provided ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while updating reservation: " + e.getMessage());
        }
    }

    // Delete a reservation by ID
    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM reservation WHERE reservationID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reservationId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try (Connection connection = DBConnUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                reservations.add(mapResultSetToReservation(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Retrieve reservations by Customer ID
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE customerID = ?";

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    reservations.add(mapResultSetToReservation(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Map ResultSet to Reservation object
    private Reservation mapResultSetToReservation(ResultSet resultSet) throws SQLException {
        return new Reservation(
                resultSet.getInt("reservationID"),
                resultSet.getInt("customerID"),
                resultSet.getInt("vehicleID"),
                resultSet.getDate("startDate").toLocalDate(),
                resultSet.getDate("endDate").toLocalDate()
        );
    }
}
