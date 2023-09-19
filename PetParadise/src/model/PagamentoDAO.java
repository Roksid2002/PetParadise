package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PagamentoDAO {

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
	
		public PagamentoBean getPayment(int utenteid) {

			 try (Connection con = ds.getConnection()) {
		            PreparedStatement ps = con.prepareStatement(
		            		"SELECT * FROM metodo_pagamento WHERE e_utente = ?");

		            ps.setInt(1, utenteid);

		            ResultSet rs = ps.executeQuery();

		            rs.next();

		            PagamentoBean pagamentoBean = new PagamentoBean();
		            pagamentoBean.setId_pagamento(rs.getInt(1));
		            pagamentoBean.setNominativo(rs.getString(2));
		            pagamentoBean.setCVV(rs.getInt(3));
		            pagamentoBean.setCodice_carta(rs.getString(4));
		            pagamentoBean.setAnnoScadenza(rs.getInt(5));
		            pagamentoBean.setE_utente(rs.getInt(6));

		            return pagamentoBean;
			 } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

		}

		public void insertPayment(PagamentoBean pagamento) {
		

			try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "INSERT INTO metodo_pagamento VALUES (?,?,?,?,?,?)",
	            Statement.RETURN_GENERATED_KEYS);

	            ps.setInt(1, pagamento.getId_pagamento());
	            ps.setString(2, pagamento.getNominativo());
	            ps.setInt(3, pagamento.getCVV());
	            ps.setString(4, pagamento.getCodice_carta());
	            ps.setInt(5, pagamento.getAnnoScadenza());
	            ps.setInt(6, pagamento.getE_utente());

	            if (ps.executeUpdate() != 1) {
	                throw new RuntimeException("INSERT error.");
	            }

	           

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

		}

		// Salva il metodo di pagamento del cliente

		public void saveMethodPayment(PagamentoBean pagamento) {

			try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "INSERT INTO metodo_pagamento VALUES (?,?,?,?,?,?)",
	            Statement.RETURN_GENERATED_KEYS);

	            ps.setInt(1, pagamento.getId_pagamento());
	            ps.setString(2, pagamento.getNominativo());
	            ps.setInt(3, pagamento.getCVV());
	            ps.setString(4, pagamento.getCodice_carta());
	            ps.setInt(5, pagamento.getAnnoScadenza());
	            ps.setInt(6, pagamento.getE_utente());

	            if (ps.executeUpdate() != 1) {
	                throw new RuntimeException("INSERT error.");
	            }

	           

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

		}

		// Permette di cercare una carta tramite il suo numero di carta

		public 	PagamentoBean SearchCard(String numero_carta) {


	        try (Connection con = ds.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                    "SELECT * FROM metodo_pagamento WHERE codice_carta = ?");

	            ps.setString(1, numero_carta);

	            ResultSet rs = ps.executeQuery();

	            rs.next();

	            PagamentoBean pagamentoBean = new PagamentoBean();
	            pagamentoBean.setId_pagamento(rs.getInt(1));
	            pagamentoBean.setNominativo(rs.getString(2));
	            pagamentoBean.setCVV(rs.getInt(3));
	            pagamentoBean.setCodice_carta(rs.getString(4));
	            pagamentoBean.setAnnoScadenza(rs.getInt(5));
	            pagamentoBean.setE_utente(rs.getInt(6));

	            return pagamentoBean;
		 } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
		}

		// Permette di elminiare il metodo di pagamento abbinato ad un utente

		public boolean DeleteMethodPayment(String numero_carta, String email) {
			int result;
			 try (Connection con = ds.getConnection()) {
		            PreparedStatement ps = con.prepareStatement(
		                    "DELETE FROM metodo_pagamento WHERE codice_carta = ? AND e_utente = ?");
		            ps.setString(1, numero_carta);
		            ps.setString(1, email);

		            result = ps.executeUpdate();
		        }catch (SQLException e) {
		            throw new RuntimeException(e);
		        }
			return true;
		}
		       

		// Aggiorna dati di pagamento
		public void UpdateMethodPayment(PagamentoBean pyB) {

			String sql = "UPDATE metodo_pagamento SET codice_carta = ?, e_utente = ?, nominativo = ?, CVV = ?, annoScadenza = ? WHERE (codice_carta = ?)";

			  try (Connection con = ds.getConnection()) {

		            Statement st = con.createStatement();
		            String query = "UPDATE metodo_pagamento SET codice_carta = ?, e_utente = ?, nominativo = ?, CVV = ?, annoScadenza = ? WHERE (codice_carta = ?)";
		            st.executeUpdate(query);


		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		        }
		    }
}

		