package dao;
import entity.Admin;
import exception.AdminNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdminService implements IAdminService {
    private static Map<Integer, Admin> adminDatabase = new ConcurrentHashMap<>();

    @Override
    public Admin getAdminById(int adminId) {
        if (!adminDatabase.containsKey(adminId)) {
            throw new AdminNotFoundException("Admin not found.");
        }
        return adminDatabase.get(adminId);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDatabase.values()
                .stream()
                .filter(admin -> admin.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new AdminNotFoundException("Admin not found."));
    }

    @Override
    public void registerAdmin(Admin admin) {
        adminDatabase.put(admin.getAdminID(), admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        if (!adminDatabase.containsKey(admin.getAdminID())) {
            throw new AdminNotFoundException("Admin not found.");
        }
        adminDatabase.put(admin.getAdminID(), admin);
    }

    @Override
    public void deleteAdmin(int adminId) {
        if (!adminDatabase.containsKey(adminId)) {
            throw new AdminNotFoundException("Admin not found.");
        }
        adminDatabase.remove(adminId);
    }
}

