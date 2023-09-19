package control;

import java.io.IOException;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "userProfileServlet", value = "/user-profile-servlet") //SERVLET PER VEDERE IL PROPRIO PROFILO
	public class ProfiloUtente extends HttpServlet {

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");

	        HttpSession session = request.getSession();
	        UtenteBean user = (UtenteBean) session.getAttribute("user");

	        request.setAttribute("profileJSP", user);

	        if (user != null) {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Utente.jsp");
	            dispatcher.include(request, response);
	        }else {
	        	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		            dispatcher.include(request, response);
	        }
	        	
	    }
	}