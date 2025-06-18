/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ari Awaludin
 */


import dao.JadwalDAO;
import dao.MatkulDAO;
import dao.DosenDAO;
import model.Jadwal;
import model.Matkul;
import model.Dosen;
import view.JadwalView;
import java.util.List;

public class JadwalController {
    private final JadwalDAO dao;
    private final JadwalView view;
    private final MatkulDAO matkulDao;
    private final DosenDAO dosenDao;

    public JadwalController(JadwalView view) {
        this.view = view;
        this.dao = new JadwalDAO();
        this.matkulDao = new MatkulDAO();
        this.dosenDao = new DosenDAO();
    }

    public void tambahJadwal(Jadwal j) {
        try {
            dao.insert(j);
            loadJadwal();
        } catch (Exception ex) {
            showError("Gagal menambah jadwal: " + ex.getMessage());
        }
    }

    public void ubahJadwal(Jadwal j) {
        try {
            dao.update(j);
            loadJadwal();
        } catch (Exception ex) {
            showError("Gagal mengubah jadwal: " + ex.getMessage());
        }
    }

    public void hapusJadwal(int id) {
        try {
            dao.delete(id);
            loadJadwal();
        } catch (Exception ex) {
            showError("Gagal menghapus jadwal: " + ex.getMessage());
        }
    }

    public void loadJadwal() {
        try {
            List<Jadwal> list = dao.getAll();
            view.setJadwalList(list);
        } catch (Exception ex) {
            showError("Gagal mengambil jadwal: " + ex.getMessage());
        }
    }

    // For combo box
    public List<Matkul> getAllMatkul() { return matkulDao.getAll(); }
    public List<Dosen> getAllDosen()   { return dosenDao.getAll(); }

    private void showError(String msg) {
        javax.swing.JOptionPane.showMessageDialog(view, msg);
    }
}

