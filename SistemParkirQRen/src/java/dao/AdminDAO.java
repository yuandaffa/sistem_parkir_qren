package dao;

import model.Admin;
import classes.JDBC;

import java.sql.*;


public class AdminDAO {


    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // TODO: ganti dengan BCrypt.checkpw() di produksi

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAdmin(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[AdminDAO] Error login: " + e.getMessage());
        }
        return null;
    }


    public Admin findById(String idAdmin) {
        String sql = "SELECT * FROM admin WHERE id_admin = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idAdmin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAdmin(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[AdminDAO] Error findById: " + e.getMessage());
        }
        return null;
    }


    public boolean save(Admin admin) {
        String sql = "INSERT INTO admin (id_admin, nama, username, password, role) VALUES (?,?,?,?,?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getIdAdmin());
            stmt.setString(2, admin.getNama());
            stmt.setString(3, admin.getUsername());
            stmt.setString(4, admin.getPassword());
            stmt.setString(5, admin.getRole().name());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[AdminDAO] Error save: " + e.getMessage());
            return false;
        }
    }

  
    private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
        return new Admin(
                rs.getString("id_admin"),
                rs.getString("nama"),
                rs.getString("username"),
                rs.getString("password"),
                Admin.Role.valueOf(rs.getString("role"))
        );
    }
}