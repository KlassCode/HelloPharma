package com.klasscode.helloPharma.model.bean;

public class Medicament {
	private int id;
	private String nom;
	private double prix;
	private int quantite;
	private String desc;
	private String dateExp;
	private String dateEnreg;

	public Medicament() {
	}

	public Medicament(int id, String nom, double prix, int quantite, String desc, String dateExp, String dateEnreg) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.desc = desc;
		this.dateExp = dateExp;
		this.dateEnreg = dateEnreg;
	}

	public Medicament(String nom, double prix, int quantite, String desc, String dateExp) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.desc=desc;
		this.quantite = quantite;
		this.dateExp = dateExp;
	}
	
	public Medicament(int id, double prix, int quantite, String desc, String dateExp) {
		super();
		this.id = id;
		this.prix = prix;
		this.desc=desc;
		this.quantite = quantite;
		this.dateExp = dateExp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDateExp() {
		return dateExp;
	}

	public void setDateExp(String dateExp) {
		this.dateExp = dateExp;
	}

	public String getDateEnreg() {
		return dateEnreg;
	}

	public void setDateEnreg(String dateEnreg) {
		this.dateEnreg = dateEnreg;
	}
	
	
}
