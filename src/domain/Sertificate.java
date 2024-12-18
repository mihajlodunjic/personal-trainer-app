/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
public class Sertificate {
    private int idSertificate;
    private String name;
    private String publisher;

    public Sertificate() {
    }

    public Sertificate(int idSertificate, String name, String publisher) {
        this.idSertificate = idSertificate;
        this.name = name;
        this.publisher = publisher;
    }

    public int getIdSertificate() {
        return idSertificate;
    }

    public void setIdSertificate(int idSertificate) {
        this.idSertificate = idSertificate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
