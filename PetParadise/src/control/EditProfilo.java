package control;


import model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "editProfileServlet", value = "/edit-profile-servlet")
public class EditProfilo extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

            int count = 0;
            Matcher matcher; 
            boolean matchFound;
            
            String nome = request.getParameter("nome");
           

            String cognome = request.getParameter("cognome");
            
           

            String dDNascita = request.getParameter("dDNascita");
            
            String email = request.getParameter("email");
            matcher = email_string.matcher(email);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String passwd = request.getParameter("password");
            matcher = password_string.matcher(passwd);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String telefono = request.getParameter("telefono");
            matcher = phone_string.matcher(telefono);
            matchFound = matcher.find();
            if (matchFound)
                count++;

            String citta = request.getParameter("citta");
            

            String indirizzo = request.getParameter("indirizzo");
            

            if (count == 3) { //controllo se tutti i campi rispettano la regolarità delle espressioni regolari
                               //update nel DB
                UtenteBean userBean = new UtenteBean();
                UtenteBean user = (UtenteBean) request.getSession().getAttribute("user");
                
                userBean.setId(user.getId());
                userBean.setNome(nome);
                userBean.setCognome(cognome);
                userBean.setEmail(email);
                userBean.setPassword(passwd);
                userBean.setDdNascita(dDNascita);
                userBean.setTelefono(telefono);
                userBean.setCitta(citta);
                userBean.setIndirizzo(indirizzo);
                userBean.setRegistrazione();
                userBean.setAdmin("false");
                System.out.println("Servlet "+ user.getId());

                UtenteDAO userDAO = new UtenteDAO();

                userDAO.updateUser(userBean);

                request.setAttribute("profileJSP", userBean); //settiamo una variabile nella risposta "profileJSP" che rappresenta userBean

                if(userBean.isAdmin().equalsIgnoreCase("true")){
                    response.sendRedirect("user-profile-servlet"); //Se admin, rimanda alla jsp del profilo admin
                }else{
                    response.sendRedirect("user-profile-servlet");
                }
            }else{ //Caso in cui count != 10
            	response.sendRedirect("user-profile-servlet");
            }


    }
      
        final Pattern email_string = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
        final Pattern password_string = Pattern.compile("^[a-zA-Z\\d\\-\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]{6,16}");
        final Pattern phone_string = Pattern.compile("^\\d{10}$");
        final Pattern address_string = Pattern.compile("^([a-zA-Z\\d\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27\\x2C]\\s?){2,20}$");
}