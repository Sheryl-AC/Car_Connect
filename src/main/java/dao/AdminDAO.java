package dao;
import entity.Admin;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // Create a new admin
    public void addAdmin(Admin admin) {
        String query = "INSERT INTO admin (username, password, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getLastName());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve admin by ID
    public Admin getAdminById(int adminId) {
        String query = "SELECT * FROM admin WHERE admin_id = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve admin by username
    public Admin getAdminByUsername(String username) {
        String query = "SELECT * FROM admin WHERE username = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update admin details
    public void updateAdmin(Admin admin) {
        String query = "UPDATE admin SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?, phone_number = ?, is_active = ? WHERE admin_id = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getLastName());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getPhoneNumber());
            preparedStatement.setBoolean(7, admin.isActive());
            preparedStatement.setInt(8, admin.getAdminID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an admin by ID
    public void deleteAdmin(int adminId) {
        String query = "DELETE FROM admin WHERE admin_id = ?";
        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all admins
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM admin";

        try (Connection connection = DBConnUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                admins.add(mapResultSetToAdmin(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Helper method to map ResultSet to Admin entity
    private Admin mapResultSetToAdmin(ResultSet resultSet) throws SQLException {
        return new Admin(
                resultSet.getInt("admin_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getTimestamp("registration_date"),
                resultSet.getTimestamp("last_login"),
                resultSet.getBoolean("is_active")
        );
    }
}

