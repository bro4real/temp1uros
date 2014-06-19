package com.b4r.ejbees;

import javax.ejb.Local;

@Local
public interface MyFirstBeanLocal {
	public String doStuff();
}
