/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemParkir {
    private List<Kendaraan> daftarKendaraan;
    private List<Tiket> daftarTiket;
    private int kapasitasMaks;

    // Tarif per jam (dalam rupiah)
    private static final double TARIF_MOBIL = 5000;
    private static final double TARIF_MOTOR = 2000;

    public SistemParkir(int kapasitasMaks) {
        this.kapasitasMaks = kapasitasMaks;
        this.daftarKendaraan = new ArrayList<>();
        this.daftarTiket = new ArrayList<>();
    }

    /**
     * Menghitung biaya parkir berdasarkan durasi waktu pada tiket.
     */
    public double hitungBiaya(Tiket tiket) {
        if (tiket.getWaktuKeluar() == null) {
            tiket.setWaktuKeluar(LocalDateTime.now());
        }
        long menitParkir = Duration.between(tiket.getWaktuMasuk(), tiket.getWaktuKeluar()).toMinutes();
        long jamParkir = Math.max(1, (long) Math.ceil(menitParkir / 60.0));

        // Cari kendaraan yang terkait tiket ini
        Kendaraan kendaraan = daftarKendaraan.stream()
                .filter(k -> k != null)
                .findFirst()
                .orElse(null);

        double tarif = TARIF_MOBIL; // default
        if (kendaraan instanceof Motor) {
            tarif = TARIF_MOTOR;
        }

        return jamParkir * tarif;
    }

    /**
     * Membuat objek Pembayaran dari jumlah yang dihitung.
     */
    public Pembayaran generatePembayaran(double jumlah) {
        return new QRPayment(jumlah);
    }

    public List<Kendaraan> getDaftarKendaraan() { return daftarKendaraan; }
    public List<Tiket> getDaftarTiket() { return daftarTiket; }

    /**
     * Mencatat kendaraan masuk ke sistem parkir beserta tiketnya.
     */
    public void kendaraanMasuk(Kendaraan kendaraan, Tiket tiket) {
        if (!cekKapasitas()) {
            System.out.println("Parkir penuh! Kapasitas maksimum: " + kapasitasMaks);
            return;
        }
        tiket.validasiTiket();
        daftarKendaraan.add(kendaraan);
        daftarTiket.add(tiket);
        System.out.println("Kendaraan " + kendaraan.getPlatNomor() + " berhasil masuk.");
        System.out.println("Tiket ID: " + tiket.getIdTiket());
    }

    /**
     * Mengecek apakah masih ada slot parkir tersedia.
     */
    public boolean cekKapasitas() {
        return hitungKendaraanAktif() < kapasitasMaks;
    }

    /**
     * Menghitung jumlah kendaraan yang masih aktif di parkiran.
     */
    public int hitungKendaraanAktif() {
        int aktif = 0;
        for (Tiket t : daftarTiket) {
            if ("AKTIF".equals(t.getStatusTiket())) {
                aktif++;
            }
        }
        return aktif;
    }

    /**
     * Menambah kendaraan baru secara interaktif via console.
     */
    public void tambahKendaraan() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan plat nomor: ");
        String platNomor = scanner.nextLine().trim();

        System.out.print("Jenis kendaraan (1=Mobil, 2=Motor): ");
        int jenis = Integer.parseInt(scanner.nextLine().trim());

        Kendaraan kendaraan;
        if (jenis == 1) {
            System.out.print("Tipe mobil (e.g., SUV, Sedan): ");
            String tipeMobil = scanner.nextLine().trim();
            System.out.print("Jumlah pintu: ");
            int jumlahPintu = Integer.parseInt(scanner.nextLine().trim());
            kendaraan = new Mobil(platNomor, tipeMobil, jumlahPintu);
        } else {
            System.out.print("Tipe motor (e.g., Matic, Manual): ");
            String tipeMotor = scanner.nextLine().trim();
            kendaraan = new Motor(platNomor, tipeMotor);
        }

        Tiket tiket = new Tiket();
        tiket.setIdTiket("TKT-" + (daftarTiket.size() + 1));
        tiket.setWaktuMasuk(LocalDateTime.now());

        kendaraanMasuk(kendaraan, tiket);
    }

    // ========== MAIN untuk demo ==========
    public static void main(String[] args) {
        System.out.println("=== SISTEM PARKIR ===\n");

        SistemParkir sistem = new SistemParkir(10);
        Admin admin = new Admin("ADM-001", "Budi Santoso");
        System.out.println("Admin: " + admin.getNama() + " (ID: " + admin.getIdAdmin() + ")");
        System.out.println("Kapasitas parkir: 10 slot\n");

        // Kendaraan 1: Mobil
        Mobil mobil1 = new Mobil("B 1234 ABC", "SUV", 4);
        Tiket tiket1 = new Tiket();
        tiket1.setIdTiket("TKT-001");
        tiket1.setWaktuMasuk(LocalDateTime.now().minusHours(2)); // masuk 2 jam lalu

        sistem.kendaraanMasuk(mobil1, tiket1);

        // Kendaraan 2: Motor
        Motor motor1 = new Motor("D 5678 XYZ", "Matic");
        Tiket tiket2 = new Tiket();
        tiket2.setIdTiket("TKT-002");
        tiket2.setWaktuMasuk(LocalDateTime.now().minusMinutes(90)); // masuk 1.5 jam lalu

        sistem.kendaraanMasuk(motor1, tiket2);

        System.out.println("\nKendaraan aktif: " + sistem.hitungKendaraanAktif());
        System.out.println("Slot tersisa: " + (10 - sistem.hitungKendaraanAktif()));

        // Proses keluar mobil
        System.out.println("\n--- Proses Keluar: " + mobil1.getPlatNomor() + " ---");
        tiket1.setWaktuKeluar(LocalDateTime.now());
        tiket1.setStatusTiket("SELESAI");

        double biaya = sistem.hitungBiaya(tiket1);
        System.out.println("Durasi parkir: 2 jam");
        System.out.printf("Biaya parkir: Rp%.0f%n", biaya);

        Pembayaran pembayaran = sistem.generatePembayaran(biaya);
        pembayaran.prosesPembayaran();

        System.out.println("\nKendaraan aktif setelah keluar: " + sistem.hitungKendaraanAktif());
    }
}

