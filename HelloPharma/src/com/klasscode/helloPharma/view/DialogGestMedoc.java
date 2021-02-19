package com.klasscode.helloPharma.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.klasscode.helloPharma.app.Observateur.Observable;
import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.MessageUser;
import com.klasscode.helloPharma.controller.ControlerMedicament;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogGestMedoc extends JDialog implements Observer, Observable {

	private final JPanel contentPanel = new JPanel();
	private JLabel label = new JLabel("");
	private String[] columnIdentifiers = { "Code", "Nom", "prix", "Quantite", "Date Expiration",
			"Date Enregistrement" };
	private ArrayList<Observer> listObserver = new ArrayList<>();
	private ControlerMedicament ctrl;
	private JTable table;
	private JTextField txtRecherche;
	private JPanel panelDetails = new JPanel();
	private JLabel lblComptMedicaments = new JLabel("");
	private JLabel lblComptMedocExp = new JLabel("");
	private long medCompt = 0;

	/**
	 * Create the dialog.
	 */
	public DialogGestMedoc(ControlerMedicament ctrl) {

		this.ctrl = ctrl;
		setUndecorated(true);
		setModal(true);
		setSize(1100, 550);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 1100, 45);
		getContentPane().add(panel);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyObserver("back to home");
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		button.setIcon(
				new ImageIcon(DialogGestMedoc.class.getResource("/com/klasscode/helloPharma/ressources/unnamed.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		button.setBackground(new Color(30, 144, 255));

		JLabel lblMedicament = new JLabel("Medicament");
		lblMedicament.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicament.setForeground(Color.WHITE);
		lblMedicament.setFont(new Font("AnjaliOldLipi", Font.BOLD, 28));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(button).addGap(213)
						.addComponent(lblMedicament, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE).addGap(283)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 52,
												Short.MAX_VALUE)
										.addComponent(lblMedicament, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setBounds(33, 57, 208, 69);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GRAY, null));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 65, 69);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		lblComptMedicaments = new JLabel("");
		lblComptMedicaments.setFont(new Font("AnjaliOldLipi", Font.BOLD, 17));
		lblComptMedicaments.setForeground(new Color(0, 0, 0));
		lblComptMedicaments.setHorizontalAlignment(SwingConstants.CENTER);
		lblComptMedicaments.setBounds(0, 12, 53, 45);
		panel_2.add(lblComptMedicaments);

		JLabel lblMedicamentsTotal = new JLabel("Medicaments");
		lblMedicamentsTotal.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblMedicamentsTotal.setForeground(new Color(255, 255, 255));
		lblMedicamentsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicamentsTotal.setBounds(68, 0, 140, 69);
		panel_1.add(lblMedicamentsTotal);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 0));
		panel_3.setBounds(287, 57, 208, 69);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(0, 0, 65, 69);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		lblComptMedocExp = new JLabel("");
		lblComptMedocExp.setHorizontalAlignment(SwingConstants.CENTER);
		lblComptMedocExp.setForeground(Color.BLACK);
		lblComptMedocExp.setFont(new Font("AnjaliOldLipi", Font.BOLD, 17));
		lblComptMedocExp.setBounds(0, 12, 53, 45);
		panel_4.add(lblComptMedocExp);

		JLabel lblMedicaments = new JLabel("Medicaments");
		lblMedicaments.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblMedicaments.setForeground(new Color(255, 255, 255));
		lblMedicaments.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicaments.setBounds(65, 0, 131, 30);
		panel_3.add(lblMedicaments);

		JLabel lblExpirs = new JLabel("Expires");
		lblExpirs.setForeground(new Color(255, 255, 255));
		lblExpirs.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpirs.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblExpirs.setBounds(65, 27, 131, 30);
		panel_3.add(lblExpirs);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(230, 230, 250));
		panel_7.setBounds(0, 135, 748, 415);
		getContentPane().add(panel_7);
		panel_7.setLayout(null);

		panelDetails = new JPanel();
		panelDetails.setBackground(Color.WHITE);
		panelDetails.setBounds(750, 45, 350, 505);
		panelDetails.setLayout(new BorderLayout());
		getContentPane().add(panelDetails);

		PanelDetailsMedoc pdm = new PanelDetailsMedoc(ctrl);
		panelDetails.add(pdm);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				String code[] = ((String) model.getValueAt(i, 0)).split("-");
				int id = Integer.parseInt(code[1]);

				pdm.showMedocInfo(id);
				panelDetails.revalidate();

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(0, 333, 850, -326);
		table.setFont(new Font("AnjaliOldLipi", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 750, 415);
		panel_7.add(scrollPane);

		txtRecherche = new JTextField();
		txtRecherche.setText("Search");
		txtRecherche.setBounds(535, 90, 203, 36);
		getContentPane().add(txtRecherche);
		txtRecherche.setColumns(10);

		txtRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtRecherche.getText();
				MyFilter(query);
			}
		});

		txtRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtRecherche.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtRecherche.setText("Search");
			}
		});
		this.addObserver(pdm);
		buildTable();
		setMedocCompteur();
	}

	public void buildTable() {

		DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
		modelTable.setRowCount(0);
		modelTable.setColumnIdentifiers(columnIdentifiers);
		ctrl.listerMedicament(table);

	}

	public void MyFilter(String query) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

	public void setMedocCompteur() {

		lblComptMedicaments.setText(ctrl.getTotal() + "");
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		if (str.equals("Medicament Enregistre")) {
			buildTable();
			setMedocCompteur();
		}
		if (str.equals("Modification Reussie")) {
			MessageUser.successMessage(str);
			buildTable();
			notifyObserver("Modification Reussie");
		}
		if (str.equals("Vente Realise")) {
			buildTable();
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
