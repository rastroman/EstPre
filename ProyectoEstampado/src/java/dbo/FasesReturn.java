/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Alejandro.Montes
 */
public class FasesReturn {
    private String numeroFase;
    private String nombreFase;

    public FasesReturn() {
    }

    public FasesReturn(String numeroFase, String nombreFase) {
        this.numeroFase = numeroFase;
        this.nombreFase = nombreFase;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getNumeroFase() {
        return numeroFase;
    }

    public void setNumeroFase(String numeroFase) {
        this.numeroFase = numeroFase;
    }
    
}
