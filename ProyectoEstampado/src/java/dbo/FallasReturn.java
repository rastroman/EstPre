/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Alejandro.Montes
 */
public class FallasReturn {
    
    private String modo;
    private String efecto;
    private String falla;
    private String prevencion;
    private String deteccion;
    private String accion;
    private String grado;
    private int sev;
    private int det;
    private int ocu;

    public FallasReturn() {
    }

    public FallasReturn(String modo, String efecto, String falla, String prevencion, String deteccion, String accion, String grado, int sev, int det, int ocu) {
        this.modo = modo;
        this.efecto = efecto;
        this.falla = falla;
        this.prevencion = prevencion;
        this.deteccion = deteccion;
        this.accion = accion;
        this.grado = grado;
        this.sev = sev;
        this.det = det;
        this.ocu = ocu;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getDet() {
        return det;
    }

    public void setDet(int det) {
        this.det = det;
    }

    public String getDeteccion() {
        return deteccion;
    }

    public void setDeteccion(String deteccion) {
        this.deteccion = deteccion;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getOcu() {
        return ocu;
    }

    public void setOcu(int ocu) {
        this.ocu = ocu;
    }

    public String getPrevencion() {
        return prevencion;
    }

    public void setPrevencion(String prevencion) {
        this.prevencion = prevencion;
    }

    public int getSev() {
        return sev;
    }

    public void setSev(int sev) {
        this.sev = sev;
    }
    
    
    
}
