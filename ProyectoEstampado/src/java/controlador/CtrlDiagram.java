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
public class CtrlDiagram extends GenericForwardComposer {

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

            
                    coincidencias = conectar.traerConcidencias(coinc);
//                    grid.setVisible(true);

                
                llenado(self);
                llenadoLista(self);

                sugerencia.addEventListener(Events.ON_SELECT, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(Event event) throws Exception {

                        guardaImg.setVisible(true);


                        String b = sugerencia.getSelectedItem().getLabel().toString();
                        numeroParte.setValue(b);

//
                    }
                });


            } catch (Exception ex) {
                Messagebox.show("Error Interno (esta aquí), Consulte al programador", "Information", Messagebox.OK, Messagebox.INFORMATION);

            }





        }


    }
}
