package dao;
import entity.Customer;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Create a new customer
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (firstName, lastName, email, phoneNumber, address, username, password, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getUsername());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.setString(8,customer.getRegistrationDate());
            preparedStatement.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding customer to the database.");
        }
    }

    // Retrieve a customer by ID
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            } else {
                System.out.println("Customer not found with ID: " + customerId);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving customer from the database.");
        }
    }

    // Update a customer
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, address = ?, username = ?, password = ? WHERE customerID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getUsername());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.setInt(8, customer.getCustomerID());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("No customer found with ID: " + customer.getCustomerID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating customer in the database.");
        }
    }

    // Delete a customer
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, customerId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("No customer found with ID: " + customerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting customer from the database.");
        }
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving customers from the database.");
        }

        return customers;
    }

    // Helper method to map ResultSet to Customer object
    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(resultSet.getInt("CustomerID"));
        customer.setFirstName(resultSet.getString("FirstName"));
        customer.setLastName(resultSet.getString("LastName"));
        customer.setEmail(resultSet.getString("Email"));
        customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
        customer.setAddress(resultSet.getString("Address"));
        customer.setUsername(resultSet.getString("Username"));
        customer.setPassword(resultSet.getString("Password"));
        customer.setRegistrationDate(resultSet.getString("RegistrationDate"));
        return customer;
    }
}
