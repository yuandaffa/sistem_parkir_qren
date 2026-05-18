public class Admin {
    private String idAdmin;
    private String nama;

    public Admin(String idAdmin, String nama) {
        this.idAdmin = idAdmin;
        this.nama = nama;
    }

    public String getIdAdmin() { return idAdmin; }
    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}
