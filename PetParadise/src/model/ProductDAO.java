package model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO {
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

	 public static boolean doDelete(Integer id) throws SQLException {
	        int result;
	        try (Connection con = ds.getConnection()) {
	        	 PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE ID = ?");
	        			 ps.setInt(1, id);

	            result = ps.executeUpdate();
	        }

	        return result != 0;
	    }
	 
	 
    public void doSave(ProductBean productBean) {

        try (Connection con = ds.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (specie, nome, descrizione, prezzo, quantita, nomeImmagine) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, productBean.getSpecie());
            ps.setString(2, productBean.getNome());
            ps.setString(3, productBean.getDescrizione());
            ps.setDouble(4, productBean.getPrezzo());
            ps.setInt(5, productBean.getQuantita());
            ps.setString(6, productBean.getNomeImmagine());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            productBean.setID(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doUpdate(ProductBean productBean)
    {
        try (Connection con = ds.getConnection()) {

            Statement st = con.createStatement();
            String query = "UPDATE prodotto SET specie = '" + productBean.getSpecie() + "', nome = '" + productBean.getNome() + "', descrizione = '" + productBean.getDescrizione() + "', prezzo = '" + productBean.getPrezzo() + "', quantita = '" + productBean.getQuantita() + 
                    "'WHERE ID= " + productBean.getID();
            st.executeUpdate(query);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAlreadyRegistered(String name, String description) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * " +
                            "FROM prodotto " +
                            "WHERE Nome=? AND Descrizione=?");

            ps.setString(1, name);
            ps.setString(2, description);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductBean doRetrieveById(int id) {

        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * " +
                            "FROM prodotto " +
                            "WHERE ID=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProductBean product = new ProductBean();
                product.setID(rs.getInt(1));
                product.setSpecie(rs.getString(2));
                product.setNome(rs.getString(3));
                product.setDescrizione(rs.getString(4));
                product.setPrezzo(rs.getDouble(5));
                product.setQuantita(rs.getInt(6));
                product.setNomeImmagine(rs.getString(7));

                return product;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductBean> doRetrieveAll() {

        try {
        	Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM prodotto ORDER BY ID");

            ArrayList<ProductBean> productsList = new ArrayList<ProductBean>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProductBean product = new ProductBean();
                product.setID(rs.getInt(1));
                product.setSpecie(rs.getString(2));
                product.setNome(rs.getString(3));
                product.setDescrizione(rs.getString(4));
                product.setPrezzo(rs.getDouble(5));
                product.setQuantita(rs.getInt(6));
                product.setNomeImmagine(rs.getString(7));

                productsList.add(product);
            }

            return productsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductBean> doRetrieveBySpecie(String specie) {

    	        try (Connection con = ds.getConnection()) {
    	            PreparedStatement ps = con.prepareStatement(
    	                    "SELECT * " +
    	                            "FROM prodotto " +
    	                            "WHERE specie=?");
                    ps.setString(1,specie );
    	            ArrayList<ProductBean> productsList = new ArrayList<ProductBean>();
    	            ResultSet rs = ps.executeQuery();

    	            while (rs.next()) {

    	                ProductBean product = new ProductBean();
    	                product.setID(rs.getInt(1));
    	                product.setSpecie(rs.getString(2));
    	                product.setNome(rs.getString(3));
    	                product.setDescrizione(rs.getString(4));
    	                product.setPrezzo(rs.getDouble(5));
    	                product.setQuantita(rs.getInt(6));
    	                product.setNomeImmagine(rs.getString(7));

    	                productsList.add(product);
    	            }

    	            return productsList;

    	        } catch (SQLException e) {
    	            throw new RuntimeException(e);
    	        }
    	    }
    
    public ArrayList<ProductBean> doRetrieveByNome(String nome) {

        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * " +
                            "FROM prodotto " +
                            "WHERE nome=?");
            ps.setString(1,nome );
            ArrayList<ProductBean> productsList = new ArrayList<ProductBean>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProductBean product = new ProductBean();
                product.setID(rs.getInt(1));
                product.setSpecie(rs.getString(2));
                product.setNome(rs.getString(3));
                product.setDescrizione(rs.getString(4));
                product.setPrezzo(rs.getDouble(5));
                product.setQuantita(rs.getInt(6));
                product.setNomeImmagine(rs.getString(7));

                productsList.add(product);
            }

            return productsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


   
    public ArrayList<ProductBean> doRetrieveByFilter(int minPrice, int maxPrice, String category) {

        try (Connection con = ds.getConnection()) {

            PreparedStatement ps;

            if (category.equalsIgnoreCase("all")) {
                ps = con.prepareStatement(
                        "SELECT * " +
                                "FROM prodotto " +
                                "WHERE Prezzo > ? AND Prezzo < ?");

                ps.setInt(1, minPrice);
                ps.setInt(2, maxPrice);
            }

            else {
                ps = con.prepareStatement(
                        "SELECT * " +
                                "FROM prodotto " +
                                "WHERE Prezzo > ? AND Prezzo < ? AND Categoria = ?");

                ps.setInt(1, minPrice);
                ps.setInt(2, maxPrice);
                ps.setString(3, category);
            }

            ArrayList<ProductBean> productsList = new ArrayList<ProductBean>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProductBean product = new ProductBean();

                product.setID(rs.getInt(1));
                product.setSpecie(rs.getString(2));
                product.setNome(rs.getString(3));
                product.setDescrizione(rs.getString(4));
                product.setPrezzo(rs.getDouble(5));
                product.setQuantita(rs.getInt(6));
                product.setNomeImmagine(rs.getString(7));

                productsList.add(product);
            }

            return productsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}