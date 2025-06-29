package view;

import controller.MatkulController;
import model.Matkul;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MatkulView extends JFrame {
    private JTextField txtKodeMk = new JTextField();
    private JTextField txtNama = new JTextField();
    private JTextField txtSks = new JTextField();
    private JButton btnTambah = new JButton("Tambah");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnKembali = new JButton("Kembali");
    private JTable table = new JTable();
    private DefaultTableModel model;
    private MatkulController controller;
    private int idTerpilih = -1;

    public MatkulView() {
        setTitle("Sistem Akademik - Kelola Mata Kuliah");
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
        
        JLabel titleLabel = new JLabel("KELOLA MATA KULIAH", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Mata Kuliah"));
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Kode Matkul:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtKodeMk.setPreferredSize(new Dimension(250, 30));
        styleTextField(txtKodeMk);
        formPanel.add(txtKodeMk, gbc);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nama Matkul:"), gbc);
        
        gbc.gridx = 1;
        txtNama.setPreferredSize(new Dimension(250, 30));
        styleTextField(txtNama);
        formPanel.add(txtNama, gbc);
        
        // Row 2
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("SKS:"), gbc);
        
        gbc.gridx = 1;
        txtSks.setPreferredSize(new Dimension(250, 30));
        styleTextField(txtSks);
        formPanel.add(txtSks, gbc);
        
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
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Mata Kuliah"));
        
        model = new DefaultTableModel(new Object[]{"ID", "Kode", "Nama Matkul", "SKS"}, 0) {
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
        
        controller = new MatkulController(this);
        controller.loadMatkul();

        // Action listeners
        btnTambah.addActionListener(e -> {
            try {
                String kode = txtKodeMk.getText();
                String nama = txtNama.getText();
                int sks = Integer.parseInt(txtSks.getText());
                if (!kode.isEmpty() && !nama.isEmpty()) {
                    Matkul m = new Matkul(0, kode, nama, sks);
                    controller.tambahMatkul(m);
                    clearInput();
                } else {
                    JOptionPane.showMessageDialog(this, "Data belum lengkap!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "SKS harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUbah.addActionListener(e -> {
            if (idTerpilih != -1) {
                try {
                    String kode = txtKodeMk.getText();
                    String nama = txtNama.getText();
                    int sks = Integer.parseInt(txtSks.getText());
                    Matkul m = new Matkul(idTerpilih, kode, nama, sks);
                    controller.ubahMatkul(m);
                    clearInput();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "SKS harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
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
                    controller.hapusMatkul(idTerpilih);
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
                    txtKodeMk.setText(model.getValueAt(row, 1).toString());
                    txtNama.setText(model.getValueAt(row, 2).toString());
                    txtSks.setText(model.getValueAt(row, 3).toString());
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
