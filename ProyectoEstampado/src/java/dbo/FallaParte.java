/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author alejandro.montes
 */
public class FallaParte {
    
    private String nombreFase;
    private String modoFalla;
    private String efectoFalla;
    private String causaFalla;

    public FallaParte() {
    }

    public FallaParte(String nombreFase, String modoFalla, String efectoFalla, String causaFalla) {
        this.nombreFase = nombreFase;
        this.modoFalla = modoFalla;
        this.efectoFalla = efectoFalla;
        this.causaFalla = causaFalla;
    }

    public String getCausaFalla() {
        return causaFalla;
    }

    public void setCausaFalla(String causaFalla) {
        this.causaFalla = causaFalla;
    }

    public String getEfectoFalla() {
        return efectoFalla;
    }

    public void setEfectoFalla(String efectoFalla) {
        this.efectoFalla = efectoFalla;
    }

    public String getModoFalla() {
        return modoFalla;
    }

    public void setModoFalla(String modoFalla) {
        this.modoFalla = modoFalla;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }
    
    
}
