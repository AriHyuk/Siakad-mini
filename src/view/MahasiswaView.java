/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ari Awaludin
 */
import controller.MahasiswaController;
import model.Mahasiswa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class MahasiswaView extends JFrame {
	private JTextField txtNim = new JTextField();
	private JTextField txtNama = new JTextField();
	private JTextField txtJurusan = new JTextField();
	private JButton btnTambah = new JButton("Tambah");
	private JButton btnUbah = new JButton("Ubah");
	private JButton btnHapus = new JButton("Hapus");
	private JTable table = new JTable();
	private DefaultTableModel model;
	private MahasiswaController controller;

	public MahasiswaView() {
    	setTitle("Data Mahasiswa");
    	setSize(600, 400);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(null);

    	add(new JLabel("NIM")).setBounds(20, 30, 80, 25);
    	txtNim.setBounds(100, 30, 150, 25); add(txtNim);
    	add(new JLabel("Nama")).setBounds(20, 70, 80, 25);
    	txtNama.setBounds(100, 70, 150, 25); add(txtNama);
    	add(new JLabel("Jurusan")).setBounds(20, 110, 80, 25);
    	txtJurusan.setBounds(100, 110, 150, 25); add(txtJurusan);

    	btnTambah.setBounds(280, 30, 80, 25); add(btnTambah);
    	btnUbah.setBounds(280, 70, 80, 25); add(btnUbah);
    	btnHapus.setBounds(280, 110, 80, 25); add(btnHapus);

    	model = new DefaultTableModel(new Object[] {"ID","NIM","Nama","Jurusan"}, 0);
    	table.setModel(model);
    	JScrollPane sp = new JScrollPane(table);
    	sp.setBounds(20, 160, 540, 180);
    	add(sp);

    	controller = new MahasiswaController(this);
    	controller.loadMahasiswa();

    	btnTambah.addActionListener(e -> {
        	Mahasiswa m = new Mahasiswa(0, txtNim.getText(), txtNama.getText(), txtJurusan.getText());
        	controller.tambahMahasiswa(m);
        	clearInput();
    	});

    	btnUbah.addActionListener(e -> {
        	int row = table.getSelectedRow();
        	if (row >= 0) {
            	int id = Integer.parseInt(model.getValueAt(row, 0).toString());
            	Mahasiswa m = new Mahasiswa(id, txtNim.getText(), txtNama.getText(), txtJurusan.getText());
            	controller.ubahMahasiswa(m);
            	clearInput();
        	}
    	});

    	btnHapus.addActionListener(e -> {
        	int row = table.getSelectedRow();
        	if (row >= 0) {
            	int id = Integer.parseInt(model.getValueAt(row, 0).toString());
            	controller.hapusMahasiswa(id);
            	clearInput();
        	}
    	});

    	table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
            	int row = table.getSelectedRow();
            	txtNim.setText(model.getValueAt(row, 1).toString());
            	txtNama.setText(model.getValueAt(row, 2).toString());
            	txtJurusan.setText(model.getValueAt(row, 3).toString());
        	}
    	});
	}

	public void setMahasiswaList(List<Mahasiswa> list) {
    	model.setRowCount(0);
    	for (Mahasiswa m : list) {
        	model.addRow(new Object[]{m.getId(), m.getNim(), m.getNama(), m.getJurusan()});
    	}
	}

	private void clearInput() {
    	txtNim.setText("");
    	txtNama.setText("");
    	txtJurusan.setText("");
	}
}
