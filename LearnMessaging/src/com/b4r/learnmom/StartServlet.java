package com.b4r.learnmom;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class StartServlet
 */
@WebServlet(name="startup", loadOnStartup=2)
public class StartServlet extends HttpServlet {
	MessageReceiver receiver = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		receiver = new MessageReceiver();
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		try {
			receiver.connection.close();
		} catch (Exception e) {
			System.out.println("Can’t close JMS connection/session "
					+ e.getMessage());
		}
	}
}
