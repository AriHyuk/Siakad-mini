/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.event.*;


/**
 *
 * @author Ari Awaludin
 */
public class MainView extends JFrame{
    private JButton btnDosen = new JButton("Dosen");
    private JButton btnMahasiswa = new JButton("Mahasiswa");

    public MainView() {
        setTitle("Menu Utama");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        btnDosen.setBounds(100, 50, 100, 30);      
        btnMahasiswa.setBounds(100, 100, 100, 30);

        add(btnDosen);
        add(btnMahasiswa);

        btnDosen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DosenView().setVisible(true);
                dispose(); 
            }
        });

        btnMahasiswa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MahasiswaView().setVisible(true);
                dispose(); 
            }
        });
    }
}
