/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;

import java.awt.*;




/**
 * @author Ari Awaludin
 */



public class MainView extends JFrame {

    private final JButton btnDosen = new JButton("Dosen");
    private final JButton btnMahasiswa = new JButton("Mahasiswa");
    private final JButton btnMatkul = new JButton("Mata Kuliah");
    private final JButton btnJadwal = new JButton("Jadwal");
    private final JButton btnKrs = new JButton("KRS");
    private final JButton btnLogout = new JButton("Logout");

    public MainView() {
        setTitle("Sistem Akademik - Menu Utama");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Use modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("SISTEM AKADEMIK", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Add logout button to header
        btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnLogout.setBackground(new Color(220, 80, 80));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        headerPanel.add(btnLogout, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel with grid layout for buttons
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        // Configure buttons
        JButton[] buttons = {btnDosen, btnMahasiswa, btnMatkul, btnJadwal, btnKrs};
        for (JButton button : buttons) {
            styleButton(button);
            contentPanel.add(button, gbc);
        }
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        
        JLabel footerLabel = new JLabel("© 2023 Sistem Akademik - Universitas Contoh");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerPanel.add(footerLabel);
        
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Button actions
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
        
        btnKrs.addActionListener(e -> {
            new KrsView().setVisible(true);
            dispose();
        });
        
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Apakah Anda yakin ingin logout?", 
                "Konfirmasi Logout", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Here you would typically return to login screen
                JOptionPane.showMessageDialog(this, "Logout berhasil");
                dispose();
                // new LoginView().setVisible(true); // Uncomment if you have login view
            }
        });
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView();
            view.setVisible(true);
        });
    }
}



