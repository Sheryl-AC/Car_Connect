package dao;
import entity.Admin;

public interface IAdminService {
    Admin getAdminById(int adminId);             // Retrieve admin details by ID
    Admin getAdminByUsername(String username);   // Retrieve admin details by username
    void registerAdmin(Admin admin);             // Register a new admin
    void updateAdmin(Admin admin);               // Update admin details
    void deleteAdmin(int adminId);               // Delete an admin by ID
}
