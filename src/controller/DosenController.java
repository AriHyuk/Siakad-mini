/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DosenDAO;
import model.Dosen;
import view.DosenView;
import java.util.List;

/**
 *
 * @author Ari Awaludin
 */
public class DosenController {
    
	private DosenDAO dao;
	private DosenView view;

	public DosenController(DosenView view) {
    	this.view = view;
    	dao = new DosenDAO();
	}

	public void loadDosen() {
    	List<Dosen> list = dao.getAllDosen();
    	view.setDosenList(list);
	}

	public void tambahDosen(Dosen m) {
    	dao.insertDosen(m);
    	loadDosen();
	}
        

	public void ubahDosen(Dosen m) {
    	dao.updateDosen(m);
    	loadDosen();
	}

	public void hapusDosen(int id) {
    	dao.deleteDosen(id);
    	loadDosen();
	}
    
}







