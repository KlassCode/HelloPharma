package com.klasscode.helloPharma.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.bean.Medicament;
import com.klasscode.helloPharma.model.dao.implement.MedicamentDao;

public class ControlerMedicament {

	private MedicamentDao model;
	private Medicament m;
	private MedicamentService ms;

	
	public ControlerMedicament(MedicamentDao model) {
		this.model = model;
		m = new Medicament();
		ms = new MedicamentService(model,m);
	}

	public void enregistrerMedoc(String nom, String prix, int qtite, String desc, Date dateExp) {

		if (nom.equals("") || prix.equals("") || dateExp == null) {
			MessageUser.displayMessage("Verifier si les Champs sont remplis correctement");
		} else {
			if (!model.searchByName(nom,null,"create")) {

				ms.verification(0, nom, prix, qtite, desc, dateExp, "insertion");
			} else {
				MessageUser.displayMessage("Ce medicament existe deja");
			}
		}
	}

	
	public void callUpdate(int id, String nom, double prix, int qtite, String descr, String dateExp, String oldName) {
		if (oldName != null) {
			if (!model.searchByName(nom, oldName,"update")) {
				Medicament m = new Medicament(id, nom, prix, qtite, descr, dateExp, null);
				updateMedicament(m);
			} else {
				MessageUser.displayMessage("Ce medicament existe deja");
			}
		}
	}

	private void updateMedicament(Medicament m) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateExp = sdf.parse(m.getDateExp());
			String prix = String.valueOf(m.getPrix());

			ms.verification(m.getId(), m.getNom(), prix, m.getQuantite(), m.getDesc(), dateExp, "update");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			MessageUser.displayMessage("Erreur Conversion Chaine incorrect");
		}
	}

	public void listerMedicament(JTable table) {

		new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
				ArrayList<Medicament> listMedicaments = ms.getMedocs();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object[] ligne = new Object[6];
				for (int i = 0; i < listMedicaments.size(); i++) {
					ligne[0] = "MED-" + listMedicaments.get(i).getId();
					ligne[1] = listMedicaments.get(i).getNom();
					ligne[2] = listMedicaments.get(i).getPrix();
					ligne[3] = listMedicaments.get(i).getQuantite();
					ligne[4] = listMedicaments.get(i).getDateExp();
					ligne[5] = listMedicaments.get(i).getDateEnreg();

					model.addRow(ligne);
				}
				model.fireTableDataChanged();
				return null;
			}

		}.execute();

	}
	
	public long getTotal() {
		ArrayList<Medicament> listMedicaments = ms.getMedocs();
		return ms.totalMedicaments(listMedicaments);
	}
	
	
	public Map<String, Object> getInfosMedicament(int id) {
		Medicament medicament = null;
		Map<String, Object> medocInfos = new HashMap<>();
		if (id > 0) {
			medicament = model.search(id);
			if (medicament != null) {
				medocInfos.put("id", medicament.getId());
				medocInfos.put("nom", medicament.getNom());
				medocInfos.put("prix", medicament.getPrix());
				medocInfos.put("quantite", medicament.getQuantite());
				medocInfos.put("dateExp", medicament.getDateExp());
				if (medicament.getDesc() != null)
					medocInfos.put("desc", medicament.getDesc());
			}
		}
		return medocInfos;
	}
	
	public Map<String, Object> getInfosMedicament(String nom) {
		Medicament medicament = null;
		Map<String, Object> medocInfos = new HashMap<>();
		if (!nom.equals("")) {
			medicament = model.search(nom);
			
			if (medicament != null) {
				medocInfos.put("id", medicament.getId());
				medocInfos.put("nom", medicament.getNom());
				medocInfos.put("prix", medicament.getPrix());
				medocInfos.put("quantite", medicament.getQuantite());
				medocInfos.put("dateExp", medicament.getDateExp());
			}
		}
		return medocInfos;
	}
	
	
}
