package com.klasscode.helloPharma.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.controller.ControlerMedicament;
import com.klasscode.helloPharma.model.dao.implement.MedicamentDao;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogNewMedoc extends JDialog implements Observer {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_nom;
	private JTextField txt_prix;
	private JTextField txtNom;
	private JTextField txtPrix;
	private JDateChooser dateExp;
	private JTextPane txtDesc;
	private JSpinner spinQtite;
	private int qtite;
	private ControlerMedicament ctrl;

	/**
	 * Create the dialog.
	 */
	public DialogNewMedoc(ControlerMedicament ctrl) {
		this.ctrl = ctrl;
		setUndecorated(true);
		setModal(true);
		setSize(850, 484);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 850, 48);
		getContentPane().add(panel);

		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reinitialize("simple");
				dispose();
			}
		});
		button.setIcon(
				new ImageIcon(DialogNewMedoc.class.getResource("/com/klasscode/helloPharma/ressources/unnamed.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		button.setBackground(new Color(30, 144, 255));

		JLabel label = new JLabel("Nouveau Medicament");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("AnjaliOldLipi", Font.BOLD, 28));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(button).addGap(241)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE).addGap(283)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup().addGap(4).addComponent(button,
								GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JLabel label_1 = new JLabel("Nom");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		label_1.setBounds(110, 100, 90, 32);
		getContentPane().add(label_1);

		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(246, 98, 270, 32);
		getContentPane().add(txtNom);

		JLabel label_2 = new JLabel("Prix");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		label_2.setBounds(110, 159, 90, 32);
		getContentPane().add(label_2);

		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(246, 157, 270, 32);
		getContentPane().add(txtPrix);

		JLabel label_3 = new JLabel("Quantite");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		label_3.setBounds(110, 213, 90, 32);
		getContentPane().add(label_3);

		spinQtite = new JSpinner();
		qtite = (int) spinQtite.getValue();
		spinQtite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				qtite = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
		spinQtite.setBounds(246, 213, 270, 32);
		getContentPane().add(spinQtite);

		JLabel label_4 = new JLabel("Date Expiration");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		label_4.setBounds(87, 270, 113, 32);
		getContentPane().add(label_4);

		dateExp = new JDateChooser();
		dateExp.setBounds(246, 270, 270, 32);
		getContentPane().add(dateExp);

		JLabel label_5 = new JLabel("Description");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		label_5.setBounds(74, 322, 113, 32);
		getContentPane().add(label_5);

		txtDesc = new JTextPane();
		txtDesc.setBackground(new Color(105, 105, 105));
		txtDesc.setBounds(243, 327, 273, 90);
		getContentPane().add(txtDesc);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ctrl.enregistrerMedoc(txtNom.getText().trim(), txtPrix.getText().trim(), qtite,
						txtDesc.getText().trim(), dateExp.getDate());
				
			}
		});
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		btnValider.setBackground(new Color(30, 144, 255));
		btnValider.setBounds(358, 436, 113, 36);
		getContentPane().add(btnValider);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFields() == false) {
					reinitialize("complexe");
				}
			}
		});
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		btnAnnuler.setBackground(Color.RED);
		btnAnnuler.setBounds(532, 436, 113, 36);
		getContentPane().add(btnAnnuler);

	}

	public boolean checkFields() {
		boolean isEmpty = true;
		String nom = txtNom.getText().trim();
		String prix = txtPrix.getText().trim();
		String desc = txtDesc.getText().trim();

		if (!nom.equals("") || prix.equals("") || qtite != 0 || desc.equals("") || dateExp != null) {
			isEmpty = false;
		}
		return isEmpty;
	}

	public void reinitialize(String type) {
		if (type.equals("simple")) {
			txtNom.setText("");
			txtPrix.setText("");
			spinQtite.setValue(0);
			txtDesc.setText("");
			dateExp.setDate(null);
		} else {
			int response = JOptionPane.showConfirmDialog(null, "Confirmer l'annulation", "Annulation",
					JOptionPane.OK_CANCEL_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				txtNom.setText("");
				txtPrix.setText("");
				spinQtite.setValue(0);
				txtDesc.setText("");
				dateExp.setDate(null);
			}
		}

	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		if (str.equals("Medicament Enregistre")) {
			MessageUser.successMessage("Medicament enregistre avec Succes");
			reinitialize("simple");
			this.dispose();
		}
	}
}
