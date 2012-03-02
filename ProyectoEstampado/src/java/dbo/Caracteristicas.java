/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Alejandro.Montes
 */
public class Caracteristicas {
    
    private String descripcion;
    private int numero;
    private String grado;
    private String observaciones;
    private String tipo;

    public Caracteristicas() {
    }

    public Caracteristicas(String descripcion, int numero, String grado, String observaciones, String tipo) {
        this.descripcion = descripcion;
        this.numero = numero;
        this.grado = grado;
        this.observaciones = observaciones;
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
