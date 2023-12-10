package Model;



public class CustomerModel {
    private String nama;
    private String nik;
    private String alamat;
    private String telp;
    private String email;

    public CustomerModel() {
    }

    public CustomerModel(String nama, String nik, String alamat, String telp, String email) {
        this.nama = nama;
        this.nik = nik;
        this.alamat = alamat;
        this.telp = telp;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "nama='" + nama + '\'' +
                ", nik='" + nik + '\'' +
                ", alamat='" + alamat + '\'' +
                ", telp='" + telp + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
