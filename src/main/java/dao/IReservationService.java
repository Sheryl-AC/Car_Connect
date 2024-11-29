package dao;

import entity.Reservation;
import java.util.List;

public interface IReservationService {
    Reservation getReservationById(int reservationId);              // Retrieve reservation by its ID
    List<Reservation> getReservationsByCustomerId(int customerId);   // Get all reservations for a customer
    void createReservation(Reservation reservation);                 // Create a new reservation
    void updateReservation(Reservation reservation);                 // Update existing reservation
    void cancelReservation(int reservationId);                       // Cancel a reservation by its ID
}

