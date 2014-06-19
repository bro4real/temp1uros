package com.b4r.ejbees;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MyFirstBean
 */
@Stateless
@LocalBean
public class MyFirstBean implements MyFirstBeanLocal {

    /**
     * Default constructor. 
     */
    public MyFirstBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String doStuff() {
		// TODO Auto-generated method stub
		return "Hello everybody!";
	}

	
}
