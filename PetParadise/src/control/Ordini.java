package control;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/save-cart-to-orders")
public class Ordini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		OrdineDAO oD = new OrdineDAO();
		Integer orderID = 0;
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
		
		String returnTo = (String)request.getParameter("returnto");
		
		String action = (String)request.getParameter("action");
		
		if (action == null)
			action = (String)request.getAttribute("action");
		
	
		
		if (action.equals("createorder")) {
			if (cart == null)
				return;
			OrdineBean order = new OrdineBean();
			
			 double totale = Double.parseDouble(request.getParameter("totale"));
			 UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
			 
			 if (user != null && user.getId() != 0) { // Verifica se l'utente non è nullo e ha un ID valido
	                order.setCart(cart);
	                order.setID_utente(user.getId());
			
				try {
					orderID = oD.insertOrder(order , totale);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
			request.getSession().removeAttribute("cart");
			
			dispatcher = getServletContext().getRequestDispatcher("/confermaordine.jsp"); 
			dispatcher.forward(request, response);
			} else {
				dispatcher = getServletContext().getRequestDispatcher("/Login.jsp"); 
				dispatcher.forward(request, response);
            }
			
		}
		
		
		if(action.contentEquals("getOrders")) {
		
			ArrayList<OrdineBean> orders = new ArrayList<OrdineBean>();
			ArrayList<UtenteBean> users = new ArrayList<UtenteBean>();
			 UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
			 
			 if(user != null && user.isAdmin().equalsIgnoreCase("true")){ //Admin controlla tutti gli ordini degli utenti
				 users = UtenteDAO.doRetriveAllUsers(); 
				 request.setAttribute("users", users);
				 
				 
				 	orders = OrdineDAO.doRetrieveAll();
				 	request.setAttribute("orders",orders);
					dispatcher = getServletContext().getRequestDispatcher("/AdminMostraOrdini.jsp");
					dispatcher.forward(request, response);
					} else { //utente controlla solamente i propri ordini
			
			orders = OrdineDAO.doRetrieveByIdUtente(user.getId());
			request.setAttribute("orders", orders);
			dispatcher = getServletContext().getRequestDispatcher("/MostraOrdini.jsp");
			dispatcher.forward(request, response);
			
			
				
			}
			
		}
		
		if (action.equals("deleteorder")) {
			

			 UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
			 
			 if(user != null && user.isAdmin().equalsIgnoreCase("true")){
				
				int IDordine = Integer.parseInt(request.getParameter("IDordine"));
				 
				OrdineDAO.doDelete(IDordine);
				
				
				dispatcher = getServletContext().getRequestDispatcher("/Confirm.jsp");
				dispatcher.forward(request, response);
			 }
		}
		
	
	
	
	if(action.contentEquals("getUtenti")) {
		
		ArrayList<UtenteBean> users = new ArrayList<UtenteBean>();
		 UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
		 
		 if(user != null && user.isAdmin().equalsIgnoreCase("true")){ //Admin controlla tutti gli ordini degli utenti
			 users = UtenteDAO.doRetriveAllUsers(); 
			 request.setAttribute("users", users);
			 
			 
			 	
				dispatcher = getServletContext().getRequestDispatcher("/AdminMostraOrdini.jsp");
				dispatcher.forward(request, response);
				}
	
		}
	
	if (action.equals("deleteutente")) {
		

		 UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
		 
		 if(user != null && user.isAdmin().equalsIgnoreCase("true")){
			
			int IDutente = Integer.parseInt(request.getParameter("IDutente"));
			 
			UtenteDAO.doDelete(IDutente);
			
			
			dispatcher = getServletContext().getRequestDispatcher("/Confirm.jsp");
			dispatcher.forward(request, response);
		 }
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	}
	