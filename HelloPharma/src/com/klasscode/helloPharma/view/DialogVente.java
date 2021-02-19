package com.klasscode.helloPharma.view;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.controller.ControlerVenteMedoc;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogVente extends JDialog implements Observer {

	private final JPanel contentPanel = new JPanel();
	private ControlerVenteMedoc ctrl;
	private JTextField txtSearch;
	private JTextField txtMontantAchat;
	private JProgressBar progressBar = new JProgressBar();
	private JSpinner spinner = new JSpinner();
	private JList list;

	private int quantiteHt = 1;
	private int currentId;

	private JLabel lblNomMedicament;
	private JLabel lblPrixMedicament;
	private JLabel lblQtite;
	private JLabel lblDate;
	private JLabel lblTotalAchat;
	private JLabel lblMonnaieAchat;

	/**
	 * Create the dialog.
	 */

	public DialogVente(ControlerVenteMedoc ctrl) {

		this.ctrl = ctrl;
		setUndecorated(true);
		setModal(true);
		setSize(1100, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 1100, 45);
		getContentPane().add(panel);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Back Home", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					dispose();
					ctrl.reinitializeVente();
				}

			}
		});
		button.setIcon(
				new ImageIcon(DialogVente.class.getResource("/com/klasscode/helloPharma/ressources/unnamed.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		button.setBackground(new Color(30, 144, 255));

		JLabel lblVente = new JLabel("Vente");
		lblVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente.setForeground(Color.WHITE);
		lblVente.setFont(new Font("AnjaliOldLipi", Font.BOLD, 28));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(button).addGap(185)
						.addComponent(lblVente, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE).addGap(283)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGap(0, 45, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE).addComponent(
										lblVente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(30, 144, 255)));
		panel_1.setBounds(0, 44, 1100, 238);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				revalidateSearch();
			}
		});
		txtSearch.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setBounds(12, 22, 299, 33);
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String txt = txtSearch.getText().trim();
				if (!txt.equals("")) {
					progressBar.setValue(0);
					showMedocInfo(txt);
				}
			}
		});
		btnSearch.setBounds(322, 23, 117, 32);
		panel_1.add(btnSearch);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 79, 299, 116);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblNom.setBounds(0, 0, 70, 27);
		panel_2.add(lblNom);

		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblPrix.setBounds(0, 28, 70, 27);
		panel_2.add(lblPrix);

		JLabel lblQtiteDispo = new JLabel("Qtite dispo :");
		lblQtiteDispo.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtiteDispo.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblQtiteDispo.setBounds(0, 55, 80, 27);
		panel_2.add(lblQtiteDispo);

		JLabel lblDateExp = new JLabel("Date Exp :");
		lblDateExp.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateExp.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblDateExp.setBounds(10, 89, 93, 27);
		panel_2.add(lblDateExp);

		lblNomMedicament = new JLabel("NomMedicament");
		lblNomMedicament.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblNomMedicament.setForeground(new Color(0, 0, 0));
		lblNomMedicament.setBounds(144, 3, 155, 24);
		panel_2.add(lblNomMedicament);

		lblPrixMedicament = new JLabel("PrixMedicament");
		lblPrixMedicament.setForeground(Color.BLACK);
		lblPrixMedicament.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblPrixMedicament.setBounds(144, 29, 155, 24);
		panel_2.add(lblPrixMedicament);

		lblQtite = new JLabel("Qtite");
		lblQtite.setForeground(Color.BLACK);
		lblQtite.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblQtite.setBounds(144, 56, 155, 24);
		panel_2.add(lblQtite);

		lblDate = new JLabel("DateExp");
		lblDate.setEnabled(false);
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("AnjaliOldLipi", Font.BOLD, 13));
		lblDate.setBounds(144, 90, 155, 24);
		panel_2.add(lblDate);

		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(0, 128, 128));
		progressBar.setBounds(12, 53, 299, 20);
		panel_1.add(progressBar);

		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!txtSearch.getText().equals("") && !lblNomMedicament.getText().equals("")) {
					ctrl.addMedocJList(list, quantiteHt, currentId);
					lblTotalAchat.setText("HTG  " + ctrl.getPrixTotal());
				}
			}
		});
		lblAjouter.setFont(new Font("AnjaliOldLipi", Font.BOLD, 12));
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter
				.setIcon(new ImageIcon(DialogVente.class.getResource("/com/klasscode/helloPharma/ressources/add.png")));
		lblAjouter.setBounds(470, 67, 117, 39);
		panel_1.add(lblAjouter);

		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setBounds(12, 207, 70, 15);
		panel_1.add(lblQuantite);

		SpinnerModel value = new SpinnerNumberModel(1, 1, 100000, 1);
		spinner = new JSpinner(value);
		spinner.setBounds(84, 199, 227, 31);
		panel_1.add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantiteHt = (int) ((JSpinner) e.getSource()).getValue();
			}
		});

		JLabel lblPanierAchat = new JLabel("Panier Achat");
		lblPanierAchat.setIcon(
				new ImageIcon(DialogVente.class.getResource("/com/klasscode/helloPharma/ressources/panier.png")));
		lblPanierAchat.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblPanierAchat.setBounds(799, 12, 157, 31);
		panel_1.add(lblPanierAchat);

		JLabel lblEnlever = new JLabel("Enlever");
		lblEnlever.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedValue = (String) list.getSelectedValue();
				if (selectedValue != null)
					ctrl.removeMedoc(list, selectedValue, lblTotalAchat);
				else
					MessageUser.displayMessage("Selectionner un medicament de la liste");
			}
		});
		lblEnlever.setIcon(
				new ImageIcon(DialogVente.class.getResource("/com/klasscode/helloPharma/ressources/delete.png")));
		lblEnlever.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnlever.setFont(new Font("AnjaliOldLipi", Font.BOLD, 12));
		lblEnlever.setBounds(470, 118, 117, 39);
		panel_1.add(lblEnlever);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(30, 144, 255), null));
		panel_3.setBounds(700, 53, 339, 169);
		panel_3.setLayout(null);
		
		list = new JList();
		JScrollPane js = new JScrollPane();
		js.setBounds(0, 0, 339, 169);
		js.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel_3.add(js);
		panel_1.add(panel_3);

		JLabel lblEntrerMontant = new JLabel("Entrer Montant Achat");
		lblEntrerMontant.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblEntrerMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrerMontant.setForeground(Color.BLACK);
		lblEntrerMontant.setBounds(151, 305, 214, 34);
		getContentPane().add(lblEntrerMontant);

		txtMontantAchat = new JTextField();
		txtMontantAchat.setBounds(85, 351, 333, 34);
		getContentPane().add(txtMontantAchat);
		txtMontantAchat.setColumns(10);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String mv = txtMontantAchat.getText().trim();
				if (!mv.contentEquals("")) {
					if (ctrl.getPrixTotal() != 0.0d) {
						int option = JOptionPane.showConfirmDialog(null, "Confirmation Vente", "Confirmation",
								JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							ctrl.nouvelleVente(mv);
							lblMonnaieAchat.setText("HTG " + ctrl.getMonnaie());

						} else {
							ctrl.reinitializeVente();
							MessageUser.displayMessage("Annulation de la vente");
							refreshComponents();
						}

					} else {
						MessageUser.displayMessage("Aucun Medicament achete");
					}
				} else {
					MessageUser.displayMessage("Entrer un montant");
				}
			}
		});
		btnValider.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		btnValider.setBackground(new Color(46, 139, 87));
		btnValider.setForeground(Color.WHITE);
		btnValider.setBounds(197, 403, 123, 34);
		getContentPane().add(btnValider);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Confirmer l'annulation ? ", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					ctrl.reinitializeVente();
					refreshComponents();
				}

			}
		});
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		btnAnnuler.setBackground(new Color(255, 0, 0));
		btnAnnuler.setBounds(197, 454, 123, 34);
		getContentPane().add(btnAnnuler);

		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(757, 310, 92, 24);
		getContentPane().add(lblTotal);
		lblTotal.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));

		lblTotalAchat = new JLabel("HTG");
		lblTotalAchat.setBounds(871, 310, 217, 24);
		getContentPane().add(lblTotalAchat);
		lblTotalAchat.setForeground(new Color(0, 0, 0));
		lblTotalAchat.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));

		JLabel lblMonnaie = new JLabel("Monnaie :");
		lblMonnaie.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblMonnaie.setBounds(757, 360, 92, 24);
		getContentPane().add(lblMonnaie);

		lblMonnaieAchat = new JLabel("HTG");
		lblMonnaieAchat.setForeground(Color.BLACK);
		lblMonnaieAchat.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblMonnaieAchat.setBounds(871, 358, 217, 24);
		getContentPane().add(lblMonnaieAchat);

	}

	public void showMedocInfo(String name) {
		// TODO Auto-generated method stub
		int i = 0;
		Map<String, Object> medicament = ctrl.findMedoc(name);
		if (!medicament.isEmpty()) {
			if (((String) medicament.get("nom")) != null) {

				try {
					while (i <= 100) {
						// fill the menu bar
						progressBar.setValue(i + 10);

						// delay the thread
						i += 10;
						if (i == 100) {
							currentId = (int) medicament.get("id");
							lblNomMedicament.setText((String) medicament.get("nom"));
							lblPrixMedicament.setText("" + medicament.get("prix"));
							lblQtite.setText("" + medicament.get("quantite"));
							lblDate.setText((String) medicament.get("dateExp"));
						}
					}
				} catch (Exception e) {
					MessageUser.displayMessage("Erreur Chargement");
				}
			} else {
				MessageUser.displayMessage("informations inexistante");
			}
		} else {
			MessageUser.displayMessage("Les informations sur Ce medicament n'existe pas");
		}
	}

	public void revalidateSearch() {
		progressBar.setValue(0);
		lblNomMedicament.setText("");
		lblPrixMedicament.setText("0.0");
		lblQtite.setText("");
		lblDate.setText("");
	}

	public void refreshComponents() {

		revalidateSearch();
		spinner.setValue(1);
		list.repaint();
		lblTotalAchat.setText("HTG");
		lblMonnaieAchat.setText("HTG");
		txtMontantAchat.setText("");
		txtSearch.setText("");

	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub

		if (str.contentEquals("Vente Realise")) {
			MessageUser.successMessage("Vente Realise avec succes");
			ctrl.updateMedicament();
			ctrl.reinitializeVente();
			refreshComponents();

		}

	}
}
