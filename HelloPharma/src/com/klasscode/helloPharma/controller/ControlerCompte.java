package com.klasscode.helloPharma.controller;

import javax.swing.JOptionPane;

import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.bean.Compte;
import com.klasscode.helloPharma.model.dao.implement.CompteDao;

public class ControlerCompte {

	private CompteDao model;

	public ControlerCompte(CompteDao model) {
		this.model = model;
	}

	public void seConnecter(String userName, String password) {
		
		Compte compte = null;
		int code = this.model.find(userName, password);
		System.out.println(code);
		if (code > 0) {
			compte = model.search(code);
			if (compte != null) {
				MessageUser.displayMessage("Vous etes Connecte");
//				
			}
		} else {
			MessageUser.displayMessage("Verifier votre identifiant ou mot de passe ");
		}
	}
	
	//test si le compte est active
		public int is_activate(int active, int idCompte) {
			String motDePasse="";
			int response=0;
				if(active==0) {
					motDePasse =JOptionPane.showInputDialog(null, "Entrer Un nouveau mot de passe", "Message", JOptionPane.OK_OPTION);
					if(this.model.upgradePassword(motDePasse,idCompte)) {
						if(this.model.active(idCompte)) {
							response=1;
						}else {
							response=0;
						}
					}
					else {
						response=0;
					}
				}else {
					response=1;
				}
			return response;
			
		}
		
		
}
