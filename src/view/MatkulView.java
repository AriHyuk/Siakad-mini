package view;

import controller.MatkulController;
import model.Matkul;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class MatkulView extends JFrame {
    private JTextField txtId   = new JTextField();
    private JTextField txtNama = new JTextField();
    private JTextField txtSks  = new JTextField();
    private JButton btnTambah  = new JButton("Tambah");
    private JButton btnUbah    = new JButton("Ubah");
    private JButton btnHapus   = new JButton("Hapus");
    private JTable table       = new JTable();
    private DefaultTableModel model;
    private MatkulController controller;

    public MatkulView() {
        setTitle("Data Mata Kuliah");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        add(new JLabel("ID Matkul")).setBounds(20, 30, 100, 25);
        txtId.setBounds(130, 30, 180, 25); add(txtId);

        add(new JLabel("Nama Matkul")).setBounds(20, 70, 100, 25);
        txtNama.setBounds(130, 70, 180, 25); add(txtNama);

        add(new JLabel("SKS")).setBounds(20, 110, 100, 25);
        txtSks.setBounds(130, 110, 180, 25);  add(txtSks);

        btnTambah.setBounds(340, 30, 90, 25); add(btnTambah);
        btnUbah.setBounds  (340, 70, 90, 25); add(btnUbah);
        btnHapus.setBounds (340,110, 90, 25); add(btnHapus);

        model = new DefaultTableModel(new Object[] {"ID Matkul", "Nama Matkul", "SKS"}, 0);
        table.setModel(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 160, 540, 180);
        add(sp);

        controller = new MatkulController(this); // TANPA conn
        controller.loadMatkul();

        btnTambah.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nama = txtNama.getText();
                int sks = Integer.parseInt(txtSks.getText());
                Matkul m = new Matkul(id, nama, sks);
                controller.tambahMatkul(m);
                clearInput();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
            }
        });

        btnUbah.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nama = txtNama.getText();
                    int sks = Integer.parseInt(txtSks.getText());
                    Matkul m = new Matkul(id, nama, sks);
                    controller.ubahMatkul(m);
                    clearInput();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
                }
            }
        });

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    controller.hapusMatkul(id);
                    clearInput();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtId.setText(model.getValueAt(row, 0).toString());
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtSks.setText(model.getValueAt(row, 2).toString());
            }
        });
    }

    public void setMatkulList(List<Matkul> list) {
        model.setRowCount(0);
        for (Matkul m : list) {
            model.addRow(new Object[]{m.getId(), m.getNama(), m.getSks()});
        }
    }

    private void clearInput() {
        txtId.setText("");
        txtNama.setText("");
        txtSks.setText("");
    }
}
