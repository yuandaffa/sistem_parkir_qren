import java.time.LocalDateTime;

public class Tiket implements Validatable {
    private String idTiket;
    private LocalDateTime waktuMasuk;
    private LocalDateTime waktuKeluar;
    private String statusTiket;

    public Tiket() {
        this.idTiket = "";
        this.waktuMasuk = LocalDateTime.now();
        this.waktuKeluar = null;
        this.statusTiket = "AKTIF";
    }

    @Override
    public void validasiTiket() {
        if (idTiket == null || idTiket.isEmpty()) {
            throw new IllegalStateException("ID Tiket tidak boleh kosong.");
        }
        if (waktuMasuk == null) {
            throw new IllegalStateException("Waktu masuk tidak boleh null.");
        }
        System.out.println("Tiket " + idTiket + " valid.");
    }

    public String getIdTiket() { return idTiket; }
    public void setIdTiket(String idTiket) { this.idTiket = idTiket; }

    public LocalDateTime getWaktuMasuk() { return waktuMasuk; }
    public void setWaktuMasuk(LocalDateTime waktuMasuk) { this.waktuMasuk = waktuMasuk; }

    public LocalDateTime getWaktuKeluar() { return waktuKeluar; }
    public void setWaktuKeluar(LocalDateTime waktuKeluar) { this.waktuKeluar = waktuKeluar; }

    public String getStatusTiket() { return statusTiket; }
    public void setStatusTiket(String statusTiket) { this.statusTiket = statusTiket; }
}
