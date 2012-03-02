/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hojaCon;

import conexion.ConnSql;
import java.sql.SQLException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Alejandro.Montes
 */
public class Troquel {

    private Window myTroquel;
    private Grid myGrid;
    private org.zkoss.zul.Button btnG;

    public void crearTroquel(Label parte, String proceso, String secu) throws SQLException, InterruptedException {
        ConnSql x = new ConnSql();
        final int idp = x.traerIdParte(parte.getValue());
        int idPro = x.obtenerIdProceso(proceso);
        String dx = x.ObtenerMaqProceso(idPro);
        final Window wind = (Window) Executions.createComponents(
                "/opc/amef/consultaParte.zul", null, null);
        myTroquel = new Window();
        myTroquel.setTitle("Alta de Troquel para el proceso " + proceso + " de " + dx);
        myTroquel.setBorder("normal");
        myTroquel.setHeight("280px");
        myTroquel.setWidth("370px");
        myGrid = new Grid();
        Columns myCol = new Columns();
        Rows myRows = new Rows();
        Column columna1 = new Column("Componente");
        Column columna2 = new Column("Descripción");
        myCol.appendChild(columna1);
        myCol.appendChild(columna2);
        myGrid.appendChild(myCol);
        myGrid.appendChild(myRows);

        Row row1 = new Row();
        Row row2 = new Row();
        Row row3 = new Row();
        Row row4 = new Row();
        Row row5 = new Row();
        Row row6 = new Row();
        Row row7 = new Row();
        Row row8 = new Row();

        myRows.appendChild(row1);
        myRows.appendChild(row2);
        myRows.appendChild(row3);
        myRows.appendChild(row4);
        myRows.appendChild(row5);
        myRows.appendChild(row6);
        myRows.appendChild(row7);
        myRows.appendChild(row8);

        org.zkoss.zul.Label nombre = new org.zkoss.zul.Label("Nombre del Troquel");
        Textbox nombreEnt = new Textbox(secu + " " + parte.getValue());
        row1.appendChild(nombre);
        row1.appendChild(nombreEnt);

        org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
        Textbox frEnt = new Textbox();
        row2.appendChild(fr);
        row2.appendChild(frEnt);

        org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
        Textbox lrEnt = new Textbox();
        row3.appendChild(lr);
        row3.appendChild(lrEnt);

        org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
        Textbox hEnt = new Textbox();
        row4.appendChild(h);
        row4.appendChild(hEnt);

        org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
        Textbox pesoEnt = new Textbox();
        row5.appendChild(peso);
        row5.appendChild(pesoEnt);

        org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
        Textbox pesotEnt = new Textbox();
        row6.appendChild(pesot);
        row6.appendChild(pesotEnt);

        org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
        Textbox piezasEnt = new Textbox();
        row7.appendChild(piezas);
        row7.appendChild(piezasEnt);

        org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
        Textbox descEnt = new Textbox();
        row8.appendChild(desc);
        row8.appendChild(descEnt);

        btnG = new Button("Guardar");
        btnG.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                myTroquel.setClosable(true);
                myTroquel.onClose();
                wind.onClose();
            }
        });
        wind.appendChild(myTroquel);
        myTroquel.appendChild(myGrid);
        myTroquel.appendChild(btnG);
        myTroquel.doModal();  
        
        
    }
}
