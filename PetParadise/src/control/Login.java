package control;

import model.*;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class Login extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UtenteDAO service = new UtenteDAO();
        UtenteBean user = service.doRetriveByEmailPasswd(email, password);

        if (user != null) {

        	 HttpSession session = request.getSession();
        	    session.setAttribute("user", user);
        	    session.setAttribute("cart", new CartBean());

            RequestDispatcher dispatcher;

            if (user.isAdmin().equalsIgnoreCase("true")) {
                dispatcher = request.getRequestDispatcher("/Homepage.jsp");
            }

            else {

               
                dispatcher = request.getRequestDispatcher("/Homepage.jsp");
            }

            dispatcher.include(request, response);
        }

        else if (user == null) {

        	  RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
        	  dispatcher.forward(request,response);
        	 
      
        }

       
    }
}