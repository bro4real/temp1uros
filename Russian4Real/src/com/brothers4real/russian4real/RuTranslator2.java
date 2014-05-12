package com.brothers4real.russian4real;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RuTranslator2
 */
@WebServlet("/translate2")
public class RuTranslator2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    final String CONNECTION_PROBLEM = "www.english2russian4real.appspot.com "
    				+ "испытывает трудности. Пожалуйста, повторите попытку позже!";
    final String NO_USER_ENTRY = "введи английское слово, товарищ!";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RuTranslator2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher requestDisp = null;
		String nextLine;
		URL url = null;
		URLConnection con = null;
		InputStreamReader inStream = null;
		BufferedReader reader = null;
		StringBuffer firstStringBuff = new StringBuffer();
		
		response.setContentType("text/html;charset=UTF-8");
		
		String unknownWord = request.getParameter("symbol");
		String resultWord = null;
	    final String DOM_TAG = "<div class=\"translateTxt\" >";

		
		if (unknownWord != ""){
			try {
				url = new URL(
						"http://translate.reference.com/translate?query="+unknownWord+"&src=en&dst=ru");
				con = url.openConnection();
				inStream = new InputStreamReader(con.getInputStream(), "UTF8");
				reader = new BufferedReader(inStream);

				// Read the lines from the resulting page
				while (true) {
					nextLine = reader.readLine();
					if (nextLine != null) {
						firstStringBuff.append(nextLine + "\n");
					} else {
						break;
					}
				}
				int indexStart = firstStringBuff.indexOf(DOM_TAG) + DOM_TAG.length();
				// System.out.println(indexStart);
				String bottomHalf = firstStringBuff.substring(indexStart);
				StringTokenizer tokenizer1 = new StringTokenizer(bottomHalf, "<");

				String translationRaw = tokenizer1.nextToken();

				StringTokenizer tokenizer2 = new StringTokenizer(translationRaw,
						",;");

				StringBuffer secondStringBuffer = new StringBuffer();
				while (tokenizer2.hasMoreTokens()) {
					secondStringBuffer.append(tokenizer2.nextToken() + " ");
				}
				
				resultWord = secondStringBuffer.toString();
			} catch (MalformedURLException e) {
				System.out.println("Please check the URL:" + e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				resultWord = CONNECTION_PROBLEM;
				e.printStackTrace();
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
						reader.close();
					} catch (IOException e1) {
						System.out.println("Can't close the streams: "
								+ e1.getMessage());
					}
				}
			}
		} else {
			resultWord = NO_USER_ENTRY;
		}
		
		request.setAttribute("unknownWord", unknownWord);
		request.setAttribute("translation", resultWord);
		requestDisp = context.getRequestDispatcher("/rutrans.jsp");
		requestDisp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
