package com.brothers4real.newsthread;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class NewsReader extends SwingWorker <String, Integer> {
	
	static int threadCount = 1;
	File fileToDisplay;
	JTextArea myTextArea;
	StringBuffer bufferedText;
	String textToDisplay;
	byte progressPercentage;
	double sizeOfFile;
	long time;
	
	public NewsReader(File f, JTextArea jta){
		
		fileToDisplay = f;
		myTextArea = jta;
		bufferedText = new StringBuffer();
		// casting from long to double needed for further calulation:
		sizeOfFile = (double)f.length();
	}

	@Override
	protected String doInBackground() throws Exception {
		// TODO Auto-generated method stub
		time = System.currentTimeMillis();
		try(FileInputStream fis = new FileInputStream(fileToDisplay);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);){
			
			int codeOfChar;
			while ((codeOfChar = br.read()) > -1){
				bufferedText.append((char)codeOfChar);
				
				// publish size of currently loaded text
				int currentlyLoaded = bufferedText.toString().length();
				publish(currentlyLoaded);
			}
			textToDisplay = bufferedText.toString();	
		} catch (IOException e){
			e.getMessage();
		}
		return textToDisplay;
	}

	@Override
	protected void process(List<Integer> chunks) {
		//super.process(chunks);
		
		for (Integer currentSize : chunks) {
			
			double unformattedPercent = (currentSize/sizeOfFile)*100;
			progressPercentage = (byte) Math.round(unformattedPercent);
			myTextArea.setText("Loading..." + progressPercentage + "%");
		}
	}

	@Override
	protected void done() {
		super.done();
		myTextArea.setText(textToDisplay);
		time = System.currentTimeMillis() - time;
		myTextArea.getRootPane().add(new JLabel(String.valueOf(time)));
		
		System.out.println("Thread no." + threadCount +" took " + time + "ms");
		threadCount++;
	}

}
