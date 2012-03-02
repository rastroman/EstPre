/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;



/**
 *
 * @author Alejandro.Montes
 */
public class Amef {
    
    private String nombre;
    private String codigo;
    private String numero;

    public Amef() {
    }

    public Amef(String nombre, String numero, String codigo) {
       this.nombre = nombre;
       this.numero = numero;
       this.codigo = codigo;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
 
    
    
}
