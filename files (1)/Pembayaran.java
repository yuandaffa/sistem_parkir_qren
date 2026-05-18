public abstract class Pembayaran {
    private double jumlahBayar;
    private String statusBayar;

    public Pembayaran(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
        this.statusBayar = "PENDING";
    }

    public abstract void prosesPembayaran();

    public String getStatusBayar() { return statusBayar; }
    public void setStatusBayar(String statusBayar) { this.statusBayar = statusBayar; }

    public double getJumlahBayar() { return jumlahBayar; }
    public void setJumlahBayar(double jumlahBayar) { this.jumlahBayar = jumlahBayar; }
}
