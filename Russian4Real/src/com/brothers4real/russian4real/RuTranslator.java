package com.brothers4real.russian4real;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class RuTranslator
 */

// couldn't use annotations for Google App Engine
// map had to be done via "web.xml"
@WebServlet("/translate")
public class RuTranslator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    final String CONNECTION_PROBLEM = "www.english2russian4real.appspot.com испытывает трудности. Пожалуйста, повторите попытку позже!";
    final String NO_USER_ENTRY = "введи английское слово, товарищ!";
    /**
     * @see HttpServlet#HttpServlet()
     */
    
   
    public RuTranslator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		RequestDispatcher requestDisp = null;
		
		response.setContentType("text/html;charset=UTF-8");
		String csvString;
		String unknownWord = request.getParameter("symbol");
		String resultWord = null;
		
		if (unknownWord != ""){
			try {
				// Google App Engine wouldn't let me use this library:
				Document doc = Jsoup.connect("http://translate.reference.com/translate?query="+unknownWord+"&src=en&dst=ru").get();
				Elements elem = doc.getElementsByClass("translateTxt");
				
				csvString = elem.text();
			    StringTokenizer tokenizer = new StringTokenizer(csvString, ",;");
			    StringBuffer sBuffer = new StringBuffer();
			    while(tokenizer.hasMoreTokens()){
			    	sBuffer.append(tokenizer.nextToken()+" ");
			    }
			    resultWord = sBuffer.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				resultWord = CONNECTION_PROBLEM;
				e.printStackTrace();
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
