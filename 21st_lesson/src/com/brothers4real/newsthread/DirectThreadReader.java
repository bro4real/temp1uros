package com.brothers4real.newsthread;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class DirectThreadReader {

	JLabel heading;
	JLabel longNewsLabel;
	JTextArea[] areaArray;
	NewsReader[] newsReaderArray;
	JPanel p1;
	JButton startThreadsButton;
	File longFile = new File("longNews.txt");
	
	public DirectThreadReader(final int numberOfThreads){

		heading = new JLabel(" Loading managed by direct threading ");
		longNewsLabel = new JLabel("Watch the progress of " + numberOfThreads + " threads in parallel:");
		areaArray = new JTextArea[numberOfThreads];
		newsReaderArray = new NewsReader[numberOfThreads];
		
		p1 = new JPanel();
		// Let's try with box layout
		BoxLayout b1 = new BoxLayout(p1, BoxLayout.Y_AXIS);
		// Set the layout manager for this panel
		p1.setLayout(b1);
		
		heading.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		heading.setFont(heading.getFont().deriveFont(24.0f));
		p1.add(heading);
		
		longNewsLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(longNewsLabel);
		
		for(int i = 0; i<numberOfThreads; i++){
			areaArray[i] = new JTextArea(5, 30);
			styleJTextArea(areaArray[i]);
			JScrollPane myScrollPane = new JScrollPane(areaArray[i]);
			p1.add(myScrollPane);
		}
	
		// create button:
		startThreadsButton = new JButton("start the threads");
		startThreadsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(startThreadsButton);
		
		startThreadsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i<numberOfThreads; i++){
					newsReaderArray[i] = new NewsReader(longFile, areaArray[i]);
					newsReaderArray[i].execute();
				}	
			}
		});
		
	}
	
	/**
	 * 
	 * @param uglyJTA Component to be styled
	 */
	static void styleJTextArea(JTextArea uglyJTA){
		
		// wrap text inside area:
		uglyJTA.setLineWrap(true);
		// and break the line at whitespace:
		uglyJTA.setWrapStyleWord(true);
		uglyJTA.setEditable(false);
		uglyJTA.setMargin(new Insets(5, 5, 0, 0));
		
		// Stop autoscrolling to the end of the text:
		DefaultCaret caret = (DefaultCaret) uglyJTA.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);		
	}
	
public static void main(String[] args) {
	
	if (args.length != 1) {
		System.out.println("Sample usage of the program: "
				+ "java DirectThreadReader (number of threads to be executed in parallel)");
		System.out.println("For Example: java DirectThreadReader 5");
		System.exit(0);
	}
	
	int numberOfThreads = Integer.parseInt(args[0]);
		
	if (numberOfThreads>6 || numberOfThreads<=0) {
		System.out.println("Valid number of threads between 1 and 6");
	}
	
		DirectThreadReader dtr = new DirectThreadReader(numberOfThreads);
		
		if (numberOfThreads==1){
			dtr.longNewsLabel.setText("Watch the progress of " + numberOfThreads + " thread:");
			dtr.startThreadsButton.setText("start the thread");
		}
		// Create the frame and set its content pane
				JFrame frame = new JFrame("Threads in action");

				frame.setContentPane(dtr.p1);
				frame.setLocation(80, 40);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
