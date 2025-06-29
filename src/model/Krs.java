/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ari Awaludin
 */


public class Krs {
    private int id;
    private int idMahasiswa;
    private int idJadwal;
    private String semester;

    public Krs(int id, int idMahasiswa, int idJadwal, String semester) {
        this.id = id;
        this.idMahasiswa = idMahasiswa;
        this.idJadwal = idJadwal;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public int getIdJadwal() {
        return idJadwal;
    }

    public String getSemester() {
        return semester;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}


