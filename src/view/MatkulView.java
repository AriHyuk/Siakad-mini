/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ari Awaludin
 */



import model.Matkul;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class MatkulView extends JFrame {

    // --- Komponen input ---
    private final JTextField txtNama = new JTextField();
    private final JTextField txtSks  = new JTextField();

    // --- Tombol aksi ---
    private final JButton btnTambah = new JButton("Tambah");
    private final JButton btnUbah   = new JButton("Ubah");
    private final JButton btnHapus  = new JButton("Hapus");

    // --- Tabel ---
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID Matkul", "Nama Matkul", "SKS"}, 0);
    private final JTable table = new JTable(model);

    // --- Konstruktor GUI ---
    public MatkulView() {
        setTitle("Data Mata Kuliah");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Label & TextField
        add(new JLabel("Nama Matkul")).setBounds(20, 30, 100, 25);
        txtNama.setBounds(130, 30, 180, 25); add(txtNama);

        add(new JLabel("SKS")).setBounds(20, 70, 100, 25);
        txtSks.setBounds(130, 70, 180, 25);  add(txtSks);

        // Tombol
        btnTambah.setBounds(340, 30, 90, 25); add(btnTambah);
        btnUbah.setBounds  (340, 70, 90, 25); add(btnUbah);
        btnHapus.setBounds (340,110, 90, 25); add(btnHapus);

        // Tabel
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 150, 540, 210);
        add(sp);

        // --- Event klik baris tabel → isi ke form ---
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                txtNama.setText(model.getValueAt(r, 1).toString());
                txtSks .setText(model.getValueAt(r, 2).toString());
            }
        });
    }

    /* ====================  Getter util dipakai Controller  ==================== */
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnUbah()   { return btnUbah;   }
    public JButton getBtnHapus()  { return btnHapus;  }

    public String getNamaInput()  { return txtNama.getText(); }
    public String getSksInput()   { return txtSks.getText();  }

    public void clearInput() {
        txtNama.setText("");
        txtSks.setText("");
    }

    /** Kembalikan id matkul ter-pilih, atau -1 jika belum memilih. */
    public int getSelectedId() {
        int row = table.getSelectedRow();
        return (row >= 0) ? Integer.parseInt(model.getValueAt(row, 0).toString()) : -1;
    }

    /** Render daftar matkul ke tabel */
    public void setMatkulList(List<Matkul> list) {
        model.setRowCount(0);
        list.forEach(m -> model.addRow(new Object[]{m.getId(), m.getNama(), m.getSks()}));
    }
}



