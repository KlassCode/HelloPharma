package com.klasscode.helloPharma.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.controller.ControlerMedicament;
import com.toedter.calendar.JDateChooser;

public class PanelDetailsMedoc extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */

	private ControlerMedicament ctrl;
	private JTextField txtNom;
	private JTextField txtPrix;
	private JLabel lblCodeMedoc = new JLabel("MED-");
	private JSpinner spinner = new JSpinner();
	private JDateChooser dateChooser = new JDateChooser();
	private JTextPane textPane = new JTextPane();
	private int qtite = 0;
	private String medocName;

	public PanelDetailsMedoc(ControlerMedicament ctrl) {
		setBackground(UIManager.getColor("Tree.textBackground"));
		this.ctrl = ctrl;
		setSize(338, 491);
		setLayout(null);

		lblCodeMedoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeMedoc.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblCodeMedoc.setBackground(new Color(30, 144, 255));
		lblCodeMedoc.setForeground(new Color(30, 144, 255));
		lblCodeMedoc.setBounds(0, 0, 338, 28);
		add(lblCodeMedoc);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Nom", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(30, 144, 255)));
		panel.setBounds(0, 40, 326, 65);
		add(panel);
		panel.setLayout(null);

		txtNom = new JTextField();
		txtNom.setEnabled(false);
		txtNom.setBounds(12, 24, 251, 34);
		panel.add(txtNom);
		txtNom.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtNom.getText().equals(""))
					txtNom.setEnabled(true);
			}
		});
		lblNewLabel.setIcon(new ImageIcon(PanelDetailsMedoc.class
				.getResource("/com/klasscode/helloPharma/ressources/96a2409a131aa7949ab57ff854cf8122-compose.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(270, 24, 44, 34);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Prix", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(30, 144, 255)));
		panel_1.setBounds(0, 117, 326, 65);
		add(panel_1);

		txtPrix = new JTextField();
		txtPrix.setEnabled(false);
		txtPrix.setColumns(10);
		txtPrix.setBounds(12, 24, 251, 34);
		panel_1.add(txtPrix);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtPrix.getText().equals(""))
					txtPrix.setEnabled(true);
			}
		});
		label.setIcon(new ImageIcon(PanelDetailsMedoc.class
				.getResource("/com/klasscode/helloPharma/ressources/96a2409a131aa7949ab57ff854cf8122-compose.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(270, 24, 44, 34);
		panel_1.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Quantite", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(30, 144, 255)));
		panel_2.setBounds(0, 194, 326, 65);
		add(panel_2);

		JLabel labelSpinner = new JLabel("");
		labelSpinner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				spinner.setEnabled(true);
			}
		});
		labelSpinner.setIcon(new ImageIcon(PanelDetailsMedoc.class
				.getResource("/com/klasscode/helloPharma/ressources/96a2409a131aa7949ab57ff854cf8122-compose.png")));
		labelSpinner.setHorizontalAlignment(SwingConstants.CENTER);
		labelSpinner.setBounds(270, 24, 44, 34);
		panel_2.add(labelSpinner);

		spinner.setEnabled(false);
		qtite = (int) spinner.getValue();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				qtite = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
		spinner.setBounds(12, 24, 252, 34);
		panel_2.add(spinner);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Date Expiration",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(30, 144, 255)));
		panel_3.setBounds(0, 262, 326, 65);
		add(panel_3);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dateChooser.getDate() != null)
					dateChooser.setEnabled(true);
			}
		});
		label_1.setIcon(new ImageIcon(PanelDetailsMedoc.class
				.getResource("/com/klasscode/helloPharma/ressources/96a2409a131aa7949ab57ff854cf8122-compose.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(270, 24, 44, 34);
		panel_3.add(label_1);

		dateChooser.setEnabled(false);
		dateChooser.setBounds(12, 24, 254, 29);
		panel_3.add(dateChooser);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Description", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(30, 144, 255)));
		panel_4.setBounds(0, 329, 326, 111);
		add(panel_4);

		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setEnabled(true);
			}
		});
		label_2.setIcon(new ImageIcon(PanelDetailsMedoc.class
				.getResource("/com/klasscode/helloPharma/ressources/96a2409a131aa7949ab57ff854cf8122-compose.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(270, 46, 44, 34);
		panel_4.add(label_2);

		textPane.setEnabled(false);
		textPane.setBounds(12, 23, 255, 76);
		panel_4.add(textPane);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(0, 437, 326, 54);
		add(panel_5);
		panel_5.setLayout(null);

		JButton button = new JButton("Valider");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rep = JOptionPane.showConfirmDialog(null, "Confirmer la modification ..", "Arret Programme",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				if (rep == JOptionPane.YES_OPTION) {
					if (!lblCodeMedoc.getText().equals("MED-")) {
						String[] convert = lblCodeMedoc.getText().split("-");
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String dateExpiration = df.format(dateChooser.getDate());

						try {
							ctrl.callUpdate(Integer.parseInt(convert[1]), txtNom.getText().trim(),
									Double.parseDouble(txtPrix.getText().trim()), qtite, textPane.getText().trim(),
									dateExpiration, medocName);
						} catch (NumberFormatException e) {
							MessageUser.displayMessage("Prix Incorrect");
						}
					} else {
						MessageUser.displayMessage("Selectionner le Medicament");
					}
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(22, 12, 113, 36);
		panel_5.add(button);

		JButton button_1 = new JButton("Annuler");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeState();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		button_1.setBackground(Color.RED);
		button_1.setBounds(183, 12, 113, 36);
		panel_5.add(button_1);

	}

	public void showMedocInfo(int code) {
		// TODO Auto-generated method stub
		Map<String, Object> medicament = ctrl.getInfosMedicament(code);
		if (!medicament.isEmpty()) {
			lblCodeMedoc.setText("MED-" + medicament.get("id"));
			txtNom.setText((String) medicament.get("nom"));
			medocName = txtNom.getText().trim();
			txtPrix.setText("" + medicament.get("prix"));
			spinner.setValue(medicament.get("quantite"));
			try {
				dateChooser.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) medicament.get("dateExp")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				MessageUser.displayMessage("Erreur Conversion Date");
			}
			if (medicament.containsKey("desc"))
				textPane.setText((String) medicament.get("desc"));
		} else {
			MessageUser.displayMessage("Les informations sur Ce medicament n'existe pas");
		}
	}

	public void changeState() {
		txtNom.setEnabled(false);
		txtPrix.setEnabled(false);
		spinner.setEnabled(false);
		dateChooser.setEnabled(false);
		textPane.setEnabled(false);
	}

	public void reinitialize() {
		lblCodeMedoc.setText("MED-");
		txtNom.setText("");
		txtPrix.setText("");
		spinner.setValue(0);
		dateChooser.setDate(null);
		textPane.setText("");
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		if (str.equals("Modification Reussie")) {
			reinitialize();
			changeState();
		}
		if (str.equals("back to home")) {
			reinitialize();
			changeState();
		}
	}
}
