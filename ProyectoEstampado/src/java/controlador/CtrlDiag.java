/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dbo.Amef;
import dbo.ArrayReturn;
import java.util.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;
import java.sql.*;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;

/**
 *
 * @author Alejandro.Montes
 */
public class CtrlDiag extends GenericForwardComposer {

    private Textbox parte;
    private Listbox sugerencia;
    private Grid guardaImg;
    //RESULTADOS DE AMEF
    private Window amefPart = new Window();
    private Window amefPart2 = new Window();
    private Window menuDiag;
    private Window windo = new Window();
    private Include verif;
    private String a;
    private List<String> coinc = new ArrayList<String>();
    public ArrayReturn coincidencias = new ArrayReturn();
    public ArrayReturn busqueda = new ArrayReturn();
    private static String _templ = "~./zul/html/fileuploaddlg.zul";
    private Label numeroParte;
    private Vbox pics;
    private Textbox nom = new Textbox();
    private Textbox pss = new Textbox();
    private Textbox nombr;
    private Textbox passwor;

    public void llenadoLista(Component comp) throws Exception {
        super.doAfterCompose(comp);

        sugerencia.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Amef r = (Amef) data;
                sugerencia.setVisible(true);
                item.appendChild(new Listcell(r.getNumero()));
                item.appendChild(new Listcell(r.getNombre()));
                item.appendChild(new Listcell(r.getCodigo()));


            }
        });

    }

    public void llenado(Component comp) throws Exception {
        super.doAfterCompose(comp);



        java.util.List res = new java.util.ArrayList();

        for (int j = 0; coinc.size() > j; j++) {
            String aux1 = coincidencias.getNombre().get(j);
            String aux2 = coincidencias.getNumero().get(j);
            String aux3 = coincidencias.getAmef().get(j);
            res.add(new Amef(aux1, aux2, aux3));
        }

        ListModelArray list = new ListModelArray(res);

//        grid.setModel(list);
        sugerencia.setModel(list);
    }

    public void onChange$parte() throws SQLException, InterruptedException {
        sugerencia.setVisible(false);
        coincidencias = null;
        busqueda = new ArrayReturn();
        String valor;
        valor = parte.getValue();
        if (valor.equals("")) {
        } else {
            try {
                conexion.ConnSql conectar = new conexion.ConnSql();
                busqueda = conectar.buscarAmef();
                int n = 0;
                coinc.removeAll(coinc);
                while (n < busqueda.getNombre().size()) {

                    String org = busqueda.getNumero().get(n);
                    int b = org.indexOf(valor);
                    if (b != -1) {
                        coinc.add(busqueda.getId().get(n));
                        

                        guardaImg.setVisible(false);
                    } else {
                        
                        guardaImg.setVisible(false);
                    }
                    n++;
                }
                int b = 0;

                while (coinc.size() > b) {
                    coincidencias = conectar.traerConcidencias(coinc);
//                    grid.setVisible(true);

                    b++;
                }
                llenado(self);
                llenadoLista(self);

                sugerencia.addEventListener(Events.ON_SELECT, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(Event event) throws Exception {
//                        final Window win = (Window) Executions.createComponents(
//                                "/login.zul", null, null);
//
//                        win.getChildren().clear();
//
//                        windo.getChildren().clear();
//                        Grid gr = new Grid();
//                        gr.getChildren().clear();
//
//                        windo.setTitle("Verificación para Historial");
//                        win.appendChild(windo);
//
//                        windo.setWidth("400px");
//                        Columns colp = new Columns();
//                        colp.getChildren().clear();
//                        Column nombre = new Column();
//                        nombre.getChildren().clear();
//                        Column password = new Column();
//                        password.getChildren().clear();
//                        Rows rowss = new Rows();
//                        rowss.getChildren().clear();
//                        Row ro = new Row();
//                        rowss.appendChild(ro);
//                        colp.appendChild(nombre);
//                        colp.appendChild(password);
//                        gr.appendChild(colp);
//                        gr.appendChild(rowss);
//                        windo.appendChild(gr);
//
//                        nombre.appendChild(new Label("Nombre"));
//                        password.appendChild(new Label("Password"));
//
//
//
//                        ro.appendChild(nom);
//                        ro.appendChild(pss);
//
//                        nom.setWidth("240px");
//                        pss.setWidth("240px");
//                        pss.setType("password");
//
//                        Button btni = new Button("Verificar");
//                        windo.appendChild(btni);
//                        btni.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {
//
//                            public void onEvent(Event event) throws Exception {
//                                boolean x = false;
//                                String n = nom.getValue();
//                                String p = pss.getValue();
//                                String parte = numeroParte.getValue();
//
//                                conexion.ConnSql conectar = new ConnSql();
//                                x = conectar.inicioLog(n, p);
//                                if (x == true) {
//                                    verif.setVisible(false);
                                    guardaImg.setVisible(true);
//                                    windo.onClose();
//                                    Messagebox.show("Verificado", "Validación", Messagebox.OK, Messagebox.INFORMATION);
//
//                                    java.util.Date fecha = new java.util.Date();
//
//
//                                    fecha.getDate();
//                                    
//                                    conectar.historial(n, "Diagrama de procesos", parte, fecha);
//
//                                } else {
//                                    Messagebox.show("Error de Verificación", "Validación", Messagebox.OK, Messagebox.INFORMATION);
//                                    windo.onClose();
//                                    sugerencia.setSelectedItem(null);
//
//                                }
//
//                            }
//                        });
//
//
//
//                        windo.setClosable(true);
//                        windo.doModal();
//
//
//
//
//
//                        pics.getChildren().clear();
//                        guardaImg.setVisible(false);
                        String b = sugerencia.getSelectedItem().getLabel().toString();
                        numeroParte.setValue(b);
//                        numeroParte.setVisible(false);
//
//
//
                    }
                });


            } catch (Exception ex) {
                Messagebox.show("Error Interno (1), Consulte al programador", "Information", Messagebox.OK, Messagebox.INFORMATION);

            }

            



        }


    }
    
    
}
