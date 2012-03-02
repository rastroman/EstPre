/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author alejandro.montes
 */


public class Departamento {

    private String departamento;
    private String departamentoAbr;

    public Departamento() {
    }

    public Departamento(String departamento, String departamentoAbr) {
        this.departamento = departamento;
        this.departamentoAbr = departamentoAbr;
    }

    
    
    
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamentoAbr() {
        return departamentoAbr;
    }

    public void setDepartamentoAbr(String departamentoAbr) {
        this.departamentoAbr = departamentoAbr;
    }
    
        
        
}
