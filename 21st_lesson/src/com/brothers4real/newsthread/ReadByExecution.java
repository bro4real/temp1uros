package com.brothers4real.newsthread;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ReadByExecution {
	
	static int NUM_OF_THREADS;
	static int NUM_OF_TEXT_AREAS;
	
	JLabel heading = new JLabel(" Loading managed by Executor Framework ");
	JLabel shortNewsLabel = new JLabel("Autoinvoked thread no.0 creates MISLEADING ABSTRACT:");
	JLabel longNewsLabel;
	JTextArea upperArea;
	JTextArea[] longAreaArray;
	JButton getNewsButton;
	
	NewsReader[] newsReaderArray;
	
	File shortFile = new File("shortNews.txt");
	File longFile = new File("longNews.txt");
	
	List<Future<?>> futures = new ArrayList<>();
	ExecutorService executor;
	
	/**
	 * 
	 * @param numOfThreads The number of threads that load text into own JTextAreas, acceptable value 1-5
	 * @param maxOfThreads The maximum number of active threads
	 */
	public ReadByExecution(int numOfThreads, int maxOfThreads) {
		System.out.println("Executor Threading timeline: \n");
		// validate number of threads to avoid gigantic GUI
				if(numOfThreads>5 || numOfThreads<=0){
					numOfThreads=5;
				}
		
		NUM_OF_TEXT_AREAS = numOfThreads;
		NUM_OF_THREADS = maxOfThreads;
		longNewsLabel = new JLabel("Read the whole story (" + NUM_OF_TEXT_AREAS + " times):");
		longAreaArray = new JTextArea[NUM_OF_TEXT_AREAS];
		newsReaderArray = new NewsReader[NUM_OF_TEXT_AREAS];
		executor = Executors.newFixedThreadPool(NUM_OF_THREADS);
		
		JPanel p1 = new JPanel();
		// Let's try with box layout
		BoxLayout b1 = new BoxLayout(p1, BoxLayout.Y_AXIS);
		// Set the layout manager for this panel
		p1.setLayout(b1);
		
		heading.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		heading.setFont(heading.getFont().deriveFont(24.0f));
		p1.add(heading);
		
		JLabel subheader = new JLabel("number of threads in the pool: " + NUM_OF_THREADS);
		subheader.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(subheader);
		
		shortNewsLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(shortNewsLabel);
		
		upperArea = new JTextArea(3, 30);
		styleJTextArea(upperArea);
		p1.add(upperArea);

		longNewsLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(longNewsLabel);
		
		for(int i = 0; i<NUM_OF_TEXT_AREAS; i++){
			longAreaArray[i] = new JTextArea(5, 30);
			styleJTextArea(longAreaArray[i]);
			JScrollPane myScrollPane = new JScrollPane(longAreaArray[i]);
			p1.add(myScrollPane);
		}
		
		getNewsButton = new JButton("get the news");
		getNewsButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		p1.add(getNewsButton);
		

		getNewsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// avoid freezing screen if user pressed "get the news" again:
				if("".equals(longAreaArray[0].getText())){
					
					NewsReader upperNR = new NewsReader(shortFile, upperArea);
					futures.add(executor.submit(upperNR));
					
					for(int i = 0; i<NUM_OF_TEXT_AREAS; i++){
						newsReaderArray[i] = new NewsReader(longFile, longAreaArray[i]);
					}
					try{
						for(int i = 0; i<NUM_OF_TEXT_AREAS; i++){
						
						futures.add(executor.submit(newsReaderArray[i]));	
							
						}
					} finally{
						executor.shutdown();
					}
					
				}
			}
		});
		
		// Create the frame and set its content pane
				JFrame frame = new JFrame("Breaking News");
				frame.setContentPane(p1);
				frame.setLocation(600, 40);
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

