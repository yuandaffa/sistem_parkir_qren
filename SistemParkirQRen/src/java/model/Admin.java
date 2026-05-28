/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Admin {

    public enum Role {
        ADMIN,
        OPERATOR
    }

    private String idAdmin;
    private String nama;
    private String username;
    private String password; 
    private Role role;

    public Admin(String idAdmin, String nama, String username, String password, Role role) {
        this.idAdmin  = idAdmin;
        this.nama     = nama;
        this.username = username;
        this.password = password;
        this.role     = role;
    }

    public boolean lakukanValidasi(Tiket tiket) {
        if (tiket == null) {
            System.out.println("[Admin] Tiket tidak ditemukan.");
            return false;
        }
        boolean valid = tiket.validasiTiket(); // Memanggil method dari interface Validatable
        if (valid) {
            System.out.println("[Admin] " + nama + " memvalidasi tiket: " + tiket.getIdTiket());
        } else {
            System.out.println("[Admin] Tiket " + tiket.getIdTiket() + " tidak valid atau sudah selesai.");
        }
        return valid;
    }


    public boolean hasRole(Role targetRole) {
        return this.role == targetRole;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Admin{id='" + idAdmin + "', nama='" + nama + "', role=" + role + "}";
    }
}