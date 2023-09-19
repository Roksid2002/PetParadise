package model;

public class PagamentoBean {
private static final long serialVersionUID = 1L;
	
	private String codice_carta;
	private String MailCliente;
	private String nominativo;
	private int CVV;
	private int annoScadenza;
	private int e_utente = 0 , id_pagamento;
	public String getCodice_carta() {
		return codice_carta;
	}
	public void setCodice_carta(String codice_carta) {
		this.codice_carta = codice_carta;
	}
	public String getMailCliente() {
		return MailCliente;
	}
	public void setMailCliente(String mailCliente) {
		MailCliente = mailCliente;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int cVV) {
		CVV = cVV;
	}
	public int getAnnoScadenza() {
		return annoScadenza;
	}
	public void setAnnoScadenza(int annoScadenza) {
		this.annoScadenza = annoScadenza;
	}
	public int getE_utente() {
		return e_utente;
	}
	public void setE_utente(int e_utente) {
		this.e_utente = e_utente;
	}
	public int getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	
	
}

