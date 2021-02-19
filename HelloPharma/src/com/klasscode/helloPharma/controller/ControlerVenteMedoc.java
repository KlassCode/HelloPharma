package com.klasscode.helloPharma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.bean.Medicament;
import com.klasscode.helloPharma.model.bean.Vente;
import com.klasscode.helloPharma.model.dao.implement.MedicamentDao;
import com.klasscode.helloPharma.model.dao.implement.VenteDao;

public class ControlerVenteMedoc {
	private VenteDao model = new VenteDao();
	private MedicamentDao modelMedoc = new MedicamentDao();
	private ControlerMedicament ctrlMedoc = new ControlerMedicament(modelMedoc);
	private Vente vente = new Vente();
	private double prixTotal = 0.0d;
	private double monnaie = 0.0d;
	private ArrayList<Medicament> listMedicamentHt = new ArrayList<>();
	private Vector<String> v = new Vector<>();
	private ArrayList<Medicament> listForUpdate = new ArrayList<>();
	private Map<String,Integer> storeQtite = new HashMap<>();
	private VenteService vs;
	
	public ControlerVenteMedoc(VenteDao model) {
		this.model = model;
		vs = new VenteService(model,vente);
	}

	public Map<String, Object> findMedoc(String nom) {
		Map<String, Object> infos = ctrlMedoc.getInfosMedicament(nom);
		return infos;
	}

	public void addMedocJList(JList list, int quantiteHt, int id) {
		boolean alreadyAdd = false;
		Medicament m = modelMedoc.search(id);
		if (m != null) {
			if (quantiteHt <= m.getQuantite()) {
				for (Medicament medoc : listMedicamentHt) {
					if (medoc.getNom().equalsIgnoreCase(m.getNom())) {
						alreadyAdd = true;
						break;
					} else {
						alreadyAdd = false;
					}
				}
				if (!alreadyAdd) {
					listMedicamentHt.add(m);
//					
					v.add(listMedicamentHt.get(listMedicamentHt.size() - 1).getNom() + " " + quantiteHt);
					list.setListData(v);
					totalVente(listMedicamentHt.get(listMedicamentHt.size() - 1), quantiteHt);
					postVenteOperation(listMedicamentHt,quantiteHt);

				} else {
					MessageUser.displayMessage("Medicament deja ajoute");
				}

			} else {
				MessageUser.displayMessage("Quantite non disponible");
			}
		} else {
			MessageUser.displayMessage("Aucun Medicament Selectionne");
		}
	}

	public void removeMedoc(JList list, String selectedValue, JLabel lblTot) {
		// TODO Auto-generated method stub
		Vector<String> tampon = new Vector<>(v);
		
		String nom = selectedValue.split(" ")[0];
		int qtite = Integer.parseInt(selectedValue.split(" ")[1]);
		double prix = 0.0d;
		CopyOnWriteArrayList<Medicament> listMed = new CopyOnWriteArrayList<>(listMedicamentHt);
		ListIterator<Medicament> it = listMed.listIterator();
		while (it.hasNext()) {
			Medicament m = it.next();
			if (m.getNom().equals(nom)) {
				prix = m.getPrix();
				listMedicamentHt.remove(m);
				prixTotal = prixTotal - (prix * qtite);
				lblTot.setText("HTG " + prixTotal);
				for(String s: v) {
					if(s.equalsIgnoreCase(selectedValue)) {
						tampon.remove(s);
						v= new Vector<>(tampon);
						list.setListData(v);
					}
				}
			}

		}
		
	}

	public void totalVente(Medicament m, int quantite) {
		double prixMedocHt = m.getPrix() * quantite;
		prixTotal += prixMedocHt;
	}

	public void postVenteOperation(ArrayList<Medicament> list, int quantite) {
		Medicament m = list.get(list.size() - 1);
		int quantiteDispo = m.getQuantite();
		quantiteDispo -= quantite;
		m.setQuantite(quantiteDispo);
		listForUpdate.add(m);
		
		storeQtite.put(m.getNom(),quantite);
	}

	public void updateMedicament() {
		if (listForUpdate.size() != 0) {
			for (Medicament m : listForUpdate) {
				modelMedoc.update(m);
			}
			listMedicamentHt = new ArrayList<Medicament>();
		}
	}

	public void reinitializeVente() {
		prixTotal = 0.0d;
		listMedicamentHt = new ArrayList<>();
		v= new Vector<>();
		listForUpdate = new ArrayList<>();
		storeQtite = new HashMap<>();
	}
	
	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public double getMonnaie() {
		return monnaie;
	}

	public void setMonnaie(double monnaie) {
		this.monnaie = monnaie;
	}

	public ArrayList<Medicament> getListForUpdate() {
		return listForUpdate;
	}

	public void nouvelleVente(String montant) {
		try {
			double montantVerse = Double.parseDouble(montant);
			if (montantVerse < prixTotal) {
				MessageUser.displayMessage("Erreur montant Verse inferieur");
			} else {
				monnaie = montantVerse - prixTotal;
				vente = new Vente(listMedicamentHt, prixTotal, montantVerse, monnaie,storeQtite);
				this.model.create(vente);
				
			}
		}catch(Exception e) {
			MessageUser.displayMessage("Insertion Incorrect");
		}
		
	}

	public void listerVente(JTable table) {
		vs.listerVente(table);
	}
	
	public Map<String,Object> getInfosVente(int id){
		return vs.getInfosVente(id);
	}

}
