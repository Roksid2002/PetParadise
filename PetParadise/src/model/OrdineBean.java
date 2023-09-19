package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrdineBean {
	    private CartBean cart = new CartBean();
	    private ProductBean product;
	    private int ID_utente, ID_prodotto, ID_ordine, quantita;
	    private double totale, prezzo;

	    public OrdineBean() {
	    	setProduct(product);
			setQuantita(1);
	    }
	    public ProductBean getProduct() {
			return product;
		}
		
		public void setProduct(ProductBean product) {
			this.product = product;
		}
		public int getID_utente() {
			return ID_utente;
		}

		public void setID_utente(int iD_utente) {
			ID_utente = iD_utente;
		}

		public int getID_prodotto() {
			return ID_prodotto;
		}

		public void setID_prodotto(int iD_prodotto) {
			ID_prodotto = iD_prodotto;
		}

		public int getID_ordine() {
			return ID_ordine;
		}

		public void setID_ordine(int iD_ordine) {
			ID_ordine = iD_ordine;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}

		public double getTotale() {
			return totale;
		}

		public void setTotale(double totale) {
			this.totale = totale;
		}

		public double getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}

		public void incrementQuantita() {
			setQuantita(getQuantita() + 1);
		}
		public CartBean getCart() {
			return cart;
		}
		public void setCart(CartBean cart) {
			this.cart = cart;
		}
		
	}