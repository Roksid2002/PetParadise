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

@WebServlet(name = "showSpecie", value = "/show-specie")
public class Specie extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String specie = (request.getParameter("specie"));
        UtenteBean currentUser = (UtenteBean) session.getAttribute("user");

        ProductDAO productDAO = new ProductDAO();
        ArrayList<ProductBean> productList = productDAO.doRetrieveBySpecie(specie);

        request.setAttribute("prodotti",productList);
        if(currentUser != null && currentUser.isAdmin().equalsIgnoreCase("true")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminCatalogo.jsp");
            dispatcher.forward(request,response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Catalogo.jsp");
            dispatcher.forward(request,response);
        }


    }
}