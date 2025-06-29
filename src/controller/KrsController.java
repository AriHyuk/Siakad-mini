/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.KrsDAO;
import model.Krs;
import view.KrsView;

import java.util.List;

/**
 *
 * @author Ari Awaludin
 */






public class KrsController {
    private final KrsView view;
    private final KrsDAO dao = new KrsDAO();

    public KrsController(KrsView view) {
        this.view = view;
    }

    public void loadData() {
        List<Krs> list = dao.getAllKrs();
        view.setKrsList(list);
    }

    public void tambahKrs(Krs k) {
        dao.insertKrs(k);
        loadData();
    }

    public void ubahKrs(Krs k) {
        dao.updateKrs(k);
        loadData();
    }

    public void hapusKrs(int id) {
        dao.deleteKrs(id);
        loadData();
    }
}


