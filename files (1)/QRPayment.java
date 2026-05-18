public class QRPayment extends Pembayaran {
    private QRCode qrCode;

    public QRPayment(double jumlahBayar) {
        super(jumlahBayar);
        this.qrCode = null;
    }

    public void generateQR() {
        String dataQR = "PAY-" + (long)(Math.random() * 1_000_000) + "-IDR" + (long)getJumlahBayar();
        this.qrCode = new QRCode(dataQR);
        System.out.println("QR Code berhasil digenerate: " + dataQR);
    }

    public QRCode getQrCode() { return qrCode; }

    @Override
    public void prosesPembayaran() {
        if (qrCode == null) {
            generateQR();
        }
        System.out.println("Memproses pembayaran QR sebesar Rp" + getJumlahBayar());
        qrCode.tampilkanQR();
        setStatusBayar("LUNAS");
        System.out.println("Pembayaran berhasil! Status: " + getStatusBayar());
    }
}
