package com.b4r.learnmom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sayHello")
public class SenderServlet extends HttpServlet {
		
	private final AtomicLong count = new AtomicLong(0);
	
	public SenderServlet() {
	        super();
	    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("Adding a message to the Queue...");
		
		MessageSender sender = new MessageSender();
		sender.sendMessage("Message no." + count.incrementAndGet());
	}
}
