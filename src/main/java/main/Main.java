package main;
import dao.CustomerDAO;
import dao.ReservationDAO;
import dao.VehicleDAO;
import entity.Customer;
import entity.Vehicle;
import entity.Reservation;
import exception.InvalidInputException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("___CarConnect - Main Menu___");
                System.out.println("1. Customer Management");
                System.out.println("2. Vehicle Management");
                System.out.println("3. Reservation Management");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        handleCustomerManagement();
                        break;
                    case 2:
                        handleVehicleManagement();
                        break;
                    case 3:
                        handleReservationManagement();
                        break;
                    case 4:
                        System.out.println("Exiting... Thank you for using CarConnect!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void handleCustomerManagement() {
        CustomerDAO customerDAO = new CustomerDAO();

        while (true) {
            try {
                System.out.println("\nCustomer Management Menu:");
                System.out.println("1. Register Customer");
                System.out.println("2. Update Customer");
                System.out.println("3. Delete Customer");
                System.out.println("4. View Customer by ID");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: // Register a customer
                        System.out.println("Enter customer details (FirstName LastName Email Phone Address Username Password RegistrationDate(YYYY-MM-DD)):");
                        String[] input = scanner.nextLine().split("\\s+");
                        if (input.length != 8) {
                            System.out.println("Error: Please provide all 8 fields.");
                            continue;
                        }
                        try {
                            //LocalDate registrationDate = LocalDate.parse(input[7]);
                            Customer customer = new Customer(
                                    0,input[0], input[1], input[2], input[3], 
                                    input[4], input[5], input[6], input[7]);
                            customerDAO.createCustomer(customer);
                            System.out.println("Customer registered successfully.");
                        } catch (Exception e) {
                            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
                        }
                        break;

                    case 2: // Update customer details
                        System.out.print("Enter customer ID to update: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter updated details (FirstName LastName Email Phone Address Username Password RegistrationDate(YYYY-MM-DD)):");
                        String[] updateInput = scanner.nextLine().split("\\s+");
                        if (updateInput.length != 8) {
                            System.out.println("Error: Please provide all 8 fields.");
                            continue;
                        }
                        try {
                           // LocalDate updatedRegistrationDate = LocalDate.parse(updateInput[7]);
                            Customer updatedCustomer = new Customer(
                                    id, updateInput[0], updateInput[1], updateInput[2], updateInput[3], 
                                    updateInput[4], updateInput[5], updateInput[6], updateInput[7]
                            );
                            customerDAO.updateCustomer(updatedCustomer);
                            System.out.println("Customer updated successfully.");
                        } catch (Exception e) {
                            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
                        }
                        break;

                    case 3: // Delete a customer
                        System.out.print("Enter customer ID to delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        customerDAO.deleteCustomer(deleteId);
                        System.out.println("Customer deleted successfully.");
                        break;

                    case 4: // View customer by ID
                        System.out.print("Enter customer ID to view: ");
                        int viewId = Integer.parseInt(scanner.nextLine());
                        Customer viewCustomer = customerDAO.getCustomerById(viewId);
                        if (viewCustomer != null) {
                            System.out.println("Customer Details: " + viewCustomer);
                        } else {
                            System.out.println("No customer found with the given ID.");
                        }
                        break;

                    case 5: // Return to main menu
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter valid numbers.");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static void handleVehicleManagement() {
        VehicleDAO vehicleDAO = new VehicleDAO();

        while (true) {
            try {
                System.out.println("\nVehicle Management Menu:");
                System.out.println("1. Add Vehicle");
                System.out.println("2. Update Vehicle");
                System.out.println("3. Remove Vehicle");
                System.out.println("4. View Available Vehicles");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Enter vehicle details (Model Make Year Color RegistrationNumber isAvailable DailyRate):");
                        String[] input = scanner.nextLine().split("\\s+");
                        if (input.length != 7) {
                            System.out.println("Error: Please provide all 7 fields.");
                            continue;
                        }
                        Vehicle vehicle = new Vehicle(0, input[0], input[1], Integer.parseInt(input[2]), input[3], input[4], Boolean.parseBoolean(input[5]), Double.parseDouble(input[6]));
                        vehicleDAO.addVehicle(vehicle);
                        System.out.println("Vehicle added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter vehicle ID to update: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter updated details (Model Make Year Color RegistrationNumber isAvailable DailyRate):");
                        String[] updateInput = scanner.nextLine().split("\\s+");
                        if (updateInput.length != 7) {
                            System.out.println("Error: Please provide all 7 fields.");
                            continue;
                        }
                        Vehicle updatedVehicle = new Vehicle(id, updateInput[0], updateInput[1], Integer.parseInt(updateInput[2]), updateInput[3], updateInput[4], Boolean.parseBoolean(updateInput[5]), Double.parseDouble(updateInput[6]));
                        vehicleDAO.updateVehicle(updatedVehicle);
                        System.out.println("Vehicle updated successfully.");
                        break;
                    case 3:
                        System.out.print("Enter vehicle ID to remove: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        vehicleDAO.deleteVehicle(deleteId);
                        System.out.println("Vehicle removed successfully.");
                        break;
                    case 4:
                        System.out.println("Available Vehicles:");
                        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
                        for (Vehicle availableVehicle : vehicles) {
                            System.out.println(availableVehicle);
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter valid numbers.");
            }
        }
    }

    private static void handleReservationManagement() {
        ReservationDAO reservationDAO = new ReservationDAO();

        while (true) {
            try {
                System.out.println("\nReservation Management Menu:");
                System.out.println("1. Create Reservation");
                System.out.println("2. Update Reservation");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. View Reservations by Customer ID");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Enter reservation details (CustomerID VehicleID StartDate(YYYY-MM-DD) EndDate(YYYY-MM-DD)):");
                        String[] input = scanner.nextLine().split("\\s+");
                        if (input.length != 4) {
                            System.out.println("Error: Please provide all 4 fields.");
                            continue;
                        }
                        try {
                            Reservation reservation = new Reservation(
                                    Integer.parseInt(input[0]),
                                    Integer.parseInt(input[1]),
                                    LocalDate.parse(input[2]),
                                    LocalDate.parse(input[3])
                            );
                            reservationDAO.createReservation(reservation);
                            System.out.println("Reservation created successfully.");
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter reservation ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter updated details (CustomerID VehicleID StartDate(YYYY-MM-DD) EndDate(YYYY-MM-DD)):");
                        String[] updateInput = scanner.nextLine().split("\\s+");
                        if (updateInput.length != 4) {
                            System.out.println("Error: Please provide all 4 fields.");
                            continue;
                        }
                        try {
                            Reservation updatedReservation = new Reservation(
                                    Integer.parseInt(updateInput[0]),
                                    Integer.parseInt(updateInput[1]),
                                    LocalDate.parse(updateInput[2]),
                                    LocalDate.parse(updateInput[3])
                            );
                            reservationDAO.updateReservation(updateId, updatedReservation);
                            System.out.println("Reservation updated successfully.");
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Invalid date format. Use YYYY-MM-DD.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter reservation ID to cancel: ");
                        int cancelId = Integer.parseInt(scanner.nextLine());
                        reservationDAO.deleteReservation(cancelId);
                        System.out.println("Reservation cancelled successfully.");
                        break;
                    case 4:
                        System.out.print("Enter customer ID to view reservations: ");
                        int customerId = Integer.parseInt(scanner.nextLine());
                        List<Reservation> reservations = reservationDAO.getReservationsByCustomerId(customerId);
                        if (reservations.isEmpty()) {
                            System.out.println("No reservations found for this customer.");
                        } else {
                            for (Reservation reservation : reservations) {
                                System.out.println(reservation);
                            }
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter valid numbers.");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
