/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
public class Trener {
    private int idTrener;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;

    public Trener(int idTrener, String korisnickoIme, String lozinka, String ime, String prezime) {
        this.idTrener = idTrener;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
    }
    public Trener(){}

    public int getIdTrener() {
        return idTrener;
    }

    public void setIdTrener(int idTrener) {
        this.idTrener = idTrener;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
}
