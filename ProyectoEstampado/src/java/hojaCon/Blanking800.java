/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hojaCon;

import conexion.ConnSql;
import dbo.Departamento;
import dbo.ListaRegreso;
import java.util.*;
import org.zkoss.zul.*;
import java.sql.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;
import conexion.ConnSql;
import org.zkoss.zk.ui.event.UploadEvent;
/**
 *
 * @author alejandro.montes
 */
public class Blanking800 {

    private Listbox troquelLi;
    private List regresoTroqueles;
    private Listbox depart;
    private List regreso;
    private Window myBlan;
    private Label troqueLiLab;
    private Window myTroquel;
    private org.zkoss.zul.Button btnGa;

    public void prensaBL800(Window comodin, final Listbox procesosMaq) throws SQLException, InterruptedException {

        ConnSql x = new ConnSql();
        final int idp = x.traerIdParte(comodin.getTitle());
        final String proceso = procesosMaq.getSelectedItem().getLabel();
        int idPro = x.obtenerIdProceso(proceso);
        String dx = x.ObtenerMaqProceso(idPro);

        final Window win = (Window) Executions.createComponents(
                "/opc/amef/consultaParte.zul", null, null);


        myBlan = new Window("Blanking 800", "normal", false);
        myBlan.setHeight("600px");
        myBlan.setWidth("370px");
        win.getChildren().clear();
        myBlan.getChildren().clear();

        win.appendChild(myBlan);
        ConnSql conectar = new ConnSql();
        Grid myGrid = new Grid();
        Columns myCol = new Columns();
        Rows myRows = new Rows();
        Column columna1 = new Column("Componente");
        Column columna2 = new Column("Descripción");
        myCol.appendChild(columna1);
        myCol.appendChild(columna2);
        Row row1 = new Row();
        Row row2 = new Row();
        Row row3 = new Row();
        Row row4 = new Row();
        Row row5 = new Row();
        Row row6 = new Row();
        Row row7 = new Row();
        Row row8 = new Row();
        Row row9 = new Row();
        Row row10 = new Row();
        Row row11 = new Row();
        Row row12 = new Row();
        Row row13 = new Row();
        Row row14 = new Row();
        Row row15 = new Row();
        Row row16 = new Row();
        Row row17 = new Row();
        Row row18 = new Row();
        Row row19 = new Row();
        Row row20 = new Row();
        Row row21 = new Row();
        Row row22 = new Row();
        Row row23 = new Row();
        Row row24 = new Row();
        Row row25 = new Row();
        Row row26 = new Row();
        Row row27 = new Row();
        Row row28 = new Row();

        String modelos = conectar.traerModelo((comodin.getTitle()));

        Label modelo = new Label("Modelo de la parte");
        Label reModelo = new Label(modelos);

        row1.appendChild(modelo);
        row1.appendChild(reModelo);



        Label NoPar = new Label("Número de Parte");
        final Label NoParte = new Label(comodin.getTitle());

        row2.appendChild(NoPar);
        row2.appendChild(NoParte);


        Label NomPar = new Label("Nombre de la Parte");
        Label NomParte = new Label(conectar.traerNombreParte(comodin.getTitle()));

        row3.appendChild(NomPar);
        row3.appendChild(NomParte);


        Label NivelIngen = new Label("Nivel de Ingeniería");
        Label NivelIng = new Label(conectar.obtenerNivIng(comodin.getTitle()));

        row4.appendChild(NivelIngen);
        row4.appendChild(NivelIng);

        Label revision = new Label("Revisión");

        String res = conectar.obtenerUltimaRevision(idp);
        Label rev = new Label(res);

        row5.appendChild(revision);
        row5.appendChild(rev);


        int idEspLam = x.traerIdEspLam(idp);
        Label pesoBlanking = new Label("Peso Blanking");
        double a = conectar.obtenerPesoBlanking(idEspLam);
        Label peBlanking = new Label(String.valueOf(a) + " Kg");

        row6.appendChild(pesoBlanking);
        row6.appendChild(peBlanking);


        Label pesoParte = new Label("Peso de la Pieza");
        double ads = conectar.obtenerPesoParte(idEspLam);
        Label pePart = new Label(String.valueOf(ads) + " Kg");

        row7.appendChild(pesoParte);
        row7.appendChild(pePart);

        Label material = new Label("Material");
        int mat = conectar.obtenerMaterial(idp);
        String noMat = conectar.obtenerNombreMat(mat);
        final Label mate = new Label(noMat);

        row8.appendChild(material);
        row8.appendChild(mate);

        int anchoMt = conectar.obtenerAnchoMat(idEspLam);
        Label anchoRollo = new Label("Ancho del Rollo");
        Label anchoRo = new Label(String.valueOf(anchoMt) + " mm");

        row9.appendChild(anchoRollo);
        row9.appendChild(anchoRo);


        int espMat = conectar.obtenerEspesor(idEspLam);
        Label espesor = new Label("Espesor del Material");
        Label espesorM = new Label(String.valueOf(espMat) + " mm");

        row10.appendChild(espesor);
        row10.appendChild(espesorM);

        Label pasoAlim = new Label("Paso de Avance");
        double pa = conectar.obtenerPasoAlim(idEspLam);
        Label pasoAlime = new Label(String.valueOf(pa) + " mm");

        row11.appendChild(pasoAlim);
        row11.appendChild(pasoAlime);

        Label PresB = new Label("Presión de Balanza");
        Div centrado = new Div();
        final Textbox PBal = new Textbox();
        Label re = new Label("Kg.f/cm^2");
        centrado.appendChild(PBal);
        centrado.appendChild(re);

        row12.appendChild(PresB);
        row12.appendChild(centrado);

        Label PresC = new Label("Presión de Nivelador (Entrada)");
        Div centr = new Div();
        Label rat = new Label("Kg.f/cm^2");
        final Textbox PCol = new Textbox();
        centr.appendChild(PCol);
        centr.appendChild(rat);

        row14.appendChild(PresC);
        row14.appendChild(centr);

        Label AcEnb = new Label("Presión de Nivelador (Salida)");
        final Textbox Ace = new Textbox();
        row15.appendChild(AcEnb);
        row15.appendChild(Ace);

        Label PersEst = new Label("RPM");
        final Textbox PerstEsta = new Textbox();
        row16.appendChild(PersEst);
        row16.appendChild(PerstEsta);

        Label piLotR = new Label("PILOT ROLL");
        final Textbox piLotRoll = new Textbox();
        row13.appendChild(piLotR);
        row13.appendChild(piLotRoll);

        Label angAl = new Label("ANG. ALIM.");
        final Textbox angAli = new Textbox();
        row24.appendChild(angAl);
        row24.appendChild(angAli);

        
        int valor = procesosMaq.getSelectedItem().getIndex() + 1;
        String dt = String.valueOf(valor);
        final String secuen = ("D" + dt + "-" + proceso);
        Label troquel = new Label("Crear un Troquel");
        troqueLiLab = new Label();
        Button troq = new Button("Crear");
        troq.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                ConnSql x = new ConnSql();
                final int idp = x.traerIdParte(NoParte.getValue());
                int idPro = x.obtenerIdProceso(proceso);
                String dx = x.ObtenerMaqProceso(idPro);
                final Window wind = (Window) Executions.createComponents(
                        "/opc/amef/consultaParte.zul", null, null);
                myTroquel = new Window();
                myTroquel.setTitle("Alta de Troquel para el proceso " + proceso + " de " + dx);
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
                final Textbox nombreEnt = new Textbox(secuen + " " + NoParte.getValue());
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
                            x.eliminarBl800Tr(dsds);
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


        row17.appendChild(troquel);
        row17.appendChild(tro);

        Label altTroq = new Label("Altura del Troquel");
        final Textbox altTroqEn = new Textbox();

        row23.appendChild(altTroq);
        row23.appendChild(altTroqEn);

        Label SujTroq = new Label("Sujeción de Troquel (Arriba)");
        final Textbox SujAr = new Textbox();

        row18.appendChild(SujTroq);
        row18.appendChild(SujAr);

        Label SujTroqAba = new Label("Sujeción de Troquel (Abajo)");
        final Textbox SujAba = new Textbox();

        row19.appendChild(SujTroqAba);
        row19.appendChild(SujAba);

        Label departa = new Label("Selecciona un Departamento");
        depart = new Listbox();
        depart.setMold("select");
        depart.setWidth("125px");
        try {
            regreso = conectar.obtenerDepartamentos();
            ListModelArray list = new ListModelArray(regreso);
            depart.setModel(list);
            depart.setItemRenderer(new ListitemRenderer() {

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

        row20.appendChild(departa);
        row20.appendChild(depart);

        Label secuencia = new Label("Secuencia");

        int sec = procesosMaq.getSelectedIndex();
        final Label secu = new Label("D" + dt + "-" + proceso);

        row21.appendChild(secuencia);
        row21.appendChild(secu);

        Label libera = new Label("Liberación");
        final Listbox liberaEn = new Listbox();
        liberaEn.setMold("select");
        Listitem Lib = new Listitem("ON");
        Listitem Lib2 = new Listitem("OFF");
        liberaEn.appendChild(Lib);
        liberaEn.appendChild(Lib2);
        row25.appendChild(libera);
        row25.appendChild(liberaEn);


        Label alin = new Label("Alineación");
        final Textbox alineac = new Textbox();
        row26.appendChild(alin);
        row26.appendChild(alineac);

        Label spee = new Label("Speed Setting");
        final Textbox speed = new Textbox();
        row27.appendChild(spee);
        row27.appendChild(speed);

        Label observac = new Label("Observaciones");
        final Textbox obs = new Textbox();
        row28.appendChild(observac);
        row28.appendChild(obs);

        Label diag = new Label("Cargar Diagrama");
        Button btnDiag = new Button("Diagrama");
        btnDiag.setUpload("true");
        btnDiag.addEventListener(Events.ON_UPLOAD, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                org.zkoss.util.media.Media media = ((UploadEvent) event).getMedia();
                try {
                    if (media != null) {

                        if (media instanceof org.zkoss.image.Image) {
                            org.zkoss.zul.Image image = new org.zkoss.zul.Image();



                            String dire = NoParte.getValue() + secu.getValue() + ".jpg";
                            dire = dire.replace('/', ' ');
                            dire.replaceAll(" ", "");




                            OutputStream outputStream = new FileOutputStream(new File("C:/Documents and Settings/alejandro.montes/My Documents/NetBeansProjects/ProyectoEstampado/web/img/HojaCond/BL800/" + dire));
                            InputStream inputStream = media.getStreamData();
                            byte[] buffer = new byte[1024];
                            for (int count; (count = inputStream.read(buffer)) != -1;) {
                                outputStream.write(buffer, 0, count);
                            }


                            File aud = new File("C:/Documents and Settings/alejandro.montes/My Documents/NetBeansProjects/ProyectoEstampado/web/img/HojaCond/BL800/" + dire);
                            String filepath = aud.getAbsolutePath();
                            Messagebox.show("La imagen se guardo satisfactoriamente " + filepath, "Information", Messagebox.OK, Messagebox.INFORMATION);
                            outputStream.flush();
                            outputStream.close();
                            inputStream.close();



                        } else {
                            Messagebox.show("No es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);

                        }


                    }
                } catch (Exception ex) {
                    Messagebox.show("Error Interno (1), Consulte al programador", "Information", Messagebox.OK, Messagebox.INFORMATION);

                }

            }
        });


        row22.appendChild(diag);
        row22.appendChild(btnDiag);

        Div btnG = new Div();
        btnG.setAlign("center");
        Button btnAc = new Button("Guardar");
        btnAc.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
                boolean x = true;
                //Blanking 800 utiliza el número de máquina número 1
                int numeroMaq = 3;
                //Bl800 utiliza el formato número 9
                int numeroFormato = 5;
                ConnSql conectar = new ConnSql();
                x = conectar.duplicidadParteHC(idp);
                if (x == true) {
                    conectar.crearHojaCond(idp);
                }
                int idHC = conectar.traerIdHojaCond(idp);
                String troquel = troqueLiLab.getValue();
                int idTroq = conectar.traerIdTroquel(troquel);
                String depa = depart.getSelectedItem().getLabel();
                int idDepa = conectar.obtenerIdDepto(depa);
                int sec = procesosMaq.getSelectedItem().getIndex() + 1;
                String dt = String.valueOf(sec);

                double presionB = Double.valueOf(PBal.getValue());
                double RPM = Double.valueOf(PerstEsta.getValue());
                double alturaTroq = Double.valueOf(altTroqEn.getValue());
                String piLotRol = piLotRoll.getValue();
                String anguloAl = angAli.getValue();
                String presionLibEn = PCol.getValue();
                String presionLibSal = Ace.getValue();
                String SujecA = SujAr.getValue();
                String SujeAb = SujAba.getValue();
                String Mater = mate.getValue();
                String observa = obs.getValue();
                String liberac = liberaEn.getSelectedItem().getLabel();
                String alineacion = alineac.getValue();
                String speedSet = speed.getValue();
                int idMat = conectar.obtenerIdMaterial(Mater);
                try {
                    conectar.eliminarDuplicidadBlanking800(idHC, sec);
                    conectar.altaBlanking800(numeroMaq, numeroFormato, idp, idHC, idTroq, idDepa, sec, idMat, presionB, RPM, piLotRol, anguloAl, liberac, alturaTroq, presionLibEn, presionLibSal, SujecA, SujeAb, alineacion, speedSet, observa);

                } catch (Exception ex) {
                    Messagebox.show("Error al intentar dar de alta la hoja de condiciones 'Mostrar'", "Asistente del APQP", Messagebox.OK, Messagebox.INFORMATION);
                }


                myBlan.setClosable(true);
                myBlan.onClose();
                win.onClose();
            }
        });
        btnG.appendChild(btnAc);




