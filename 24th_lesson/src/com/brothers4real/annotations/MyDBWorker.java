package com.brothers4real.annotations;

@DBParams(dbName = "Students", password = "mysecret", uid = 108)
public class MyDBWorker  {

	void startDB(){
		System.out.println("starting database...");
	}
}

