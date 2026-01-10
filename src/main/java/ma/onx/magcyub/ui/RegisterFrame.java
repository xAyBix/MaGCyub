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

import ma.onx.magcyub.utils.Constants;

/**
 * Contains the register frame
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-10 03:09
 * 
 */
public class RegisterFrame extends JFrame{

	private static final long serialVersionUID = 286152628570753547L;
	public static RegisterFrame registerFrame;
	
	private JTextField rootFileField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JLabel messageLabel;
	
    public RegisterFrame () {
        setTitle(Constants.APP_NAME + " - Register");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Modern green color palette
        Color darkGreen = new Color(34, 139, 34);      // Forest green
        Color bgGreen = new Color(240, 255, 240);      // Honeydew
        Color accentGreen = new Color(46, 184, 92);    // Modern green
        Color textColor = new Color(25, 25, 25);       // Dark text
        Color warningBg = new Color(255, 243, 205);    // Light yellow
        Color warningBorder = new Color(255, 193, 7);  // Amber
        
        // Set background
        getContentPane().setBackground(bgGreen);

        // Create components
        JLabel titleLabel = new JLabel("MaGCyub", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        titleLabel.setForeground(darkGreen);

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
        warningPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(warningBorder, 2),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        
        // Warning icon (⚠)
        JLabel warningIcon = new JLabel("⚠", SwingConstants.CENTER);
        warningIcon.setFont(new Font("SansSerif", Font.BOLD, 20));
        warningIcon.setForeground(new Color(255, 152, 0));
        warningPanel.add(warningIcon, BorderLayout.WEST);
        
        // Warning text
        JTextArea warningText = new JTextArea(
            "Remember your root file path and password!\n" +
            "There is no recovery option. If forgotten, you will lose your personal space forever."
        );
        warningText.setFont(new Font("SansSerif", Font.PLAIN, 12));
        warningText.setForeground(new Color(102, 60, 0));
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
        rootFileField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentGreen, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rootFileField.setBackground(Color.WHITE);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor);
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(250, 35));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentGreen, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passwordField.setBackground(Color.WHITE);
        
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(textColor);
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        confirmPasswordField.setPreferredSize(new Dimension(250, 35));
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentGreen, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        confirmPasswordField.setBackground(Color.WHITE);
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        registerButton.setBackground(accentGreen);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setPreferredSize(new Dimension(140, 40));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect for button
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(darkGreen);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(accentGreen);
            }
        });
        
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(new Color(220, 53, 69)); // Modern red
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        // Layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(bgGreen);
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

        // Confirm Password label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        mainPanel.add(confirmPasswordLabel, gbc);

        // Confirm Password field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        mainPanel.add(confirmPasswordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(20, 8, 5, 8);
        mainPanel.add(registerButton, gbc);

        // Message label
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 8, 10, 8);
        mainPanel.add(messageLabel, gbc);

        add(mainPanel);

        setVisible(true);
    }
	
	
}
