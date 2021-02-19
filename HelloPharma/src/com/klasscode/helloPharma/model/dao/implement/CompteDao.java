package com.klasscode.helloPharma.model.dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.klasscode.helloPharma.app.Observateur.Observable;
import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.app.util.Security;
import com.klasscode.helloPharma.model.DAO;
import com.klasscode.helloPharma.model.bean.Compte;

public class CompteDao extends DAO<Compte> implements Observable {

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	@Override
	public boolean create(Compte compte) {
		// TODO Auto-generated method stub
		String requete = "INSERT INTO compte(username,password,privileges,date_creation) values(?,?,?,?);";
		try (PreparedStatement prepare = this.connection.prepareStatement(requete)) {
			String password = Security.encrypt(compte.getPassword());
			System.out.println(password);
//					DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
//					String dateRendezVous= df.format(date);
			prepare.setString(1, compte.getUsername());
			prepare.setString(2, password);
			prepare.setString(3, compte.getPrivileges());
			prepare.setObject(4, compte.getDateCreation());

			prepare.executeUpdate();
			notifyObserver("Compte Cree avec succes");
			return true;
		} catch (SQLException s) {
			JOptionPane.showMessageDialog(null, "Erreur Database " + s.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Compte compte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Compte compte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Compte search(int id) {

		Compte compte = new Compte();
		String query = "SELECT * FROM compte WHERE id=?";
		try (PreparedStatement prepare = this.connection.prepareStatement(query)) {

			prepare.setInt(1, id);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				compte = new Compte(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("privileges"), rs.getDate("date_creation"), rs.getInt("activate"));
			}
//			if(compte.getUsername()!=null) {
//				notifyObserver("Connexion Reussie");
//			}
			notifyObserver(compte.getUsername());
//			notifyObserver(compte.getUsername() + "-" + compte.getPrivileges() + "-" + compte.getActivate() + "-"
//					+ compte.getPassword() + "-" + compte.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erreur Database " + e.getMessage());
		}
		return compte;
	}

	@Override
	public ArrayList selectAll() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM compte";
		ArrayList<Compte> listCompte = new ArrayList<>();
		try (PreparedStatement p = this.connection.prepareStatement(query)) {
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				Compte m = search(id);
				listCompte.add(m);
			}
			return listCompte;
		} catch (SQLException e) {
			MessageUser.displayMessage("Erreur " + e.getMessage());
			return null;
		}
	}

	public int find(String username, String password) {
		int id = 0;
		System.out.println(username+ " "+ password);
		String query = "SELECT * FROM compte WHERE userName=? and password= ?";
		try (PreparedStatement prepare = this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			prepare.setString(1, username);
			prepare.setString(2, password);
			ResultSet result = prepare.executeQuery();
			if (result.first()) {
				id = result.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// JOptionPane.showMessageDialog(null, "Erreur sql\n" + e.getMessage(),"Erreur",
			// JOptionPane.ERROR_MESSAGE);
		}

		return id;
	}

	public boolean upgradePassword(String motDePasse, int id) {
		// TODO Auto-generated method stub
		String pass = Security.encrypt(motDePasse);

		String query = "update compte set password=? where id =?";
		try (PreparedStatement prepare = this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			prepare.setString(1, pass);
			prepare.setInt(2, id);

			prepare.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erreur sql\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}
	
	public boolean active(int id) {
		String query= "update compte set activate=1 where id =?";
		try(PreparedStatement prepare = this.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
		
			prepare.setInt(1, id);
			
			prepare.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erreur sql\n" + e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
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
		listObserver= new ArrayList<Observer>();
	}

	@Override
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		for(Observer o: listObserver) {
			o.update(str);
		}
		
	}

}
