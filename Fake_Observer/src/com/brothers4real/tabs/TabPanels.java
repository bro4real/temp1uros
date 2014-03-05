package com.brothers4real.tabs;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class TabPanels extends JPanel {
	
	private JTextField displayField;
	private JTextField comment = new JTextField(30);

	JButton buttonPlus, buttonMinus, buttonReset;
	JPanel winCont;

	public void setDisplayValue(String val) {
		displayField.setText(val);
	}

	public String getDisplayValue() {
		return displayField.getText();
	}
	
	public void setCommentValue(String val) {
		comment.setText(val);
	}
	
	
	public TabPanels() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		winCont = new JPanel();
		GridLayout gl2 = new GridLayout(2, 2);
		// Set the layout manager for this panel
		winCont.setLayout(gl2);

		// Create the display field
		displayField = new JTextField(15);
		this.setDisplayValue("0");
		winCont.add(displayField);
		
		// Create buttons using constructor of the
		// class JButton that takes the label of the
		// button as a parameter
		buttonReset = new JButton("reset");
		buttonPlus = new JButton(" + ");
		buttonMinus = new JButton(" - ");

		winCont.add(buttonReset);
		winCont.add(buttonPlus);
		winCont.add(buttonMinus);

		Engine calcEngine = new Engine(this);

		buttonReset.addActionListener(calcEngine);
		buttonPlus.addActionListener(calcEngine);
		buttonMinus.addActionListener(calcEngine);
		
		tabbedPane.addTab("Subject", null, winCont, "action");
		
		this.setCommentValue("Current value: zero");
		
		tabbedPane.addTab("Fake observer", null, comment, "comment");

		// Add the tabbed pane to this panel.
		add(tabbedPane);

	}
	
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TabPanels(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {  // is this anonymous inner class/interface?
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
