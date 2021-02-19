package com.klasscode.helloPharma.model.dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.klasscode.helloPharma.app.Observateur.Observable;
import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.DAO;
import com.klasscode.helloPharma.model.bean.Medicament;
import com.klasscode.helloPharma.model.bean.Vente;

public class VenteDao extends DAO<Vente> implements Observable {
	
	private ArrayList<Observer> listObserver = new ArrayList<>();

	@Override
	public boolean create(Vente objet) {
		// TODO Auto-generated method stub
		boolean estRealise = false;
		int idVente = 0;
		int idMedicament = 0;
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			MessageUser.displayMessage("Erreur" + e1);
		}
		String query = "INSERT INTO vente(montantTotal,montantVerse,monnaie,dateVente) VALUES(?,?,?,NOW());";
		try (PreparedStatement prepare = this.connection.prepareStatement(query)) {
			prepare.setDouble(1, objet.getMontantTotal());
			prepare.setDouble(2, objet.getMontantVerse());
			prepare.setDouble(3, objet.getMonnaie());
			prepare.executeUpdate();

			ResultSet result = this.connection.prepareStatement("SELECT MAX(idVente) AS maxId FROM vente;")
					.executeQuery();
			while (result.next()) {
				idVente = result.getInt("maxId");
			}
			System.out.println("IdVente-->" + idVente);
			String requete = "INSERT INTO vente_medicament(idVente,idMedicament,qtite_ht) VALUES(?,?,?);";
			if (objet.getListMedicament().size() == 0) {
				estRealise = false;
				this.connection.rollback();
				notifyObserver("Annulation Vente");
			} else {
				for (Medicament medoc : objet.getListMedicament()) {
					try (PreparedStatement p = this.connection.prepareStatement(requete);) {
						if (objet.getQtiteHt().containsKey(medoc.getNom())) {
							int qtite = objet.getQtiteHt().get(medoc.getNom());
							p.setInt(1, idVente);
							p.setInt(2, medoc.getId());
							p.setInt(3, qtite);
							p.executeUpdate();
						}

					}
				}

				notifyObserver("Vente Realise");
				estRealise = true;
				this.connection.commit();
			}
			return estRealise;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur " + e.getMessage());
		}
		return estRealise;
	}

	@Override
	public boolean delete(Vente objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Vente objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vente search(int id) {
		// TODO Auto-generated method stub
		MedicamentDao medoc = new MedicamentDao();
		String query = "SELECT * FROM vente WHERE idVente =" + id;
		Vente vente = new Vente();
		ArrayList<Medicament> listMedicament = new ArrayList<>();
		try (PreparedStatement p = this.connection.prepareStatement(query); ResultSet rs = p.executeQuery()) {
			while (rs.next()) {
				int idVente = rs.getInt("idVente");
				String requete = "SELECT * FROM vente_medicament WHERE idVente= " + idVente;
				PreparedStatement prepare = this.connection.prepareStatement(requete);
				ResultSet result = prepare.executeQuery();
				while (result.next()) {
					Medicament m = medoc.search(result.getInt("idMedicament"));
					Map<String,Integer> qtiteHt = new HashMap<>();
					qtiteHt.put(m.getNom(), result.getInt("qtite_ht"));
					
					listMedicament.add(medoc.search(result.getInt("idMedicament")));
					vente = new Vente(rs.getInt("idVente"), listMedicament, rs.getDouble("montantTotal"),
							rs.getDouble("montantVerse"), rs.getDouble("monnaie"), rs.getString("dateVente"));
					vente.setQtiteHt(qtiteHt);
				}
			}

			return vente;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur 1: " + e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Vente> selectAll() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM vente";
		ArrayList<Vente> listVente = new ArrayList<>();
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idVente");
				Vente v = search(id);
				listVente.add(v);
			}
			return listVente;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur " + e.getMessage());
			return null;
		}

	}

	@Override
	public void addObserver(Observer obs) {
		// TODO Auto-generated method stub
		listObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		listObserver = new ArrayList<Observer>();
	}

	@Override
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		for (Observer obs : listObserver) {
			obs.update(str);
		}
	}

}
