// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ma.onx.magcyub.checks.Checks;
import ma.onx.magcyub.components.PersonalSpace;
import ma.onx.magcyub.utils.Constants;

/**
 * Contains the login frame
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-10 02:30
 * 
 */
public class LoginFrame extends JFrame {
	private int attempts = 0;
	private static final long serialVersionUID = -4433781974231405897L;
	public static LoginFrame loginFrame;

	private JTextField rootFileField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel messageLabel;

	public LoginFrame() {
		setTitle(Constants.APP_NAME + " - Login");
		setSize(450, 450);
		setIconImage(Constants.IMG);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		// Dark mode green color palette
		Color darkBg = new Color(18, 18, 18); // Very dark background
		Color accentGreen = new Color(46, 184, 92); // Bright green accent
		Color darkGreen = new Color(34, 139, 34); // Forest green
		Color textColor = new Color(220, 220, 220); // Light gray text
		Color inputBg = new Color(40, 40, 40); // Input field background
		Color warningBg = new Color(45, 35, 20); // Dark yellow background
		Color warningBorder = new Color(200, 150, 50); // Amber border
		Color warningTextC = new Color(255, 200, 100); // Light amber text

		// Set background
		getContentPane().setBackground(darkBg);

		// Create components
		JLabel titleLabel = new JLabel("MaGCyub", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
		titleLabel.setForeground(accentGreen);

		// Add icon to title label
		try {
			ImageIcon icon = new ImageIcon(Constants.IMG);
			Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
			titleLabel.setIcon(new ImageIcon(img));
			titleLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
			titleLabel.setIconTextGap(10);
		} catch (Exception e) {
			System.err.println("Could not load icon: " + e.getMessage());
		}

		// Warning note panel
		JPanel warningPanel = new JPanel(new BorderLayout(8, 0));
		warningPanel.setBackground(warningBg);
		warningPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(warningBorder, 2),
				BorderFactory.createEmptyBorder(10, 12, 10, 12)));

		// Warning icon (⚠)
		JLabel warningIcon = new JLabel("⚠", SwingConstants.CENTER);
		warningIcon.setFont(new Font("SansSerif", Font.BOLD, 20));
		warningIcon.setForeground(new Color(255, 180, 60));
		warningPanel.add(warningIcon, BorderLayout.WEST);

		// Warning text
		JTextArea warningText = new JTextArea(
				"You have only 3 login attempts!\n" + "After 3 failed attempts, the personal space will be destroyed.");
		warningText.setFont(new Font("SansSerif", Font.PLAIN, 12));
		warningText.setForeground(warningTextC);
		warningText.setBackground(warningBg);
		warningText.setEditable(false);
		warningText.setLineWrap(true);
		warningText.setWrapStyleWord(true);
		warningText.setFocusable(false);
		warningPanel.add(warningText, BorderLayout.CENTER);

		JLabel __rootFileLabel__ = new JLabel("Root file:");
		__rootFileLabel__.setForeground(textColor);
		__rootFileLabel__.setFont(new Font("SansSerif", Font.PLAIN, 14));

		rootFileField = new JTextField(20);
		rootFileField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		rootFileField.setPreferredSize(new Dimension(250, 35));
		rootFileField.setBackground(inputBg);
		rootFileField.setForeground(textColor);
		rootFileField.setCaretColor(accentGreen);
		rootFileField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(accentGreen, 2),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(textColor);
		passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

		passwordField = new JPasswordField(20);
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		passwordField.setPreferredSize(new Dimension(250, 35));
		passwordField.setBackground(inputBg);
		passwordField.setForeground(textColor);
		passwordField.setCaretColor(accentGreen);
		passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(accentGreen, 2),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		loginButton = new JButton("Login");
		loginButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		loginButton.setBackground(accentGreen);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setPreferredSize(new Dimension(140, 40));
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Hover effect for button
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(darkGreen);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(accentGreen);
			}
		});
		
		loginButton.addActionListener(e -> {
			attempts ++;
			// Check if credentials are correct
			if (Checks.checkCredentials(rootFileField.getText(), passwordField.getPassword())) {
				new MainFrame();
				dispose();
			}else if(attempts==3) {
				PersonalSpace.destroy();
				System.exit(1);
			}else {
				passwordField.setText("");
				messageLabel.setText("Wrong credentials. You have " + (3-attempts) + " more attempts!");
			}
		});

		messageLabel = new JLabel("", SwingConstants.CENTER);
		messageLabel.setForeground(new Color(255, 100, 100)); // Light red for dark mode
		messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

		// Layout
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(darkBg);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);

		// Title
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 8, 15, 8);
		mainPanel.add(titleLabel, gbc);

		// Warning panel
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 8, 15, 8);
		mainPanel.add(warningPanel, gbc);

		// Root file label
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 8, 5, 8);
		mainPanel.add(__rootFileLabel__, gbc);

		// Root file field
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		mainPanel.add(rootFileField, gbc);

		// Password label
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		mainPanel.add(passwordLabel, gbc);

		// Password field
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		mainPanel.add(passwordField, gbc);

		// Login button
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.insets = new Insets(20, 8, 5, 8);
		mainPanel.add(loginButton, gbc);

		// Message label
		gbc.gridy = 5;
		gbc.insets = new Insets(5, 8, 10, 8);
		mainPanel.add(messageLabel, gbc);

		add(mainPanel);

		setVisible(true);
	}

}
