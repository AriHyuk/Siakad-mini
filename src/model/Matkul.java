/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ari Awaludin
 */



public class Matkul {
    private int id;
    private String kodeMk;
    private String nama;
    private int sks;

    public Matkul() {}

    public Matkul(int id, String kodeMk, String nama, int sks) {
        this.id = id;
        this.kodeMk = kodeMk;
        this.nama = nama;
        this.sks = sks;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getKodeMk() { return kodeMk; }
    public void setKodeMk(String kodeMk) { this.kodeMk = kodeMk; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }
}


