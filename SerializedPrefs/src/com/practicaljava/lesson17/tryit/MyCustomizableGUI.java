package com.practicaljava.lesson17.tryit;

import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

public class MyCustomizableGUI extends JPanel implements ActionListener {

	private JButton buttonPref;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JTextField textField;
	private JDialog prefDialog;
	private JFrame frame;
	
	private JLabel colorLabel;
	private JLabel fontLabel;
	private JLabel fontSizeLabel;
	private JComboBox color;
	private JComboBox font;
	private JComboBox fontSize;
		
	private String[] colorList = {"Black", "Red", "Green", "Blue"};
	private String[] fontList = {"Verdana", "Arial", "Georgia"};
	private String[] fontSizeList = {"12", "16", "25", "40", "64"};
	
	
	// Constructor
	MyCustomizableGUI () {
		
		this.setLayout (new BoxLayout(this, BoxLayout.Y_AXIS));
		textField = new JTextField (20);
		buttonPref = new JButton ("Preferences");
		
		this.add(textField);
		this.add(buttonPref);
		
		buttonPref.addActionListener (this);
		
		frame = new JFrame ("My text editor");
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
		
		// JDialog settings
		prefDialog = new JDialog(frame, "Dialog", true);
		prefDialog.setLayout (new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setLayout (new GridLayout(3,2));
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		
		buttonSave = new JButton("Save");
		buttonCancel = new JButton("Cancel");
		
		colorLabel = new JLabel ("Color:");
		fontLabel = new JLabel ("Font:");
		fontSizeLabel = new JLabel ("Font size:");
		color = new JComboBox(colorList);
		font = new JComboBox(fontList);
		fontSize = new JComboBox(fontSizeList);

		p1.add(colorLabel);
		p1.add(color);
		p1.add(fontLabel);
		p1.add(font);
		p1.add(fontSizeLabel);
		p1.add(fontSize);
		p2.add(buttonCancel);
		p2.add(buttonSave);
	
		prefDialog.add(BorderLayout.NORTH, p1);
		prefDialog.add(BorderLayout.SOUTH, p2);
	
		prefDialog.pack();
	}
	
	public void actionPerformed(ActionEvent e){
		
			buttonSave.addActionListener (new ActionListener () {
				
				public void actionPerformed(ActionEvent eSave) {
					
					String fontChosen;
					int fontSizeChosen;
					
					FileOutputStream fileOut = null;
					ObjectOutputStream objectOut = null;
					
					prefDialog.setVisible(false);
					
					if ((String) color.getSelectedItem() == "Black") {
						textField.setForeground(Color.black);
					} else if ((String) color.getSelectedItem() == "Red") {
						textField.setForeground(Color.red);
					} else if ((String) color.getSelectedItem() == "Green") {
						textField.setForeground(Color.green);
					} else if ((String) color.getSelectedItem() == "Blue") {
						textField.setForeground(Color.blue);
					}
					
					fontChosen = (String) font.getSelectedItem();
					fontSizeChosen = Integer.parseInt((String) fontSize.getSelectedItem());
					textField.setFont(new Font(fontChosen, Font.PLAIN, fontSizeChosen));
					
					UserPreferences userPrefs = new UserPreferences();
					userPrefs.setColor((String) color.getSelectedItem());
					userPrefs.setFont(fontChosen);
					userPrefs.setFontSize(fontSizeChosen);
					
					try {
						
						fileOut = new FileOutputStream("preferences.ser");
						
						objectOut = new ObjectOutputStream(fileOut);
						
						objectOut.writeObject(userPrefs);
						
					} catch (IOException e) {
						
						try {
							objectOut.flush();
							objectOut.close();
							fileOut.close();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
			}
			);

			
			buttonCancel.addActionListener (new ActionListener () {
				
				public void actionPerformed(ActionEvent eCancel) {
					prefDialog.setVisible(false);
				}
			}
			);
			
			prefDialog.setVisible(true);
	}
	
	
	public void setFont (String f, int fs) {
		textField.setFont(new Font(f, Font.PLAIN, fs));
		
	}
	
	public static void main(String[] args) {
		
		MyCustomizableGUI custGUI = new MyCustomizableGUI();
		
		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		UserPreferences savedPrefs;
		
		try {
			
			fileIn = new FileInputStream ("preferences.ser");
			objectIn = new ObjectInputStream (fileIn);
			
			savedPrefs = (UserPreferences) objectIn.readObject();
			
			
			if ( savedPrefs.getColor().contains("Red")) {
				custGUI.textField.setForeground(Color.red);
				custGUI.color.setSelectedItem("Red");
			} else if (savedPrefs.getColor().contains("Green")) {
				custGUI.textField.setForeground(Color.green);
				custGUI.color.setSelectedItem("Green");
			} else if (savedPrefs.getColor().contains("Blue")) {
				custGUI.textField.setForeground(Color.blue);
				custGUI.color.setSelectedItem("Blue");
			}
			custGUI.setFont(savedPrefs.getFont(), savedPrefs.getFontSize());
			custGUI.font.setSelectedItem(savedPrefs.getFont());
			custGUI.fontSize.setSelectedItem("" + savedPrefs.getFontSize());
			
			/* I tried to resize the main frame from here but it caused major slowdown
			 * 
			 * if (savedPrefs.getFontSize()<18) {
			 * custGUI.frame.setBounds(200, 200, 180, 90);
			 * } else {
			 * custGUI.frame.setBounds(200, 200, savedPrefs.getFontSize()*10, savedPrefs.getFontSize()*5);
			 * }
			 */
			
		} catch (FileNotFoundException noFile) {
			// load with defaults
			custGUI.setFont("Arial", 25);
			custGUI.textField.setForeground(Color.black);
			
		} catch (ClassNotFoundException noPrefs) {
			noPrefs.printStackTrace();
		} catch (IOException e) {
			try {
				objectIn.close();
				fileIn.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} finally {
			if (fileIn != null) {
				try {
					objectIn.close();
					fileIn.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
}