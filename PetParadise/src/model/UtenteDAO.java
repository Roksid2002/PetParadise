package model;


import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UtenteDAO {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage2");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	
    public void doSave(UtenteBean userBean) {

        try (Connection con = ds.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (Nome, Cognome, Data_Nascita, Email, password, Telefono, Citta, Indirizzo, Data_Registrazione, admin) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, userBean.getNome());
            ps.setString(2, userBean.getCognome());
            ps.setString(3, userBean.getDdNascita());
            ps.setString(4, userBean.getEmail());
            ps.setString(5, userBean.getPassword());
            ps.setString(6, userBean.getTelefono());
            ps.setString(7, userBean.getCitta());
            ps.setString(8, userBean.getIndirizzo());
            ps.setString(9, userBean.getRegistrazione());
            ps.setString(10, userBean.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            userBean.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UtenteBean doRetriveById(int id) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT *"+
                    "FROM Utente"+
                    " WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            UtenteBean userBean = new UtenteBean();
            userBean.setId(rs.getInt(1));
            userBean.setNome(rs.getString(2));
            userBean.setCognome(rs.getString(3));
            userBean.setEmail(rs.getString(4));
            userBean.setPassword(rs.getString(5));
            userBean.setTelefono(rs.getString(6));
            userBean.setCitta(rs.getString(7));
            userBean.setIndirizzo(rs.getString(8));
            userBean.setDdNascita(rs.getString(9));
            userBean.setRegistrazione(rs.getString(10));
            userBean.setAdmin(rs.getString(11));
            return userBean;
            }
            return null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public UtenteBean doRetriveByEmailPasswd(String email, String password){
            try(Connection con = ds.getConnection()){
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * " +
                                "FROM Utente " +
                                "WHERE Email=? AND Password=SHA1(?)");
                ps.setString(1,email);
                ps.setString(2,password);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    UtenteBean userBean = new UtenteBean();
                    userBean.setId(rs.getInt(1));
                    userBean.setNome(rs.getString(2));
                    userBean.setCognome(rs.getString(3));
                    userBean.setEmail(rs.getString(4));
                    userBean.setPassword(rs.getString(5));
                    userBean.setTelefono(rs.getString(6));
                    userBean.setCitta(rs.getString(7));
                    userBean.setIndirizzo(rs.getString(8));
                    userBean.setDdNascita(rs.getString(9));
                    userBean.setRegistrazione(rs.getString(10));
                    userBean.setAdmin(rs.getString(11));
                    return userBean;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public void updateUser(UtenteBean user){
        try(Connection con = ds.getConnection()){ System.out.println(user.getId());
            Statement statement = con.createStatement();
            String query = "UPDATE Utente SET Nome = '" + user.getNome()
                    + "', Cognome = '" + user.getCognome()
                    + "', Data_Nascita = '" + user.getDdNascita()
                    + "', Email = '" + user.getEmail()
                    + "', password = '" + user.getPassword()
                    + "', Telefono = '" + user.getTelefono()
                    +  "', Citta = '" + user.getCitta()
                    + "', Indirizzo = '" + user.getIndirizzo()
                    + "', admin = '" + user.isAdmin()
                    + "' WHERE ID = " + user.getId();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
}
       

   
   
    public boolean isAlreadyRegistered(String email){
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT email FROM Utente where Utente.email=?");
            ps.setString(1,email);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<UtenteBean> doRetriveAllUsers() {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente ORDER BY ID");

            ArrayList<UtenteBean> listUsers = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UtenteBean user = new UtenteBean();
                user.setId(rs.getInt(1));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setTelefono(rs.getString(6));
                user.setCitta(rs.getString(7));
                user.setIndirizzo(rs.getString(8));
                user.setDdNascita(rs.getString(9));
                user.setAdmin(rs.getString(10));
                listUsers.add(user);
            }
            return listUsers;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void doDelete(int idUtente) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE ID=?");
            ps.setInt(1, idUtente);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted == 0) {
                throw new SQLException("Non è stato possibile eliminare l'utente con ID: " + idUtente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}