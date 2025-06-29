/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.KrsController;
import dao.KrsDAO;
import dao.MahasiswaDAO;
import dao.JadwalDAO;
import model.Krs;
import model.Mahasiswa;
import model.Jadwal;

/**
 *
 * @author Ari Awaludin
 */




import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class KrsView extends JFrame {
    private JComboBox<Mahasiswa> comboMahasiswa = new JComboBox<>();
    private JComboBox<Jadwal> comboJadwal = new JComboBox<>();
    private JTextField txtSemester = new JTextField();

    private JButton btnTambah = new JButton("Tambah");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnKembali = new JButton("Kembali");

    private JTable table = new JTable();
    private DefaultTableModel model;
    private int idTerpilih = -1;

    private KrsController controller;

    public KrsView() {
        setTitle("Sistem Akademik - Kelola KRS");
        setSize(900, 650);
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
        
        JLabel titleLabel = new JLabel("KELOLA KARTU RENCANA STUDI (KRS)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Form KRS"));
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Mahasiswa:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        comboMahasiswa.setPreferredSize(new Dimension(250, 30));
        styleComboBox(comboMahasiswa);
        formPanel.add(comboMahasiswa, gbc);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Jadwal:"), gbc);
        
        gbc.gridx = 1;
        comboJadwal.setPreferredSize(new Dimension(250, 30));
        styleComboBox(comboJadwal);
        formPanel.add(comboJadwal, gbc);
        
        // Row 2
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Semester:"), gbc);
        
        gbc.gridx = 1;
        txtSemester.setPreferredSize(new Dimension(250, 30));
        styleTextField(txtSemester);
        formPanel.add(txtSemester, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        mainPanel.add(formPanel, BorderLayout.WEST);
        
        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar KRS"));
        
        model = new DefaultTableModel(new Object[]{"ID", "Mahasiswa", "Jadwal", "Semester"}, 0) {
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
        sp.setPreferredSize(new Dimension(500, 400));
        tablePanel.add(sp, BorderLayout.CENTER);
        
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        controller = new KrsController(this);
        controller.loadData();
        loadComboData();

        // Action listeners
        btnTambah.addActionListener(e -> {
            try {
                Mahasiswa mhs = (Mahasiswa) comboMahasiswa.getSelectedItem();
                Jadwal jadwal = (Jadwal) comboJadwal.getSelectedItem();
                String semester = txtSemester.getText();
                if (mhs != null && jadwal != null && !semester.isEmpty()) {
                    controller.tambahKrs(new Krs(0, mhs.getId(), jadwal.getId(), semester));
                    clearInput();
                } else {
                    JOptionPane.showMessageDialog(this, "Data belum lengkap!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUbah.addActionListener(e -> {
            if (idTerpilih != -1) {
                try {
                    Mahasiswa mhs = (Mahasiswa) comboMahasiswa.getSelectedItem();
                    Jadwal jadwal = (Jadwal) comboJadwal.getSelectedItem();
                    String semester = txtSemester.getText();
                    controller.ubahKrs(new Krs(idTerpilih, mhs.getId(), jadwal.getId(), semester));
                    clearInput();
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
                    controller.hapusKrs(idTerpilih);
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
                    int idMhs = Integer.parseInt(model.getValueAt(row, 1).toString());
                    int idJadwal = Integer.parseInt(model.getValueAt(row, 2).toString());
                    txtSemester.setText(model.getValueAt(row, 3).toString());

                    for (int i = 0; i < comboMahasiswa.getItemCount(); i++) {
                        if (comboMahasiswa.getItemAt(i).getId() == idMhs) {
                            comboMahasiswa.setSelectedIndex(i); break;
                        }
                    }
                    for (int i = 0; i < comboJadwal.getItemCount(); i++) {
                        if (comboJadwal.getItemAt(i).getId() == idJadwal) {
                            comboJadwal.setSelectedIndex(i); break;
                        }
                    }
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

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void loadComboData() {
        comboMahasiswa.removeAllItems();
        comboJadwal.removeAllItems();

        List<Mahasiswa> mahasiswaList = new MahasiswaDAO().getAllMahasiswa();
        for (Mahasiswa m : mahasiswaList) {
            comboMahasiswa.addItem(m);
        }

        List<Jadwal> jadwalList = new JadwalDAO().getAll();
        for (Jadwal j : jadwalList) {
            comboJadwal.addItem(j);
        }
    }

    public void setKrsList(List<Krs> list) {
        model.setRowCount(0);
        for (Krs k : list) {
            model.addRow(new Object[]{k.getId(), k.getIdMahasiswa(), k.getIdJadwal(), k.getSemester()});
        }
    }

    private void clearInput() {
        idTerpilih = -1;
        txtSemester.setText("");
        if (comboMahasiswa.getItemCount() > 0) comboMahasiswa.setSelectedIndex(0);
        if (comboJadwal.getItemCount() > 0) comboJadwal.setSelectedIndex(0);
        table.clearSelection();
    }
}



