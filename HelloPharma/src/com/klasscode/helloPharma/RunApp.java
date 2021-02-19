package com.klasscode.helloPharma;

import java.awt.EventQueue;

import com.klasscode.helloPharma.controller.ControlerCompte;
import com.klasscode.helloPharma.model.dao.implement.CompteDao;
import com.klasscode.helloPharma.view.Login;

public class RunApp {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompteDao model = new CompteDao();
					ControlerCompte ctrl = new ControlerCompte(model);
					Login frame = new Login(ctrl);
					model.addObserver(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
