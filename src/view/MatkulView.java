package view;

import controller.MatkulController;
import model.Matkul;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class MatkulView extends JFrame {
    private JTextField txtKodeMk = new JTextField();
    private JTextField txtNama = new JTextField();
    private JTextField txtSks  = new JTextField();
    private JButton btnTambah  = new JButton("Tambah");
    private JButton btnUbah    = new JButton("Ubah");
    private JButton btnHapus   = new JButton("Hapus");
    private JTable table       = new JTable();
    private DefaultTableModel model;
    private MatkulController controller;
    // Id tidak diinput user
    private int idTerpilih = -1;

    public MatkulView() {
        setTitle("Data Mata Kuliah");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        add(new JLabel("Kode Matkul")).setBounds(20, 30, 100, 25);
        txtKodeMk.setBounds(130, 30, 180, 25); add(txtKodeMk);

        add(new JLabel("Nama Matkul")).setBounds(20, 70, 100, 25);
        txtNama.setBounds(130, 70, 180, 25); add(txtNama);

        add(new JLabel("SKS")).setBounds(20, 110, 100, 25);
        txtSks.setBounds(130, 110, 180, 25);  add(txtSks);

        btnTambah.setBounds(340, 30, 90, 25); add(btnTambah);
        btnUbah.setBounds  (340, 70, 90, 25); add(btnUbah);
        btnHapus.setBounds (340,110, 90, 25); add(btnHapus);

        model = new DefaultTableModel(new Object[] {"ID", "Kode", "Nama Matkul", "SKS"}, 0);
        table.setModel(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 160, 540, 180);
        add(sp);

        controller = new MatkulController(this);
        controller.loadMatkul();

        btnTambah.addActionListener(e -> {
            try {
                String kode = txtKodeMk.getText();
                String nama = txtNama.getText();
                int sks = Integer.parseInt(txtSks.getText());
                Matkul m = new Matkul(0, kode, nama, sks);
                controller.tambahMatkul(m);
                clearInput();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
            }
        });

        btnUbah.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && idTerpilih != -1) {
                try {
                    String kode = txtKodeMk.getText();
                    String nama = txtNama.getText();
                    int sks = Integer.parseInt(txtSks.getText());
                    Matkul m = new Matkul(idTerpilih, kode, nama, sks);
                    controller.ubahMatkul(m);
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
                    controller.hapusMatkul(idTerpilih);
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
                txtKodeMk.setText(model.getValueAt(row, 1).toString());
                txtNama.setText(model.getValueAt(row, 2).toString());
                txtSks.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    public void setMatkulList(List<Matkul> list) {
        model.setRowCount(0);
        for (Matkul m : list) {
            model.addRow(new Object[]{m.getId(), m.getKodeMk(), m.getNama(), m.getSks()});
        }
    }

    private void clearInput() {
        idTerpilih = -1;
        txtKodeMk.setText("");
        txtNama.setText("");
        txtSks.setText("");
        table.clearSelection();
    }
}
