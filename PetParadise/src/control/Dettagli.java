package control;

import model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet(name = "showProduct", value = "/show-product")
public class Dettagli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int prodottoId = Integer.parseInt(request.getParameter("productId"));
        ProductDAO productDAO = new ProductDAO();

        ProductBean prodotto = productDAO.doRetrieveById(prodottoId);

        RequestDispatcher dispatcher;
        if (prodotto != null) {
            HttpSession session = request.getSession();
            UtenteBean utenteBean = (UtenteBean) session.getAttribute("user");
            if(utenteBean == null || utenteBean.isAdmin().equalsIgnoreCase("false")){
                request.setAttribute("prodotto",prodotto);
                dispatcher = request.getRequestDispatcher("/Dettagli.jsp");
                dispatcher.forward(request,response);
            }else{
                request.setAttribute("prodotto",prodotto);
                dispatcher = request.getRequestDispatcher("/AdminModifica.jsp");
                dispatcher.forward(request,response);
            }

        }
    }
}