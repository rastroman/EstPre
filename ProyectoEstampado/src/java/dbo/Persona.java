/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author alejandro.montes
 */
public class Persona {
    
    private int idPersona;
    private int idDepto;
    private String nombrePersona;
    private String nombreAbr;

    public Persona() {
    }

    public Persona(int idPersona, int idDepto, String nombrePersona, String nombreAbr) {
        this.idPersona = idPersona;
        this.idDepto = idDepto;
        this.nombrePersona = nombrePersona;
        this.nombreAbr = nombreAbr;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreAbr() {
        return nombreAbr;
    }

    public void setNombreAbr(String nombreAbr) {
        this.nombreAbr = nombreAbr;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    
    
}
