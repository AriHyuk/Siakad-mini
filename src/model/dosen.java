package model;

public class Dosen {
    private int id_dosen;
    private String nama;
    private String alamat;

    // Constructor
    public Dosen() {}

    public Dosen(int id, String nama, String alamat) {
        this.id_dosen = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Getter dan Setter
    public int getId() {
        return id_dosen;
    }

    public void setId(int id) {
        this.id_dosen = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
