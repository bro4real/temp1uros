package com.brothers4real.downloadzip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DwldAndZipManyFiles {

	public static void main(String... args){
		
		List<String> URLList = new ArrayList<>();
		List<String> OutputFilesList = new ArrayList<>();
		
		InputStream inStream4remoteFiles=null;
		FileOutputStream fos2save = null;
		FileOutputStream fos2zip = null;
		FileInputStream fis4savedFiles = null;
		ZipOutputStream zos = null;
		
		System.out.println("Downloading selected files, please wait...");
		
		for(int i=0; i<(args.length-1); i=i+2){
			//URLList.add(args[i]);
			OutputFilesList.add(args[i+1]);
		
			try {
				
				URL remoteFile = new URL(args[i]);
				URLConnection connection = remoteFile.openConnection();
				
				// Open output and input streams
				fos2save = new FileOutputStream(args[i+1]);
				inStream4remoteFiles = connection.getInputStream(); 
				
				// Now save the file
				
				int savedBytes;
				
				while((savedBytes=inStream4remoteFiles.read())!=-1){
					fos2save.write(savedBytes);
					
				}
			} catch(MalformedURLException e){
			       System.out.println("Please check the URL:" + 
			                                           e.toString() );
			     } catch(IOException  e1) {
			      System.out.println("Can't read  from the Internet: "+ 
			                                          e1.toString() ); 	
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				System.out.println("The file found at:\n"+args[i]+
						"\nhas been downloaded successfully as " + args[i+1]);
				//check if it's the last URL in args array
				if (i<args.length-3){
					System.out.println("Downloading next file...");
				}
			}
			
		}
		
		try {

			fos2zip = new FileOutputStream(args[args.length-1]);
			zos = new ZipOutputStream(fos2zip);

			for (String file : OutputFilesList) {

				System.out.println("Zipping file " + file + ", please wait...");
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				fis4savedFiles = new FileInputStream //(SOURCE_FOLDER + File.separator + file);
													(file); //my code
				int zippedBytes;
				while ((zippedBytes = fis4savedFiles.read()) != -1) {
					zos.write(zippedBytes);	
				}
				
				zos.closeEntry();
				
			}
			System.out.println("Files successfully zipped in " + args[args.length-1] + "!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			fis4savedFiles.close();
			zos.close();
			fos2zip.flush();
			fos2zip.close();
			inStream4remoteFiles.close();
			fos2save.flush();
			fos2save.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
