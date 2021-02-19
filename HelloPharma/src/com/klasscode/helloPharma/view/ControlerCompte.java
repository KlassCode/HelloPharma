package com.klasscode.helloPharma.view;

import javax.swing.JOptionPane;

import com.klasscode.helloPharma.model.bean.Compte;
import com.klasscode.helloPharma.model.dao.implement.CompteDao;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;

public class ControlerCompte {

	private CompteDao model;

	private ControlerCompte(CompteDao model) {
		this.model = model;
	}

	public void seConnecter(String userName, String password) {
		Compte compte = null;
		int code = this.model.find(userName, password);
		System.out.println(code);
		if (code > 0) {
			compte = model.search(code);
			if (compte != null) {
				// MessageUser.displayMessage("Vous etes Connecte");
				new NoticeWindow(NoticeType.SUCCESS_NOTIFICATION, "Vous etes Connecte", NoticeWindow.LONG_DELAY,
						NPosition.TOP_RIGHT);
			}
		} else {
			new NoticeWindow(NoticeType.WARNING_NOTIFICATION, "Verifier Identifiant/Password", NoticeWindow.LONG_DELAY,
					NPosition.CENTER);
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
