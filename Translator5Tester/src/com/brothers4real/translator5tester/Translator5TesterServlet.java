package com.brothers4real.translator5tester;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Translator5TesterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
