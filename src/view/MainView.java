package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        setSize(900, 700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set modern look and feel
        setModernLookAndFeel();
        
        // Setup main UI components
        setupUI();
        
        // Setup event listeners
        setupEventListeners();
        
        // Set window close listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    private void setModernLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.focus", new Color(0, 0, 0, 0)); // Remove focus highlight
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupUI() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);
        
        // Add header
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Add center content
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);
        
        // Add footer
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 70));
        
        // Application title
        JLabel titleLabel = new JLabel("SISTEM AKADEMIK", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Logout button
        styleButton(btnLogout, new Color(231, 76, 60));
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        headerPanel.add(btnLogout, BorderLayout.EAST);
        
        return headerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        contentPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.weightx = 1.0;
        gbc.ipady = 20;
        
        // Style and add menu buttons
        styleButton(btnDosen, new Color(52, 152, 219));
        styleButton(btnMahasiswa, new Color(155, 89, 182));
        styleButton(btnMatkul, new Color(26, 188, 156));
        styleButton(btnJadwal, new Color(241, 196, 15));
        styleButton(btnKrs, new Color(230, 126, 34));
        
        contentPanel.add(btnDosen, gbc);
        contentPanel.add(btnMahasiswa, gbc);
        contentPanel.add(btnMatkul, gbc);
        contentPanel.add(btnJadwal, gbc);
        contentPanel.add(btnKrs, gbc);
        
        return contentPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(52, 152, 219));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel footerLabel = new JLabel("© 2025 Sistem Akademik - Universitas Pamulang", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel, BorderLayout.CENTER);
        
        return footerPanel;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void setupEventListeners() {
        // Menu button actions
        btnDosen.addActionListener(e -> openView(new DosenView()));
        btnMahasiswa.addActionListener(e -> openView(new MahasiswaView()));
        btnMatkul.addActionListener(e -> openView(new MatkulView()));
        btnJadwal.addActionListener(e -> openView(new JadwalView()));
        btnKrs.addActionListener(e -> openView(new KrsView()));
        
        // Logout action
        btnLogout.addActionListener(e -> confirmLogout());
        
        // Set keyboard mnemonics
        btnDosen.setMnemonic(KeyEvent.VK_D);
        btnMahasiswa.setMnemonic(KeyEvent.VK_M);
        btnMatkul.setMnemonic(KeyEvent.VK_K);
        btnJadwal.setMnemonic(KeyEvent.VK_J);
        btnKrs.setMnemonic(KeyEvent.VK_R);
    }

private void openView(JFrame view) {
    view.setVisible(true);
    this.setVisible(false); // Sembunyikan MainView alih-alih dispose
    view.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            MainView.this.setVisible(true); // Tampilkan kembali MainView saat view ditutup
        }
    });
}

    private void confirmLogout() {
        Object[] options = {"Ya, Logout", "Batal"};
        int confirm = JOptionPane.showOptionDialog(
            this, 
            "Apakah Anda yakin ingin logout dari sistem?", 
            "Konfirmasi Logout",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[1]
        );
        
        if (confirm == 0) {
            dispose();
            // Uncomment to return to login view:
            // new LoginView().setVisible(true);
        }
    }

    private void confirmExit() {
        Object[] options = {"Ya, Keluar", "Batal"};
        int confirm = JOptionPane.showOptionDialog(
            this, 
            "Apakah Anda yakin ingin keluar dari aplikasi?", 
            "Konfirmasi Keluar",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[1]
        );
        
        if (confirm == 0) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView();
            view.setVisible(true);
        });
    }
}