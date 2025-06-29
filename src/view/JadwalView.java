/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.JadwalController;
import model.Jadwal;
import model.Matkul;
import model.Dosen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author Ari Awaludin
 */



public class JadwalView extends JFrame {
    private JComboBox<String> cmbMatkul = new JComboBox<>();
    private JComboBox<String> cmbDosen = new JComboBox<>();
    private JTextField txtRuang = new JTextField();
    private JTextField txtWaktu = new JTextField();
    private JButton btnTambah = new JButton("Tambah");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnKembali = new JButton("Kembali");
    private JTable table = new JTable();
    private DefaultTableModel model;
    private JadwalController controller;
    private List<Matkul> listMatkul;
    private List<Dosen> listDosen;
    private int idTerpilih = -1;

    public JadwalView() {
        setTitle("Sistem Akademik - Kelola Jadwal");
        setSize(1000, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("KELOLA JADWAL PERKULIAHAN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Jadwal"));
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0 - Mata Kuliah
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Mata Kuliah:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbMatkul.setPreferredSize(new Dimension(300, 30));
        styleComboBox(cmbMatkul);
        formPanel.add(cmbMatkul, gbc);
        
        // Row 1 - Dosen
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Dosen:"), gbc);
        
        gbc.gridx = 1;
        cmbDosen.setPreferredSize(new Dimension(300, 30));
        styleComboBox(cmbDosen);
        formPanel.add(cmbDosen, gbc);
        
        // Row 2 - Ruang
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Ruang:"), gbc);
        
        gbc.gridx = 1;
        txtRuang.setPreferredSize(new Dimension(300, 30));
        styleTextField(txtRuang);
        formPanel.add(txtRuang, gbc);
        
        // Row 3 - Waktu
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Waktu:"), gbc);
        
        gbc.gridx = 1;
        txtWaktu.setPreferredSize(new Dimension(300, 30));
        styleTextField(txtWaktu);
        formPanel.add(txtWaktu, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        styleButton(btnTambah, new Color(76, 175, 80));
        styleButton(btnUbah, new Color(33, 150, 243));
        styleButton(btnHapus, new Color(244, 67, 54));
        styleButton(btnKembali, new Color(158, 158, 158));
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUbah);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnKembali);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        mainPanel.add(formPanel, BorderLayout.WEST);
        
        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Jadwal"));
        
        model = new DefaultTableModel(new Object[]{"ID", "Mata Kuliah", "Dosen", "Ruang", "Waktu"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.setGridColor(new Color(240, 240, 240));
        
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 400));
        tablePanel.add(sp, BorderLayout.CENTER);
        
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        controller = new JadwalController(this);
        refreshCombo();
        controller.loadJadwal();

        // Action listeners
        btnTambah.addActionListener(e -> {
            try {
                if (cmbMatkul.getSelectedIndex() >= 0 && cmbDosen.getSelectedIndex() >= 0 
                    && !txtRuang.getText().isEmpty() && !txtWaktu.getText().isEmpty()) {
                    
                    int idMatkul = listMatkul.get(cmbMatkul.getSelectedIndex()).getId();
                    int idDosen = listDosen.get(cmbDosen.getSelectedIndex()).getId();
                    String ruang = txtRuang.getText();
                    String waktu = txtWaktu.getText();
                    
                    Jadwal j = new Jadwal(0, idMatkul, idDosen, ruang, waktu);
                    controller.tambahJadwal(j);
                    clearInput();
                } else {
                    JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUbah.addActionListener(e -> {
            if (idTerpilih != -1) {
                try {
                    if (cmbMatkul.getSelectedIndex() >= 0 && cmbDosen.getSelectedIndex() >= 0 
                        && !txtRuang.getText().isEmpty() && !txtWaktu.getText().isEmpty()) {
                        
                        int idMatkul = listMatkul.get(cmbMatkul.getSelectedIndex()).getId();
                        int idDosen = listDosen.get(cmbDosen.getSelectedIndex()).getId();
                        String ruang = txtRuang.getText();
                        String waktu = txtWaktu.getText();
                        
                        Jadwal j = new Jadwal(idTerpilih, idMatkul, idDosen, ruang, waktu);
                        controller.ubahJadwal(j);
                        clearInput();
                    } else {
                        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnHapus.addActionListener(e -> {
            if (idTerpilih != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                    this, 
                    "Apakah Anda yakin ingin menghapus data ini?", 
                    "Konfirmasi Hapus", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.hapusJadwal(idTerpilih);
                    clearInput();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnKembali.addActionListener(e -> {
            dispose();
            new MainView().setVisible(true);
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idTerpilih = Integer.parseInt(model.getValueAt(row, 0).toString());
                    
                    // Find and select the corresponding matkul and dosen
                    String selectedMatkul = model.getValueAt(row, 1).toString();
                    String selectedDosen = model.getValueAt(row, 2).toString();
                    
                    for (int i = 0; i < cmbMatkul.getItemCount(); i++) {
                        if (cmbMatkul.getItemAt(i).equals(selectedMatkul)) {
                            cmbMatkul.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    for (int i = 0; i < cmbDosen.getItemCount(); i++) {
                        if (cmbDosen.getItemAt(i).equals(selectedDosen)) {
                            cmbDosen.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    txtRuang.setText(model.getValueAt(row, 3).toString());
                    txtWaktu.setText(model.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    public void setJadwalList(List<Jadwal> list) {
        model.setRowCount(0);
        for (Jadwal j : list) {
            String namaMatkul = "", namaDosen = "";
            for (Matkul m : listMatkul) {
                if (m.getId() == j.getIdMatkul()) {
                    namaMatkul = m.getKodeMk() + " - " + m.getNama();
                    break;
                }
            }
            for (Dosen d : listDosen) {
                if (d.getId() == j.getIdDosen()) {
                    namaDosen = d.getNama();
                    break;
                }
            }
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
        // Load mata kuliah
        listMatkul = controller.getAllMatkul();
        cmbMatkul.removeAllItems();
        for (Matkul m : listMatkul) {
            cmbMatkul.addItem(m.getKodeMk() + " - " + m.getNama());
        }
        
        // Load dosen
        listDosen = controller.getAllDosen();
        cmbDosen.removeAllItems();
        for (Dosen d : listDosen) {
            cmbDosen.addItem(d.getNama());
        }
    }
}

