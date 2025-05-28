/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ari Awaludin
 */
import dao.MahasiswaDAO;
import model.Mahasiswa;
import view.MahasiswaView;
import java.util.List;

public class MahasiswaController {
	private MahasiswaDAO dao;
	private MahasiswaView view;

	public MahasiswaController(MahasiswaView view) {
    	this.view = view;
    	dao = new MahasiswaDAO();
	}

	public void loadMahasiswa() {
    	List<Mahasiswa> list = dao.getAllMahasiswa();
    	view.setMahasiswaList(list);
	}

	public void tambahMahasiswa(Mahasiswa m) {
    	dao.insertMahasiswa(m);
    	loadMahasiswa();
	}

	public void ubahMahasiswa(Mahasiswa m) {
    	dao.updateMahasiswa(m);
    	loadMahasiswa();
	}

	public void hapusMahasiswa(int id) {
    	dao.deleteMahasiswa(id);
    	loadMahasiswa();
	}
}

