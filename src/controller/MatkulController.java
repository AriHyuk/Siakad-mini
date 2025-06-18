package controller;

import dao.MatkulDAO;
import model.Matkul;
import view.MatkulView;
import java.util.List;

public class MatkulController {
    private final MatkulDAO dao;
    private final MatkulView view;

    public MatkulController(MatkulView view) {
        this.view = view;
        this.dao = new MatkulDAO();
    }

    public void tambahMatkul(Matkul m) {
        try {
            dao.insert(m);
            loadMatkul();
        } catch (Exception ex) {
            showError("Gagal menambah data: " + ex.getMessage());
        }
    }

    public void ubahMatkul(Matkul m) {
        try {
            dao.update(m);
            loadMatkul();
        } catch (Exception ex) {
            showError("Gagal mengubah data: " + ex.getMessage());
        }
    }

    public void hapusMatkul(int id) {
        try {
            dao.delete(id);
            loadMatkul();
        } catch (Exception ex) {
            showError("Gagal menghapus data: " + ex.getMessage());
        }
    }

    public void loadMatkul() {
        try {
            List<Matkul> list = dao.getAll();
            view.setMatkulList(list);
        } catch (Exception ex) {
            showError("Gagal mengambil data: " + ex.getMessage());
        }
    }

    private void showError(String msg) {
        javax.swing.JOptionPane.showMessageDialog(view, msg);
    }
}
