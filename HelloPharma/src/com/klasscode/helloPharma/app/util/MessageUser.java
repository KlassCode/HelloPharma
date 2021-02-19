package com.klasscode.helloPharma.app.util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MessageUser {

	public static void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "HelloPharma", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void successMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.PLAIN_MESSAGE, new ImageIcon(
				MessageUser.class.getResource("/com/klasscode/helloPharma/ressources/chemistry_icon(4).png")));
	}
}
