package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ari Awaludin
 */
public class Mahasiswa {
	private int id;
	private String nim;
	private String nama;
	private String jurusan;

	// Constructor, Getter & Setter
	public Mahasiswa() {}
	public Mahasiswa(int id, String nim, String nama, String jurusan) {
    	this.id = id;
    	this.nim = nim;
    	this.nama = nama;
    	this.jurusan = jurusan;
	}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNim() { return nim; }
	public void setNim(String nim) { this.nim = nim; }
	public String getNama() { return nama; }
	public void setNama(String nama) { this.nama = nama; }
	public String getJurusan() { return jurusan; }
	public void setJurusan(String jurusan) { this.jurusan = jurusan; }
        
        @Override
        public String toString() {
        return nama + " (" + nim + ")";
}

}

