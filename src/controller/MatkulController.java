/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ari Awaludin
 */


import dao.MatkulDAO;
import model.Matkul;
import view.MatkulView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

public class MatkulController {
    private final MatkulDAO dao;
    private final MatkulView view;

    public MatkulController(Connection conn) {
        this.dao = new MatkulDAO(conn);
        this.view = new MatkulView();

        initListener();
        loadMatkul();
        view.setVisible(true);
    }

    private void initListener() {
        view.getBtnTambah().addActionListener(this::tambah);
        view.getBtnUbah().addActionListener(this::ubah);
        view.getBtnHapus().addActionListener(this::hapus);
    }

    public void loadMatkul() {
        try {
            view.setMatkulList(dao.getAll());
        } catch (Exception e) {
            showError("Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambah(ActionEvent e) {
        try {
            String nama = view.getNamaInput();
            int sks     = Integer.parseInt(view.getSksInput());
            dao.insert(new Matkul(0, nama, sks));
            loadMatkul();
            view.clearInput();
        } catch (Exception ex) {
            showError("Gagal menambah data: " + ex.getMessage());
        }
    }

    private void ubah(ActionEvent e) {
        try {
            int id      = view.getSelectedId();
            String nama = view.getNamaInput();
            int sks     = Integer.parseInt(view.getSksInput());
            dao.update(new Matkul(id, nama, sks));
            loadMatkul();
            view.clearInput();
        } catch (Exception ex) {
            showError("Gagal mengubah data: " + ex.getMessage());
        }
    }

    private void hapus(ActionEvent e) {
        try {
            int id = view.getSelectedId();
            if (id != -1) {
                dao.delete(id);
                loadMatkul();
                view.clearInput();
            }
        } catch (Exception ex) {
            showError("Gagal menghapus data: " + ex.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

