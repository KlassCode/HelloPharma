package com.klasscode.helloPharma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyledDocument;

import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.controller.ControlerVenteMedoc;
import javax.swing.JToggleButton;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PanelDetailVente extends JPanel {
	private ControlerVenteMedoc ctrl;
	private JLabel lblNoVente;
	private JPanel panel;
	private JLabel lblMontantTotal;
	private JLabel lblMontantverse;
	private JLabel lblMonnaie;
	private JLabel lblDateAchat;
	private JPanel panel_1;
	private JLabel lblMedicaments;
	private JLabel afficheMonnaie;
	private JLabel afficheDate ;
	private JLabel afficheMontantTotal ;
	private JLabel afficheMontantVerse;
	private JList list;
	
	
	public PanelDetailVente(ControlerVenteMedoc ctrl) {
		this.ctrl=ctrl;
		setSize(349, 505);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 5, 349, 37);
		add(panel);
		panel.setLayout(null);
		
		lblNoVente = new JLabel("No vente");
		lblNoVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoVente.setForeground(new Color(255, 255, 255));
		lblNoVente.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblNoVente.setBounds(12, 0, 325, 38);
		panel.add(lblNoVente);
		lblNoVente.setBackground(new Color(30, 144, 255));
		
		lblMontantTotal = new JLabel("Montant Total");
		lblMontantTotal.setForeground(new Color(0, 0, 0));
		lblMontantTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantTotal.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblMontantTotal.setBounds(12, 86, 140, 31);
		add(lblMontantTotal);
		
		lblMontantverse = new JLabel("MontantVerse");
		lblMontantverse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantverse.setForeground(Color.BLACK);
		lblMontantverse.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblMontantverse.setBounds(12, 129, 140, 31);
		add(lblMontantverse);
		
		lblMonnaie = new JLabel("Monnaie");
		lblMonnaie.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonnaie.setForeground(Color.BLACK);
		lblMonnaie.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblMonnaie.setBounds(12, 171, 140, 31);
		add(lblMonnaie);
		
		lblDateAchat = new JLabel("Date");
		lblDateAchat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateAchat.setForeground(Color.BLACK);
		lblDateAchat.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblDateAchat.setBounds(12, 214, 140, 31);
		add(lblDateAchat);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(0, 257, 349, 37);
		add(panel_1);
		
		lblMedicaments = new JLabel("Medicaments");
		lblMedicaments.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicaments.setForeground(Color.WHITE);
		lblMedicaments.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblMedicaments.setBackground(new Color(30, 144, 255));
		lblMedicaments.setBounds(12, 0, 325, 38);
		panel_1.add(lblMedicaments);
		
		afficheMontantTotal = new JLabel("");
		afficheMontantTotal.setHorizontalAlignment(SwingConstants.CENTER);
		afficheMontantTotal.setForeground(Color.BLACK);
		afficheMontantTotal.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		afficheMontantTotal.setBounds(164, 86, 140, 31);
		add(afficheMontantTotal);
		
		afficheMontantVerse = new JLabel("");
		afficheMontantVerse.setHorizontalAlignment(SwingConstants.CENTER);
		afficheMontantVerse.setForeground(Color.BLACK);
		afficheMontantVerse.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		afficheMontantVerse.setBounds(164, 129, 140, 31);
		add(afficheMontantVerse);
		
		afficheMonnaie = new JLabel("");
		afficheMonnaie.setHorizontalAlignment(SwingConstants.CENTER);
		afficheMonnaie.setForeground(Color.BLACK);
		afficheMonnaie.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		afficheMonnaie.setBounds(164, 171, 140, 31);
		add(afficheMonnaie);
		
		afficheDate = new JLabel("");
		afficheDate.setHorizontalAlignment(SwingConstants.CENTER);
		afficheDate.setForeground(Color.BLACK);
		afficheDate.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		afficheDate.setBounds(127, 214, 210, 31);
		add(afficheDate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 306, 339, 199);
		panel_3.setLayout(null);
		
		list = new JList<String>();
		JScrollPane js = new JScrollPane();
		js.setBounds(0, 195, 339, -193);
		js.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel_3.add(js);
		add(panel_3);
		
//		SimpleAttributeSet keyWord = new SimpleAttributeSet();
//		StyleConstants.setForeground(keyWord, Color.BLACK);
//		// StyleConstants.setBackground(keyWord, new Color(26,114,182));
//		for (Medicament m : v.getListMedicament()) {
//			try {
//				sd.insertString(sd.getLength(), m.getNom() + "\n", keyWord);
//			} catch (BadLocationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}
	public void showVenteInfo(int code) {
		// TODO Auto-generated method stub
		
		Map<String, Object> v = ctrl.getInfosVente(code);
		if (!v.isEmpty()) {
			
			lblNoVente.setText("VNT-" + v.get("id"));
			afficheDate.setText(""+v.get("dateVente"));
			afficheMonnaie.setText("HTG " + v.get("monnaie"));
			afficheMontantVerse.setText("HTG " + v.get("montantVerse"));
			afficheMontantTotal.setText("HTG " + v.get("montantTotal"));
			Map<String,Integer> qtiteHt = (Map<String, Integer>) v.get("qtite Ht");
			//textPane.setText("");
			
			Vector<String> vc = new Vector();
			for(String s:qtiteHt.keySet()) {
				vc.add(s+" "+ qtiteHt.get(s));
			}
			
			//sd.insertString(sd.getLength(), s + " : " +qtiteHt.get(s)+ "\n", keyWord);
			System.out.println(vc.size());
			list.setListData(vc);
			
		} else {
			MessageUser.displayMessage("informations inexistantes");
		}
	}
}
