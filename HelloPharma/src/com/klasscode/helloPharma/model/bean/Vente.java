package com.klasscode.helloPharma.model.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vente {

	private int idVente = 0;
	private ArrayList<Medicament> listMedicament = new ArrayList<Medicament>();
	private double montantTotal = 0.0d;
	private double montantVerse = 0.0d;
	private double monnaie = 0.0d;
	private String dateVente;
	private Map<String,Integer> qtiteHt = new HashMap<>();

	public Vente() {
	}

	public Vente(int idVente, ArrayList<Medicament> listMedicament, double montantTotal, double montantVerse,
			double monnaie, String dateVente) {
		super();

		this.idVente = idVente;
		this.listMedicament = listMedicament;
		this.montantTotal = montantTotal;
		this.montantVerse = montantVerse;
		this.monnaie = monnaie;
		this.dateVente = dateVente;
	}

	public Vente(ArrayList<Medicament> listMedicament, double montantTotal, double montantVerse, double monnaie, Map<String,Integer> qtiteHt) {
		super();
		this.listMedicament = listMedicament;
		this.montantTotal = montantTotal;
		this.montantVerse = montantVerse;
		this.monnaie = monnaie;
		this.qtiteHt= qtiteHt;
	}

	public int getIdVente() {
		return idVente;
	}

	public void setIdVente(int idVente) {
		this.idVente = idVente;
	}

	public void addMedicament(Medicament opt) {
		listMedicament.add(opt);
	}

	public void addQtite(String nom,int qtite) {
		qtiteHt.put(nom,qtite);
	}
	
	public ArrayList<Medicament> getListMedicament() {
		return listMedicament;
	}

	public void setListMedicament(ArrayList<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public double getMontantVerse() {
		return montantVerse;
	}

	public void setMontantVerse(double montantVerse) {
		this.montantVerse = montantVerse;
	}

	public double getMonnaie() {
		return monnaie;
	}

	public void setMonnaie(double monnaie) {
		this.monnaie = monnaie;
	}

	public String getDateVente() {
		return dateVente;
	}

	public void setDateVente(String dateVente) {
		this.dateVente = dateVente;
	}

	public Map<String, Integer> getQtiteHt() {
		return qtiteHt;
	}

	public void setQtiteHt(Map<String, Integer> qtiteHt) {
		this.qtiteHt = qtiteHt;
	}

}
