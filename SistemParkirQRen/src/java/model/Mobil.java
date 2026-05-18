/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asus
 */
public class Mobil extends Kendaraan {
    private String tipeMobil;
    private int jumlahPintu;

    public Mobil(String platNomor, String tipeMobil, int jumlahPintu) {
        super(platNomor, "Mobil");
        this.tipeMobil = tipeMobil;
        this.jumlahPintu = jumlahPintu;
    }

    public String getTipeMobil() { return tipeMobil; }
    public void setTipeMobil(String tipeMobil) { this.tipeMobil = tipeMobil; }

    public int getJumlahPintu() { return jumlahPintu; }
    public void setJumlahPintu(int jumlahPintu) { this.jumlahPintu = jumlahPintu; }
}