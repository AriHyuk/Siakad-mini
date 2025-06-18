/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ari Awaludin
 */


import controller.JadwalController;
import model.Jadwal;
import model.Matkul;
import model.Dosen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class JadwalView extends JFrame {
    private JComboBox<String> cmbMatkul = new JComboBox<>();
    private JComboBox<String> cmbDosen  = new JComboBox<>();
    private JTextField txtRuang = new JTextField();
    private JTextField txtWaktu = new JTextField();
    private JButton btnTambah = new JButton("Tambah");
    private JButton btnUbah   = new JButton("Ubah");
    private JButton btnHapus  = new JButton("Hapus");
    private JTable table      = new JTable();
    private DefaultTableModel model;
    private JadwalController controller;

    // Keep list for id mapping
    private List<Matkul> listMatkul;
    private List<Dosen> listDosen;
    private int idTerpilih = -1;

    public JadwalView() {
        setTitle("Data Jadwal");
        setSize(700, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        add(new JLabel("Mata Kuliah")).setBounds(20, 30, 100, 25);
        cmbMatkul.setBounds(130, 30, 200, 25); add(cmbMatkul);

        add(new JLabel("Dosen")).setBounds(20, 70, 100, 25);
        cmbDosen.setBounds(130, 70, 200, 25); add(cmbDosen);

        add(new JLabel("Ruang")).setBounds(20, 110, 100, 25);
        txtRuang.setBounds(130, 110, 200, 25); add(txtRuang);

        add(new JLabel("Waktu")).setBounds(20, 150, 100, 25);
        txtWaktu.setBounds(130, 150, 200, 25); add(txtWaktu);

        btnTambah.setBounds(360, 30, 90, 25); add(btnTambah);
        btnUbah.setBounds  (360, 70, 90, 25); add(btnUbah);
        btnHapus.setBounds (360,110, 90, 25); add(btnHapus);

        model = new DefaultTableModel(new Object[] {
            "ID", "Matkul", "Dosen", "Ruang", "Waktu"
        }, 0);
        table.setModel(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 640, 150);
        add(sp);

        controller = new JadwalController(this);
        refreshCombo();

        controller.loadJadwal();

        btnTambah.addActionListener(e -> {
            try {
                int idMatkul = listMatkul.get(cmbMatkul.getSelectedIndex()).getId();
                int idDosen  = listDosen.get(cmbDosen.getSelectedIndex()).getId();
                String ruang = txtRuang.getText();
                String waktu = txtWaktu.getText();
                Jadwal j = new Jadwal(0, idMatkul, idDosen, ruang, waktu);
                controller.tambahJadwal(j);
                clearInput();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
            }
        });

        btnUbah.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && idTerpilih != -1) {
                try {
                    int idMatkul = listMatkul.get(cmbMatkul.getSelectedIndex()).getId();
                    int idDosen  = listDosen.get(cmbDosen.getSelectedIndex()).getId();
                    String ruang = txtRuang.getText();
                    String waktu = txtWaktu.getText();
                    Jadwal j = new Jadwal(idTerpilih, idMatkul, idDosen, ruang, waktu);
                    controller.ubahJadwal(j);
                    clearInput();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
                }
            }
        });

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && idTerpilih != -1) {
                try {
                    controller.hapusJadwal(idTerpilih);
                    clearInput();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus: " + ex.getMessage());
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                idTerpilih = Integer.parseInt(model.getValueAt(row, 0).toString());
                cmbMatkul.setSelectedItem(model.getValueAt(row, 1).toString());
                cmbDosen.setSelectedItem(model.getValueAt(row, 2).toString());
                txtRuang.setText(model.getValueAt(row, 3).toString());
                txtWaktu.setText(model.getValueAt(row, 4).toString());
            }
        });
    }

    // Isi list jadwal ke tabel
    public void setJadwalList(List<Jadwal> list) {
        model.setRowCount(0);
        for (Jadwal j : list) {
            // ambil nama matkul & dosen berdasarkan id
            String namaMatkul = "", namaDosen = "";
            for (Matkul m : listMatkul)
                if (m.getId() == j.getIdMatkul())
                    namaMatkul = m.getKodeMk() + " - " + m.getNama();
            for (Dosen d : listDosen)
                if (d.getId() == j.getIdDosen())
                    namaDosen = d.getNama();
            model.addRow(new Object[]{
                j.getId(), namaMatkul, namaDosen, j.getRuang(), j.getWaktu()
            });
        }
    }

    private void clearInput() {
        idTerpilih = -1;
        txtRuang.setText("");
        txtWaktu.setText("");
        table.clearSelection();
    }

    private void refreshCombo() {
        // Isi ComboBox matkul
        listMatkul = controller.getAllMatkul();
        cmbMatkul.removeAllItems();
        for (Matkul m : listMatkul) {
            cmbMatkul.addItem(m.getKodeMk() + " - " + m.getNama());
        }
        // Isi ComboBox dosen
        listDosen = controller.getAllDosen();
        cmbDosen.removeAllItems();
        for (Dosen d : listDosen) {
            cmbDosen.addItem(d.getNama());
        }
    }
}

