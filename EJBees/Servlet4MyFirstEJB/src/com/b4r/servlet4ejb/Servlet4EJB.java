package com.b4r.servlet4ejb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b4r.ejbees.MyFirstBeanLocal;

/**
 * Servlet implementation class Servlet4EJB
 */
@WebServlet("/Servlet4EJB")
public class Servlet4EJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB 
	private MyFirstBeanLocal firstBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet4EJB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("Enterprise Java Bean says: " + firstBean.doStuff());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
