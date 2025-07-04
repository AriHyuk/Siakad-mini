/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ari Awaludin
 */

import view.MainView;

import view.LoginView;

import dao.UserDAO;

public class LoginController {
	private UserDAO userDAO;
	private LoginView loginView;

	public LoginController(LoginView view) {
    	this.loginView = view;
    	userDAO = new UserDAO();
	}

	public void login() {
    	String username = loginView.getUsername();
    	String password = loginView.getPassword();
    	if (userDAO.login(username, password)) {
        	loginView.showMessage("Login berhasil");
                new MainView().setVisible(true);
        	loginView.dispose();
    	} else {
        	loginView.showMessage("Username atau password salah");
    	}
	}
}

