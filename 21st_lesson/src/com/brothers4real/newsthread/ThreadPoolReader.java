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

public class ThreadPoolReader {

	JLabel heading;
	JLabel longNewsLabel;
	JTextArea[] areaArray;
	NewsReader[] newsReaderArray;
	JPanel p1;
	JButton startThreadsButton;
	File longFile = new File("longNews.txt");
	
	List<Future<?>> futures = new ArrayList<>();
	ExecutorService executor;
	
	public ThreadPoolReader(final int numberOfThreads, int poolSize){
		
		heading = new JLabel(" Loading managed by Executor Framework threading ");
		longNewsLabel = new JLabel("Watch the file loading on " + numberOfThreads 
				+ " separate threads, maximum " + poolSize + " at a time");
		areaArray = new JTextArea[numberOfThreads];
		newsReaderArray = new NewsReader[numberOfThreads];
		executor = Executors.newFixedThreadPool(poolSize);
		
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
				try{
					for(int i = 0; i<numberOfThreads; i++){
						newsReaderArray[i] = new NewsReader(longFile, areaArray[i]);
						futures.add(executor.submit(newsReaderArray[i]));	
					}
				} finally{
					executor.shutdown();
				}	
			}
		});
	}
	
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
		
		if (args.length != 2) {
			System.out.println("Sample usage of the program: "
					+ "java ThreadPoolReader (number of threads) (threadpool size)");
			System.out.println("For Example: java ThreadPoolReader 5 3");
			System.exit(0);
		}
		
		int numberOfThreads = Integer.parseInt(args[0]);
		int poolSize = Integer.parseInt(args[1]);
		
		// too many threads can freeze the system!
		if (numberOfThreads>6 || numberOfThreads<=0) {
			System.out.println("Please specify number of threads between 1 and 6, otherwise UI might exit the screen!");
			System.exit(0);
		} 
		
		ThreadPoolReader tpr = new ThreadPoolReader(numberOfThreads, poolSize);
		
		// make sensible UI text:
		if (poolSize>numberOfThreads){
				tpr.longNewsLabel.setText("Watch the file loading on " 
						+ numberOfThreads + " separate threads, maximum " + numberOfThreads + " at a time");
			
		}
		if (numberOfThreads==1){
				tpr.longNewsLabel.setText("Watch the progress of " + numberOfThreads + " thread:");
				tpr.startThreadsButton.setText("start the thread");
		}
			
		// Create the frame and set its content pane
		JFrame frame = new JFrame("Threads in action");
		frame.setContentPane(tpr.p1);
		frame.setLocation(80, 40);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
}
