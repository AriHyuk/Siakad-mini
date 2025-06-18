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



/**
 * @author Ari Awaludin
 */
public class MainView extends JFrame {

    private final JButton btnDosen     = new JButton("Dosen");
    private final JButton btnMahasiswa = new JButton("Mahasiswa");
    private final JButton btnMatkul    = new JButton("Matkul");
    private final JButton btnJadwal    = new JButton("Jadwal");    // NEW

    public MainView() {
        setTitle("Menu Utama");
        setSize(320, 270); // dinaikkan biar muat 4 tombol
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // ── Posisi tombol ─────────────────────────────
        btnDosen.setBounds    (100,  30, 120, 30);
        btnMahasiswa.setBounds(100,  70, 120, 30);
        btnMatkul.setBounds   (100, 110, 120, 30);
        btnJadwal.setBounds   (100, 150, 120, 30);  // posisi baru

        add(btnDosen);
        add(btnMahasiswa);
        add(btnMatkul);
        add(btnJadwal);  // NEW

        // ── Event-handler ─────────────────────────────
        btnDosen.addActionListener(e -> {
            new DosenView().setVisible(true);
            dispose();
        });

        btnMahasiswa.addActionListener(e -> {
            new MahasiswaView().setVisible(true);
            dispose();
        });

        btnMatkul.addActionListener(e -> {
            new MatkulView().setVisible(true);
            dispose();
        });

        btnJadwal.addActionListener(e -> {
            new JadwalView().setVisible(true);
            dispose();
        });
    }
}


