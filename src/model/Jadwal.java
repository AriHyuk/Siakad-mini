/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ari Awaludin
 */


public class Jadwal {
    private int id;
    private int idMatkul;
    private int idDosen;
    private String ruang;
    private String waktu;

    public Jadwal() {}

    public Jadwal(int id, int idMatkul, int idDosen, String ruang, String waktu) {
        this.id = id;
        this.idMatkul = idMatkul;
        this.idDosen = idDosen;
        this.ruang = ruang;
        this.waktu = waktu;
    }
    
    

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdMatkul() { return idMatkul; }
    public void setIdMatkul(int idMatkul) { this.idMatkul = idMatkul; }

    public int getIdDosen() { return idDosen; }
    public void setIdDosen(int idDosen) { this.idDosen = idDosen; }

    public String getRuang() { return ruang; }
    public void setRuang(String ruang) { this.ruang = ruang; }

    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
    
    @Override
    public String toString() {
    return "Jadwal ID " + id + " - Ruang " + ruang + ", " + waktu;
}

}

