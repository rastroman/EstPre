/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Alejandro
 */
public class Fase {
    
    private String nombreFase;
    private String nombreFaseAbr;
    private int idFase;
    public Fase() {
    }

    public Fase(String nombreFase, String nombreFaseAbr) {
        this.nombreFase = nombreFase;
        this.nombreFaseAbr = nombreFaseAbr;
    }
    public Fase(int idFase) {
        this.idFase = idFase;
      
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getNombreFaseAbr() {
        return nombreFaseAbr;
    }

    public void setNombreFaseAbr(String nombreFaseAbr) {
        this.nombreFaseAbr = nombreFaseAbr;
    }
    
     
    
}
