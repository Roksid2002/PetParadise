package control;


import model.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "LogoutServlet", value = "/logout-servlet")
public class Logout extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("user");
       

        session.invalidate(); //cancello la sessione
        response.sendRedirect("Homepage.jsp"); //rimando alla homepage

    }
}