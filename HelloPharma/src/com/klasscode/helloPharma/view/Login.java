package com.klasscode.helloPharma.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.klasscode.helloPharma.app.Observateur.Observer;
import com.klasscode.helloPharma.app.util.Security;

public class Login extends JFrame implements Observer{

	private ControlerCompte controler;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setUndecorated(true);
		setTitle("Hello-Pharma Connexion");
		setSize(350,500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTextLogin = new JPanel();
		panelTextLogin.setBackground(new Color(7, 98, 140));
		panelTextLogin.setBounds(0, 0, 350, 42);
		contentPane.add(panelTextLogin);
		panelTextLogin.setLayout(null);
		
		JLabel lblConnexion = new JLabel("Connexion");
		lblConnexion.setFont(new Font("AnjaliOldLipi", Font.BOLD, 21));
		lblConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexion.setForeground(new Color(255, 255, 255));
		lblConnexion.setBounds(12, 0, 326, 42);
		panelTextLogin.add(lblConnexion);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(255, 255, 255));
		label.setIcon(new ImageIcon(Login.class.getResource("/com/klasscode/helloPharma/ressources/chemistry_icon(2).png")));
		label.setBounds(0, 42, 350, 133);
		contentPane.add(label);
		
		JLabel label_login = new JLabel("");
		label_login.setHorizontalAlignment(SwingConstants.CENTER);
		label_login.setIcon(new ImageIcon(Login.class.getResource("/com/klasscode/helloPharma/ressources/user-login-man-person-location-place-geo-point-50935.png")));
		label_login.setBounds(57, 234, 59, 34);
		contentPane.add(label_login);
		
		JLabel label_password = new JLabel("");
		label_password.setHorizontalAlignment(SwingConstants.CENTER);
		label_password.setIcon(new ImageIcon(Login.class.getResource("/com/klasscode/helloPharma/ressources/login.png")));
		label_password.setBounds(57, 295, 59, 34);
		contentPane.add(label_password);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(129, 234, 194, 34);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(129, 295, 194, 34);
		contentPane.add(txtPassword);
		
		JLabel lblHelloPharma = new JLabel("Hello Pharma");
		lblHelloPharma.setFont(new Font("AnjaliOldLipi", Font.BOLD, 17));
		lblHelloPharma.setForeground(new Color(7, 98, 140));
		lblHelloPharma.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloPharma.setBounds(0, 172, 350, 34);
		contentPane.add(lblHelloPharma);
		
		JButton btnLogin = new JButton("se connecter");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String password= new String(txtPassword.getPassword());
				String pass= Security.encrypt(password);
				controler.seConnecter(txtPseudo.getText().trim(),pass);
			
			}
		});
		btnLogin.setBackground(new Color(7, 98, 140));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		btnLogin.setBounds(57, 364, 252, 34);
		contentPane.add(btnLogin);
		
		JLabel lblKlasscodeprod = new JLabel("klassCode-Prod");
		lblKlasscodeprod.setHorizontalAlignment(SwingConstants.CENTER);
		lblKlasscodeprod.setFont(new Font("AnjaliOldLipi", Font.BOLD, 14));
		lblKlasscodeprod.setBounds(57, 466, 252, 34);
		contentPane.add(lblKlasscodeprod);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFermer.setForeground(Color.WHITE);
		btnFermer.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		btnFermer.setBackground(new Color(255, 0, 0));
		btnFermer.setBounds(57, 410, 252, 34);
		contentPane.add(btnFermer);
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		System.out.println(str);
	}
}
