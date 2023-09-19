package control;

import model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "adminServlet", value = "/admin-servlet")
public class AdminCatalog extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("user");

        if (user.isAdmin().equalsIgnoreCase("true")) {
            ProductDAO product = new ProductDAO();
            ArrayList<ProductBean> categoryList = product.doRetrieveAll();

            request.setAttribute("product", categoryList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminCatalogo.jsp");
            dispatcher.include(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Homepage.jsp");
            dispatcher.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UtenteBean userBean = (UtenteBean) session.getAttribute("user");

        String action = request.getParameter("action");

		
			
        if (userBean != null && userBean.isAdmin().equalsIgnoreCase("true")) {
        	if (action != null) {
        		if (action.equalsIgnoreCase("addproduct")) {
        	
            Matcher matcher;
            boolean matchFound;
            int count = 0;


            String nome = request.getParameter("nome");
            if (nome.length() != 0 && nome.length() <= 30)
                count++;
            System.out.println(nome);
           

            String prezzo = request.getParameter("prezzo");
           
                count++;

            String specie = request.getParameter("specie");
            if (specie.length() <= 20 && specie.length() != 0)
                count++;

            String descrizione = request.getParameter("descrizione");
            if (descrizione.length() <= 255 && descrizione.length() != 0)
                count++;

            Part part = request.getPart("image");
            String subpath;

            if (!part.getSubmittedFileName().isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + "photo" + File.separator + "products";
                String imagepath = uploadPath + File.separator + part.getSubmittedFileName();
                part.write(imagepath);
                subpath = "./photo/products/" + part.getSubmittedFileName();
            } else {
                subpath = "./photo/products/noimage.png";
            }
            ProductDAO service = new ProductDAO();

            if (count == 4 && !service.isAlreadyRegistered(nome, descrizione)) {
                ProductBean newProduct = new ProductBean();
                newProduct.setNome(nome);
                newProduct.setPrezzo(Double.parseDouble(prezzo));
                newProduct.setSpecie(specie);
                newProduct.setDescrizione(descrizione);
                newProduct.setNomeImmagine (subpath);
                service.doSave(newProduct);
               
                

                
                response.sendRedirect("AdminCatalogo.jsp");
            }
        		} else if (action.equalsIgnoreCase("deleteproduct")) {
        			int productId = Integer.parseInt(request.getParameter("productId"));
        			try {
						ProductDAO.doDelete(productId);
					} catch (SQLException e) {
						e.printStackTrace();
					}
        			 response.sendRedirect("AdminCatalogo.jsp");
        			
            }else if (action.equalsIgnoreCase("modifica")) {
            	int productId = Integer.parseInt(request.getParameter("productId"));
                ProductBean productToEdit = ProductDAO.doRetrieveById(productId);

                if (productToEdit != null) {
                    // Popola il bean con i nuovi dati dalla richiesta
                    productToEdit.setSpecie(request.getParameter("specie"));
                    productToEdit.setNome(request.getParameter("nome"));
                    productToEdit.setDescrizione(request.getParameter("descrizione"));
                    productToEdit.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    productToEdit.setQuantita(Integer.parseInt(request.getParameter("quantita")));

                    // Chiama il metodo doUpdate del DAO per aggiornare il prodotto
                    ProductDAO.doUpdate(productToEdit);

                    // Reindirizza alla pagina di elenco prodotti o a una pagina di conferma
                    response.sendRedirect("Homepage.jsp");            	
            	}

            	
            }


            }

        } 
    }
        final Pattern decimal_String = Pattern.compile("^(\\d+(?:[.,]\\d{2})?)$");
        final Pattern int_String = Pattern.compile("^\\d+$");
}