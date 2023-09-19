package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UtenteBean{
    public UtenteBean(){}

    private int id;
    private String nome, cognome, email, password, telefono, citta, indirizzo, admin;
    private GregorianCalendar ddNascita, registrazione;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

   

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

   

    public String isAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getDdNascita() {
        return ddNascita.get(Calendar.YEAR)+"-"
                +((ddNascita.get(Calendar.MONTH) + 1)) + "-"
                +(ddNascita.get(Calendar.DAY_OF_MONTH));
    }
    public void setDdNascita(String data){

        String[] data_split = data.split("-");

        int anno = Integer.parseInt(data_split[0]);
        int mese = Integer.parseInt(data_split[1]);
        int giorno = Integer.parseInt(data_split[2]);

        mese--;
        this.ddNascita = new GregorianCalendar(anno,mese,giorno);
    }

    public String getRegistrazione() {
        return registrazione.get(Calendar.YEAR)+"-"
                +(registrazione.get(Calendar.MONTH) +1)+"-"
                +(registrazione.get(Calendar.DAY_OF_MONTH));
    }
    public void setRegistrazione(){
        this.registrazione = new GregorianCalendar();
    }

    public void setRegistrazione(String data) {
        String[] data_split = data.split("-");

        int anno = Integer.parseInt(data_split[0]);
        int mese = Integer.parseInt(data_split[1]);
        int giorno = Integer.parseInt(data_split[2]);

        mese--;
        this.registrazione = new GregorianCalendar(anno,mese,giorno);
    }


}