package view;

import controller.DosenController;
import model.Dosen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class DosenView extends JFrame {

    private JTextField txtNama = new JTextField();
    private JTextField txtAlamat = new JTextField();
    private JButton btnTambah = new JButton("Tambah");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JTable table = new JTable();
    private DefaultTableModel model;
    private DosenController controller;

    public DosenView() {
        setTitle("Data Dosen");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(20, 30, 80, 25); add(lblNama);
        txtNama.setBounds(100, 30, 150, 25); add(txtNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(20, 70, 80, 25); add(lblAlamat);
        txtAlamat.setBounds(100, 70, 150, 25); add(txtAlamat);

        btnTambah.setBounds(280, 30, 80, 25); add(btnTambah);
        btnUbah.setBounds(280, 70, 80, 25); add(btnUbah);
        btnHapus.setBounds(280, 110, 80, 25); add(btnHapus);

        model = new DefaultTableModel(new Object[]{"ID", "Nama", "Alamat"}, 0);
        table.setModel(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 150, 540, 200); add(sp);

        controller = new DosenController(this);
        controller.loadDosen();

        // Event: Tambah
        btnTambah.addActionListener(e -> {
            Dosen d = new Dosen(0, txtNama.getText(), txtAlamat.getText());
            controller.tambahDosen(d);
            clearInput();
        });

        // Event: Ubah
        btnUbah.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                Dosen d = new Dosen(id, txtNama.getText(), txtAlamat.getText());
                controller.ubahDosen(d);
                clearInput();
            }
        });

        // Event: Hapus
        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                controller.hapusDosen(id);
                clearInput();
            }
        });

        // Klik Tabel
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtAlamat.setText(model.getValueAt(row, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    public void setDosenList(List<Dosen> list) {
        model.setRowCount(0);
        for (Dosen d : list) {
            model.addRow(new Object[]{d.getId(), d.getNama(), d.getAlamat()});
        }
    }

    private void clearInput() {
        txtNama.setText("");
        txtAlamat.setText("");
    }
}
