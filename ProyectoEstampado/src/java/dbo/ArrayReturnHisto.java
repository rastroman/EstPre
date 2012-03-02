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
public class ArrayReturnHisto {
  private List<String> usuario = new ArrayList<String>();
  private List<String> razon = new ArrayList<String>();
  private List<String> fecha = new ArrayList<String>();
  private List<String> pieza = new ArrayList<String>();

    public ArrayReturnHisto() {
    }

  
  
    public List<String> getFecha() {
        return fecha;
    }

    public void setFecha(List<String> fecha) {
        this.fecha = fecha;
    }

    public List<String> getPieza() {
        return pieza;
    }

    public void setPieza(List<String> pieza) {
        this.pieza = pieza;
    }

    public List<String> getRazon() {
        return razon;
    }

    public void setRazon(List<String> razon) {
        this.razon = razon;
    }

    public List<String> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<String> usuario) {
        this.usuario = usuario;
    }
  
  
  
  
}