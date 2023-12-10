package Model;

public class CarModel {
    private int idMobil;
    private String merk;
    private String tipe;
    private int tahun;
    private String nopol;
    private int harga;
    private String status;

    public CarModel() {
    }

    public CarModel(int idMobil, String merk, String tipe, int tahun, String nopol, int harga, String status) {
        this.idMobil = idMobil;
        this.merk = merk;
        this.tipe = tipe;
        this.tahun = tahun;
        this.nopol = nopol;
        this.harga = harga;
        this.status = status;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "idMobil=" + idMobil +
                ", merk='" + merk + '\'' +
                ", tipe='" + tipe + '\'' +
                ", tahun=" + tahun +
                ", nopol='" + nopol + '\'' +
                ", harga=" + harga +
                ", status='" + status + '\'' +
                '}';
    }
}
