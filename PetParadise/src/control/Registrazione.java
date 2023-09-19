package control;

import model.*;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "registerServlet",value = "/register-servlet")
public class Registrazione extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        UtenteDAO utenteDAO = new UtenteDAO();

        String email = request.getParameter("email");


        try {
            if(utenteDAO.isAlreadyRegistered(email)){ //eMail già esistente
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/confirmPage.jsp");
                request.setAttribute("type","error-registration");
                request.setAttribute("msg","Email già presente");
                request.setAttribute("redirect", "/login.jsp");
                dispatcher.forward(request,response);

            } else{ //Controllo lato server tramite espressioni regolari definite in basso
                int count = 0;
                Matcher matcher; 
                boolean matchFound;
                String nome = request.getParameter("nome");


                String cognome = request.getParameter("cognome");
               

                String dDNascita = request.getParameter("dDNascita");

                email = request.getParameter("email");
                matcher = email_string.matcher(email);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String passwd = request.getParameter("password");
                matcher = password_string.matcher(passwd);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String telefono = request.getParameter("telefono");
                matcher = phone_string.matcher(telefono);
                matchFound = matcher.find();
                if(matchFound)
                    count++;

                String citta =request.getParameter("citta");
               

               

                String indirizzo = request.getParameter("indirizzo");
                

                if(count == 3) { //controllo se tutti i campi rispettano la regolarita delle espressioni regolari
                                  //creazione e registrazione di un nuovo user nel database
                    UtenteBean utenteBean = new UtenteBean();

                    utenteBean.setNome(nome);
                    utenteBean.setCognome(cognome);
                    utenteBean.setEmail(email);
                    utenteBean.setPassword(passwd);
                    utenteBean.setDdNascita(dDNascita);
                    utenteBean.setTelefono(telefono);
                    utenteBean.setCitta(citta);
                    utenteBean.setIndirizzo(indirizzo);
                    utenteBean.setRegistrazione();
                    utenteBean.setAdmin("false");


                    utenteDAO.doSave(utenteBean);

                    CartBean cart =  new CartBean();

                    
                    request.setAttribute("user",utenteBean);
                    request.setAttribute("cart",cart);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Homepage.jsp");
                    dispatcher.forward(request,response);
                }
                else{
                	
                     RequestDispatcher dispatcher = request.getRequestDispatcher("/Registrazione.jsp");
                     dispatcher.forward(request,response);
                }
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

  
    final Pattern email_string = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
    final Pattern password_string = Pattern.compile("^[a-zA-Z\\d\\-\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]{6,16}");
    final Pattern phone_string = Pattern.compile("^\\d{10}$");
   
}