/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author alejandro.montes
 */
public class CaraFases {
 
    private String nombreFase;
    private int numeroFase;
    private String descripcion;

    public CaraFases() {
    }

    
    
    public CaraFases(String nombreFase, int numeroFase, String descripcion) {
        this.nombreFase = nombreFase;
        this.numeroFase = numeroFase;
        this.descripcion = descripcion;
    }

    
    
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public int getNumeroFase() {
        return numeroFase;
    }

    public void setNumeroFase(int numeroFase) {
        this.numeroFase = numeroFase;
    }

   
    
}

