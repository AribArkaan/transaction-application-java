package Model;

public class UserModel {
    private int idUser;
    private String nama;
    private String username;
    private String password;
    private String hakAkses;

    public UserModel() {
    }

    public UserModel(int idUser, String nama, String username, String password, String hakAkses) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.hakAkses = hakAkses;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "idUser=" + idUser +
                ", nama='" + nama + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hakAkses='" + hakAkses + '\'' +
                '}';
    }
}
