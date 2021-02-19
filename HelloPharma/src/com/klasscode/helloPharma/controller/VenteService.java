package com.klasscode.helloPharma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.klasscode.helloPharma.model.bean.Vente;
import com.klasscode.helloPharma.model.dao.implement.VenteDao;

public class VenteService {
	
	private VenteDao model;
	private Vente m;
	
	public VenteService(VenteDao model, Vente m) {
		super();
		this.model = model;
		this.m = m;
	}
	
	public Map<String, Object> getInfosVente(int id) {
		Vente vente = null;
		Map<String, Object> venteInfos = new HashMap<>();
		if (id > 0) {
			vente = model.search(id);
			if (vente != null) {
				venteInfos.put("id", vente.getIdVente());
				venteInfos.put("montantTotal", vente.getMontantTotal());
				venteInfos.put("montantVerse", vente.getMontantVerse());
				venteInfos.put("monnaie", vente.getMonnaie());
				venteInfos.put("dateVente", vente.getDateVente());
				venteInfos.put("qtite Ht", vente.getQtiteHt());
			}
		}
		return venteInfos;
	}
	
	public void listerVente(JTable table) {
		ArrayList<Vente> listVente = model.selectAll();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] ligne = new Object[5];
		for (int i = 0; i < listVente.size(); i++) {
			ligne[0] = "VNT-" + listVente.get(i).getIdVente();
			ligne[1] = listVente.get(i).getMontantTotal();
			ligne[2] = listVente.get(i).getMontantVerse();
			ligne[3] = listVente.get(i).getMonnaie();
			ligne[4] = listVente.get(i).getDateVente();

			model.addRow(ligne);
		}
		model.fireTableDataChanged();
	}
}
