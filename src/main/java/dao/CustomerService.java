package dao;

import entity.Customer;
import exception.InvalidInputException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService implements ICustomerService {

    // Thread-safe storage for customer data
    private static final Map<Integer, Customer> customerDatabase = new ConcurrentHashMap<>();

    @Override
    public Customer getCustomerById(int customerId) {
        // Validate input and retrieve the customer if it exists
        if (!customerDatabase.containsKey(customerId)) {
            throw new InvalidInputException("Customer not found with ID: " + customerId);
        }
        return customerDatabase.get(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new InvalidInputException("Username cannot be null or blank.");
        }
        return customerDatabase.values()
                .stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException("Customer not found with username: " + username));
    }

    @Override
    public void registerCustomer(Customer customer) {
        // Validate the customer object
        if (customer == null) {
            throw new InvalidInputException("Cannot register a null customer.");
        }
        if (customerDatabase.containsKey(customer.getCustomerID())) {
            throw new InvalidInputException("Customer with ID " + customer.getCustomerID() + " already exists.");
        }
        customerDatabase.put(customer.getCustomerID(), customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        // Ensure the customer exists before updating
        if (customer == null) {
            throw new InvalidInputException("Cannot update a null customer.");
        }
        if (!customerDatabase.containsKey(customer.getCustomerID())) {
            throw new InvalidInputException("Customer with ID " + customer.getCustomerID() + " not found.");
        }
        customerDatabase.put(customer.getCustomerID(), customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        // Ensure the customer exists before deleting
        if (!customerDatabase.containsKey(customerId)) {
            throw new InvalidInputException("Customer not found with ID: " + customerId);
        }
        customerDatabase.remove(customerId);
    }
}

