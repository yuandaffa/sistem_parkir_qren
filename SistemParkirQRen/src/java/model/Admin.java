/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Admin {
    private String idAdmin;
    private String nama;

    public Admin(String idAdmin, String nama) {
        this.idAdmin = idAdmin;
        this.nama = nama;
    }

    public String getIdAdmin() { return idAdmin; }
    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}

