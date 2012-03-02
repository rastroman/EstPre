/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.util.Date;

/**
 *
 * @author alejandro.montes
 */
public class Revisiones {
    
    private String rev;
    private String motivo;
    private Date fecha;
    private String ing;
    private String cal;
    private String prod;

    public Revisiones(String rev, String motivo, Date fecha, String ing, String cal, String prod) {
        this.rev = rev;
        this.motivo = motivo;
        this.fecha = fecha;
        this.ing = ing;
        this.cal = cal;
        this.prod = prod;
    }

    public Revisiones(String rev, String motivo, Date fecha) {
        this.rev = rev;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public Revisiones() {
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
    
    
    
}
