/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
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

