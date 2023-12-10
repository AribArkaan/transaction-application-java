package Model;

import java.util.Date;

public class ModelTransaksi {
    private int id_transaksi;
    private String peminjam;
    private String nopol;
    private Date tgl_pinjaman;
    private Date tgl_kembali;
    private int harga;
    private int lama;
    private int total;

    public ModelTransaksi() {
    }

    public ModelTransaksi(int id_transaksi, String peminjam, String nopol, Date tgl_pinjaman, Date tgl_kembali, int harga, int lama, int total) {
        this.id_transaksi = id_transaksi;
        this.peminjam = peminjam;
        this.nopol = nopol;
        this.tgl_pinjaman = tgl_pinjaman;
        this.tgl_kembali = tgl_kembali;
        this.harga = harga;
        this.lama = lama;
        this.total = total;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(String peminjam) {
        this.peminjam = peminjam;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public Date getTgl_pinjaman() {
        return tgl_pinjaman;
    }

    public void setTgl_pinjaman(Date tgl_pinjaman) {
        this.tgl_pinjaman = tgl_pinjaman;
    }

    public Date getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(Date tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getLama() {
        return lama;
    }

    public void setLama(int lama) {
        this.lama = lama;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ModelTransaksi{" +
                "id_transaksi=" + id_transaksi +
                ", peminjam='" + peminjam + '\'' +
                ", nopol='" + nopol + '\'' +
                ", tgl_pinjaman=" + tgl_pinjaman +
                ", tgl_kembali=" + tgl_kembali +
                ", harga=" + harga +
                ", lama=" + lama +
                ", total=" + total +
                '}';
    }
}
