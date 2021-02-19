package com.klasscode.helloPharma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.bean.Medicament;
import com.klasscode.helloPharma.model.dao.implement.MedicamentDao;

public class MedicamentService {
	
	private MedicamentDao model;
	private Medicament m;
	
	public MedicamentService(MedicamentDao model, Medicament m) {
		super();
		this.model = model;
		this.m = m;
	}

	public void verification(int id, String nom, String prix, int qtite, String desc, Date dateExp, String requete) {
		try {
			double price = Double.parseDouble(prix);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateExpiration = df.format(dateExp);
			if (qtite > 0) {
				if (requete.equals("insertion")) {
					m = new Medicament(nom, price, qtite, desc, dateExpiration);
					System.out.println("3-bis" + m.getDesc());
					model.create(m);
				} else {
					m = new Medicament(id, nom, price, qtite, desc, dateExpiration, null);
					model.update(m);
				}
			} else {
				MessageUser.displayMessage("Verifier que la quantite n'est pas a 0");
			}
		} catch (Exception e) {
			MessageUser.displayMessage("Valeurs champs prix incorrect");
		}
	}
	
	public long totalMedicaments(List<Medicament> liste) {
		
		return liste.stream().count();
	}
	
	public ArrayList<Medicament> getMedocs(){
		ArrayList<Medicament> listMedicaments = model.selectAll();
		return listMedicaments;
	}
	
}
