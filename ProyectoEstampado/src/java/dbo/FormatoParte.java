/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author alejandro.montes
 */
public class FormatoParte {
    
    private String tipoFormato;
    private String codigoRevision;
    private String mapaProceso;
    private String fechaRevision;
    private String numeroParte;

    public FormatoParte() {
    }

    public FormatoParte(String tipoFormato, String codigoRevision, String mapaProceso, String fechaRevision, String numeroParte) {
        this.tipoFormato = tipoFormato;
        this.codigoRevision = codigoRevision;
        this.mapaProceso = mapaProceso;
        this.fechaRevision = fechaRevision;
        this.numeroParte = numeroParte;
    }

    public String getCodigoRevision() {
        return codigoRevision;
    }

    public void setCodigoRevision(String codigoRevision) {
        this.codigoRevision = codigoRevision;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getMapaProceso() {
        return mapaProceso;
    }

    public void setMapaProceso(String mapaProceso) {
        this.mapaProceso = mapaProceso;
    }

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getTipoFormato() {
        return tipoFormato;
    }

    public void setTipoFormato(String tipoFormato) {
        this.tipoFormato = tipoFormato;
    }
    
    
    
}
