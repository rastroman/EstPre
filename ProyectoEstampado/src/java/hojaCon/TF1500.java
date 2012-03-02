/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hojaCon;

import org.zkoss.zul.*;
import java.sql.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import conexion.ConnSql;
import dbo.Departamento;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.zkoss.zk.ui.event.UploadEvent;

/**
 *
 * @author Alejandro.Montes
 */
public class TF1500 {

    private Window myTf1500;
    private Grid gridPrinc;
    private Grid grid1;
    private Grid grid2;
    private Grid grid3;
    private Grid grid4;
    private Listbox rdl3;
    private List regreso;
    private Column col2;
    private Column col3;
    private Column col4;
    private Column col5;
    private Column col6;
    private Column col7;
    private Label troqueLiLab;
    private Label troqueLiLab2;
    private Label troqueLiLab3;
    private Label troqueLiLab4;
    private Label troqueLiLab5;
    private Label troqueLiLab6;
    private Window myTroquel;
    private org.zkoss.zul.Button btnGa;
    private Button btnDiag;
    private int modo;

    public void prensaTF1500(final Window comodin, Listbox procesosTF1500) throws SQLException, InterruptedException {
        ConnSql x = new ConnSql();

        final int idp = x.traerIdParte(comodin.getTitle());
        final Window win = (Window) Executions.createComponents(
                "/opc/amef/consultaParte.zul", null, null);
        myTf1500 = new Window();
        myTf1500.setTitle("Transfer 1500");
        myTf1500.setBorder("normal");
        myTf1500.setHeight("930px");
        myTf1500.setWidth("800px");
        win.getChildren().clear();

        grid1 = new Grid();
        Columns myCol = new Columns();
        Rows myRows = new Rows();
        Column columna1 = new Column("Automatización");
        Column columna2 = new Column("Ángulo");
        myCol.appendChild(columna1);
        myCol.appendChild(columna2);

        Row row1 = new Row();
        Row row2 = new Row();
        Row row3 = new Row();
        Row row4 = new Row();
        Row row5 = new Row();
        Row row6 = new Row();

        Label first = new Label("#1 ON");
        final Textbox firston = new Textbox();
        row1.appendChild(first);
        row1.appendChild(firston);

        Label firsto = new Label("#1 OFF");
        final Textbox firstoff = new Textbox();
        row2.appendChild(firsto);
        row2.appendChild(firstoff);

        Label second = new Label("#2 ON");
        final Textbox secondon = new Textbox();
        row3.appendChild(second);
        row3.appendChild(secondon);

        Label secondo = new Label("#2 OFF");
        final Textbox secondoff = new Textbox();
        row4.appendChild(secondo);
        row4.appendChild(secondoff);

        Label third = new Label("#3 ON");
        final Textbox thirdon = new Textbox();
        row5.appendChild(third);
        row5.appendChild(thirdon);

        Label thirdo = new Label("#3 OFF");
        final Textbox thirdoff = new Textbox();
        row6.appendChild(thirdo);
        row6.appendChild(thirdoff);

        myRows.appendChild(row1);
        myRows.appendChild(row2);
        myRows.appendChild(row3);
        myRows.appendChild(row4);
        myRows.appendChild(row5);
        myRows.appendChild(row6);

        grid1.appendChild(myCol);
        grid1.appendChild(myRows);

        grid2 = new Grid();
        Columns myColu = new Columns();
        Rows myRowws = new Rows();
        Column columna1a = new Column("Automatización");
        Column columna2a = new Column("Ángulo");
        myColu.appendChild(columna1a);
        myColu.appendChild(columna2a);


        Row row7 = new Row();
        Row row8 = new Row();
        Row row9 = new Row();
        Row row10 = new Row();
        Row row11 = new Row();
        Row row12 = new Row();

        Label fourth = new Label("#4 ON");
        final Textbox fourthon = new Textbox();
        row7.appendChild(fourth);
        row7.appendChild(fourthon);

        Label fourtho = new Label("#4 OFF");
        final Textbox fourthoff = new Textbox();
        row8.appendChild(fourtho);
        row8.appendChild(fourthoff);

        Label fiveth = new Label("#5 ON");
        final Textbox fivethon = new Textbox();
        row9.appendChild(fiveth);
        row9.appendChild(fivethon);

        Label fivetho = new Label("#5 OFF");
        final Textbox fivethoff = new Textbox();
        row10.appendChild(fivetho);
        row10.appendChild(fivethoff);

        Label sixth = new Label("#6 ON");
        final Textbox sixthon = new Textbox();
        row11.appendChild(sixth);
        row11.appendChild(sixthon);

        Label sixtho = new Label("#6 OFF");
        final Textbox sixthoff = new Textbox();
        row12.appendChild(sixtho);
        row12.appendChild(sixthoff);



        myRowws.appendChild(row7);
        myRowws.appendChild(row8);
        myRowws.appendChild(row9);
        myRowws.appendChild(row10);
        myRowws.appendChild(row11);
        myRowws.appendChild(row12);

        grid2.appendChild(myColu);
        grid2.appendChild(myRowws);

        gridPrinc = new Grid();
        Columns myCo = new Columns();
        Rows myRo = new Rows();
        Column grid1a = new Column("");
        Column grid2a = new Column("");
        myCo.appendChild(grid1a);
        myCo.appendChild(grid2a);
        Row niv = new Row();
        niv.appendChild(grid1);
        niv.appendChild(grid2);
        myRo.appendChild(niv);
        gridPrinc.appendChild(myCo);
        gridPrinc.appendChild(myRo);

        grid3 = new Grid();
        Columns titulos = new Columns();
        Rows filas = new Rows();
        Column col1 = new Column("Titulos");

        final int secu = x.obtenerSigSecuencia(idp);

        col2 = new Column("Sin asignar");
        col3 = new Column("Sin asignar");
        col4 = new Column("Sin asignar");
        col5 = new Column("Sin asignar");
        col6 = new Column("Sin asignar");
        col7 = new Column("Sin asignar");



        try {
            procesosTF1500.setSelectedIndex(0);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col2 = new Column("D" + secu + "-" + proc);

        } catch (Exception ex) {
        }
        try {
            procesosTF1500.setSelectedIndex(1);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col3 = new Column("D" + (secu + 1) + "-" + proc);
        } catch (Exception ex) {
        }
        try {
            procesosTF1500.setSelectedIndex(2);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col4 = new Column("D" + (secu + 2) + "-" + proc);
        } catch (Exception ex) {
        }
        try {
            procesosTF1500.setSelectedIndex(3);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col5 = new Column("D" + (secu + 3) + "-" + proc);
        } catch (Exception ex) {
        }
        try {
            procesosTF1500.setSelectedIndex(4);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col6 = new Column("D" + (secu + 4) + "-" + proc);
        } catch (Exception ex) {
        }
        try {
            procesosTF1500.setSelectedIndex(5);
            String proc = procesosTF1500.getSelectedItem().getLabel().toString();
            col7 = new Column("D" + (secu + 5) + "-" + proc);
        } catch (Exception ex) {
        }



        titulos.appendChild(col1);
        titulos.appendChild(col2);
        titulos.appendChild(col3);
        titulos.appendChild(col4);
        titulos.appendChild(col5);
        titulos.appendChild(col6);
        titulos.appendChild(col7);


        Row rowT1 = new Row();
        Row rowT2 = new Row();
        Row rowT3 = new Row();
        Row rowT4 = new Row();
        Row rowT5 = new Row();
        Row rowT6 = new Row();


        Label colchon = new Label("Colchon");
        Label nombreTroq = new Label("Troquel");
        Label longPer = new Label("Long. de Perno");
        Label presc = new Label("Pres.Cil.Nitro.");
        Label sujecionSup = new Label("Suejeción Sup.");
        Label sujecionInf = new Label("Sujeción Inf.");


        rowT1.appendChild(colchon);
        rowT2.appendChild(nombreTroq);
        rowT3.appendChild(longPer);
        rowT4.appendChild(presc);
        rowT5.appendChild(sujecionSup);
        rowT6.appendChild(sujecionInf);

        Label rd1 = new Label();

        rd1.setValue("1");

        troqueLiLab = new Label();

        final String secuen = (col2.getLabel());
        Button troq = new Button("Crear");
        troq.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        
                        
                        
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {

                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();


            }
        });


        Div tro = new Div();
        tro.appendChild(troq);
        Separator tr = new Separator();
        tro.appendChild(tr);
        tro.appendChild(troqueLiLab);



        final Textbox rd3 = new Textbox();
        final Textbox rd4 = new Textbox();
        final Textbox rd5 = new Textbox();
        final Textbox rd6 = new Textbox();
        if ("Sin asignar".equals(col2.getLabel())) {

            tro.setVisible(false);
            rd3.setVisible(false);
            rd4.setVisible(false);
            rd5.setVisible(false);
            rd6.setVisible(false);
        }

        rowT1.appendChild(rd1);
        rowT2.appendChild(tro);
        rowT3.appendChild(rd3);
        rowT4.appendChild(rd4);
        rowT5.appendChild(rd5);
        rowT6.appendChild(rd6);

        Label ra1 = new Label();
        ra1.setValue("2");



        troqueLiLab2 = new Label();
        final String secuen2 = (col3.getLabel());
        Button troq2 = new Button("Crear");
        troq2.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen2);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen2 + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab2.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {

                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();


            }
        });


        Div tro2 = new Div();
        tro2.appendChild(troq2);
        Separator tr2 = new Separator();
        tro2.appendChild(tr2);
        tro2.appendChild(troqueLiLab2);





        final Textbox ra3 = new Textbox();
        final Textbox ra4 = new Textbox();
        final Textbox ra5 = new Textbox();
        final Textbox ra6 = new Textbox();
        if ("Sin asignar".equals(col3.getLabel())) {

            tro2.setVisible(false);
            ra3.setVisible(false);
            ra4.setVisible(false);
            ra5.setVisible(false);
            ra6.setVisible(false);
        }

        rowT1.appendChild(ra1);
        rowT2.appendChild(tro2);
        rowT3.appendChild(ra3);
        rowT4.appendChild(ra4);
        rowT5.appendChild(ra5);
        rowT6.appendChild(ra6);

        Label rb1 = new Label();
        rb1.setValue("3");



        troqueLiLab3 = new Label();
        final String secuen3 = (col4.getLabel());
        Button troq3 = new Button("Crear");
        troq3.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen3);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen3 + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab3.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {

                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();


            }
        });


        Div tro3 = new Div();
        tro3.appendChild(troq3);
        Separator tr3 = new Separator();
        tro3.appendChild(tr3);
        tro3.appendChild(troqueLiLab3);



        final Textbox rb3 = new Textbox();
        final Textbox rb4 = new Textbox();
        final Textbox rb5 = new Textbox();
        final Textbox rb6 = new Textbox();
        if ("Sin asignar".equals(col4.getLabel())) {

            tro3.setVisible(false);
            rb3.setVisible(false);
            rb4.setVisible(false);
            rb5.setVisible(false);
            rb6.setVisible(false);
        }

        rowT1.appendChild(rb1);
        rowT2.appendChild(tro3);
        rowT3.appendChild(rb3);
        rowT4.appendChild(rb4);
        rowT5.appendChild(rb5);
        rowT6.appendChild(rb6);

        Label rc1 = new Label();
        rc1.setValue("4");
        troqueLiLab4 = new Label();
        final String secuen4 = (col5.getLabel());
        Button troq4 = new Button("Crear");
        troq4.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen4);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen4 + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab4.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {
                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();
            }
        });
        Div tro4 = new Div();
        tro4.appendChild(troq4);
        Separator tr4 = new Separator();
        tro4.appendChild(tr4);
        tro4.appendChild(troqueLiLab4);


        final Textbox rc3 = new Textbox();
        final Textbox rc4 = new Textbox();
        final Textbox rc5 = new Textbox();
        final Textbox rc6 = new Textbox();

        if ("Sin asignar".equals(col5.getLabel())) {

            tro4.setVisible(false);
            rc3.setVisible(false);
            rc4.setVisible(false);
            rc5.setVisible(false);
            rc6.setVisible(false);
        }

        rowT1.appendChild(rc1);
        rowT2.appendChild(tro4);
        rowT3.appendChild(rc3);
        rowT4.appendChild(rc4);
        rowT5.appendChild(rc5);
        rowT6.appendChild(rc6);

        Label re1 = new Label();
        re1.setValue("5");


        troqueLiLab5 = new Label();
        final String secuen5 = (col6.getLabel());
        Button troq5 = new Button("Crear");
        troq5.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen5);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen5 + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab5.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {
                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();
            }
        });
        Div tro5 = new Div();
        tro5.appendChild(troq5);
        Separator tr5 = new Separator();
        tro5.appendChild(tr5);
        tro5.appendChild(troqueLiLab5);

        final Textbox re3 = new Textbox();
        final Textbox re4 = new Textbox();
        final Textbox re5 = new Textbox();
        final Textbox re6 = new Textbox();
        if ("Sin asignar".equals(col6.getLabel())) {

            tro5.setVisible(false);
            re3.setVisible(false);
            re4.setVisible(false);
            re5.setVisible(false);
            re6.setVisible(false);
        }

        rowT1.appendChild(re1);
        rowT2.appendChild(tro5);
        rowT3.appendChild(re3);
        rowT4.appendChild(re4);
        rowT5.appendChild(re5);
        rowT6.appendChild(re6);

        Label rf1 = new Label();
        rf1.setValue("6");

        troqueLiLab6 = new Label();
        final String secuen6 = (col7.getLabel());
        Button troq6 = new Button("Crear");
        troq6.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + secuen6);
                myTroquel.setBorder("normal");
                myTroquel.setHeight("400px");
                myTroquel.setWidth("370px");
                Grid myGrid = new Grid();
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
                final Textbox nombreEnt = new Textbox(secuen6 + " " + comodin.getTitle());
                row1.appendChild(nombre);
                row1.appendChild(nombreEnt);

                org.zkoss.zul.Label fr = new org.zkoss.zul.Label("FR (Dimensión troquel)");
                final Textbox frEnt = new Textbox();
                row2.appendChild(fr);
                row2.appendChild(frEnt);

                org.zkoss.zul.Label lr = new org.zkoss.zul.Label("LR (Dimensión troquel)");
                final Textbox lrEnt = new Textbox();
                row3.appendChild(lr);
                row3.appendChild(lrEnt);

                org.zkoss.zul.Label h = new org.zkoss.zul.Label("H (Dimensión troquel)");
                final Textbox hEnt = new Textbox();
                row4.appendChild(h);
                row4.appendChild(hEnt);

                org.zkoss.zul.Label peso = new org.zkoss.zul.Label("Peso superior");
                final Textbox pesoEnt = new Textbox();
                row5.appendChild(peso);
                row5.appendChild(pesoEnt);

                org.zkoss.zul.Label pesot = new org.zkoss.zul.Label("Peso total");
                final Textbox pesotEnt = new Textbox();
                row6.appendChild(pesot);
                row6.appendChild(pesotEnt);

                org.zkoss.zul.Label piezas = new org.zkoss.zul.Label("Piezas por golpe");
                final Textbox piezasEnt = new Textbox();
                row7.appendChild(piezas);
                row7.appendChild(piezasEnt);

                org.zkoss.zul.Label desc = new org.zkoss.zul.Label("Descripción");
                final Textbox descEnt = new Textbox();
                row8.appendChild(desc);
                row8.appendChild(descEnt);

                btnGa = new Button("Guardar");
                btnGa.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                        myTroquel.setClosable(true);
                        ConnSql x = new ConnSql();
                        String nombreTroq = nombreEnt.getValue();
                        String FR = frEnt.getValue();
                        String LR = lrEnt.getValue();
                        String hEn = hEnt.getValue();
                        String pesoS = pesoEnt.getValue();
                        String pesoT = pesotEnt.getValue();
                        String piezGol = piezasEnt.getValue();
                        String descE = descEnt.getValue();
                        boolean dsx = x.duplicidadTroquel(nombreTroq);
                        if (dsx == false) {
                            x.altaTroquel(idp, nombreTroq, Double.valueOf(FR), Double.valueOf(LR), Double.valueOf(hEn), Double.valueOf(pesoS), Double.valueOf(pesoT), Integer.valueOf(piezGol), descE);
                            troqueLiLab6.setValue(nombreTroq);
                            Messagebox.show("Alta de Troquel Exitosa " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            wind.onClose();
                        } else {
                            int dsds = x.traerIdTroquel(nombreTroq);
                            int idHojaC = x.traerIdHojaCond(idp);
                            x.eliminarTF1500(idHojaC);
                            x.eliminarTodoTF1500(dsds);
                            x.eliminarTroquel(nombreTroq);
                            Messagebox.show("Se sustituyo el troquel anterior. " + nombreTroq, "Error", Messagebox.OK, Messagebox.INFORMATION);
                            dsx = true;
                        }
                    }
                });
                wind.appendChild(myTroquel);
                myTroquel.appendChild(myGrid);
                myTroquel.appendChild(btnGa);
                myTroquel.doModal();
            }
        });
        Div tro6 = new Div();
        tro6.appendChild(troq6);
        Separator tr6 = new Separator();
        tro6.appendChild(tr6);
        tro6.appendChild(troqueLiLab6);



        final Textbox rf3 = new Textbox();
        final Textbox rf4 = new Textbox();
        final Textbox rf5 = new Textbox();
        final Textbox rf6 = new Textbox();
        if ("Sin asignar".equals(col7.getLabel())) {

            tro6.setVisible(false);
            rf3.setVisible(false);
            rf4.setVisible(false);
            rf5.setVisible(false);
            rf6.setVisible(false);
        }

        rowT1.appendChild(rf1);
        rowT2.appendChild(tro6);
        rowT3.appendChild(rf3);
        rowT4.appendChild(rf4);
        rowT5.appendChild(rf5);
        rowT6.appendChild(rf6);

        filas.appendChild(rowT1);
        filas.appendChild(rowT2);
        filas.appendChild(rowT3);
        filas.appendChild(rowT4);
        filas.appendChild(rowT5);
        filas.appendChild(rowT6);

        grid3.appendChild(titulos);
        grid3.appendChild(filas);

        grid4 = new Grid();
        Columns titulosl = new Columns();
        Rows filasl = new Rows();
        Column coll1 = new Column("Titulos");
        Column coll2 = new Column("");
        Column coll3 = new Column("Titulos");
        Column coll4 = new Column("");
        Column coll5 = new Column("Titulos");
        Column coll6 = new Column("");

        titulosl.appendChild(coll1);
        titulosl.appendChild(coll2);
        titulosl.appendChild(coll3);
        titulosl.appendChild(coll4);
        titulosl.appendChild(coll5);
        titulosl.appendChild(coll6);

        Row rowlT = new Row();
        Row rowlT1 = new Row();
        Row rowlT2 = new Row();
        Row rowlT3 = new Row();
        Row rowlT4 = new Row();
        Row rowlT5 = new Row();
        Row rowlT6 = new Row();
        Row rowlT7 = new Row();
        Row rowlT8 = new Row();

        Label numeroParte = new Label("Número de parte");
        //OBTENER NOMBRE DE PARTE
        Label material = new Label("Material");
        //OBTENER MATERIAL DE LA PARTE
        Label espesor = new Label("Espesor");
        //OBTENER ESPESOR
        Label departamento = new Label("Departamento");
        //PERMITIR SELECCIONAR DEPARTAMENTO
        Label revision = new Label("Revisión");
        //OBTENER REVISIÓN
        Label aceiteEmb = new Label("Aceite de Embutido");
        //TERMINADO
        Label FrecRoc = new Label("Peso de Blanking");
        //OBTENER PESO BL
        Label pesoP = new Label("Peso pieza");
        //OBTENER PESO PIEZA TERMINADA
        Label matriz = new Label("Número de Matriz");


        rowlT.appendChild(numeroParte);
        rowlT1.appendChild(material);
        rowlT2.appendChild(espesor);
        rowlT3.appendChild(departamento);
        rowlT4.appendChild(revision);
        rowlT5.appendChild(aceiteEmb);
        rowlT6.appendChild(FrecRoc);
        rowlT7.appendChild(pesoP);
        rowlT8.appendChild(matriz);

        Label rdl = new Label();
        rdl.setValue(comodin.getTitle());
        Label rdl1 = new Label();
        int mat = x.obtenerMaterial(idp);
        String noMat = x.obtenerNombreMat(mat);
        rdl1.setValue(noMat);
        Label rdl2 = new Label();
        int idEspLam = x.traerIdEspLam(idp);
        int espMat = x.obtenerEspesor(idEspLam);
        rdl2.setValue(String.valueOf(espMat) + " mm");
        rdl3 = new Listbox();
        rdl3.setMold("select");
        rdl3.setWidth("100px");

        try {
            regreso = x.obtenerDepartamentos();
            ListModelArray list = new ListModelArray(regreso);
            rdl3.setModel(list);
            rdl3.setItemRenderer(new ListitemRenderer() {

                public void render(Listitem item, Object data) throws Exception {
                    if (data == null) {
                        return;
                    }
                    Departamento r = (Departamento) data;
                    item.appendChild(new Listcell(r.getDepartamento()));
                }
            });
        } catch (Exception ex) {

            Messagebox.show("Error en listbox Departamentos", "Error", Messagebox.OK, Messagebox.ERROR);
        }

        Label rdl4 = new Label();
        String res = x.obtenerUltimaRevision(idp);
        rdl4.setValue(res);
        final Textbox rdl5 = new Textbox();
        Label rdl6 = new Label();

        double a = x.obtenerPesoBlanking(idEspLam);
        rdl6.setValue(String.valueOf(a) + " Kg");
        Label rdl7 = new Label();
        double ads = x.obtenerPesoParte(idEspLam);
        rdl7.setValue(String.valueOf(ads) + " Kg");
        final Textbox rdl8 = new Textbox();


        rowlT.appendChild(rdl);
        rowlT1.appendChild(rdl1);
        rowlT2.appendChild(rdl2);
        rowlT3.appendChild(rdl3);
        rowlT4.appendChild(rdl4);
        rowlT5.appendChild(rdl5);
        rowlT6.appendChild(rdl6);
        rowlT7.appendChild(rdl7);
        rowlT8.appendChild(rdl8);

        Label ral = new Label("Modelo de la parte");
        Label ral1 = new Label("Dimen. cerrada de la barra");
        Label ral2 = new Label("Carrera de Alimentación");
        Label ral3 = new Label("Carrera de Elevación");
        Label ral4 = new Label("Carrera del Sujetador");
        Label ral5 = new Label("Altura de Troquel");
        Label ral6 = new Label("Copas de Succión");
        Label ral7 = new Label("Presión de Balanza");
        Label ral8 = new Label("Código según PC");

        rowlT.appendChild(ral);
        rowlT1.appendChild(ral1);
        rowlT2.appendChild(ral2);
        rowlT3.appendChild(ral3);
        rowlT4.appendChild(ral4);
        rowlT5.appendChild(ral5);
        rowlT6.appendChild(ral6);
        rowlT7.appendChild(ral7);
        rowlT8.appendChild(ral8);

        Label rbl = new Label();
        rbl.setValue(x.traerModelo(comodin.getTitle()));
        final Textbox rbl1 = new Textbox();
        final Textbox rbl2 = new Textbox();
        final Textbox rbl3 = new Textbox();
        final Textbox rbl4 = new Textbox();
        final Textbox rbl5 = new Textbox();
        final Textbox rbl6 = new Textbox();
        final Textbox rbl7 = new Textbox();
        final Textbox rbl8 = new Textbox();

        rowlT.appendChild(rbl);
        rowlT1.appendChild(rbl1);
        rowlT2.appendChild(rbl2);
        rowlT3.appendChild(rbl3);
        rowlT4.appendChild(rbl4);
        rowlT5.appendChild(rbl5);
        rowlT6.appendChild(rbl6);
        rowlT7.appendChild(rbl7);
        rowlT8.appendChild(rbl8);

        Label rcl = new Label("Nombre de la parte");
        Label rcl1 = new Label("Detección DB 1");
        Label rcl2 = new Label("Detección DB 2");
        Label rcl3 = new Label("Rack");
        Label rcl4 = new Label("Nivel de Ingeniería");
        Label rcl5 = new Label("Cantidad de rack");
        Label rcl6 = new Label("R.P.M Máquina");
        Label rcl7 = new Label("Frecuencia de Rociado");


        rowlT.appendChild(rcl);
        rowlT1.appendChild(rcl1);
        rowlT2.appendChild(rcl2);
        rowlT3.appendChild(rcl4);
        rowlT4.appendChild(rcl3);
        rowlT5.appendChild(rcl5);
        rowlT6.appendChild(rcl6);
        rowlT7.appendChild(rcl7);

        Label rel = new Label();
        rel.setValue(x.traerNombreParte(comodin.getTitle()));
        final Textbox rel1 = new Textbox();
        final Textbox rel2 = new Textbox();
        final Textbox rel3 = new Textbox();
        Label rel4 = new Label();
        String nivE = x.obtenerNivIng(comodin.getTitle());
        rel4.setValue(nivE);

        final Textbox rel5 = new Textbox();
        final Textbox rel6 = new Textbox();
        final Textbox rel7 = new Textbox();

        rowlT.appendChild(rel);
        rowlT1.appendChild(rel1);
        rowlT2.appendChild(rel2);
        rowlT3.appendChild(rel4);
        rowlT4.appendChild(rel3);
        rowlT5.appendChild(rel5);
        rowlT6.appendChild(rel6);
        rowlT7.appendChild(rel7);

        filasl.appendChild(rowlT);
        filasl.appendChild(rowlT1);
        filasl.appendChild(rowlT2);
        filasl.appendChild(rowlT3);
        filasl.appendChild(rowlT4);
        filasl.appendChild(rowlT5);
        filasl.appendChild(rowlT6);
        filasl.appendChild(rowlT7);
        filasl.appendChild(rowlT8);

        grid4.appendChild(titulosl);
        grid4.appendChild(filasl);
        grid4.setAlign("center");

        Div center = new Div();

        Button btnAc = new Button("Guardar");
        btnAc.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                myTf1500.setClosable(true);
                myTf1500.onClose();
                win.onClose();

                ConnSql con = new ConnSql();
                int idpa = con.traerIdParte(comodin.getTitle());
                boolean dsx = con.duplicidadParteHC(idpa);
                if (dsx == true) {
                    con.crearHojaCond(idpa);
                }
                int idHojaC = con.traerIdHojaCond(idpa);
                //La prensa TF1500 utiliza el idMaq 9
                int idMaq = 9;
                String depa = rdl3.getSelectedItem().getLabel();
                int idDepa = con.obtenerIdDepto(depa);
                //La pensa TF1500 utiliza el formato 14
                int idFormato = 14;

                String aceiteEmb = rdl5.getValue();
                String frecuenciaRoc = rel7.getValue();
                double dimenBarra = Double.valueOf(rbl1.getValue());
                double carreraAlim = Double.valueOf(rbl2.getValue());
                double carreraElevaci = Double.valueOf(rbl3.getValue());
                double carreraSuje = Double.valueOf(rbl4.getValue());
                String rack = rel3.getValue();
                double cantidad = Double.valueOf(rel5.getValue());
                String deteccionDb = rel1.getValue();
                String deteccionDb2 = rel2.getValue();
                String CopasSucc = rbl6.getValue();
                double alturaTroq = Double.valueOf(rbl5.getValue());
                int rpm = Integer.valueOf(rel6.getValue());
                double presionBalanza = Double.valueOf(rbl7.getValue());
                int numeroMatriz = Integer.valueOf(rdl8.getValue());
                String codigoSegunPc = rbl8.getValue();

                String aut1on = firston.getValue();
                String aut1off = firstoff.getValue();
                String aut2on = secondon.getValue();
                String aut2off = secondoff.getValue();
                String aut3on = thirdon.getValue();
                String aut3off = thirdoff.getValue();
                String aut4on = fourthon.getValue();
                String aut4off = fourthoff.getValue();
                String aut5on = fivethon.getValue();
                String aut5off = fivethoff.getValue();
                String aut6on = sixthon.getValue();
                String aut6off = sixthoff.getValue();

                if (col7.getLabel().equals("Sin asignar")) {
                    modo = 2;
                    if (col6.getLabel().equals("Sin asignar")) {
                        modo = 3;
                        if (col5.getLabel().equals("Sin asignar")) {
                            modo = 4;
                            if (col4.getLabel().equals("Sin asignar")) {
                                modo = 5;
                                if (col3.getLabel().equals("Sin asignar")) {
                                    modo = 6;
                                    if (col2.getLabel().equals("Sin asignar")) {
                                        modo = 7;
                                    }
                                }
                            }
                        }

                    }
                } else {
                    modo = 1;
                }

                String nombreTroq = troqueLiLab.getValue();
                int idTroq = con.traerIdTroquel(nombreTroq);
                int secuencia = secu;
                String longPerno = rd3.getValue();
                String presCili = rd4.getValue();
                String sujecionSup = rd5.getValue();
                String sujecionInf = rd6.getValue();

                String nombreTroq2 = troqueLiLab2.getValue();
                int idTroq2 = con.traerIdTroquel(nombreTroq2);
                int secuencia2 = secu + 1;
                String longPerno2 = ra3.getValue();
                String presCili2 = ra4.getValue();
                String sujecionSup2 = ra5.getValue();
                String sujecionInf2 = ra6.getValue();

                String nombreTroq3 = troqueLiLab3.getValue();
                int idTroq3 = con.traerIdTroquel(nombreTroq3);
                int secuencia3 = secu + 2;
                String longPerno3 = rb3.getValue();
                String presCili3 = rb4.getValue();
                String sujecionSup3 = rb5.getValue();
                String sujecionInf3 = rb6.getValue();

                String nombreTroq4 = troqueLiLab4.getValue();
                int idTroq4 = con.traerIdTroquel(nombreTroq4);
                int secuencia4 = secu + 3;
                String longPerno4 = rc3.getValue();
                String presCili4 = rc4.getValue();
                String sujecionSup4 = rc5.getValue();
                String sujecionInf4 = rc6.getValue();

                String nombreTroq5 = troqueLiLab5.getValue();
                int idTroq5 = con.traerIdTroquel(nombreTroq5);
                int secuencia5 = secu + 4;
                String longPerno5 = re3.getValue();
                String presCili5 = re4.getValue();
                String sujecionSup5 = re5.getValue();
                String sujecionInf5 = re6.getValue();

                String nombreTroq6 = troqueLiLab6.getValue();
                int idTroq6 = con.traerIdTroquel(nombreTroq6);
                int secuencia6 = secu + 5;
                String longPerno6 = rf3.getValue();
                String presCili6 = rf4.getValue();
                String sujecionSup6 = rf5.getValue();
                String sujecionInf6 = rf6.getValue();





                int idTF1;
                int idTF2;
                int idTF3;
                int idTF4;
                int idTF5;
                int idTF6;


                switch (modo) {
                    case 1:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                        con.altaTF15002(idTroq2, secuencia2, longPerno2, presCili2, sujecionSup2, sujecionInf2);
                        con.altaTF15003(idTroq3, secuencia3, longPerno3, presCili3, sujecionSup3, sujecionInf3);
                        con.altaTF15004(idTroq4, secuencia4, longPerno4, presCili4, sujecionSup4, sujecionInf4);
                        con.altaTF15005(idTroq5, secuencia5, longPerno5, presCili5, sujecionSup5, sujecionInf5);
                        con.altaTF15006(idTroq6, secuencia6, longPerno6, presCili6, sujecionSup6, sujecionInf6);

                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = con.traerIdTF15002(idTroq2);
                        idTF3 = con.traerIdTF15003(idTroq3);
                        idTF4 = con.traerIdTF15004(idTroq4);
                        idTF5 = con.traerIdTF15005(idTroq5);
                        idTF6 = con.traerIdTF15006(idTroq6);

                        con.altaTF1500mod1(idMaq, idHojaC, idTF1, idTF2, idTF3, idTF4, idTF5, idTF6, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 2:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                        con.altaTF15002(idTroq2, secuencia2, longPerno2, presCili2, sujecionSup2, sujecionInf2);
                        con.altaTF15003(idTroq3, secuencia3, longPerno3, presCili3, sujecionSup3, sujecionInf3);
                        con.altaTF15004(idTroq4, secuencia4, longPerno4, presCili4, sujecionSup4, sujecionInf4);
                        con.altaTF15005(idTroq5, secuencia5, longPerno5, presCili5, sujecionSup5, sujecionInf5);
      
                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = con.traerIdTF15002(idTroq2);
                        idTF3 = con.traerIdTF15003(idTroq3);
                        idTF4 = con.traerIdTF15004(idTroq4);
                        idTF5 = con.traerIdTF15005(idTroq5);
                        idTF6 = 0;
                        con.altaTF1500mod2(idMaq, idHojaC, idTF1, idTF2, idTF3, idTF4, idTF5, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 3:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                        con.altaTF15002(idTroq2, secuencia2, longPerno2, presCili2, sujecionSup2, sujecionInf2);
                        con.altaTF15003(idTroq3, secuencia3, longPerno3, presCili3, sujecionSup3, sujecionInf3);
                        con.altaTF15004(idTroq4, secuencia4, longPerno4, presCili4, sujecionSup4, sujecionInf4);

                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = con.traerIdTF15002(idTroq2);
                        idTF3 = con.traerIdTF15003(idTroq3);
                        idTF4 = con.traerIdTF15004(idTroq4);
                        idTF5 = 0;
                        idTF6 = 0;

                        con.altaTF1500mod3(idMaq, idHojaC, idTF1, idTF2, idTF3, idTF4, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 4:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                        con.altaTF15002(idTroq2, secuencia2, longPerno2, presCili2, sujecionSup2, sujecionInf2);
                        con.altaTF15003(idTroq3, secuencia3, longPerno3, presCili3, sujecionSup3, sujecionInf3);                                 
                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = con.traerIdTF15002(idTroq2);
                        idTF3 = con.traerIdTF15003(idTroq3);
                        idTF4 = 0;
                        idTF5 = 0;
                        idTF6 = 0;

                        con.altaTF1500mod4(idMaq, idHojaC, idTF1, idTF2, idTF3, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 5:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                        con.altaTF15002(idTroq2, secuencia2, longPerno2, presCili2, sujecionSup2, sujecionInf2);
                       
                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = con.traerIdTF15002(idTroq2);
                        idTF3 = 0;
                        idTF4 = 0;
                        idTF5 = 0;
                        idTF6 = 0;

                        con.altaTF1500mod5(idMaq, idHojaC, idTF1, idTF2, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 6:
                        con.altaTF15001(idTroq, secuencia, longPerno, presCili, sujecionSup, sujecionInf);
                       
                        idTF1 = con.traerIdTF15001(idTroq);
                        idTF2 = 0;
                        idTF3 = 0;
                        idTF4 = 0;
                        idTF5 = 0;
                        idTF6 = 0;

                        con.altaTF1500mod6(idMaq, idHojaC, idTF1, idDepa, idFormato, aceiteEmb, frecuenciaRoc, carreraAlim, rack, cantidad, deteccionDb, deteccionDb2, alturaTroq, rpm, presionBalanza, CopasSucc, dimenBarra, carreraSuje, carreraElevaci, numeroMatriz, codigoSegunPc, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off, aut5on, aut5off, aut6on, aut6off);

                        break;
                    case 7:
                        Messagebox.show("Si ves este mensaje el sistema tiene un error interno, contacta al desarrollador", "Information", Messagebox.OK, Messagebox.INFORMATION);
                        break;
                    
                }

                myTf1500.setClosable(true);
                myTf1500.onClose();
                win.onClose();
            }
        });

        btnDiag = new Button("Diagrama");
        btnDiag.setUpload("true");
        btnDiag.addEventListener(Events.ON_UPLOAD, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
                try {
                    if (media != null) {
                        if (media instanceof org.zkoss.image.Image) {
                            org.zkoss.zul.Image image = new org.zkoss.zul.Image();
                            String dire = comodin.getTitle() + "TF1500.jpg";
                            dire = dire.replace('/', ' ');
                            dire.replaceAll(" ", "");
                            OutputStream outputStream = new FileOutputStream(new File("C:/Documents and Settings/alejandro.montes/My Documents/NetBeansProjects/ProyectoEstampado/web/img/HojaCond/TF1500/" + dire));
                            InputStream inputStream = media.getStreamData();
                            byte[] buffer = new byte[1024];
                            for (int count; (count = inputStream.read(buffer)) != -1;) {
                                outputStream.write(buffer, 0, count);
                            }

                            File aud = new File("C:/Documents and Settings/alejandro.montes/My Documents/NetBeansProjects/ProyectoEstampado/web/img/HojaCond/TF1500/" + dire);
                            String filepath = aud.getAbsolutePath();
                            Messagebox.show("La imagen se guardo satisfactoriamente " + filepath, "Information", Messagebox.OK, Messagebox.INFORMATION);
                            outputStream.flush();
                            outputStream.close();
                            inputStream.close();
                        } else {
                            Messagebox.show("No es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
                        }
                        btnDiag.setDisabled(true);
                    }
                } catch (Exception ex) {
                    Messagebox.show("Error Interno (1), Consulte al programador", "Information", Messagebox.OK, Messagebox.INFORMATION);
                }
            }
        });

        center.appendChild(btnAc);
        center.appendChild(btnDiag);
        center.setAlign("center");
        myTf1500.appendChild(gridPrinc);
        myTf1500.appendChild(grid3);
        myTf1500.appendChild(grid4);
        myTf1500.appendChild(center);
        win.appendChild(myTf1500);
        myTf1500.setMaximizable(true);
        myTf1500.doModal();

    }
}
