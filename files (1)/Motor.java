public class Motor extends Kendaraan {
    private String tipeMotor;

    public Motor(String platNomor, String tipeMotor) {
        super(platNomor, "Motor");
        this.tipeMotor = tipeMotor;
    }

    public String getTipeMotor() { return tipeMotor; }
    public void setTipeMotor(String tipeMotor) { this.tipeMotor = tipeMotor; }
}
