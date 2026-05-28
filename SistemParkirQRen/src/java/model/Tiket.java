/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Tiket implements Validatable {


    public enum StatusTiket {
        AKTIF, SELESAI, BATAL
    }


    private String idTiket;
    private LocalDateTime waktuMasuk;
    private LocalDateTime waktuKeluar;
    private StatusTiket statusTiket;

    private Kendaraan kendaraan;


    public Tiket(String idTiket, Kendaraan kendaraan) {
        this.idTiket = idTiket;
        this.kendaraan = kendaraan;
        this.waktuMasuk = LocalDateTime.now();  
        this.statusTiket = StatusTiket.AKTIF;  
        this.waktuKeluar = null;
    }


    @Override
    public boolean validasiTiket() {
        return idTiket != null
                && !idTiket.isBlank()
                && statusTiket == StatusTiket.AKTIF;
    }


    public long hitungDurasi() {
        LocalDateTime akhir = (waktuKeluar != null) ? waktuKeluar : LocalDateTime.now();
        return ChronoUnit.MINUTES.between(waktuMasuk, akhir);
    }

    public void checkout() {
        this.waktuKeluar = LocalDateTime.now();
        this.statusTiket = StatusTiket.SELESAI;
    }


    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(LocalDateTime waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public LocalDateTime getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(LocalDateTime waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public StatusTiket getStatusTiket() {
        return statusTiket;
    }

    public void setStatusTiket(StatusTiket statusTiket) {
        this.statusTiket = statusTiket;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }


    @Override
    public String toString() {
        return "Tiket{id='" + idTiket + "', status=" + statusTiket
                + ", masuk=" + waktuMasuk + "}";
    }
}