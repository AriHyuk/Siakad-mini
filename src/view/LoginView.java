/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ari Awaludin
 */
import controller.LoginController;
import javax.swing.*;

public class LoginView extends JFrame {
	private JTextField txtUsername = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JButton btnLogin = new JButton("Login");
	private LoginController controller;

	public LoginView() {
    	setTitle("Login");
    	setSize(300, 200);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(null);
    	txtUsername.setBounds(100, 30, 150, 25);
    	txtPassword.setBounds(100, 70, 150, 25);
    	btnLogin.setBounds(100, 110, 80, 30);

    	add(new JLabel("Username")).setBounds(20, 30, 80, 25);
    	add(txtUsername);
    	add(new JLabel("Password")).setBounds(20, 70, 80, 25);
    	add(txtPassword);
    	add(btnLogin);

    	controller = new LoginController(this);

    	btnLogin.addActionListener(e -> controller.login());
	}

	public String getUsername() {
    	return txtUsername.getText();
	}
	public String getPassword() {
    	return new String(txtPassword.getPassword());
	}
	public void showMessage(String message) {
    	JOptionPane.showMessageDialog(this, message);
	}
}

