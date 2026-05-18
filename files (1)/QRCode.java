public class QRCode {
    private String dataQR;

    public QRCode(String dataQR) {
        this.dataQR = dataQR;
    }

    public String getDataQR() { return dataQR; }

    public void tampilkanQR() {
        System.out.println("=== QR CODE ===");
        System.out.println("Data: " + dataQR);
        System.out.println("===============");
    }
}
