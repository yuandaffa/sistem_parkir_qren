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
