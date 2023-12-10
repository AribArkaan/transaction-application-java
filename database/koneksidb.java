package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class koneksidb {
    Connection connection;
    Statement statement;
    String SQL;
    String url;
    String username;
    String password;

    public koneksidb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection koneksisql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public void tutupKoneksi() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public ResultSet eksekusiQuery(String sql) {
        koneksisql();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println("Error executing query: " + ex.getMessage());
        }
        return resultSet;
    }

    public String eksekusiUpdate(String sql) {
        koneksisql();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            result = ex.toString();
            System.out.println("Error executing update: " + ex.getMessage());
        }
        return result;
    }

    // Fungsi untuk eksekusi query select pada semua kolom
    public ResultSet querySelectAll(String namaTabel) {
        SQL = "SELECT * FROM " + namaTabel;
        return this.eksekusiQuery(SQL);
    }

    // Fungsi untuk eksekusi query select pada semua kolom dengan where
    public ResultSet querySelectAll(String namaTabel, String kondisi) {
        SQL = "SELECT * FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);
    }

    // Fungsi untuk eksekusi query select dengan kolom spesifik
    public ResultSet querySelect(String[] namaKolom, String namaTabel) {
        int i;
        SQL = "SELECT ";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + namaTabel;
        return this.eksekusiQuery(SQL);
    }

    // Fungsi untuk eksekusi query select dengan kolom spesifik dengan where
    public ResultSet querySelect(String[] namaKolom, String namaTabel, String kondisi) {
        int i;
        SQL = "SELECT ";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);
    }

    // Fungsi eksekusi query insert
    public String queryInsert(String namaTabel, String[] isiTabel) {
        int i;
        SQL = "INSERT INTO " + namaTabel + " VALUES(";
        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }
        SQL += ")";
        return this.eksekusiUpdate(SQL);
    }

    // Fungsi eksekusi query insert
    public String queryInsert(String namaTabel, String[] namaKolom, String[] isiTabel) {
        int i;
        SQL = "INSERT INTO " + namaTabel + "(";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }
        SQL += ")";
        return this.eksekusiUpdate(SQL);
    }

    // Fungsi eksekusi query update
    public String queryUpdate(String namaTabel, String[] namaKolom, String[] isiTabel, String kondisi) {
        int i;
        SQL = "UPDATE " + namaTabel + " SET ";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i] + "='" + isiTabel[i] + "'";
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);
    }

    // Fungsi eksekusi query delete
    public String queryDelete(String namaTabel) {
        SQL = "DELETE FROM " + namaTabel;
        return this.eksekusiUpdate(SQL);
    }

    // Fungsi eksekusi query delete dengan where
    public String queryDelete(String namaTabel, String kondisi) {
        SQL = "DELETE FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);
    }
}
