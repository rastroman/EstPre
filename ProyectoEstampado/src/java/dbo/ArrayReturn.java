/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro.Montes
 */
public class ArrayReturn {
  private List<String> numero = new ArrayList<String>();
  private List<String> nombre = new ArrayList<String>();
  private List<String> amef = new ArrayList<String>();
  private List<String> id = new ArrayList<String>();
    public List<String> getAmef() {
        return amef;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getId() {
        return id;
    }

    public ArrayReturn() {
    }
    
   public ArrayReturn (List<String> nombre, List<String> numero, List<String> codigo){
    
    this.nombre = nombre;
    this.amef = codigo;
    this.numero = numero;
    
      }




        

    public List<String> getNombre() {
        return nombre;
    }

    public void setAmef(List<String> amef) {
        this.amef = amef;
    }

    public void setNombre(List<String> nombre) {
        this.nombre = nombre;
    }

    public void setNumero(List<String> numero) {
        this.numero = numero;
    }

    public List<String> getNumero() {
        return numero;
    }
}
