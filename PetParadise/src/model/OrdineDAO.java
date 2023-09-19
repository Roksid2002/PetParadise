package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAO {
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

	public static void doDelete(int idOrdine) {
	    try (Connection con = ds.getConnection()) {
	        PreparedStatement ps = con.prepareStatement(
	                "DELETE FROM Ordine " +
	                        "WHERE ID_Ordine=?");

	        ps.setInt(1, idOrdine);

	        int rowsDeleted = ps.executeUpdate();

	       

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	
	    public static int doSave(OrdineBean order)
	    {
	        try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "INSERT INTO Ordine VALUES (?,?,?,?,?)",
	            Statement.RETURN_GENERATED_KEYS);

	            ps.setInt(1, order.getID_utente());
	            System.out.println(order.getID_utente());
	            ps.setDouble(2, order.getTotale());
	            System.out.println(order.getTotale());
	            ps.setInt(3, order.getID_prodotto());
	            System.out.println(order.getID_prodotto());
	            ps.setInt(4, order.getQuantita());
	            System.out.println(order.getQuantita());
	            ps.setDouble(5, order.getPrezzo());
	            System.out.println(order.getPrezzo());

	            if (ps.executeUpdate() != 1) {
	                throw new RuntimeException("INSERT error.");
	            }

	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            int id = rs.getInt(1);
	            order.setID_ordine(id);
	            return id;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public static ArrayList<OrdineBean> doRetrieveByIdUtente(int id)
	    {
	        try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "SELECT * " +
	                            "FROM Ordine " +
	                            "WHERE ID_Utente=?");

	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();
	            ArrayList<OrdineBean> orders = new ArrayList<>();

	            while (rs.next()) {
	            	OrdineBean orderBean = new OrdineBean();
	                orderBean.setID_ordine(rs.getInt(1));
	                orderBean.setID_utente(rs.getInt(2));
	                orderBean.setTotale(rs.getDouble(3));
	                orderBean.setID_prodotto(rs.getInt(4));
	                orderBean.setQuantita(rs.getInt(5));
	                orderBean.setPrezzo(rs.getDouble(6));
	                orders.add(orderBean);
	            }

	            return orders;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public static OrdineBean doRetrieveByIdOrder(int id)
	    {
	        try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "SELECT * " +
	                            "FROM Ordine " +
	                            "WHERE ID_Ordine=?");

	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();

	            rs.next();

	            OrdineBean ordineBean = new OrdineBean();
	            ordineBean.setID_ordine(rs.getInt(1));
	            ordineBean.setID_utente(rs.getInt(2));
	            ordineBean.setTotale(rs.getDouble(3));
	            ordineBean.setID_prodotto(rs.getInt(4));
	            ordineBean.setQuantita(rs.getInt(5));
	            ordineBean.setPrezzo(rs.getDouble(6));

	            return ordineBean;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    public Integer insertOrder(OrdineBean order, double totale) throws SQLException {
			int orderID = getNextOrderID();
			 try (Connection con = ds.getConnection()) {
		            PreparedStatement ps = con.prepareStatement( "INSERT INTO Ordine VALUES(?, ?, ?, ?, ?, ?)");
		          
			try {
				
				ps.setInt(1,orderID);
				ps.setInt(2, order.getID_utente());
				ps.setInt(5, 10);

				for (Map.Entry<Integer, ProductBean> set : order.getCart().getProducts().entrySet()) {

					ps.setInt(4, set.getKey());
					ps.setDouble(3, totale);
					ps.setDouble(6, set.getValue().getPrezzo() + set.getValue().getPrezzo());					
					
					ps.executeUpdate();

				}
				ps.close();
			
				

			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
			return orderID;}
		}
	    
	    
	    public static ArrayList<OrdineBean> doRetrieveAll()
	    {
	        try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "SELECT * " +
	                            "FROM Ordine ");

	            ResultSet rs = ps.executeQuery();
	            ArrayList<OrdineBean> orders = new ArrayList<>();

	            while (rs.next()) {
	            	OrdineBean ordineBean = new OrdineBean();
	            	ordineBean.setID_ordine(rs.getInt(1));
	 	            ordineBean.setID_utente(rs.getInt(2));
	 	            ordineBean.setTotale(rs.getDouble(3));
	 	            ordineBean.setID_prodotto(rs.getInt(4));
	 	            ordineBean.setQuantita(rs.getInt(5));
	 	            ordineBean.setPrezzo(rs.getDouble(6));
	                orders.add(ordineBean);
	            }

	            return orders;

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    private int getNextOrderID() throws SQLException {
			
			int nextOrder = -1;
			 try (Connection con = ds.getConnection()) {
		            PreparedStatement ps = con.prepareStatement( "SELECT MAX(ID_Ordine) + 1 FROM Ordine ");
			try {
				
				ResultSet rs = ps.executeQuery();
				
				
				if (rs.next())
					nextOrder = rs.getInt(1);

			}catch (SQLException e) {
				
			}
			
			return nextOrder;
			
		}
	}}