package entity;
import java.time.LocalDate;

public class Reservation {
    private int reservationID;
    private int customerID;
    private int vehicleID;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor to initialize all fields
    public Reservation(int reservationID, int customerID, int vehicleID, LocalDate startDate, LocalDate endDate) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Constructor for creating a new reservation (without reservationID as it is auto-incremented)
    public Reservation(int customerID, int vehicleID, LocalDate startDate, LocalDate endDate) {
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter and Setter methods for all fields

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", customerID=" + customerID +
                ", vehicleID=" + vehicleID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
