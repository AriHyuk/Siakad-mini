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
    private String nama;
    private int sks;

    public Matkul() {}

    public Matkul(int id, String nama, int sks) {
        this.id   = id;
        this.nama = nama;
        this.sks  = sks;
    }

    // getter & setter
    public int getId()           { return id;   }
    public void setId(int id)    { this.id = id; }

    public String getNama()              { return nama; }
    public void   setNama(String nama)   { this.nama = nama; }

    public int getSks()          { return sks;  }
    public void setSks(int sks)  { this.sks = sks; }
}

