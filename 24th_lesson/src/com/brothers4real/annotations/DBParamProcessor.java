package com.brothers4real.annotations;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class DBParamProcessor {

	static final String PACKAGE_NAME = "com.brothers4real.annotations";
	
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Sample usage of the program: java DBParamProcessor MyDBWorker");
			System.exit(0);
		}

		try {
			Class introspectedClass = Class.forName(args[0]);
			//Class[] classesInPackage = getClasses(PACKAGE_NAME);
			//boolean validCmdLineArg = Arrays.asList(classesInPackage).contains(introspectedClass);
			
			//if (validCmdLineArg){
				
					if (introspectedClass.isAnnotationPresent(DBParams.class)){
						try {
							DBParams params = (DBParams) introspectedClass.getAnnotation(DBParams.class);
							
							System.out.println("DB name: " + params.dbName()+"\nUser ID: " 
									+ params.uid()+"\npassword: " + params.password());
						} catch (Exception e) {
							e.printStackTrace();
						}		 
					} else {
						System.out.println(introspectedClass + " isn't properly annotated!");
					}
				
			//} 
		} catch (ClassNotFoundException e) {
			
			System.out.println("The class " + args[0] + ", provided via cmd line,"
					+ "\ndoesn't exist in this package. Please check the spelling!");
		}			
			
	}

	/**
	* Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	*
	* @param packageName The base package
	* @return The classes
	* @throws ClassNotFoundException
	* @throws IOException
	*/
	private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		
		ClassLoader myClassLoader = Thread.currentThread().getContextClassLoader();
		assert myClassLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = myClassLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}
	
	/**
	* method used to find all classes in a given directory and subdirs.
	*
	* @param directory   The base directory
	* @param packageName The package name for classes found inside the base directory
	* @return The classes
	* @throws ClassNotFoundException
	*/
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
		}
}