        myRows.appendChild(row1);
        myRows.appendChild(row2);
        myRows.appendChild(row3);
        myRows.appendChild(row4);
        myRows.appendChild(row5);
        myRows.appendChild(row6);
        myRows.appendChild(row7);
        myRows.appendChild(row8);
        myRows.appendChild(row9);
        myRows.appendChild(row10);
        myRows.appendChild(row11);
        myRows.appendChild(row12);
        myRows.appendChild(row14);
        myRows.appendChild(row15);
        myRows.appendChild(row16);
        myRows.appendChild(row13);
        myRows.appendChild(row24);
        myRows.appendChild(row17);
        myRows.appendChild(row23);
        myRows.appendChild(row18);
        myRows.appendChild(row19);
        myRows.appendChild(row20);
        myRows.appendChild(row21);
        myRows.appendChild(row25);
        myRows.appendChild(row26);
        myRows.appendChild(row27);
        myRows.appendChild(row28);
        myRows.appendChild(row22);
        myGrid.appendChild(myRows);
        myGrid.appendChild(myCol);
        myBlan.appendChild(myGrid);
        myBlan.appendChild(btnG);
        myBlan.setSizable(true);
        myBlan.setMaximizable(true);
        myBlan.setContentStyle("overflow:auto");
        myBlan.doModal();

    }
}
