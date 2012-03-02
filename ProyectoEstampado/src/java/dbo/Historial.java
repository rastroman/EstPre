/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Alejandro.Montes
 */
public class Historial {
    private String usuario;
    private String razon;
    private String fecha;
    private String pieza;

    public Historial() {
    }

    
    public Historial(String usuario, String razon, String fecha, String pieza) {
        this.usuario = usuario;
        this.razon = razon;
        this.fecha = fecha;
        this.pieza = pieza;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
