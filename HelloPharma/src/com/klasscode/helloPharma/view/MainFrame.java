package com.klasscode.helloPharma.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import com.klasscode.helloPharma.controller.ControlerMedicament;
import com.klasscode.helloPharma.controller.ControlerVenteMedoc;
import com.klasscode.helloPharma.model.dao.implement.MedicamentDao;
import com.klasscode.helloPharma.model.dao.implement.VenteDao;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private MedicamentDao model = new MedicamentDao();
	private VenteDao modelVente = new VenteDao();

	private ControlerMedicament ctrl = new ControlerMedicament(model);
	private ControlerVenteMedoc ctrlVente = new ControlerVenteMedoc(modelVente);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}
		// setUndecorated(true);
		setTitle("Hello-Pharma Home");
		setSize(1000, 570);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);

		// initialize view
		DialogNewMedoc dnm = new DialogNewMedoc(ctrl);
		model.addObserver(dnm);

		DialogGestMedoc dgm = new DialogGestMedoc(ctrl);
		model.addObserver(dgm);

		DialogListVente dlv = new DialogListVente(ctrlVente);
		modelVente.addObserver(dlv);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), new Color(30, 144, 255), null, null),
				null));

		mainPanel = new JPanel();

		mainPanel.setBackground(new Color(245, 247, 251));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
				.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)));

		JLabel lblWelcomeUser = new JLabel("Welcome,");
		lblWelcomeUser.setForeground(new Color(128, 128, 128));
		lblWelcomeUser.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		lblUser.setForeground(new Color(0, 0, 0));

		JPanel panel_medoc = new JPanel();

		panel_medoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dnm.setVisible(true);
			}
		});
		panel_medoc.setBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null));
		panel_medoc.setBackground(new Color(255, 255, 255));

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dgm.setVisible(true);
			}
		});
		panel_1.setBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null));
		panel_1.setBackground(new Color(255, 255, 255));

		JLabel lblListeMedicaments = new JLabel("Liste Medicaments");
		lblListeMedicaments.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeMedicaments.setFont(new Font("Jamrul", Font.BOLD, 14));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(
				new ImageIcon(MainFrame.class.getResource("/com/klasscode/helloPharma/ressources/catalog-icon.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 215, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblListeMedicaments, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING).addGap(0, 179, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 113, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblListeMedicaments,
												GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogVente dv = new DialogVente(ctrlVente);
				modelVente.removeObserver();
				modelVente.addObserver(dv);
				modelVente.addObserver(dgm);
				modelVente.addObserver(dlv);
				
				dv.setVisible(true);
			}
		});
		panel_3.setBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null));
		panel_3.setBackground(new Color(255, 255, 255));

		JLabel lblVente = new JLabel("Vente");
		lblVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente.setFont(new Font("Jamrul", Font.BOLD, 14));

		JLabel label_3 = new JLabel("");
		label_3.setIcon(
				new ImageIcon(MainFrame.class.getResource("/com/klasscode/helloPharma/ressources/payroll-icon.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0, 215, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(lblVente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblVente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dlv.setVisible(true);
			}
		});
		panel_4.setBorder(new CompoundBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null), null));
		panel_4.setBackground(Color.WHITE);

		JLabel lblListeVentes = new JLabel("Liste Ventes");
		lblListeVentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeVentes.setFont(new Font("Jamrul", Font.BOLD, 14));

		JLabel label_2 = new JLabel("");
		label_2.setIcon(
				new ImageIcon(MainFrame.class.getResource("/com/klasscode/helloPharma/ressources/512x512bb.jpg")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGap(0, 215, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(lblListeVentes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_4
				.setVerticalGroup(
						gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 125, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblListeVentes,
												GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		panel_4.setLayout(gl_panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null));
		panel_5.setBackground(Color.WHITE);

		JLabel lblRapport = new JLabel("Rapport");
		lblRapport.setHorizontalAlignment(SwingConstants.CENTER);
		lblRapport.setFont(new Font("Jamrul", Font.BOLD, 14));

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(MainFrame.class
				.getResource("/com/klasscode/helloPharma/ressources/1486564180-finance-financial-report_81493.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGap(0, 215, Short.MAX_VALUE)
				.addGroup(gl_panel_5.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRapport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_5
				.setVerticalGroup(
						gl_panel_5.createParallelGroup(Alignment.TRAILING).addGap(0, 179, Short.MAX_VALUE)
								.addGroup(gl_panel_5.createSequentialGroup().addContainerGap()
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 113, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblRapport,
												GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		panel_5.setLayout(gl_panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new CompoundBorder(new CompoundBorder(
				new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), null), null), null),
				null));
		panel_6.setBackground(Color.WHITE);

		JLabel lblAdminPanel = new JLabel("Admin Panel");
		lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPanel.setFont(new Font("Jamrul", Font.BOLD, 14));

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(MainFrame.class
				.getResource("/com/klasscode/helloPharma/ressources/financial-admin-icon-blue-v2-262x300.png")));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGap(0, 215, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(lblAdminPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup().addComponent(label_5)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAdminPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_6.setLayout(gl_panel_6);
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup().addGap(12)
						.addComponent(lblWelcomeUser, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(772, Short.MAX_VALUE))
				.addGroup(
						gl_mainPanel.createSequentialGroup().addGap(46)
								.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
										.addComponent(panel_medoc, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
								.addGap(110)
								.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
								.addGap(115)
								.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
										.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
								.addGap(84)));
		gl_mainPanel.setVerticalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWelcomeUser, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_medoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
				.addGap(27)));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(MainFrame.class.getResource(
				"/com/klasscode/helloPharma/ressources/347-3476100_chemistry-project-cover-page-design-hd-png-download.png")));

		JLabel lblMedicaments = new JLabel("Nouveau Medicament");
		lblMedicaments.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicaments.setFont(new Font("Jamrul", Font.BOLD, 14));
		GroupLayout gl_panel_medoc = new GroupLayout(panel_medoc);
		gl_panel_medoc.setHorizontalGroup(gl_panel_medoc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_medoc.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_medoc.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMedicaments, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_medoc
				.setVerticalGroup(
						gl_panel_medoc.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_medoc.createSequentialGroup().addContainerGap()
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 113, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblMedicaments,
												GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		panel_medoc.setLayout(gl_panel_medoc);
		mainPanel.setLayout(gl_mainPanel);

		JLabel lblHellopharma = new JLabel("HelloPharma");
		lblHellopharma.setForeground(new Color(30, 144, 255));
		lblHellopharma.setFont(new Font("AnjaliOldLipi", Font.BOLD, 17));
		lblHellopharma.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(
				MainFrame.class.getResource("/com/klasscode/helloPharma/ressources/chemistry_icon(4).png")));

		JLabel lblUseronline = new JLabel("User-Online");
		lblUseronline.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(MainFrame.class
				.getResource("/com/klasscode/helloPharma/ressources/free-user-login-icon-305-thumb(2).png")));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(17).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblHellopharma, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addGap(463)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblUseronline, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblHellopharma,
						GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblUseronline,
						GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE).addContainerGap()));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rep = JOptionPane.showConfirmDialog(null, "confirmer la fermetture de la session",
						"Arret Programme", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				if (rep == JOptionPane.YES_OPTION)
					dispose();
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(
				new ImageIcon(MainFrame.class.getResource("/com/klasscode/helloPharma/ressources/out-icon-28.jpg")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap().addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2,
				GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE));
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}
