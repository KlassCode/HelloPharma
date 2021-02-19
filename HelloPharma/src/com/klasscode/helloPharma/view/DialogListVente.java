package com.klasscode.helloPharma.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.controller.ControlerVenteMedoc;

public class DialogListVente extends JDialog implements Observer {

	private final JPanel contentPanel = new JPanel();
	private ControlerVenteMedoc ctrl;
	private JTextField txtSearch;
	private JTable tableVente;
	private String[] columnIdentifiers = { "CODE", "MONTANT TOTAL", "MONTANT VERSE", "MONNAIE", "DATE VENTE" };
	private JPanel panel_3 = new JPanel();
	private JPanel panel_4;
	private int i;

	/**
	 * Create the dialog.
	 */
	public DialogListVente(ControlerVenteMedoc ctrl) {

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
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		button.setIcon(
				new ImageIcon(DialogListVente.class.getResource("/com/klasscode/helloPharma/ressources/unnamed.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		button.setBackground(new Color(30, 144, 255));

		JLabel lblVente = new JLabel("Vente");
		lblVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente.setForeground(Color.WHITE);
		lblVente.setFont(new Font("AnjaliOldLipi", Font.BOLD, 28));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE).addGap(193)
						.addComponent(lblVente, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE).addGap(283)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING).addGap(0, 45, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44,
												Short.MAX_VALUE)
										.addComponent(lblVente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setBounds(40, 57, 220, 78);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder(null,
				new CompoundBorder(null, new EtchedBorder(EtchedBorder.RAISED, new Color(105, 105, 105), null))));
		panel_2.setBounds(0, 0, 66, 78);
		panel_1.add(panel_2);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setBackground(new Color(255, 255, 255));
		lblTotal.setBounds(65, 12, 155, 26);
		panel_1.add(lblTotal);

		JLabel lblVente_1 = new JLabel("Vente");
		lblVente_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente_1.setForeground(Color.WHITE);
		lblVente_1.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblVente_1.setBackground(Color.WHITE);
		lblVente_1.setBounds(65, 40, 155, 26);
		panel_1.add(lblVente_1);

		txtSearch = new JTextField();
		txtSearch.setText("Search");
		txtSearch.setColumns(10);
		txtSearch.setBounds(308, 77, 266, 39);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtSearch.getText();
				MyFilter(query);
				System.out.println(i);
			}
		});

		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtSearch.setText("Search");
			}
		});
		getContentPane().add(txtSearch);

		panel_3.setBounds(0, 145, 751, 405);
		getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout());

		panel_4 = new JPanel();
		
		PanelDetailVente detailVente = new PanelDetailVente(ctrl);
		panel_4.add(detailVente);
		
		panel_4.setBackground(new Color(192, 192, 192));
		panel_4.setBounds(751, 45, 349, 505);
		panel_4.setLayout(new BorderLayout());
		getContentPane().add(panel_4);
		
		

		tableVente = new JTable();
		tableVente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				i = tableVente.getSelectedRow();
				TableModel model = tableVente.getModel();
				String vente = model.getValueAt(i, 0).toString();
				System.out.println(vente);
				String[] tableCode = vente.split("-");
				int idVente = Integer.parseInt(tableCode[1]);
				System.out.println(i);
				detailVente.showVenteInfo(idVente);
				panel_4.revalidate();
			}
		});

		tableVente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tableVente);
		panel_3.add(scrollPane);

		buildTable();
	}

	public void buildTable() {
		DefaultTableModel modelTable = (DefaultTableModel) tableVente.getModel();
		modelTable.setRowCount(0);
		modelTable.setColumnIdentifiers(columnIdentifiers);
		ctrl.listerVente(tableVente);
	}

	public void MyFilter(String query) {

		DefaultTableModel model = (DefaultTableModel) tableVente.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		tableVente.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub

	}
}
