/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;


/**
 *
 * @author Ari Awaludin
 */


public class MainView extends JFrame {

    private final JButton btnDosen     = new JButton("Dosen");
    private final JButton btnMahasiswa = new JButton("Mahasiswa");
    private final JButton btnMatkul    = new JButton("Matkul");   // NEW

    public MainView() {
        setTitle("Menu Utama");
        setSize(320, 220);               // dinaikkan sedikit biar muat 3 tombol
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // ── Posisi tombol ─────────────────────────────
        btnDosen.setBounds    (100,  40, 120, 30);
        btnMahasiswa.setBounds(100,  85, 120, 30);
        btnMatkul.setBounds   (100, 130, 120, 30);  // posisi baru

        add(btnDosen);
        add(btnMahasiswa);
        add(btnMatkul);                   // NEW

        // ── Event-handler ─────────────────────────────
        btnDosen.addActionListener(e -> {
            new DosenView().setVisible(true);
            dispose();
        });

        btnMahasiswa.addActionListener(e -> {
            new MahasiswaView().setVisible(true);
            dispose();
        });

        btnMatkul.addActionListener(e -> {            // NEW
            new MatkulView().setVisible(true);
            dispose();
        });
    }
}

