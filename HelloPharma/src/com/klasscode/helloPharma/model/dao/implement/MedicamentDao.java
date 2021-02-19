package com.klasscode.helloPharma.model.dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.klasscode.helloPharma.app.Observateur.Observable;
import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.model.DAO;
import com.klasscode.helloPharma.model.bean.Medicament;

public class MedicamentDao extends DAO<Medicament> implements Observable {

	private ArrayList<Observer> listObserver = new ArrayList<>();

	@Override
	public boolean create(Medicament objet) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO medicament(nom,prix,quantite,descr,dateExp) VALUES(?,?,?,?,?);";
		try (PreparedStatement prepare = this.connection.prepareStatement(query)) {

			prepare.setString(1, objet.getNom());
			prepare.setDouble(2, objet.getPrix());
			prepare.setInt(3, objet.getQuantite());
			prepare.setString(4, objet.getDesc());
			prepare.setString(5, objet.getDateExp());

			prepare.executeUpdate();
			notifyObserver("Medicament Enregistre");
			return true;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Medicament objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Medicament objet) {
		// TODO Auto-generated method stub
		String query = "UPDATE medicament SET nom= ?, prix= ?, quantite= ?, descr=?, dateExp= ? WHERE id= ?";
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			p.setString(1, objet.getNom());
			p.setDouble(2, objet.getPrix());
			p.setInt(3, objet.getQuantite());
			System.out.println("Date Format--> " + objet.getDateExp());
			p.setString(4, objet.getDesc());
			p.setString(5, objet.getDateExp());
			p.setInt(6, objet.getId());
			p.executeUpdate();
			notifyObserver("Modification Reussie");
			return true;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur" + e.getMessage());
			return false;
		}
	}

	@Override
	public Medicament search(int id) {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM medicament WHERE id =" + id;
		Medicament medicament = new Medicament();
		try (PreparedStatement p = this.connection.prepareStatement(query); ResultSet rs = p.executeQuery()) {
			while (rs.next()) {
				medicament = new Medicament(rs.getInt("id"), rs.getString("nom"), rs.getDouble("prix"),
						rs.getInt("quantite"), rs.getString("descr"), rs.getString("dateExp"),
						rs.getString("dateEnreg"));
			}
			return medicament;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur 1: " + e.getMessage());
			return null;
		}
	}
	
	public Medicament search(String nom) {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM medicament WHERE nom = ?"; 
		Medicament medicament = new Medicament();
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			p.setString(1, nom);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				medicament = new Medicament(rs.getInt("id"), rs.getString("nom"), rs.getDouble("prix"),
						rs.getInt("quantite"), rs.getString("descr"), rs.getString("dateExp"),
						rs.getString("dateEnreg"));
			}
			rs.close();
			return medicament;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur 1: " + e.getMessage());
			return null;
		}
	}

	public boolean searchByName(String nom,String oldName,String sType) {
		String query = "SELECT * FROM medicament WHERE nom =?";
		boolean search = false;
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			p.setString(1, nom);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				if(sType.equalsIgnoreCase("update")) {
					if (rs.getString("nom").equals("") || rs.getString("nom").equalsIgnoreCase(oldName)) {
						search = false;
					} else {
						search = true;
					}
				}else {
					if(rs.getString("nom").equals("")) {
						search= false;
					}else {
						search = true;
					}
				}
			}
			rs.close();
			System.out.print(search);
			return search;
		} catch (SQLException s) {
			MessageUser.displayMessage("Erreur sql" + s.getMessage());
			return true;
		}
	}

	@Override
	public ArrayList<Medicament> selectAll() {
		// TODO Auto-generated method stub
		String query = "SELECT id FROM medicament";
		ArrayList<Medicament> listMedicament = new ArrayList<>();
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Medicament m = search(id);
				listMedicament.add(m);
			}
			return listMedicament;
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
		listObserver = new ArrayList<>();
	}

	@Override
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		for (Observer obs : listObserver) {
			obs.update(str);
		}
	}

}
