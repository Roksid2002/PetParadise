package control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/search-product-servlet")
public class RicercaProdotto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera il parametro searchQuery dalla richiesta AJAX
        String searchQuery = request.getParameter("searchQuery");

        ProductDAO productDAO = new ProductDAO();

        // Esegui la ricerca per nome
        ArrayList<ProductBean> risultatiRicerca = productDAO.doRetrieveByNome(searchQuery);

        // Imposta i risultati della ricerca come attributo nella richiesta
        request.setAttribute("searchResults", risultatiRicerca);

        // Reindirizza alla pagina risultati-ricerca.jsp
        request.getRequestDispatcher("risultati-ricerca.jsp").forward(request, response);
    }
}
