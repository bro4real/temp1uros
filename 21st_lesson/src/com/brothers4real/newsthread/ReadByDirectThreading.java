package com.brothers4real.newsthread;

import java.io.File;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// it didn't work this way: import java.awt.Component;



import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class ReadByDirectThreading {

	static int NTEXTAREAS;
	
	JLabel heading = new JLabel(" Loading managed by direct threading ");
	JLabel shortNewsLabel = new JLabel("Autoinvoked thread no.0 creates MISLEADING ABSTRACT:");
	JLabel longNewsLabel;
	JTextArea upperArea;
	JTextArea[] areaArray;
	JButton getNewsButton;
	
	NewsReader[] newsReaderArray;
	
	File shortFile = new File("shortNews.txt");
	File longFile = new File("longNews.txt");
	
	/**
	 * 
	 * @param numOfThreads The number of threads that load text into own JTextAreas, acceptable value 1-5
	 */
	public ReadByDirectThreading(int numOfThreads) {
		System.out.println("Direct Threading timeline: \n");
		// validate num of threads to avoid CPU breakdown and gigantic GUI!!!
		if(numOfThreads>5 || numOfThreads<=0){
			numOfThreads=5;
		}
		
		NTEXTAREAS = numOfThreads;
		longNewsLabel = new JLabel("Read the whole story (" + NTEXTAREAS + " times):");
		areaArray = new JTextArea[NTEXTAREAS];
		newsReaderArray = new NewsReader[NTEXTAREAS];
		
		JPanel p1 = new JPanel();
		// Let's try with box layout
		BoxLayout b1 = new BoxLayout(p1, BoxLayout.Y_AXIS);
		// Set the layout manager for this panel
		p1.setLayout(b1);
		
		heading.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		heading.setFont(heading.getFont().deriveFont(24.0f));
		p1.add(heading);
		
		shortNewsLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(shortNewsLabel);
		
		upperArea = new JTextArea(3, 30);
		styleJTextArea(upperArea);
		p1.add(upperArea);

		longNewsLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(longNewsLabel);
		
		for(int i = 0; i<NTEXTAREAS; i++){
			areaArray[i] = new JTextArea(5, 30);
			styleJTextArea(areaArray[i]);
			JScrollPane myScrollPane = new JScrollPane(areaArray[i]);
			p1.add(myScrollPane);
		}
	
		// create button:
		getNewsButton = new JButton("get the news");
		getNewsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(getNewsButton);

		getNewsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// avoid freezing screen if user pressed "get the news" again:
				if("".equals(upperArea.getText())){
					
					NewsReader upperNR = new NewsReader(shortFile, upperArea);
					upperNR.execute();
				
					for(int i = 0; i<NTEXTAREAS; i++){
						newsReaderArray[i] = new NewsReader(longFile, areaArray[i]);
						newsReaderArray[i].execute();
					}
					
				}
			}
		});

		// Create the frame and set its content pane
		JFrame frame = new JFrame("Breaking News");

		frame.setContentPane(p1);
		frame.setLocation(80, 40);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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

}

