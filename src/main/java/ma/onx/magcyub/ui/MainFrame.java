// MaGCyub
// Copyright (C) 2026 Akram BELBEKRI
// Original creator of the software
// Licensed under GNU GPL v3

package ma.onx.magcyub.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import ma.onx.magcyub.utils.Constants;
import ma.onx.magcyub.utils.FileTable;
import ma.onx.magcyub.utils.PopulateTree;

/**
 * Contains the main frame
 * 
 * @author Akram BELBEKRI
 * @since 2026-01-09 18:34
 * 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -8830760309829138731L;
	public static MainFrame mainFrame;

	public MainFrame() {
		config();
		setResizable(true);

		// Dark mode green color palette
		Color darkBg = new Color(18, 18, 18);           // Very dark background
		Color cardDarkBg = new Color(30, 30, 30);       // Dark card background
		Color accentGreen = new Color(46, 184, 92);     // Bright green accent
		Color darkGreen = new Color(34, 139, 34);       // Forest green
		Color lightGreen = new Color(144, 238, 144);    // Light green for text
		Color textColor = new Color(220, 220, 220);     // Light gray text
		Color dimText = new Color(160, 160, 160);       // Dimmed text
		Color tabBg = new Color(40, 40, 40);            // Tab background
		Color borderColor = new Color(60, 60, 60);      // Border color

		// Set background
		getContentPane().setBackground(darkBg);
		setLayout(new BorderLayout());

		// Main content area with centered card
		JPanel contentArea = new JPanel(new GridBagLayout());
		contentArea.setBackground(darkBg);
		contentArea.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// Card container with CardLayout for switching pages
		JPanel cardContainer = new JPanel(new CardLayout());
		cardContainer.setPreferredSize(new Dimension(700, 400));
		cardContainer.setMaximumSize(new Dimension(800, 500));

		// Create the three pages as cards
		JPanel settingsCard = createCard("Settings", darkBg, textColor, dimText, accentGreen, cardDarkBg, borderColor);
		JPanel personalSpaceCard = createCard("Personal Space", darkBg, textColor, dimText, accentGreen, cardDarkBg, borderColor);
		JPanel helpCard = createCard("Help", darkBg, textColor, dimText, accentGreen, cardDarkBg, borderColor);

		cardContainer.add(personalSpaceCard, "Personal Space");
		cardContainer.add(settingsCard, "Settings");
		cardContainer.add(helpCard, "Help");

		// Center the card container
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		contentArea.add(cardContainer, gbc);

		// Bottom panel with centered tabs and save button using GridBagLayout
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.setBackground(darkBg);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));

		GridBagConstraints bottomGbc = new GridBagConstraints();

		// Left spacer to balance the save button
		JPanel leftSpacer = new JPanel();
		leftSpacer.setBackground(darkBg);
		leftSpacer.setPreferredSize(new Dimension(120, 40));
		bottomGbc.gridx = 0;
		bottomGbc.gridy = 0;
		bottomGbc.weightx = 0;
		bottomPanel.add(leftSpacer, bottomGbc);

		// Center panel for tabs
		JPanel tabsCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 0));
		tabsCenterPanel.setBackground(darkBg);

		// Create tab buttons with icons
		JButton settingsTab = createTabButton("Settings", false, accentGreen, lightGreen, tabBg, borderColor);
		JButton personalSpaceTab = createTabButton("Personal Space", true, accentGreen, lightGreen, tabBg, borderColor);
		JButton helpTab = createTabButton("Help", false, accentGreen, lightGreen, tabBg, borderColor);

		// Track active tab
		JButton[] tabs = { personalSpaceTab, settingsTab, helpTab };

		// Tab click handlers
		CardLayout cardLayout = (CardLayout) cardContainer.getLayout();

		settingsTab.addActionListener(e -> {
			cardLayout.show(cardContainer, "Settings");
			setActiveTab(tabs, settingsTab, accentGreen, lightGreen, tabBg, borderColor);
		});

		personalSpaceTab.addActionListener(e -> {
			cardLayout.show(cardContainer, "Personal Space");
			setActiveTab(tabs, personalSpaceTab, accentGreen, lightGreen, tabBg, borderColor);
		});

		helpTab.addActionListener(e -> {
			cardLayout.show(cardContainer, "Help");
			setActiveTab(tabs, helpTab, accentGreen, lightGreen, tabBg, borderColor);
		});

		tabsCenterPanel.add(settingsTab);
		tabsCenterPanel.add(personalSpaceTab);
		tabsCenterPanel.add(helpTab);

		bottomGbc.gridx = 1;
		bottomGbc.weightx = 1.0;
		bottomGbc.anchor = GridBagConstraints.CENTER;
		bottomPanel.add(tabsCenterPanel, bottomGbc);

		// Save button panel (right side)
		JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		savePanel.setBackground(darkBg);
		savePanel.setPreferredSize(new Dimension(120, 40));

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Monospaced", Font.BOLD, 16));
		saveButton.setBackground(accentGreen);
		saveButton.setForeground(Color.WHITE);
		saveButton.setFocusPainted(false);
		saveButton.setBorderPainted(false);
		saveButton.setPreferredSize(new Dimension(120, 40));
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Hover effect for save button
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				saveButton.setBackground(darkGreen);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				saveButton.setBackground(accentGreen);
			}
		});

		// Save button action
		saveButton.addActionListener(e -> {
			if (System.getenv(Constants.APP_ENV) == null) {
				new RegisterFrame();
			}else {
				JOptionPane.showMessageDialog(this, "Settings saved successfully!", "Save",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		savePanel.add(saveButton);

		bottomGbc.gridx = 2;
		bottomGbc.weightx = 0;
		bottomGbc.anchor = GridBagConstraints.EAST;
		bottomPanel.add(savePanel, bottomGbc);

		// Add components to frame
		add(contentArea, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	private JButton createTabButton(String text, boolean active, Color accentGreen, Color lightGreen, Color tabBg, Color borderColor) {
		JButton tab = new JButton(text);
		tab.setFont(new Font("Monospaced", Font.BOLD, 22));
		tab.setFocusPainted(false);
		tab.setBorderPainted(false);
		tab.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tab.setPreferredSize(new Dimension(254, 45));

		if (active) {
			tab.setBackground(accentGreen);
			tab.setForeground(Color.WHITE);
		} else {
			tab.setBackground(tabBg);
			tab.setForeground(lightGreen);
			tab.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		}

		// Hover effect
		tab.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if (tab.getBackground() != accentGreen) {
					tab.setBackground(new Color(50, 80, 50));
				}
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				if (tab.getBackground() != accentGreen) {
					tab.setBackground(tabBg);
				}
			}
		});

		return tab;
	}

	private void setActiveTab(JButton[] tabs, JButton activeTab, Color accentGreen, Color lightGreen, Color tabBg, Color borderColor) {
		for (JButton tab : tabs) {
			if (tab == activeTab) {
				tab.setBackground(accentGreen);
				tab.setForeground(Color.WHITE);
				tab.setBorder(null);
			} else {
				tab.setBackground(tabBg);
				tab.setForeground(lightGreen);
				tab.setBorder(BorderFactory.createLineBorder(borderColor, 1));
			}
		}
	}

	private JPanel createCard(String title, Color darkBg, Color textColor, Color dimText, Color accentGreen, Color cardDarkBg, Color borderColor) {
		JPanel card = new JPanel(new BorderLayout(15, 15));
		card.setBackground(cardDarkBg);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(borderColor, 2),
				BorderFactory.createEmptyBorder(25, 30, 25, 30)));

		// Header with icon
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		headerPanel.setBackground(cardDarkBg);

		JLabel header = new JLabel(title);
		header.setFont(new Font("Monospaced", Font.BOLD, 26));
		header.setForeground(accentGreen);
		
		headerPanel.add(header);

		card.add(headerPanel, BorderLayout.NORTH);

		// Content area with scroll
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(cardDarkBg);

		if (title.equals("Settings")) {
			contentPanel.add(createSettingsContent(textColor, dimText, accentGreen, cardDarkBg, borderColor));
		} else if (title.equals("Personal Space")) {
			contentPanel.add(createPersonalSpaceContent(textColor, dimText, accentGreen, cardDarkBg, borderColor));
		} else if (title.equals("Help")) {
			contentPanel.add(createHelpContent(textColor, dimText, accentGreen, cardDarkBg));
		}

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBorder(null);
		scrollPane.setBackground(cardDarkBg);
		scrollPane.getViewport().setBackground(cardDarkBg);
		card.add(scrollPane, BorderLayout.CENTER);

		return card;
	}

	private JPanel createSettingsContent(Color textColor, Color dimText, Color accentGreen, Color cardDarkBg, Color borderColor) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(cardDarkBg);

		JLabel desc = new JLabel("Configure your application preferences");
		desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
		desc.setForeground(dimText);
		desc.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(desc);
		panel.add(Box.createVerticalStrut(20));

		// Settings options
		JCheckBox notifCheckbox = new JCheckBox("Enable notifications");
		notifCheckbox.setFont(new Font("SansSerif", Font.PLAIN, 14));
		notifCheckbox.setBackground(cardDarkBg);
		notifCheckbox.setForeground(textColor);
		notifCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(notifCheckbox);
		panel.add(Box.createVerticalStrut(10));

		JCheckBox autoSaveCheckbox = new JCheckBox("Auto-save changes");
		autoSaveCheckbox.setFont(new Font("SansSerif", Font.PLAIN, 14));
		autoSaveCheckbox.setBackground(cardDarkBg);
		autoSaveCheckbox.setForeground(textColor);
		autoSaveCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(autoSaveCheckbox);

		return panel;
	}

	private JPanel createPersonalSpaceContent(Color textColor, Color dimText, Color accentGreen, Color cardDarkBg, Color borderColor) {
	    JPanel panel = new JPanel(new BorderLayout(10, 10));
	    panel.setBackground(cardDarkBg);

	    // Top panel with Import and Export buttons
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setBackground(cardDarkBg);
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
	    buttonPanel.setBackground(cardDarkBg);
	    
	    JButton importButton = new JButton("Import");
	    importButton.setFont(new Font("SansSerif", Font.BOLD, 13));
	    importButton.setBackground(accentGreen);
	    importButton.setForeground(Color.WHITE);
	    importButton.setFocusPainted(false);
	    importButton.setBorderPainted(false);
	    importButton.setPreferredSize(new Dimension(100, 32));
	    importButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    JButton exportButton = new JButton("Export");
	    exportButton.setFont(new Font("SansSerif", Font.BOLD, 13));
	    exportButton.setBackground(accentGreen);
	    exportButton.setForeground(Color.WHITE);
	    exportButton.setFocusPainted(false);
	    exportButton.setBorderPainted(false);
	    exportButton.setPreferredSize(new Dimension(100, 32));
	    exportButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    // Hover effects
	    Color darkGreen = new Color(34, 139, 34);
	    importButton.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            importButton.setBackground(darkGreen);
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            importButton.setBackground(accentGreen);
	        }
	    });
	    
	    exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            exportButton.setBackground(darkGreen);
	        }
	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            exportButton.setBackground(accentGreen);
	        }
	    });
	    
	    buttonPanel.add(importButton);
	    buttonPanel.add(exportButton);
	    topPanel.add(buttonPanel, BorderLayout.EAST);
	    
	    panel.add(topPanel, BorderLayout.NORTH);

	    // Main content area with split pane
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setBackground(cardDarkBg);
	    splitPane.setDividerLocation(200);
	    splitPane.setDividerSize(5);
	    
	    // Left: Tree viewer
	    JPanel treePanel = new JPanel(new BorderLayout());
	    treePanel.setBackground(cardDarkBg);
	    treePanel.setBorder(BorderFactory.createCompoundBorder(
	        BorderFactory.createLineBorder(borderColor, 1),
	        BorderFactory.createEmptyBorder(5, 5, 5, 5)
	    ));
	    
	    // Create tree with root directory
	    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Personal Space");
	    PopulateTree.populateTree(rootNode, new File(System.getProperty("user.home")+"/MaGCyub/Personal Space")); // Change to your root path
	    
	    JTree fileTree = new JTree(rootNode);
	    fileTree.setBackground(cardDarkBg);
	    fileTree.setForeground(textColor);
	    fileTree.setFont(new Font("SansSerif", Font.PLAIN, 13));
	    
	    // Customize tree appearance for dark mode
	    fileTree.setCellRenderer(new DefaultTreeCellRenderer() {
	        @Override
	        public Component getTreeCellRendererComponent(JTree tree, Object value,
	                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	            
	            if (selected) {
	                setBackgroundSelectionColor(accentGreen);
	                setTextSelectionColor(Color.WHITE);
	            } else {
	                setBackgroundNonSelectionColor(cardDarkBg);
	                setTextNonSelectionColor(textColor);
	            }
	            setBorderSelectionColor(accentGreen);
	            return this;
	        }
	    });
	    
	    JScrollPane treeScrollPane = new JScrollPane(fileTree);
	    treeScrollPane.setBackground(cardDarkBg);
	    treeScrollPane.getViewport().setBackground(cardDarkBg);
	    treeScrollPane.setBorder(null);
	    treePanel.add(treeScrollPane, BorderLayout.CENTER);
	    
	    splitPane.setLeftComponent(treePanel);

	    // Right: File explorer
	    JPanel explorerPanel = new JPanel(new BorderLayout());
	    explorerPanel.setBackground(cardDarkBg);
	    explorerPanel.setBorder(BorderFactory.createCompoundBorder(
	        BorderFactory.createLineBorder(borderColor, 1),
	        BorderFactory.createEmptyBorder(5, 5, 5, 5)
	    ));
	    
	    // File list/table
	    String[] columnNames = {"Name", "Type", "Size", "Modified"};
	    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    
	    JTable fileTable = new JTable(tableModel);
	    fileTable.setBackground(cardDarkBg);
	    fileTable.setForeground(textColor);
	    fileTable.setGridColor(borderColor);
	    fileTable.setSelectionBackground(accentGreen);
	    fileTable.setSelectionForeground(Color.WHITE);
	    fileTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    fileTable.setRowHeight(25);
	    fileTable.getTableHeader().setBackground(new Color(30, 30, 30));
	    fileTable.getTableHeader().setForeground(textColor);
	    fileTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
	    
	    JScrollPane tableScrollPane = new JScrollPane(fileTable);
	    tableScrollPane.setBackground(cardDarkBg);
	    tableScrollPane.getViewport().setBackground(cardDarkBg);
	    tableScrollPane.setBorder(null);
	    explorerPanel.add(tableScrollPane, BorderLayout.CENTER);
	    
	    splitPane.setRightComponent(explorerPanel);
	    
	    panel.add(splitPane, BorderLayout.CENTER);

	    // Tree selection listener to update file explorer
	    fileTree.addTreeSelectionListener(e -> {
	        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
	        if (selectedNode != null) {
	            File selectedFile = (File) selectedNode.getUserObject();
	            if (selectedFile != null && selectedFile.isDirectory()) {
	                FileTable.updateFileTable(tableModel, selectedFile);
	            }
	        }
	    });
	    
	 // Import button action
	    importButton.addActionListener(e -> {
	        FileDialog fileDialog = new FileDialog(mainFrame, "Import File", FileDialog.LOAD);
	        fileDialog.setMultipleMode(false);
	        fileDialog.setVisible(true);
	        
	        String directory = fileDialog.getDirectory();
	        String fileName = fileDialog.getFile();
	        
	        if (directory != null && fileName != null) {
	            File selectedFile = new File(directory, fileName);
	            JOptionPane.showMessageDialog(panel,
	                "Importing: " + selectedFile.getName(),
	                "Import",
	                JOptionPane.INFORMATION_MESSAGE);
	            // Add your import logic here
	        }
	    });

	    // Export button action
	    exportButton.addActionListener(e -> {
	        int selectedRow = fileTable.getSelectedRow();
	        if (selectedRow >= 0) {
	            String fileName = (String) tableModel.getValueAt(selectedRow, 0);
	            FileDialog fileDialog = new FileDialog(mainFrame, "Export File", FileDialog.SAVE);
	            fileDialog.setFile(fileName);
	            fileDialog.setVisible(true);
	            
	            String directory = fileDialog.getDirectory();
	            String savedFileName = fileDialog.getFile();
	            
	            if (directory != null && savedFileName != null) {
	                JOptionPane.showMessageDialog(panel,
	                    "Exporting: " + fileName + " to " + directory + savedFileName,
	                    "Export",
	                    JOptionPane.INFORMATION_MESSAGE);
	                // Add your export logic here
	            }
	        } else {
	            JOptionPane.showMessageDialog(panel,
	                "Please select a file to export",
	                "Export",
	                JOptionPane.WARNING_MESSAGE);
	        }
	    });

	    return panel;
	}


	private JPanel createHelpContent(Color textColor, Color dimText, Color accentGreen, Color cardDarkBg) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(cardDarkBg);

		JLabel desc = new JLabel("Find answers to common questions");
		desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
		desc.setForeground(dimText);
		desc.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(desc);
		panel.add(Box.createVerticalStrut(20));

		String helpText = "Frequently Asked Questions\n\n" + "Q: How do I change my password?\n"
				+ "A: Go to Settings and select 'Change Password'.\n\n" + "Q: Where are my files stored?\n"
				+ "A: Files are stored in your root file location.\n\n" + "Q: How do I backup my data?\n"
				+ "A: Use the export function in Personal Space.\n\n" + "Q: Can I share files with others?\n"
				+ "A: Yes, use the share button in Personal Space.";

		JTextArea helpArea = new JTextArea(helpText);
		helpArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
		helpArea.setEditable(false);
		helpArea.setLineWrap(true);
		helpArea.setWrapStyleWord(true);
		helpArea.setBackground(cardDarkBg);
		helpArea.setForeground(textColor);
		helpArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(helpArea);

		return panel;
	}

	// Interface's frame configuration
	private void config() {
		setTitle(Constants.APP_NAME);
		setIconImage(Constants.IMG);
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}