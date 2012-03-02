package controlador;

import conexion.ConnSql;
import dbo.Amef;
import dbo.ArrayReturn;
import dbo.ArrayReturnHisto;
import dbo.CaraFases;
import dbo.Caracteristicas;
import dbo.Departamento;
import dbo.FallaParte;
import dbo.FallasReturn;
import dbo.Fase;
import dbo.FasesReturn;
import dbo.FormatoParte;
import dbo.Historial;
import dbo.ListaRegreso;
import dbo.NumeroParte;
import dbo.Persona;
import dbo.Revisiones;
import hojaCon.Blanking200;
import hojaCon.Blanking400;
import hojaCon.Blanking800;
import hojaCon.TF1500;
import hojaCon.Transfer500;
import hojaCon.tandem200A;
import hojaCon.tandem200B;
import hojaCon.tandem400800;
import java.util.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.theme.Themes;

public class Controller extends GenericForwardComposer {

    private int contadorR = 0;
    private Include rz;
    private Textbox nombre;
    private Textbox password;
    private Button btn;
    private Include indexx;
    private Window principal;
    private Grid log;
    private Listbox boxConsulta;
    private Listbox boxReportes;
    private Listbox boxFormatos;
    private Include resultado;
    private Label consultap;
    private Label CP2;
    private Textbox parte;
    private Listbox sugerencia;
    private Listbox fase;
    private Listbox faseFalla;
    private Listbox faseFalla2;
    private Grid grid;
    //RESULTADOS DE AMEF
    private Window amefPart = new Window();
    private Window fallaProceso = new Window();
    private Window opcProc = new Window();
    //HISTORIAL
    private Image historialimg;
    private Label notes;
    private Listbox historialbox;
    private Caption capt;
    public ArrayReturnHisto histoarray = new ArrayReturnHisto();
    ///////
    private List<String> coinc = new ArrayList<String>();
    private String aux;
    public ArrayReturn coincidencias = new ArrayReturn();
    public ArrayReturn busqueda = new ArrayReturn();
    //Asistente
    private Listcell crearParte;
    private Listbox asistbox;
    private Include parteCrear;
    private Listcell crearTroquel;
    private Image sigimg;
    private Image sigimgt;
    private Textbox numeroEntrada;
    private Label numeroEntradaLab;
    private Textbox nomEntrada;
    private Label nomEntradaLab;
    private Label duplicidad;
    private String parteAsist;
    private String nombreAsist;
    private Image backimg;
    private Image backimgt;
    private Label controlPaso;
    private Window CaracAsis;
    private Window WParteAsis;
    private Window WModeloAsis;
    private Window WTroquelAsis;
    private Window WPersonaAsis;
    private Window WElabAproIngAsis;
    private Button WParteAsisButton;
    private Grid WParteAsisGrid;
    private Div WParteAsisDiv;
    private Grid auxgrid;
    private Window AmefAsis;
    private Textbox codeAmef;
    private Window fasesAsis;
    // Características
    private Button recargarCara;
    private Listbox right;
    private Listbox rightf;
    private Listbox leftf;
    private List regreso;
    private List regresof;
    private List regresoProcMaq;
    private List regresoProcMaqTF1500;
    private List regresoProcMaqTF500;
    private List regresoTan;
    private List regresoTr;
    private List regresoTranspor;
    private List regresoInspec;
    private List regresoPrepa;
    private List regresoBlank;
    private List regresoClientes;
    private List regresoTroqueles;
    private List regresoMateriales;
    private List regresoSecuencia;
    private Listbox left;
    private Listitem item2;
    private Window comodin;
    private Window overview;
    private Listbox caracParte;
    private Listbox fallasParte;
    private Button updateover;
    private Button updateover2;
    private Label lupdateover;
    private Label lupdateover2;
    private Button updateoverf;
    private Button updateoverf2;
    private boolean xd = false;
    private Textbox nom = new Textbox();
    private Textbox pss = new Textbox();
    private Window windo = new Window();
    private Window menuTroqueles;
    private Listbox alta;
    private Listbox baja;
    private Listcell altaComp;
    private Listcell bajaComp;
    private Listcell editComp;
    private Listbox editar;
    private Listcell editFalla;
    private Listbox fallaComp;
    private Listcell elaboraComp;
    private Listbox persona;
    private Listcell editMat;
    private Listbox materiales;
    private Listcell planCtrl;
    private Listbox planControl;
    private Label comodinNombre;
    private Textbox modeloEntrada;
    private Textbox descEntrada;
    private Textbox troquelEntrada;
    private Textbox dFR;
    private Textbox dLR;
    private Textbox dH;
    private Textbox PeSup;
    private Textbox PeTot;
    private Textbox PGolpe;
    private Textbox descTEntrada;
    private Listcell crearModoF;
    private Textbox modoEntrada;
    private Window WModoAsis;
    private Textbox modoOEntrada;
    private Listcell crearEfectoF;
    private Textbox efectoEntrada;
    private Textbox efectoOEntrada;
    private Window WEfectoAsis;
    private Window WCausaAsis;
    private Textbox causaEntrada;
    private Textbox causaOEntrada;
    private Window WDeteccionAsis;
    private Window WPrevencionAsis;
    private Textbox deteccionEntrada;
    private Textbox deteccionOEntrada;
    private Textbox prevencionEntrada;
    private Textbox prevencionOEntrada;
    private Window WGradoAsis;
    private Textbox gradoEntrada;
    private Textbox gradoOEntrada;
    private Window WProcesoAsis;
    private Textbox procesoEntrada;
    private Textbox procesoAbrEntrada;
    private Textbox procesoOEntrada;
    private Window WDepartamentoAsis;
    private Textbox deptoEntrada;
    private Textbox deptoOEntrada;
    private Window WClienteAsis;
    private Textbox clienteEntrada;
    private Textbox clienteDirEntrada;
    private Textbox clienteTelEntrada;
    private Window WUsuarioAsis;
    private Textbox usuarioEntrada;
    private Listbox tipoUsuario;
    private Textbox passUsuarioEnt;
    private Label verc;
    private Image manualImage;
    private Window WCaracteristicaAsis;
    private Listbox partecarac;
    private Listbox partedepa;
    private Label labelCa;
    private Button traerCara;
    private Button accionCara;
    private Textbox caracDeEntrada;
    private Textbox caraNumEntrada;
    private Textbox caraGradEntrada;
    private Textbox caraObEntrada;
    private Textbox caraTiEntrada;
    private Caption eventoCara;
    private Groupbox feo;
    private Window conf;
    private int relleno1 = 0;
    private int relleno2 = 0;
    private int relleno3 = 0;
    private int relleno4 = 0;
    private int relleno5 = 0;
    private int relleno6 = 0;
    private String rellenoS1 = " ";
    private String rellenoS2 = " ";
    private String rellenoS3 = " ";
    private String rellenoS4 = " ";
    private String rellenoS5 = " ";
    private Listbox temas;
    private Listbox depart;
    private Button ctemas;
    private Window temaw1;
    private Window temaw2;
    private Window temaw3;
    private Listcell editIng;
    private Listbox partenivI;
    private Listbox parteElabApro;
    private Listbox formatoElabApro;
    private Button accionNivI;
    private Button accionElaApro;
    private Row nivRow1;
    private Row nivRow2;
    private Row nivRow3;
    private Row nivRow4;
    private Row nivRow5;
    private Row nivRow6;
    private Label NivelEdit;
    private Textbox NivelEditT;
    private Button GuarNivI;
    private Window WNivelIngAsis;
    private Listbox rellenoCara;
    private Label nt;
    private Button accionDepa;
    private Textbox personaEntrada;
    private Textbox personaEntradaAbr;
    private Window WEquipoAsis;
    private Textbox equipoEntrada;
    private Textbox equipoDesEntrada;
    private Listcell elabApro;
    private Listbox elaboroElabApro;
    private Listbox elaboroEquipo;
    private Listbox aproboElabApro;
    private Datebox db;
    private Listbox parteCambioPa;
    private Button accionCambioPa;
    private Label nopart;
    private Textbox nupart;
    private Window WCambioParteAsis;
    private Button reuNivI;
    private Listbox nivIngDisp;
    private Window WEquipoElaAsis;
    private Button accionEquipo;
    private Listbox parteElaEquipo;
    private Listbox pesonasEquipo;
    private Window WMaterialAsis;
    private Grid proMecaMa;
    private Grid compoQuimMa;
    //Especificaciones de Standar para alta de material
    private Textbox laminaEntrada;
    private Textbox nombreMateEntrada;
    private Textbox espesorMatEntrada;
    private Textbox anchoMatEntrada;
    //Especificaciones mecánicas del material
    private Textbox tensionMaEntrada;
    private Textbox limFluMaEntrada;
    private Textbox alarMatEntrada;
    //Especificaciones químicas del material
    private Textbox carbonoMaEntrada;
    private Textbox silicioMaEntrada;
    private Textbox mangaMatEntrada;
    private Textbox fosfoMatEntrada;
    private Textbox azuMatEntrada;
    private Button modeloPar;
    private Listbox ModeDisp;
    //INICIO DEL ASISTENTE
    private Textbox nombreParEntrada;
    private Textbox numeroParEntrada;
    private Label comodinParte;
    private Grid gridCara;
    private Label caracpart;
    //INICIO AL SISTEMA
    private Button recargarFas;
    private Window WCaracAsistente;
    private Listbox regresoInsp;
    private Listbox regresoTrans;
    private Button accionTipo;
    private Listbox procesoTipo;
    private Listbox regresoPrepara;
    private Listbox regresoBlanking;
    private Listbox regresoTandem;
    private Listbox regresoTransfer;
    private Button accionMaquina;
    private Label labelCas;
    private Listbox procesoMaquina;
    private Row ocult;
    private Window formatosAsis;
    private Image sts;
    private Image stx;
    private Menubar menubar;
    private Row inicio;
    private Image home;
    private Include resultado2;
    private Listbox procesosMaq;
    private Button accionProcesosMaq;
    private Listbox ingenieria;
    private Listbox asegCalid;
    private Listbox produccion;
    private Button accionRevisiones;
    private Grid revisiones;
    private Window RevisionesAsis;
    private Button mostrarRev;
    private Listbox revRealizadas;
    private Textbox revText;
    private Datebox fechaRev;
    private Textbox motivoRe;
    private java.util.List ref = new java.util.ArrayList();
    private Textbox amefEntrada;
    private Window materialAsis;
    private Button accionMaterial;
    private Grid compGridMat;
    private Label nombreParteR;
    private Label numeroParterR;
    private Label nivelInge;
    private Listbox matOpc;
    private Listbox selCliente;
    private Textbox anchoMat;
    private Textbox espesorMat;
    private Label proveedors;
    private Textbox pesoBlEnt;
    private Textbox pesoParteEnt;
    private Label calculoAproLab;
    private Label calculoAproL;
    private Button calculoAprov;
    private Textbox pasoAlEnt;
    private Textbox tolEnt;
    private West menuPrin;
    private Row asistente;
    private Window tfFormatos;
    private Button accionProcesosTF;
    private Listbox procesosTF500;
    private Listbox procesosTF1500;
    private Button confTF500;
    private Button confTF1500;
    private Row TdMod;
    private Image stmodelo;
    private Label pesoRolloUt;
    private Label calculoAproLabT;
    private Label calculoAproLT;
    private Label pesoRolloUtD;
    
    
    public void llenadoLista(Component comp) throws Exception {
        super.doAfterCompose(comp);


        sugerencia.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Amef r = (Amef) data;

                item.appendChild(new Listcell(r.getNumero()));
                item.appendChild(new Listcell(r.getNombre()));
                item.appendChild(new Listcell(r.getCodigo()));
                sugerencia.setVisible(true);

            }
        });

    }

    public void onSelect$procesosMaq() throws SQLException, InterruptedException {
        ConnSql x = new ConnSql();
        int idp = x.traerIdParte(comodin.getTitle());
        String proceso = procesosMaq.getSelectedItem().getLabel();
        int idPro = x.obtenerIdProceso(proceso);
        String dx = x.ObtenerMaqProceso(idPro);


        if ((dx.equals("Tandem 200 (Tipo A)"))) {

            tandem200A td = new tandem200A();
            td.prensaTandem(comodin, procesosMaq);

        } else {

            if (dx.equals("Tandem 200 (Tipo B)")) {
                tandem200B td = new tandem200B();
                td.prensaTandem(comodin, procesosMaq);

            } else {
                if (dx.equals("Tandem 800") || dx.equals("Tandem 400")) {
                    tandem400800 td = new tandem400800();
                    td.prensaTandem(comodin, procesosMaq);

                } else {
                    if ((dx.equals("Blanking 200"))) {

                        Blanking200 bl = new Blanking200();
                        bl.prensaBL200(comodin, procesosMaq);

                    } else {

                        if ((dx.equals("Blanking 400"))) {

                            Blanking400 bl = new Blanking400();
                            bl.prensaBL400(comodin, procesosMaq);

                        } else {
                            if ((dx.equals("Blanking 800"))) {

                                Blanking800 bl = new Blanking800();
                                bl.prensaBL800(comodin, procesosMaq);

                            } else {
                                alert(procesosMaq.getSelectedItem().getLabel());
                            }
                        }
                    }
                }
            }
        }

        procesosMaq.getSelectedItem().setDisabled(true);
    }

    public void onClick$confTF500() throws SQLException, InterruptedException {

        Transfer500 tf = new Transfer500();

        tf.prensaTF500(comodin, procesosTF500);

    }
    
    public void onClick$crearAmef(){
    resultado.setSrc("/opc/amef/crearAmef.zul");
    
    }
    
    public void onClick$confTF1500() throws SQLException, InterruptedException {

        TF1500 tf = new TF1500();

        tf.prensaTF1500(comodin, procesosTF1500);

    }

    public void onClick$accionProcesosTF() throws SQLException, Exception {
        accionProcesosTF.setLabel("Reiniciar");
        nivRow1.setVisible(true);
        nivRow2.setVisible(true);
        procesosTF1500.getChildren().clear();
        procesosTF500.getChildren().clear();

        ConnSql conectar = new ConnSql();
        String par = comodin.getTitle();
        int id = conectar.traerIdParte(par);
        regresoProcMaqTF1500 = conectar.obtenerfasesPrensasTF1500(id);
        regresoProcMaqTF500 = conectar.obtenerfasesPrensasTF500(id);
        ListModelArray TF1500 = new ListModelArray(regresoProcMaqTF1500);
        procesosTF1500.setModel(TF1500);
        ListModelArray TF500 = new ListModelArray(regresoProcMaqTF500);
        procesosTF500.setModel(TF500);
        procesosTF1500.setVisible(true);
        procesosTF1500.setAutopaging(true);
        procesosTF500.setVisible(true);
        procesosTF500.setAutopaging(true);
        llenadoFaseMaq500(self);
        llenadoFaseMaq1500(self);

        if (regresoProcMaqTF500.isEmpty()) {
        } else {
            confTF500.setVisible(true);
        }
        if (regresoProcMaqTF1500.isEmpty()) {
        } else {
            confTF1500.setVisible(true);
        }
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

        sugerencia.setModel(list);
    }

    public void llenadoTest(Component comp) throws Exception {
        super.doAfterCompose(comp);

        String aux1 = revText.getValue();


        java.util.Date aux2 = fechaRev.getValue();

        String aux3 = motivoRe.getValue();
        String ing = "  ";
        String asCal = "  ";
        String producc = "  ";

        String numPa = comodin.getTitle();
        ConnSql conectar = new ConnSql();



        if (aux1.isEmpty()) {
        } else {
            if (aux2 == null) {
            } else {
                if (aux3.isEmpty()) {
                } else {

                    boolean b = conectar.duplicidadRevision(numPa, aux1);

                    if (b == true) {
                        ref.add(new Revisiones(aux1, aux3, aux2, ing, asCal, producc));
                        ListModelArray list = new ListModelArray(ref);

                        conectar.altaRevisionF1(aux1, aux2, aux3, ing, asCal, producc, numPa);
                        revRealizadas.setModel(list);
                    } else {
                        alert("La revisión " + aux1 + " ya existe para el número de parte " + comodin.getTitle());
                    }
                }

            }
        }


    }

    public void llenadoTest2(Component comp) throws Exception {
        super.doAfterCompose(comp);


        String aux1 = revText.getValue();
        java.util.Date aux2 = fechaRev.getValue();
        String aux3 = motivoRe.getValue();
        String ing = ingenieria.getSelectedItem().getLabel();
        String asCal = asegCalid.getSelectedItem().getLabel();
        String producc = produccion.getSelectedItem().getLabel();

        String numPa = comodin.getTitle();

        ConnSql conectar = new ConnSql();






        if (aux1.isEmpty()) {
        } else {
            if (aux2 == null) {
            } else {
                if (aux3.isEmpty()) {
                } else {
                    if (ing.isEmpty()) {
                    } else {
                        if (asCal.isEmpty()) {
                        } else {
                            if (producc.isEmpty()) {
                            } else {
                                boolean b = conectar.duplicidadRevision(numPa, aux1);




                                if (b == true) {
                                    ref.add(new Revisiones(aux1, aux3, aux2, ing, asCal, producc));
                                    ListModelArray list = new ListModelArray(ref);

                                    conectar.altaRevisionF2(aux1, aux2, aux3, ing, asCal, producc, numPa);
                                    revRealizadas.setModel(list);
                                } else {
                                    alert("Revisión ya Existe para el número de parte " + comodin.getTitle());
                                }
                            }

                        }
                    }
                }
            }
        }








    }

    public void llenadoTestLista2(Component comp) throws Exception {
        super.doAfterCompose(comp);

        revRealizadas.setVisible(true);
        revRealizadas.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Revisiones r = (Revisiones) data;
                SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
                String d = formato.format(r.getFecha());
                item.appendChild(new Listcell(r.getRev()));
                item.appendChild(new Listcell(d));
                item.appendChild(new Listcell(r.getMotivo()));
                item.appendChild(new Listcell(r.getIng()));
                item.appendChild(new Listcell(r.getCal()));
                item.appendChild(new Listcell(r.getProd()));

            }
        });

    }

    public void llenadoTestLista(Component comp) throws Exception {
        super.doAfterCompose(comp);

        revRealizadas.setVisible(true);
        revRealizadas.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }


                Revisiones r = (Revisiones) data;
                SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
                String d = formato.format(r.getFecha());
                item.appendChild(new Listcell(r.getRev()));
                item.appendChild(new Listcell(d));
                item.appendChild(new Listcell(r.getMotivo()));
                item.appendChild(new Listcell(r.getIng()));
                item.appendChild(new Listcell(r.getCal()));
                item.appendChild(new Listcell(r.getProd()));

            }
        });

    }

    public void onClick$accionRevisiones() {
        revisiones.setVisible(true);
    }

    public void onClick$accionMaterial() throws SQLException, Exception {
        ConnSql conectar = new ConnSql();
        String nParte = comodin.getTitle();
        String nomParte = conectar.traerNombreParte(nParte);
        String nivelIngen = conectar.obtenerNivIng(nParte);
        numeroParterR.setValue(nParte);
        nombreParteR.setValue(nomParte);
        nivelInge.setValue(nivelIngen);

        matOpc.getItems().clear();
        selCliente.getItems().clear();

        regresoClientes = conectar.obtenerClientes();
        regresoMateriales = conectar.obtenerMateriales();

        ListModelArray list = new ListModelArray(regresoClientes);
        ListModelArray list2 = new ListModelArray(regresoMateriales);


        selCliente.setModel(list);
        matOpc.setModel(list2);

        selCliente(self);
        matOpc(self);

        accionMaterial.setVisible(false);
        compGridMat.setVisible(true);

    }

    public void onClick$mostrarRev() throws SQLException, Exception {

        ConnSql conectar = new ConnSql();

        if (nivRow1.isVisible()) {
            nivRow1.setVisible(false);
            nivRow2.setVisible(false);
            nivRow3.setVisible(false);
            nivRow4.setVisible(false);
            nivRow5.setVisible(false);
            nivRow6.setVisible(false);
            mostrarRev.setLabel("Revisiones");
        } else {

            ingenieria.getItems().clear();
            asegCalid.getItems().clear();
            produccion.getItems().clear();
            regresof = conectar.obtenerPersonas();

            ListModelArray list = new ListModelArray(regresof);
            ingenieria.setModel(list);
            asegCalid.setModel(list);
            produccion.setModel(list);

            ingenieriaApro(self);
            asegCalApro(self);
            produccionApro(self);

            nivRow1.setVisible(true);
            nivRow2.setVisible(true);
            nivRow3.setVisible(true);
            nivRow4.setVisible(true);
            nivRow5.setVisible(true);
            nivRow6.setVisible(true);
            mostrarRev.setLabel("Ocultar");
        }
    }

    public void onClick$altaDeRevision() throws Exception {

        try {
            if (nivRow1.isVisible()) {

                llenadoTest2(self);
                llenadoTestLista2(self);

            } else {

                llenadoTest(self);
                llenadoTestLista(self);
            }
        } catch (Exception ex) {

            Messagebox.show("Inserta todos los datos", "Error", Messagebox.OK, Messagebox.ERROR);

        }

    }

    public void onClick$accionProcesosMaq() throws SQLException, Exception {
        procesosMaq.getChildren().clear();

        ConnSql conectar = new ConnSql();
        String par = comodin.getTitle();
        int id = conectar.traerIdParte(par);
        regresoProcMaq = conectar.obtenerfasesPrensas(id);
        ListModelArray list = new ListModelArray(regresoProcMaq);
        procesosMaq.setModel(list);
        procesosMaq.setVisible(true);
        procesosMaq.setAutopaging(true);
        accionProcesosMaq.setLabel("Reiniciar");
        llenadoFaseMaq(self);
    }

    public void onOK$password() throws SQLException, InterruptedException {
        onClick$btn();
    }

    public void onClick$accionCara() throws SQLException {
        partecarac();
        partecarac.setVisible(true);
        accionCara.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onClick$accionTipo() throws SQLException {
        procesoTipo();
        procesoTipo.setVisible(true);
        accionTipo.setVisible(false);
        labelCa.setVisible(true);


    }

    public void onSelect$procesoTipo() throws SQLException {

        if ("Tandem".equals(procesoTipo.getSelectedItem().getLabel())) {
            ConnSql conexion = new ConnSql();
            List partes = conexion.obtenerTipoMaquinaTd();


            procesoMaquina.setItemRenderer(new ListitemRenderer() {

                public void render(Listitem item, Object data) throws Exception {
                    if (data == null) {
                        return;
                    }
                    NumeroParte r = (NumeroParte) data;

                    item.appendChild(new Listcell(r.getNumeroParte()));



                }
            });
            ListModelArray list = new ListModelArray(partes);
            procesoMaquina.setModel(list);
            procesoMaquina.setVisible(true);
            accionMaquina.setVisible(false);
            labelCas.setVisible(true);

            ocult.setVisible(true);

        } else {
            if ("Transfer".equals(procesoTipo.getSelectedItem().getLabel())) {
                ConnSql conexion = new ConnSql();
                List partes = conexion.obtenerTipoMaquinaTf();


                procesoMaquina.setItemRenderer(new ListitemRenderer() {

                    public void render(Listitem item, Object data) throws Exception {
                        if (data == null) {
                            return;
                        }
                        NumeroParte r = (NumeroParte) data;

                        item.appendChild(new Listcell(r.getNumeroParte()));



                    }
                });
                ListModelArray list = new ListModelArray(partes);
                procesoMaquina.setModel(list);
                procesoMaquina.setVisible(true);
                accionMaquina.setVisible(false);
                labelCas.setVisible(true);

                ocult.setVisible(true);
            } else {
                if ("Blanking".equals(procesoTipo.getSelectedItem().getLabel())) {

                    ConnSql conexion = new ConnSql();
                    List partes = conexion.obtenerTipoMaquinaBl();


                    procesoMaquina.setItemRenderer(new ListitemRenderer() {

                        public void render(Listitem item, Object data) throws Exception {
                            if (data == null) {
                                return;
                            }
                            NumeroParte r = (NumeroParte) data;

                            item.appendChild(new Listcell(r.getNumeroParte()));



                        }
                    });
                    ListModelArray list = new ListModelArray(partes);
                    procesoMaquina.setModel(list);
                    procesoMaquina.setVisible(true);
                    accionMaquina.setVisible(false);
                    labelCas.setVisible(true);

                    ocult.setVisible(true);

                } else {
                    procesoMaquina.setVisible(false);
                    ocult.setVisible(false);
                }

            }



        }


    }

    public void onClick$accionMaquina() throws SQLException {
        procesoMaquina();
        procesoMaquina.setVisible(true);
        accionMaquina.setVisible(false);
        labelCas.setVisible(true);


    }

    public void onClick$accionEquipo() throws SQLException {
        parteElaEquipo();
        parteElaEquipo.setVisible(true);
        accionEquipo.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onClick$reuNivI() throws SQLException {
        reuNivI.setVisible(false);
        nivIngDisp.setVisible(true);
        nivIngDisp();
        nivIngDisp.setWidth("250px");
    }

    public void onClick$modeloPar() throws SQLException {
        modeloPar.setVisible(false);
        ModeDisp.setVisible(true);
        ModeDisp();
        ModeDisp.setWidth("250px");
    }

    public void onClick$accionDepa() throws SQLException {
        partedepa();
        partedepa.setVisible(true);
        accionDepa.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onClick$accionNivI() throws SQLException {
        partenivI();
        partenivI.setVisible(true);
        accionNivI.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onClick$accionElaApro() throws SQLException {

        parteElabApro();
        parteElabApro.setVisible(true);
        accionElaApro.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onClick$accionCambioPa() throws SQLException {
        parteCambioPa();
        parteCambioPa.setVisible(true);
        accionCambioPa.setVisible(false);
        labelCa.setVisible(true);
    }

    public void onMouseOver$accionCara() {
        labelCa.setVisible(true);
    }

    public void onMouseOver$TdMod() {
        stmodelo.setSrc("/img/3d.png");

    }

    public void onMouseOut$TdMod() {
        stmodelo.setSrc("/img/log3dd.png");
    }

    public void onMouseOver$home() {
        home.setSrc("/img/home2.png");
    }

    public void onMouseOut$home() {
        home.setSrc("/img/home.png");
    }

    public void onMouseOver$accionEquipo() {
        labelCa.setVisible(true);
    }

    public void onMouseOut$accionEquipo() {
        if (accionEquipo.isVisible()) {
            labelCa.setVisible(true);
        }
    }

    public void onMouseOut$accionCara() {
        if (accionCara.isVisible()) {
            labelCa.setVisible(false);
        }
    }

    public void onMouseOver$accionTipo() {
        labelCa.setVisible(true);
    }

    public void onMouseOut$accionTipo() {
        if (accionTipo.isVisible()) {
            labelCa.setVisible(false);
        }
    }

    public void onMouseOver$accionMaquina() {
        labelCas.setVisible(true);
    }

    public void onMouseOut$accionMaquina() {
        if (accionMaquina.isVisible()) {
            labelCas.setVisible(false);
        }
    }

    public void onMouseOver$accionCambioPa() {
        labelCa.setVisible(true);
    }

    public void onMouseOut$accionCambioPa() {
        if (accionCambioPa.isVisible()) {
            labelCa.setVisible(false);
        }
    }

    public void onMouseOver$accionDepa() {
        labelCa.setVisible(true);
    }

    public void onMouseOut$accionDepa() {
        if (accionDepa.isVisible()) {
            labelCa.setVisible(false);
        }
    }

    public void onMouseOver$accionNivI() {
        labelCa.setVisible(true);
    }

    public void onMouseOver$accionElaApro() {
        labelCa.setVisible(true);

    }

    public void onMouseOut$accionElaApro() {
        labelCa.setVisible(true);

    }

    public void onMouseOut$accionNivI() {
        if (accionNivI.isVisible()) {
            labelCa.setVisible(false);
        }
    }

    public void onSelect$partenivI() throws SQLException {
        nivRow1.setVisible(true);
        nivRow2.setVisible(true);
        String seleccion = partenivI.getSelectedItem().getLabel();
        ConnSql conectar = new ConnSql();
        String nivIng = conectar.obtenerNivIng(seleccion);
        NivelEdit.setValue(nivIng);


    }

    public void onSelect$parteElaEquipo() throws SQLException, Exception {
        nivRow1.setVisible(true);
        nivRow2.setVisible(true);

        String seleccion = parteElaEquipo.getSelectedItem().getLabel();
        ConnSql conectar = new ConnSql();

        regresof = conectar.obtenerEquipos();

        ListModelArray list = new ListModelArray(regresof);
        elaboroEquipo.setModel(list);


        AproboElaboroEquipo(self);

    }

    public void onSelect$elaboroEquipo() throws SQLException, Exception {
        nivRow1.setVisible(true);
        nivRow2.setVisible(true);
        nivRow3.setVisible(true);
        String seleccion = elaboroEquipo.getSelectedItem().getLabel();
        ConnSql conectar = new ConnSql();

        regresof = conectar.obtenerPersonasEquipo(seleccion);

        ListModelArray list = new ListModelArray(regresof);
        pesonasEquipo.setModel(list);

        EquipoElabora(self);


    }

    public void onSelect$parteElabApro() throws SQLException {

        nivRow1.setVisible(true);

        String seleccion = parteElabApro.getSelectedItem().getLabel();
        formatoElabApro(seleccion);


    }

    public void onSelect$parteCambioPa() throws SQLException {
        nivRow1.setVisible(true);
        nivRow2.setVisible(true);

        String num = parteCambioPa.getSelectedItem().getLabel();
        nupart.setValue(num);
        ConnSql conectar = new ConnSql();
        String nomPar = conectar.traerNombreParte(num);
        nopart.setValue(nomPar);


    }

    public void onSelect$formatoElabApro() throws SQLException, Exception {

        nivRow2.setVisible(true);
        nivRow3.setVisible(true);
        nivRow4.setVisible(true);
        String seleccion = formatoElabApro.getSelectedItem().getLabel();
        ConnSql conectar = new ConnSql();
        regresof = conectar.obtenerPersonas();

        ListModelArray list = new ListModelArray(regresof);
        elaboroElabApro.setModel(list);
        aproboElabApro.setModel(list);

        elaboroElabApro(self);
        aproboElabApro(self);

    }

    public void onClick$ctemas() {
        temas.setVisible(true);
        ctemas.setVisible(false);


    }

    public void onClick$te1() {

        Themes.setTheme(Executions.getCurrent(), "breeze");
        Executions.sendRedirect(null);


    }

    public void onClick$te2() {
        Themes.setTheme(Executions.getCurrent(), "sapphire");
        Executions.sendRedirect(null);
    }

    public void onClick$te3() {
        Themes.setTheme(Executions.getCurrent(), "silvertail");
        Executions.sendRedirect(null);
    }

    public void onClick$te4() {
        Themes.setTheme(Executions.getCurrent(), "classicblue");
        Executions.sendRedirect(null);
    }

    public void onClick$nuevoNivI() {
        nivRow3.setVisible(true);
        NivelEditT.setValue(NivelEdit.getValue());

    }

    public void onClick$btn() throws SQLException, InterruptedException {
        boolean resp;


        String name = nombre.getValue();
        String pass = password.getValue();

        conexion.ConnSql conectar = new conexion.ConnSql();

        resp = conectar.inicioLog(name, pass);

        if (resp == true) {

            nt.setValue("Alejandro Montes Rivera, proyecto de Estadía");
            log.setVisible(false);
            btn.setVisible(false);
            principal.setTitle("Bienvenido " + name);
            comodinNombre.setValue(name);
            principal.setWidth("1000px");
            principal.setHeight("2000px");
            menubar.setVisible(true);
            principal.setMaximizable(true);
        } else {

            Messagebox.show("Usuario y/o contraseña incorrectos", "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    // RESPUESTA DE BORDERLAYOUT
    public void onClick$listaConsulta() {

        boxConsulta.setVisible(true);
        boxReportes.setVisible(false);
        boxFormatos.setVisible(false);
    }

    public void onClick$listaReportes() {
        boxReportes.setVisible(true);
        boxConsulta.setVisible(false);
        boxFormatos.setVisible(false);

    }

    public void onClick$listaFormatos() {
        boxReportes.setVisible(false);
        boxConsulta.setVisible(false);
        boxFormatos.setVisible(true);
    }

    // REDIRECCION DE PANTALLA PRINCIPAL DE TRABAJO
    public void onClick$asistente() throws SQLException, InterruptedException {
        int tipo = 0;
        conexion.ConnSql tipoUsuari = new ConnSql();
        String us = comodinNombre.getValue();
        tipo = tipoUsuari.traerTipo(us);
        if (tipo == 1) {
            resultado.setSrc("/reset.zul");
            resultado.setSrc("/opc/asistente.zul");
        } else {
            if (contadorR < 1) {
                Messagebox.show("No tiene privilegios suficientes para obtener acceso a la Administración", "Information", Messagebox.OK, Messagebox.INFORMATION);
                contadorR++;
            }
        }

    }

    public void onClick$diagrama() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/diagrama.zul");
    }

    public void inicio() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/inicio/entrada.zul");
    }

    public void onClick$amef() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/amef.zul");

    }

    public void onClick$condiciones() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/hojaCondiciones.zul");
    }

    public void onClick$control() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/planControl.zul");
    }

    public void onClick$procesos() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/hojaProcesos.zul");
    }

    public void onClick$historial() {
        resultado.setSrc("/reset.zul");
        resultado.setSrc("/opc/historial.zul");

    }

    public void onClick$cerrarSe() {

        Executions.sendRedirect("/");
    }

    public void onClick$manySe() {

        resultado2.setSrc("/test2.zul");
    }

    public void onClick$manySes() {

        resultado2.setSrc("/video.zul");
    }

    public void onMouseOver$CP() throws InterruptedException {
        CP2.setValue("Consultar");
    }

    public void onMouseOver$manualImage() throws InterruptedException {
        manualImage.setSrc("/img/manual2.png");
    }

    public void onMouseOut$manualImage() throws InterruptedException {
        manualImage.setSrc("/img/manual.png");
    }

    public void onMouseOver$traerCara() throws InterruptedException {
        verc.setVisible(true);
    }

    public void onClick$inicio() {
        resultado.setSrc("/inicio/entrada.zul");
    }

    public void onClick$traerCara() throws InterruptedException {
        ConnSql conexion = new ConnSql();
        List cara = conexion.obtenercaracteristicas(partecarac.getSelectedItem().getLabel());

        final Window win = (Window) Executions.createComponents(
                "/opc/amef/consultaParte.zul", null, null);

        amefPart.getChildren().clear();
        win.getChildren().clear();

        amefPart.setTitle("CARACTERISTICAS DE PARTE " + partecarac.getSelectedItem().getLabel());

        win.appendChild(amefPart);

        Image img = new Image("/img/buscar.png");
        amefPart.appendChild(img);
        Label lb = new Label("Detalles");
        amefPart.appendChild(lb);


        amefPart.setHeight("400px");
        amefPart.setWidth("700px");
        win.setBorder("10px");

        //Construcción de LISTBOX

        fase = new Listbox();
        Listhead tit = new Listhead();

        Listheader col2 = new Listheader("Descripción");
        Listheader col = new Listheader("Número");
        Listheader col3 = new Listheader("Observaciones");
        Listheader col5 = new Listheader("Grado");
        Listheader col4 = new Listheader("Tipo");

        col2.setWidth("300px");
        col.setWidth("100px");
        tit.appendChild(col2);
        tit.appendChild(col);
        tit.appendChild(col3);
        tit.appendChild(col4);
        tit.appendChild(col5);


        fase.appendChild(tit);

        amefPart.appendChild(fase);

        amefPart.setSizable(true);
        amefPart.setClosable(true);
        amefPart.setMaximizable(true);
        amefPart.setContentStyle("overflow:auto");
        amefPart.doModal();


        fase.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Caracteristicas r = (Caracteristicas) data;

                item.appendChild(new Listcell(r.getDescripcion()));
                item.appendChild(new Listcell(String.valueOf(r.getNumero())));
                item.appendChild(new Listcell(r.getObservaciones()));
                item.appendChild(new Listcell(r.getGrado()));
                item.appendChild(new Listcell(r.getTipo()));



            }
        });
        ListModelArray list = new ListModelArray(cara);
        fase.setModel(list);

    }

    public void onMouseOut$traerCara() throws InterruptedException {
        verc.setVisible(false);
    }

    public void onMouseOut$CP() throws InterruptedException {
        CP2.setValue(" ");
    }

    public void onClick$CP() {
        consultap.setValue("CONSULTAR PARTE");
    }

    //FUNCIONAMIENTO BUSQUEDA PARTE (SUGERENCIA)
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

                    } else {
                    }
                    n++;
                }
                
                    coincidencias = conectar.traerConcidencias(coinc);
//                    grid.setVisible(true);
                
                llenado(self);
                llenadoLista(self);

                sugerencia.addEventListener(Events.ON_SELECT, new org.zkoss.zk.ui.event.EventListener() {

                    public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {


                        final Window win = (Window) Executions.createComponents(
                                "/opc/amef/consultaParte.zul", null, null);

                        amefPart.getChildren().clear();
                        win.getChildren().clear();

                        Listitem aux2 = sugerencia.getSelectedItem();
                        String a = aux2.getLabel();

                        conexion.ConnSql conectar = new ConnSql();

                        List fases = conectar.procesosAmef(a);


                        amefPart.setTitle("AMEF DE PARTE " + a);

                        win.appendChild(amefPart);

                        Image img = new Image("/img/buscar.png");
                        amefPart.appendChild(img);
                        Label lb = new Label("Detalles");
                        amefPart.appendChild(lb);


                        amefPart.setHeight("400px");
                        amefPart.setWidth("700px");
                        win.setBorder("10px");

                        //Construcción de LISTBOX

                        fase = new Listbox();
                        Listhead tit = new Listhead();

                        Listheader col2 = new Listheader("Nombre de Procesos");

                        Listheader col = new Listheader("Número de Proceso");
                        col.setWidth("150px");
                        tit.appendChild(col);
                        tit.appendChild(col2);

                        fase.appendChild(tit);

                        amefPart.appendChild(fase);

                        amefPart.setSizable(true);
                        amefPart.setClosable(true);
                        amefPart.setMaximizable(true);
                        amefPart.setContentStyle("overflow:auto");
                        amefPart.doModal();


                        fase.setItemRenderer(new ListitemRenderer() {

                            public void render(Listitem item, Object data) throws Exception {
                                if (data == null) {
                                    return;
                                }
                                FasesReturn r = (FasesReturn) data;

                                item.appendChild(new Listcell(r.getNumeroFase()));
                                item.appendChild(new Listcell(r.getNombreFase()));


                            }
                        });

                        ListModelArray list = new ListModelArray(fases);
                        fase.setModel(list);


                        fase.addEventListener(Events.ON_SELECT, new org.zkoss.zk.ui.event.EventListener() {

                            public void onEvent(Event event) throws Exception {
                                amefPartSelect();
//                                opcionParteProceso();
                            }
                        });

                        amefPart.addEventListener(Events.ON_CLOSE, new org.zkoss.zk.ui.event.EventListener() {

                            public void onEvent(Event event) throws Exception {

                                sugerencia.setSelectedItem(null);

                            }
                        });


                    }
                });


            } catch (Exception ex) {
                Messagebox.show("Error Interno (1), Consulte al programador", "Information", Messagebox.OK, Messagebox.INFORMATION);

            }
        }

    }

    public void amefPartSelect() throws InterruptedException, SQLException {


        final Window win = (Window) Executions.createComponents(
                "/opc/amef/fallasParte.zul", null, null);
        win.getChildren().clear();
        fallaProceso.getChildren().clear();
        fallaProceso.getAttributes().clear();
        fallaProceso.setTitle("Fallas de la Parte ");

        Listitem aux0 = sugerencia.getSelectedItem();
        Listitem aux2 = fase.getSelectedItem();
        String b = aux0.getLabel();
        String a = aux2.getLabel();
        conexion.ConnSql conectar = new ConnSql();

        List fallasComp = conectar.fallaParte(a, b);

        fallaProceso.setTitle("FALLAs DEL PROCESO " + a + " DE LA PARTE " + b);
        Image img = new Image("/img/buscar.png");
        fallaProceso.appendChild(img);
        Label lb = new Label("Detalles");

        img.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(Event event) throws Exception {

                sugerencia.setSelectedItem(null);

            }
        });

        fallaProceso.appendChild(lb);

        //Construcción de LISTBOX

        faseFalla = new Listbox();
        Listhead tit = new Listhead();

        Listheader col = new Listheader("Modo");
        Listheader col2 = new Listheader("Efectos");
        Listheader col3 = new Listheader("Falla");
        Listheader col4 = new Listheader("Prevención");
        Listheader col5 = new Listheader("Detección");
        Listheader col6 = new Listheader("Acción");
        Listheader col7 = new Listheader("Grado");
        col7.setWidth("50px");
        Listheader col8 = new Listheader("Sev");
        col8.setWidth("50px");
        Listheader col9 = new Listheader("Ocu");
        col9.setWidth("50px");
        Listheader col10 = new Listheader("Det");
        col10.setWidth("50px");

        tit.appendChild(col);
        tit.appendChild(col2);
        tit.appendChild(col3);
        tit.appendChild(col4);
        tit.appendChild(col5);
        tit.appendChild(col6);
        tit.appendChild(col7);
        tit.appendChild(col8);
        tit.appendChild(col9);
        tit.appendChild(col10);

        faseFalla.appendChild(tit);
        faseFalla.setHeight("1500px");

        fallaProceso.addEventListener(Events.ON_CLOSE, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(Event event) throws Exception {

                fase.setSelectedItem(null);
//                opcProc.onClose();

            }
        });

        fallaProceso.appendChild(faseFalla);





        faseFalla.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                FallasReturn r = (FallasReturn) data;

                item.appendChild(new Listcell(r.getModo()));
                item.appendChild(new Listcell(r.getEfecto()));
                item.appendChild(new Listcell(r.getFalla()));
                item.appendChild(new Listcell(r.getPrevencion()));
                item.appendChild(new Listcell(r.getDeteccion()));
                item.appendChild(new Listcell(r.getAccion()));
                item.appendChild(new Listcell(r.getGrado()));
                item.appendChild(new Listcell(String.valueOf(r.getSev())));
                item.appendChild(new Listcell(String.valueOf(r.getOcu())));
                item.appendChild(new Listcell(String.valueOf(r.getDet())));


            }
        });

        ListModelArray list = new ListModelArray(fallasComp);
        faseFalla.setModel(list);
        win.appendChild(fallaProceso);
        fallaProceso.setSizable(true);
        fallaProceso.setClosable(true);
        fallaProceso.setMaximizable(true);
        fallaProceso.setMaximized(true);
        fallaProceso.setContentStyle("overflow:auto");
        fallaProceso.doPopup();
    }

    public void onMouseOver$historialimg() {

        historialimg.setSrc("/img/historial3.png");
        notes.setValue("VER HISTORIAL");
    }

    public void onMouseOver$sts() {
        sts.setSrc("/img/apqp3.png");
    }

    public void onMouseOut$sts() {
        sts.setSrc("/img/apqp2.png");
    }

    public void onMouseOver$stx() {
        stx.setSrc("/img/txt2.png");
    }

    public void onMouseOut$stx() {
        stx.setSrc("/img/txt.jpg");
    }

    public void onMouseOut$historialimg() {

        historialimg.setSrc("/img/historial2.png");
        notes.setValue("HISTORIAL");
    }

    public void onClick$historialimg() throws SQLException, Exception {
        capt.setVisible(false);
        feo.setVisible(false);
        historialbox.setVisible(true);
        ConnSql conectar = new ConnSql();

        histoarray = conectar.buscarHistorial();

        historialReg(self);
        HistoLista(self);
    }

    public void historialReg(Component comp) throws Exception {
        super.doAfterCompose(comp);



        java.util.List res = new java.util.ArrayList();

        for (int j = 0; histoarray.getUsuario().size() > j; j++) {

            String aux1 = histoarray.getUsuario().get(j);
            String aux2 = histoarray.getRazon().get(j);
            String aux3 = histoarray.getFecha().get(j);
            String aux4 = histoarray.getPieza().get(j);

            res.add(new Historial(aux1, aux2, aux3, aux4));
        }

        ListModelArray list = new ListModelArray(res);

//        grid.setModel(list);
        historialbox.setModel(list);
    }

    public void HistoLista(Component comp) throws Exception {
        super.doAfterCompose(comp);

        historialbox.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Historial r = (Historial) data;

                item.appendChild(new Listcell(r.getUsuario()));
                item.appendChild(new Listcell(r.getPieza()));
                item.appendChild(new Listcell(r.getRazon()));
                item.appendChild(new Listcell(r.getFecha()));

            }
        });

    }
    public void onClick$crearParte() {
        parteCrear.setVisible(true);

        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        parteCrear.setSrc("/opc/asistente/AltaParte/numeroParteAlta.zul");
        menuPrin.setVisible(false);
    }
    public void onClick$altaMat() {
        parteCrear.setVisible(true);
        materiales.setVisible(false);
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        parteCrear.setSrc("/opc/asistente/Materiales/AltaMateriales.zul");
    }
    public void onClick$cambioNP() {
        parteCrear.setVisible(true);
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        editar.setVisible(false);
        parteCrear.setSrc("/opc/asistente/Parte/cambiarNumParte.zul");
    }
    public void onClick$editIng() {
        parteCrear.setVisible(true);
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        editar.setVisible(false);
        parteCrear.setSrc("/opc/asistente/NivIngenieria/CambiarNivIng.zul");
    }
    public void onClick$crearDepartamento() {
        parteCrear.setVisible(true);
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        parteCrear.setSrc("/opc/asistente/Departamento/AltaDepartamento.zul");
    }
    public void onClick$crearModelo() {
        parteCrear.setVisible(true);
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        parteCrear.setSrc("/opc/asistente/Modelo/AltaModelo.zul");
    }
    public void onMouseOver$sigimg() {
        sigimg.setSrc("/img/siguiente2.png");
    }
    public void onMouseOut$sigimg() {
        sigimg.setSrc("/img/siguiente1.png");
    }
    public void onMouseOver$backimg() {
        backimg.setSrc("/img/atras2.png");
    }
    public void onMouseOut$backimg() {
        backimg.setSrc("/img/atras1.png");
    }

    public void onClick$backimg() throws SQLException {
        if (WEquipoAsis == null) {
        } else {
            if (WEquipoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }

        if (WProcesoAsis == null) {
        } else {
            if (WProcesoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WPersonaAsis == null) {
        } else {
            if (WPersonaAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WCaracteristicaAsis == null) {
        } else {
            if (WCaracteristicaAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WUsuarioAsis == null) {
        } else {
            if (WUsuarioAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WClienteAsis == null) {
        } else {
            if (WClienteAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WGradoAsis == null) {
        } else {
            if (WGradoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WDepartamentoAsis == null) {
        } else {
            if (WDepartamentoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WPrevencionAsis == null) {
        } else {
            if (WPrevencionAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WDeteccionAsis == null) {
        } else {
            if (WDeteccionAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WCausaAsis == null) {
        } else {
            if (WCausaAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WModoAsis == null) {
        } else {
            if (WModoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WEfectoAsis == null) {
        } else {
            if (WEfectoAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }

        if (menuTroqueles == null) {
        } else {
            if (menuTroqueles.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WModeloAsis == null) {
        } else {
            if (WModeloAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WMaterialAsis == null) {
        } else {
            if (WMaterialAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WTroquelAsis == null) {
        } else {
            if (WTroquelAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }

        if (WCambioParteAsis == null) {
        } else {
            if (WCambioParteAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WEquipoElaAsis == null) {
        } else {
            if (WEquipoElaAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WElabAproIngAsis == null) {
        } else {
            if (WElabAproIngAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }
        if (WParteAsis == null) {
        } else {
            ConnSql x = new ConnSql();
            if (WParteAsis.isVisible()) {
                menuPrin.setVisible(true);
                inicio();
            }

            if (fasesAsis.isVisible()) {
                int idP = x.traerIdParte(comodin.getTitle());
                x.eliminarAmef(idP);
                
                x.eliminarParte(comodin.getTitle());
                fasesAsis.setVisible(false);
                WParteAsis.setVisible(true);
            }

            if (WCaracAsistente.isVisible()) {
                WCaracAsistente.setVisible(false);
                int idp = x.traerIdParte(comodin.getTitle());
                x.eliminarTodosTroquelesParte(idp);
                x.eliminarFases(idp);
                int idm = x.traerIdMatriz(idp);
                x.eliminarCaracteristicasProcesos(String.valueOf(idm));
                x.eliminarCaracteristicas(String.valueOf(idm));
                x.eliminarMatrizCaracteristicas(String.valueOf(idm));
                fasesAsis.setVisible(true);
            }

            if (RevisionesAsis.isVisible()) {
                ref.clear();
                revRealizadas.getChildren().clear();
                revRealizadas.setVisible(false);
                RevisionesAsis.setVisible(false);
                WCaracAsistente.setVisible(true);
                x.eliminarRevisiones(comodin.getTitle());
            }

            if (materialAsis.isVisible()) {
                int idp = x.traerIdParte(comodin.getTitle());
                x.eliminarClientesParte(idp);
                int idEspLam = x.traerIdEspLam(idp);
                x.eliminarMaterialEsp(idEspLam);
                x.eliminarEspLam(idp);
                RevisionesAsis.setVisible(true);
                materialAsis.setVisible(false);
            }
            if (formatosAsis.isVisible()) {
                try {
                    int idp = x.traerIdParte(comodin.getTitle());
                    int idHojaC = x.traerIdHojaCond(idp);
                    
                    x.eliminarSecuencia(idp);
                    x.eliminarTodasPrensas(idp);
                    x.eliminarTodoTF1500(idp);
                    x.eliminarTF500(idHojaC);
                    x.eliminarTF1500(idHojaC);
                    x.eliminarHojaCond(idp);
                    x.eliminarClientesParte(idp);
                    int idEspLam = x.traerIdEspLam(idp);
                    x.eliminarMaterialEsp(idEspLam);
                    x.eliminarEspLam(idp);
                } catch (Exception ex) {
                }
                materialAsis.setVisible(true);
                formatosAsis.setVisible(false);
            }

            if (tfFormatos.isVisible()) {

                tfFormatos.setVisible(false);
                formatosAsis.setVisible(true);

            }
        }
        if (WNivelIngAsis == null) {
        } else {
            if (WNivelIngAsis.isVisible()) {
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            }
        }

    }

    public void onClick$WParteAsisButton() throws SQLException {
        WParteAsisButton.setVisible(false);

        WParteAsisDiv.setVisible(true);
        parteAsist = numeroEntrada.getValue();
        conexion.ConnSql conectar = new ConnSql();
        conectar.eliminarParte(parteAsist);
        nomEntradaLab.setValue("");
        numeroEntradaLab.setValue("");
        auxgrid.setVisible(false);



    }

    public void onClick$sigimg() throws SQLException, InterruptedException, Exception {



        if (WModeloAsis == null) {
        } else {
            String modelo;
            String descripcion;

            modelo = modeloEntrada.getValue();
            descripcion = descEntrada.getValue();
            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadModelo(modelo);
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaModelo", modelo, "Creación de Modelo", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);
            } else {
                Messagebox.show("El modelo ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }
        }

        if (WElabAproIngAsis == null) {
        } else {
            String numeroPart;
            String formato;
            String personaEla;
            String personaApro;


            numeroPart = parteElabApro.getSelectedItem().getLabel();
            formato = formatoElabApro.getSelectedItem().getLabel();
            personaEla = elaboroElabApro.getSelectedItem().getLabel();
            personaApro = aproboElabApro.getSelectedItem().getLabel();



            java.util.Date fecha = new java.util.Date();
            fecha.getDate();
            historial("AltaElaApro", numeroPart, "Establecimiento de nuevo Elaborador y Aprobador", fecha, "Registro de Elaborador , Aprobador", relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, numeroPart, formato, personaEla, personaApro, rellenoS5, rellenoCara);
            parteCrear.setVisible(false);
            asistbox.setVisible(true);

        }
        if (WEquipoElaAsis == null) {
        } else {
            String numeroPart;
            String formato;
            String personaEla;
            String personaApro;


            numeroPart = parteElaEquipo.getSelectedItem().getLabel();
            String equipoEla = elaboroEquipo.getSelectedItem().getLabel();

            java.util.Date fecha = new java.util.Date();
            fecha.getDate();
            historial("ParteEquipo", numeroPart, "Establecimiento de equipo para AMEF de la parte", fecha, "Registro de Equipo para la parte" + numeroPart, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, numeroPart, equipoEla, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
            parteCrear.setVisible(false);
            asistbox.setVisible(true);

        }
        if (WTroquelAsis == null) {
        } else {

            String troquel = troquelEntrada.getValue();
            int FR = Integer.valueOf(dFR.getValue());
            int LR = Integer.valueOf(dLR.getValue());
            int H = Integer.valueOf(dH.getValue());
            int Ps = Integer.valueOf(PeSup.getValue());
            int Pt = Integer.valueOf(PeTot.getValue());
            int PGol = Integer.valueOf(PGolpe.getValue());
            String desc = descTEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadTroquel(troquel);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaTroquel", troquel, "Creación de Troquel", fecha, desc, FR, LR, H, Ps, Pt, PGol, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);


            } else {
                Messagebox.show("El troquel ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }


        }

        if (WModoAsis == null) {
        } else {

            String modo = modoEntrada.getValue();
            String descripcion = modoOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadModo(modo);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaModo", modo, "Creación de Modo - Falla", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El modo ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }

        }

        if (WEfectoAsis == null) {
        } else {

            String efecto = efectoEntrada.getValue();
            String descripcion = efectoOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadEfecto(efecto);
            boolean test2 = false;
            if (test == false) {


                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaEfecto", efecto, "Creación de Falla - Efecto", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El efecto ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }
        }
        if (WPrevencionAsis == null) {
        } else {

            String prevencion = prevencionEntrada.getValue();
            String descripcion = prevencionOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadPrevencion(prevencion);
            boolean test2 = false;
            if (test == false) {
                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaPrevención", prevencion, "Creación de Falla - Prevención", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El control de prevención ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }

        }
        if (WDeteccionAsis == null) {
        } else {

            String deteccion = deteccionEntrada.getValue();
            String descripcion = deteccionOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadDeteccion(deteccion);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaDetección", deteccion, "Creación de Falla - Detección", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);


            } else {
                Messagebox.show("El control de detección ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }

        }
        if (WCausaAsis == null) {
        } else {

            String causa = causaEntrada.getValue();
            String descripcion = causaOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadCausa(causa);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaCausa", causa, "Creación de Falla - Causa", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("La Causa ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
        if (WDepartamentoAsis == null) {
        } else {

            String departamento = deptoEntrada.getValue();
            String departamentoAbr = deptoOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadDepartamento(departamento);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaDepartamento", departamento, "Creación de Departamento", fecha, departamentoAbr, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El Departamento ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
        if (WGradoAsis == null) {
        } else {

            String grado = gradoEntrada.getValue();
            String descripcion = gradoOEntrada.getValue();

            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadGrado(grado);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaGrado", grado, "Creación de Falla - Grado", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El grado ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }

        }

        if (WUsuarioAsis == null) {
        } else {
            int tUser = 0;
            String usuario = usuarioEntrada.getValue();
            String tipoUser = tipoUsuario.getSelectedItem().getLabel();
            //  String tipoUser = (String) tipoUsuario.getSelectedItem().getValue();
            String passus = passUsuarioEnt.getValue();
            ConnSql conex = new ConnSql();
            if (tipoUser.equals("Administrador")) {
                tUser = 1;
            }
            if (tipoUser.equals("Consultor")) {
                tUser = 2;
            }
            if (tipoUser.equals("Visitante")) {
                tUser = 3;
            }



            boolean test = conex.duplicidadUsuario(usuario);
            boolean test2 = false;
            if (test == false) {
                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaUsuario", usuario, "Creación de cuenta de usuario", fecha, tipoUser, tUser, relleno2, relleno3, relleno4, relleno5, relleno6, passus, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El usuario ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
        if (WProcesoAsis == null) {
        } else {

            String proceso = procesoEntrada.getValue();
            String descripcion = procesoOEntrada.getValue();
            String procesoAbr = procesoAbrEntrada.getValue();
            String protipo = procesoTipo.getSelectedItem().getLabel();
            ConnSql conex = new ConnSql();


            boolean test = conex.duplicidadProceso(proceso);
            boolean test2 = false;
            if (test == false) {
                if (procesoMaquina.isVisible()) {
                    String maquina = procesoMaquina.getSelectedItem().getLabel();

                    java.util.Date fecha = new java.util.Date();
                    fecha.getDate();
                    Controller x = new Controller();
                    x.historial("AltaProcesoMaq", proceso, "Creación de Proceso", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, procesoAbr, protipo, maquina, rellenoS4, rellenoS5, rellenoCara);
                    parteCrear.setVisible(false);
                    asistbox.setVisible(true);


                } else {
                    java.util.Date fecha = new java.util.Date();
                    fecha.getDate();
                    Controller x = new Controller();
                    x.historial("AltaProceso", proceso, "Creación de Proceso", fecha, descripcion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, procesoAbr, protipo, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                    parteCrear.setVisible(false);
                    asistbox.setVisible(true);
                }
            } else {
                Messagebox.show("El proceso ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }
        }


        if (WClienteAsis == null) {
        } else {

            String cliente = clienteEntrada.getValue();
            String direccion = clienteDirEntrada.getValue();
            String telefono = clienteTelEntrada.getValue();
            ConnSql conex = new ConnSql();
            boolean test = conex.duplicidadCliente(cliente);
            boolean test2 = false;
            if (test == false) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                x.historial("AltaCliente", cliente, "Alta de Cliente", fecha, direccion, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, telefono, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);

            } else {
                Messagebox.show("El cliente ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
        if (WCaracteristicaAsis == null) {
        } else {

            String par = null;
            if (partecarac == null) {
                par = comodinParte.getValue();
            } else {
                par = partecarac.getSelectedItem().getLabel();
            }
            String descr = caracDeEntrada.getValue();
            int numca = Integer.valueOf(caraNumEntrada.getValue());
            String grado = caraGradEntrada.getValue();
            String obs = caraObEntrada.getValue();
            String tipo = caraTiEntrada.getValue();
            ConnSql conex = new ConnSql();
            int idP = conex.traerIdParte(par);
            int idM = conex.traerIdMatriz(idP);
            boolean test = conex.duplicidadCaracter(descr, idM);

            if (test == false) {

                if (leftf.getChildren().isEmpty()) {
                    Messagebox.show("Asigne todos los valores y arrastre a la tabla izquierda los procesos que son afectados", "Error", Messagebox.OK, Messagebox.ERROR);

                } else {

                    java.util.Date fecha = new java.util.Date();
                    fecha.getDate();
                    Controller x = new Controller();
                    x.historial("AltaCaracteristica", descr, "Alta de Caracteristica", fecha, par, numca, idM, relleno3, relleno4, relleno5, relleno6, grado, obs, tipo, rellenoS4, rellenoS5, leftf);
                    parteCrear.setVisible(false);
                    asistbox.setVisible(true);
                }


            } else {
                Messagebox.show("La Característica ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }


        }

        if (WNivelIngAsis == null) {
        } else {
            String antiguo = NivelEdit.getValue();
            String Nparte = partenivI.getSelectedItem().getLabel();
            if (nivIngDisp.isVisible()) {
                String nuevo = nivIngDisp.getSelectedItem().getLabel();

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                String cambio = "Cambio de nivel de Ingeniería " + antiguo + " a " + nuevo;
                x.historial("AltaNivelIng", Nparte, cambio, fecha, nuevo, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);


            } else {


                String nuevo = NivelEditT.getValue();


                ConnSql conectar = new ConnSql();

                boolean test = conectar.duplicidadNivelIng(nuevo);

                if (test == true) {

                    java.util.Date fecha = new java.util.Date();
                    fecha.getDate();
                    Controller x = new Controller();
                    String cambio = "Cambio de nivel de Ingeniería " + antiguo + " a " + nuevo;
                    x.historial("AltaNivelIng", Nparte, cambio, fecha, nuevo, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                    parteCrear.setVisible(false);
                    asistbox.setVisible(true);


                } else {
                    Messagebox.show("El nivel de Ingeniería ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

                }
            }
        }

        if (WPersonaAsis == null) {
        } else {
            String person = personaEntrada.getValue();
            String personaAbr = personaEntradaAbr.getValue();
            String departamento = partedepa.getSelectedItem().getLabel();
            ConnSql conectar = new ConnSql();

            boolean test = conectar.duplicidadPersona(person);

            if (test == true) {

                java.util.Date fecha = new java.util.Date();
                fecha.getDate();
                Controller x = new Controller();
                String cambio = "Alta de persona en el sistema ";
                x.historial("AltaPersona", person, cambio, fecha, personaAbr, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, departamento, rellenoS2, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                parteCrear.setVisible(false);
                asistbox.setVisible(true);


            } else {
                Messagebox.show("La persona " + person + " ya ha sido registrada con anterioridad", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }




        }

        if (WEquipoAsis == null) {
        } else {

            String nombreEquipo = equipoEntrada.getValue();
            String desEquipo = equipoDesEntrada.getValue();
            ConnSql conex = new ConnSql();

            boolean test = conex.duplicidadEquipo(nombreEquipo);

            if (test == false) {

                if (leftf.getChildren().isEmpty()) {
                    Messagebox.show("Asigne el nombre del equipo y arrastre a la tabla izquierda las personas formaran parte de él", "Error", Messagebox.OK, Messagebox.ERROR);

                } else {

                    java.util.Date fecha = new java.util.Date();
                    fecha.getDate();
                    Controller x = new Controller();
                    String cambio = "Alta de equipo " + nombreEquipo + " en el sistema";
                    x.historial("AltaEquipo", nombreEquipo, cambio, fecha, desEquipo, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, rellenoS1, rellenoS2, rellenoS3, rellenoS4, rellenoS5, leftf);
                    parteCrear.setVisible(false);
                    asistbox.setVisible(true);
                }


            } else {
                Messagebox.show("El equipo ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }


        }

        if (WCambioParteAsis == null) {
        } else {

            String numPar = parteCambioPa.getSelectedItem().getLabel();
            ConnSql conex = new ConnSql();
            String numParN = nupart.getValue();
            String nomPar = conex.traerNombreParte(numPar);


            java.util.Date fecha = new java.util.Date();
            fecha.getDate();
            Controller x = new Controller();
            String cambio = "Cambio de número de Parte " + numPar + " a " + numParN + " en el sistema";
            x.historial("CambioParte", nomPar, cambio, fecha, numParN, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, numPar, numParN, rellenoS3, rellenoS4, rellenoS5, leftf);
            parteCrear.setVisible(false);
            asistbox.setVisible(true);






        }

        if (WMaterialAsis == null) {
        } else {

            int caso = 1;
            String proveedor = laminaEntrada.getValue();
            String material = nombreMateEntrada.getValue();



            java.util.Date fecha = new java.util.Date();
            Controller x = new Controller();
            if (proMecaMa.isVisible()) {

                caso = 2;

            }
            if (compoQuimMa.isVisible()) {
                caso = 3;

            }
            if (proMecaMa.isVisible() & compoQuimMa.isVisible()) {
                caso = 4;

            }

            conexion.ConnSql conec = new ConnSql();
            boolean bd;
            bd = conec.duplicidadMaterial(material);
            if (bd == true) {
                switch (caso) {

                    case 1:
                        alert("No se dieron de alta las opciones mecánicas ni químicas");
                        fecha.getDate();
                        String cambio = "Registro de Material " + material + " del proveedor " + proveedor + " en el sistema";
                        x.historial("AltaMaterialSolo", material, cambio, fecha, proveedor, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, proveedor, material, rellenoS3, rellenoS4, rellenoS5, leftf);
                        parteCrear.setVisible(false);
                        asistbox.setVisible(true);
                        break;

                    case 2:
                        alert("Alta de opciones mecánicas");

                        String tension = tensionMaEntrada.getValue();
                        String limitedeF = limFluMaEntrada.getValue();
                        String alargamiento = alarMatEntrada.getValue();
                        fecha.getDate();
                        String cambios = "Registro de Material " + material + " del proveedor " + proveedor + " en el sistema";
                        historial("AltaMaterialMeca", material, cambios, fecha, proveedor, relleno1, relleno2, relleno3, relleno4, relleno5, relleno6, tension, limitedeF, rellenoS3, rellenoS4, alargamiento, leftf);
                        parteCrear.setVisible(false);
                        asistbox.setVisible(true);
                        break;

                    case 3:
                        alert("Alta de opciones químicas");

                        int carbono = Integer.valueOf(carbonoMaEntrada.getValue());
                        int silicio = Integer.valueOf(silicioMaEntrada.getValue());
                        int mangane = Integer.valueOf(mangaMatEntrada.getValue());
                        int fosforo = Integer.valueOf(fosfoMatEntrada.getValue());
                        int azufre = Integer.valueOf(azuMatEntrada.getValue());
                        fecha.getDate();
                        cambio = "Registro de Material " + material + " del proveedor " + proveedor + " en el sistema";
                        historial("AltaMaterialQuim", material, cambio, fecha, proveedor, carbono, silicio, mangane, fosforo, azufre, relleno6, proveedor, material, rellenoS3, rellenoS4, rellenoS5, leftf);
                        parteCrear.setVisible(false);
                        asistbox.setVisible(true);


                        break;
                    case 4:
                        alert("Alta de opciones mecánicas y químicas");

                        int ten = Integer.valueOf(tensionMaEntrada.getValue());
                        int limi = Integer.valueOf(limFluMaEntrada.getValue());
                        int alar = Integer.valueOf(alarMatEntrada.getValue());
                        int car = Integer.valueOf(carbonoMaEntrada.getValue());
                        int sil = Integer.valueOf(silicioMaEntrada.getValue());
                        int man = Integer.valueOf(mangaMatEntrada.getValue());
                        int fos = Integer.valueOf(fosfoMatEntrada.getValue());
                        int azu = Integer.valueOf(azuMatEntrada.getValue());
                        fecha.getDate();
                        cambio = "Registro de Material " + material + " del proveedor " + proveedor + " en el sistema";
                        historialEspecial("AltaMaterialQuiMec", material, cambio, fecha, proveedor, car, sil, man, fos, azu, ten, limi, alar, relleno1, relleno1, relleno1, relleno1, proveedor, material, rellenoS3, rellenoS4, rellenoS1, rellenoS1, rellenoS1, rellenoS1, rellenoS1, rellenoS1, rellenoS1, leftf);
                        parteCrear.setVisible(false);
                        asistbox.setVisible(true);

                        break;

                }

            } else {
                Messagebox.show("El material ya existe, si la información cambio modifica el material", "Información", Messagebox.OK, Messagebox.INFORMATION);

            }

        }
        if (WParteAsis == null) {
        } else {
            ConnSql conex = new ConnSql();
            if (fasesAsis.isVisible()) {


                if (leftf.getChildren().isEmpty()) {
                } else {
                    ConnSql conectar = new ConnSql();
                    List<String> numFa = new ArrayList<String>();
                    int tam = leftf.getChildren().size() - 2;

                    for (int rb = 0; tam >= rb; rb++) {
                        leftf.setSelectedIndex(rb);
                        numFa.add(conectar.obtenerIdFase(leftf.getSelectedItem().getLabel().toString()));

                    }
                    int idParte;
                    idParte = conectar.traerIdParte(comodin.getTitle().toString());
                    conectar.relacionFaseParte(idParte, numFa);
                    fasesAsis.setVisible(false);
                    WCaracAsistente.setVisible(true);
                }

            } else {

                if (WCaracAsistente.isVisible()) {



                    String par = caracpart.getValue();
                    String descr = caracDeEntrada.getValue();
                    int numca = Integer.valueOf(caraNumEntrada.getValue());
                    String grado = caraGradEntrada.getValue();
                    String obs = caraObEntrada.getValue();
                    String tipo = caraTiEntrada.getValue();
                    int idP = conex.traerIdParte(par);
                    boolean test = false;
                    boolean t = conex.duplicidadMatriz(par);
                    if (t == true) {
                        test = conex.insertarMatrizCara(idP);
                    } else {
                        test = true;
                    }
                    int idM = conex.traerIdMatriz(idP);
                    if (test == true) {
                        test = conex.duplicidadCaracter(descr, idM);
                    }
                    if (test == false) {

                        if (left.getChildren().isEmpty()) {
                            Messagebox.show("Asigne todos los valores y arrastre a la tabla izquierda los procesos que son afectados", "Error", Messagebox.OK, Messagebox.ERROR);

                        } else {

                            List<String> numFas = new ArrayList<String>();

                            boolean test2 = conex.altaCaracteristica(descr, numca, grado, obs, tipo, idM);

                            int idCara = conex.obtenerIdCara(descr, idM);
                            int x = left.getChildren().size();
                            for (int rb = 0; left.getChildren().size() > rb; rb++) {
                                left.setSelectedIndex(rb);
                                String auxs = conex.obtenerIdFase(left.getSelectedItem().getLabel().toString());
                                numFas.add(auxs);

                                int auxs2 = Integer.valueOf(auxs);
                                conex.altaCaracteristicaProceso(auxs2, idCara, idM);


                            }



                            if (test2 == true) {
                                Messagebox.show("Característica añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }

                        }
//                                 
//                                 
                    }


                    Messagebox.show("Deseas crear otra Caractéristica", "Asistente",
                            Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {

                        public void onEvent(Event e) throws Exception {
                            if ("onYes".equals(e.getName())) {

                                caracDeEntrada.setValue("");
                                caraNumEntrada.setValue("");
                                caraGradEntrada.setValue("");
                                caraObEntrada.setValue("");
                                caraTiEntrada.setValue("");
                                onClick$recargarCara();


                            } else {

                                WCaracAsistente.setVisible(false);
                                RevisionesAsis.setVisible(true);
                            }
                        }
                    });


                } else {

                    if (RevisionesAsis.isVisible()) {

                        RevisionesAsis.setVisible(false);
                        materialAsis.setVisible(true);

                    } else {

                        if (materialAsis.isVisible()) {

                            String material = matOpc.getSelectedItem().getLabel();
                            double pasAl = Double.valueOf(pasoAlEnt.getValue());
                            String tolerancia = tolEnt.getValue();
                            double pesoBl = Double.valueOf(pesoBlEnt.getValue());
                            double pesoParte = Double.valueOf(pesoParteEnt.getValue());
                            String nivelIng = nivelInge.getValue();
                            String prove = proveedors.getValue();
                            String cliente = selCliente.getSelectedItem().getLabel();
                            double aprovechamient = Double.valueOf(calculoAproLab.getValue());
                            int idCliente = conex.obtenerIdClientes(cliente);
                            int idP = conex.traerIdParte(comodin.getTitle());
                            int idM = conex.obtenerIdMaterial(material);
                            double anchoRollo = Double.valueOf(anchoMat.getValue());
                            double espesor = Double.valueOf(espesorMat.getValue());
                            double pesoT = Double.valueOf(pesoRolloUt.getValue());
                            double aprovT = Double.valueOf(calculoAproLabT.getValue());
                            boolean e = false;
                            boolean b = conex.asignarCliente(idCliente, idP);
                            boolean c = conex.insertarEspecificación(idP, idM);
                            
                            
                            
                            
                            if (c = true) {
                                int idEsp = conex.obtenerIdEspecificacion(idP, idM);
                                e = conex.materialEsp(idEsp, pasAl, pesoBl, pesoParte, aprovechamient, espesor, anchoRollo, aprovT, pesoT);
                            }
                            if (b == true) {
                                if (e == true) {

                                    materialAsis.setVisible(false);
                                    formatosAsis.setVisible(true);


                                }

                            }

                        } else {

                            if (formatosAsis.isVisible()) {


                                if (procesosMaq.isVisible()) {
                                    if (procesosMaq.getItems().isEmpty()) {
                                        Messagebox.show("No existen procesos en Blanking y Transfer", "Información", Messagebox.OK, Messagebox.INFORMATION);
                                    } else {
                                        int idp = conex.traerIdParte(comodin.getTitle());
                                        conex.registroSecu(procesosMaq.getItemCount(), idp);

                                    }
                                    formatosAsis.setVisible(false);
                                    tfFormatos.setVisible(true);

                                } else {
                                    Messagebox.show("Debes checar si existen procesos en Blanking y Transfer", "Información", Messagebox.OK, Messagebox.INFORMATION);

                                }

                            } else {

                                String nombreParte = nombreParEntrada.getValue();
                                String numeroParte = numeroParEntrada.getValue();
                                String nivelIng = nivIngDisp.getSelectedItem().getLabel();
                                String modelo = ModeDisp.getSelectedItem().getLabel();
                                String codigoAmef = amefEntrada.getValue();

                                int niv = conex.obtenerIdNivIng(nivelIng);
                                int mod = conex.obtenerIdModelo(modelo);

                                boolean test = conex.buscarDuplicidadParte(numeroParte);

                                if (test == true) {

                                    java.util.Date fecha = new java.util.Date();
                                    fecha.getDate();
                                    Controller x = new Controller();
                                    x.historial("AltaParte", nombreParte, "Creación de parte", fecha, numeroParte, niv, mod, relleno3, relleno4, relleno5, relleno6, codigoAmef, numeroParte, rellenoS3, rellenoS4, rellenoS5, rellenoCara);
                                    comodin.setTitle(numeroParte);
                                    WParteAsis.setVisible(false);
                                    fasesAsis.setVisible(true);



                                } else {
                                    Messagebox.show("El número de parte ya existe", "Información", Messagebox.OK, Messagebox.INFORMATION);
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    public void onClick$recargarCara() throws Exception {
        caracpart.setValue(comodin.getTitle());
        right.getChildren().clear();
        left.getChildren().clear();

        ConnSql conectar = new ConnSql();
        String partes = caracpart.getValue();
        regresof = conectar.obtenerProcesosParte(partes);

        ListModelArray list = new ListModelArray(regresof);
        right.setModel(list);

        llenadoFase2(self);
        gridCara.setVisible(true);

    }

    public void onSelect$partecarac() {
        traerCara.setVisible(true);
        eventoCara.setVisible(true);
    }

    public void onClick$calculoAprov() {
        double pesoBlanking = Double.valueOf(pesoBlEnt.getValue());
        double pesoParte = Double.valueOf(pesoParteEnt.getValue());


        double resul = pesoParte * 100 / pesoBlanking;
        DecimalFormat dfa = new DecimalFormat("#.###");
        String re = String.valueOf(dfa.format(resul));
        calculoAproLab.setVisible(true);
        calculoAproLab.setValue(re);
        calculoAproL.setValue(" %");
        calculoAproL.setVisible(true);

    }
    
    public void onClick$calculoAprovT() {
        
        double espesor = Double.valueOf(espesorMat.getValue());
        double PasoAlim = Double.valueOf(pasoAlEnt.getValue());
        double ancho = Double.valueOf(anchoMat.getValue());
        double pesoParte = Double.valueOf(pesoParteEnt.getValue());
        
        double pesoT = (espesor * ancho *PasoAlim * 7.85)/1000000; 
        pesoRolloUt.setValue(String.valueOf(pesoT));
        pesoRolloUtD.setValue(" kg");
        nivRow4.setVisible(true);
        double resul = pesoParte * 100 / pesoT;
        DecimalFormat dfa = new DecimalFormat("#.###");
        String re = String.valueOf(dfa.format(resul));
        calculoAproLabT.setVisible(true);
        calculoAproLabT.setValue(re);
        calculoAproLT.setValue(" %");
        calculoAproLT.setVisible(true);

    }

    public void onSelect$matOpc() throws SQLException {
        String material = matOpc.getSelectedItem().getLabel();
        ConnSql x = new ConnSql();
        String prov = x.obtenerProvedorMat(material);
        proveedors.setValue(prov);

        nivRow1.setVisible(true);
        nivRow2.setVisible(true);
        nivRow3.setVisible(true);
    }

    public void partecarac() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerPartes();


        partecarac.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(partes);
        partecarac.setModel(list);


    }

    public void procesoTipo() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerTipoProceso();


        procesoTipo.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(partes);
        procesoTipo.setModel(list);


    }

    public void procesoMaquina() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerTipoMaquina();


        procesoMaquina.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(partes);
        procesoMaquina.setModel(list);


    }

    public void parteElaEquipo() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerPartes();


        parteElaEquipo.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(partes);
        parteElaEquipo.setModel(list);


    }

    public void nivIngDisp() throws SQLException {
        ConnSql conexion = new ConnSql();
        List nivelIng = conexion.obtenerNivelesIng();


        nivIngDisp.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(nivelIng);
        nivIngDisp.setModel(list);
        nivIngDisp.setWidth("250px");


    }

    public void ModeDisp() throws SQLException {
        ConnSql conexion = new ConnSql();
        List modelo = conexion.obtenerModelos();


        ModeDisp.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });



        ListModelArray list = new ListModelArray(modelo);
        ModeDisp.setModel(list);
        ModeDisp.setWidth("250px");

    }

    public void partedepa() throws SQLException {
        ConnSql conexion = new ConnSql();
        List departamentos = conexion.obtenerDepartamentos();


        partedepa.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Departamento r = (Departamento) data;

                item.appendChild(new Listcell(r.getDepartamento()));



            }
        });

        ListModelArray list = new ListModelArray(departamentos);
        partedepa.setModel(list);


    }

    public void partenivI() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerPartes();


        partenivI.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;

                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });

        ListModelArray list = new ListModelArray(partes);
        partenivI.setModel(list);


    }

    public void parteElabApro() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerPartes();


        parteElabApro.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;


                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });

        ListModelArray list = new ListModelArray(partes);
        parteElabApro.setModel(list);


    }

    public void parteCambioPa() throws SQLException {
        ConnSql conexion = new ConnSql();
        List partes = conexion.obtenerPartes();


        parteCambioPa.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                NumeroParte r = (NumeroParte) data;


                item.appendChild(new Listcell(r.getNumeroParte()));



            }
        });

        ListModelArray list = new ListModelArray(partes);
        parteCambioPa.setModel(list);


    }

    public void formatoElabApro(String comp) throws SQLException {
        ConnSql conexion = new ConnSql();


        List formato = conexion.obtenerFormatoParte(comp);


        formatoElabApro.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                FormatoParte r = (FormatoParte) data;
                item.appendChild(new Listcell(r.getTipoFormato()));
            }
        });

        ListModelArray list = new ListModelArray(formato);
        formatoElabApro.setModel(list);


    }

    public void elaboroElabApro(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        elaboroElabApro.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void ingenieriaApro(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        ingenieria.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void selCliente(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        selCliente.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                ListaRegreso r = (ListaRegreso) data;
                item2 = item;

                item2.appendChild(new Listcell(String.valueOf(r.getRegreso())));

            }
        });

    }

    public void matOpc(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        matOpc.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                ListaRegreso r = (ListaRegreso) data;
                item2 = item;

                item2.appendChild(new Listcell(String.valueOf(r.getRegreso())));

            }
        });

    }

    public void asegCalApro(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        asegCalid.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void produccionApro(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        produccion.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void aproboElabApro(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        aproboElabApro.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void AproboElaboroEquipo(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        elaboroEquipo.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void EquipoElabora(Component comp) throws SQLException, Exception {
        super.doAfterCompose(comp);


        pesonasEquipo.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona())));
                item2.appendChild(new Listcell(String.valueOf(r.getNombreAbr())));
            }
        });

    }

    public void onClick$recargarFase() throws Exception {


        rightf.getChildren().clear();
        leftf.getChildren().clear();
        ConnSql conectar = new ConnSql();
        String partes = partecarac.getSelectedItem().getLabel();
        regresof = conectar.obteneraracteristicas(partes);
        ListModelArray list = new ListModelArray(regresof);
        rightf.setModel(list);
        llenadoFase(self);



    }

    public void onClick$recargarFas() throws Exception {

        recargarFas.setLabel("Reiniciar");
        rightf.getChildren().clear();
        leftf.getChildren().clear();
        regresoInsp.getChildren().clear();
        regresoTrans.getChildren().clear();
        regresoPrepara.getChildren().clear();
        regresoBlanking.getChildren().clear();
        regresoTandem.getChildren().clear();
        regresoTransfer.getChildren().clear();

        ConnSql conectar = new ConnSql();

        regresof = conectar.obtenerfasesRecAl();
        Listhead ad = new Listhead();
        Listheader ap = new Listheader("Entradas y Almacén");
        ad.appendChild(ap);
        rightf.appendChild(ad);

        Listhead mr = new Listhead();
        Listheader ar = new Listheader("Procesos Anexados");
        mr.appendChild(ar);
        leftf.appendChild(mr);

        ListModelArray list = new ListModelArray(regresof);
        rightf.setModel(list);
        llenadoFase(self);

        regresoInspec = conectar.obtenerfasesInsp();
        Listhead da = new Listhead();
        Listheader pa = new Listheader("Inspecciones");
        da.appendChild(pa);
        regresoInsp.appendChild(da);

        ListModelArray listIn = new ListModelArray(regresoInspec);
        regresoInsp.setModel(listIn);
        llenadoFaseIn(self);

        regresoTranspor = conectar.obtenerfasesTrans();
        Listhead ra = new Listhead();
        Listheader la = new Listheader("Transporte");
        ra.appendChild(la);
        regresoTrans.appendChild(ra);

        ListModelArray listTra = new ListModelArray(regresoTranspor);
        regresoTrans.setModel(listTra);
        llenadoFaseTra(self);

        regresoPrepa = conectar.obtenerfasesPrepa();
        Listhead ua = new Listhead();
        Listheader oa = new Listheader("Preparación");
        ua.appendChild(oa);
        regresoPrepara.appendChild(ua);

        ListModelArray listPre = new ListModelArray(regresoPrepa);
        regresoPrepara.setModel(listPre);
        llenadoFasePrep(self);

        regresoBlank = conectar.obtenerfasesBlank();
        Listhead ub = new Listhead();
        Listheader ob = new Listheader("Blanking");
        ub.appendChild(ob);
        regresoBlanking.appendChild(ub);

        ListModelArray listBl = new ListModelArray(regresoBlank);
        regresoBlanking.setModel(listBl);
        llenadoFaseBl(self);

        regresoTan = conectar.obtenerfasesTan();
        Listhead ib = new Listhead();
        Listheader ii = new Listheader("Tandem");
        ib.appendChild(ii);
        regresoTandem.appendChild(ib);

        ListModelArray listTan = new ListModelArray(regresoTan);
        regresoTandem.setModel(listTan);
        llenadoFaseTd(self);

        regresoTr = conectar.obtenerfasesTran();
        Listhead rub = new Listhead();
        Listheader rob = new Listheader("Transfer");
        rub.appendChild(rob);
        regresoTransfer.appendChild(rub);

        ListModelArray listTran = new ListModelArray(regresoTr);
        regresoTransfer.setModel(listTran);
        llenadoFaseTr(self);

    }

    public void recargarFas() throws Exception {


        rightf.getChildren().clear();
        regresoInsp.getChildren().clear();
        regresoTrans.getChildren().clear();
        regresoPrepara.getChildren().clear();
        regresoBlanking.getChildren().clear();
        regresoTandem.getChildren().clear();
        regresoTransfer.getChildren().clear();

        ConnSql conectar = new ConnSql();

        regresof = conectar.obtenerfasesRecAl();
        Listhead ad = new Listhead();
        Listheader ap = new Listheader("Entradas y Almacén");
        ad.appendChild(ap);
        rightf.appendChild(ad);

        ListModelArray list = new ListModelArray(regresof);
        rightf.setModel(list);
        llenadoFase(self);

        regresoInspec = conectar.obtenerfasesInsp();
        Listhead da = new Listhead();
        Listheader pa = new Listheader("Inspecciones");
        da.appendChild(pa);
        regresoInsp.appendChild(da);

        ListModelArray listIn = new ListModelArray(regresoInspec);
        regresoInsp.setModel(listIn);
        llenadoFaseIn(self);

        regresoTranspor = conectar.obtenerfasesTrans();
        Listhead ra = new Listhead();
        Listheader la = new Listheader("Transporte");
        ra.appendChild(la);
        regresoTrans.appendChild(ra);

        ListModelArray listTra = new ListModelArray(regresoTranspor);
        regresoTrans.setModel(listTra);
        llenadoFaseTra(self);

        regresoPrepa = conectar.obtenerfasesPrepa();
        Listhead ua = new Listhead();
        Listheader oa = new Listheader("Preparación");
        ua.appendChild(oa);
        regresoPrepara.appendChild(ua);

        ListModelArray listPre = new ListModelArray(regresoPrepa);
        regresoPrepara.setModel(listPre);
        llenadoFasePrep(self);

        regresoBlank = conectar.obtenerfasesBlank();
        Listhead ub = new Listhead();
        Listheader ob = new Listheader("Blanking");
        ub.appendChild(ob);
        regresoBlanking.appendChild(ub);

        ListModelArray listBl = new ListModelArray(regresoBlank);
        regresoBlanking.setModel(listBl);
        llenadoFaseBl(self);

        regresoTan = conectar.obtenerfasesTan();
        Listhead ib = new Listhead();
        Listheader ii = new Listheader("Tandem");
        ib.appendChild(ii);
        regresoTandem.appendChild(ib);

        ListModelArray listTan = new ListModelArray(regresoTan);
        regresoTandem.setModel(listTan);
        llenadoFaseTd(self);

        regresoTr = conectar.obtenerfasesTran();
        Listhead rub = new Listhead();
        Listheader rob = new Listheader("Transfer");
        rub.appendChild(rob);
        regresoTransfer.appendChild(rub);

        ListModelArray listTran = new ListModelArray(regresoTr);
        regresoTransfer.setModel(listTran);
        llenadoFaseTr(self);

    }

    public void onClick$recargarPersonas() throws Exception {

        rightf.getChildren().clear();
        leftf.getChildren().clear();
        ConnSql conectar = new ConnSql();
        regresof = conectar.obtenerPersonas();

        ListModelArray list = new ListModelArray(regresof);
        rightf.setModel(list);
        llenadoPersonas(self);

    }

    public void llenadoCarac(Component comp) throws Exception {
        super.doAfterCompose(comp);


        right.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Caracteristicas r = (Caracteristicas) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNumero()), "/img/plus.png"));
            }
        });

    }

    public void llenadoFaseMaq(Component comp) throws Exception {
        super.doAfterCompose(comp);


        procesosMaq.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase())));
                item2.appendChild(new Listcell(r.getNombreFaseAbr()));
            }
        });

    }

    public void llenadoFaseMaq1500(Component comp) throws Exception {
        super.doAfterCompose(comp);


        procesosTF1500.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase())));
                item2.appendChild(new Listcell(r.getNombreFaseAbr()));
            }
        });

    }

    public void llenadoFaseMaq500(Component comp) throws Exception {
        super.doAfterCompose(comp);


        procesosTF500.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase())));
                item2.appendChild(new Listcell(r.getNombreFaseAbr()));
            }
        });

    }

    public void llenadoFase(Component comp) throws Exception {
        super.doAfterCompose(comp);


        rightf.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFase2(Component comp) throws Exception {
        super.doAfterCompose(comp);


        right.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFaseIn(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoInsp.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFaseTra(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoTrans.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFasePrep(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoPrepara.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFaseBl(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoBlanking.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFaseTd(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoTandem.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoFaseTr(Component comp) throws Exception {
        super.doAfterCompose(comp);


        regresoTransfer.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Fase r = (Fase) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombreFase()), "/img/plus.png"));

            }
        });

    }

    public void llenadoPersonas(Component comp) throws Exception {
        super.doAfterCompose(comp);


        rightf.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                Persona r = (Persona) data;
                item2 = item;
                item2.setDraggable("true");
                item2.setDroppable("true");
                item2.appendChild(new Listcell(String.valueOf(r.getNombrePersona()), "/img/plus.png"));

            }
        });

    }

    public void onMove$Item2(Component dragged) {

        if (self instanceof Listitem) {
            self.getParent().insertBefore(dragged, self);
        } else {
            self.appendChild(dragged);
        }
    }

    public void onClick$right() throws InterruptedException {

        if (right.getChildren().isEmpty()) {
        } else {

            String cara = right.getSelectedItem().getLabel().toString();
            ConnSql conectar = new ConnSql();
            String desCara = conectar.obteneraracteristicas2(cara);
            Messagebox.show(desCara, "Descripción", Messagebox.OK, Messagebox.INFORMATION);

        }
    }

    public void onClick$left() throws InterruptedException {
        if (left.getChildren().isEmpty()) {
        } else {

            String cara = left.getSelectedItem().getLabel().toString();

            ConnSql conectar = new ConnSql();
            String desCara = conectar.obteneraracteristicas2(cara);
            Messagebox.show(desCara, "Descripción", Messagebox.OK, Messagebox.INFORMATION);
        }

    }

    public void onClick$updateover() throws Exception {
        ConnSql conectar = new ConnSql();

        int idParte = conectar.traerIdParte(comodin.getTitle().toString());
        List rf = conectar.obteneraracteristicasFases(idParte);

        ListModelArray list = new ListModelArray(rf);
        caracParte.setModel(list);
        ParteCaracR(self);
        caracParte.setVisible(true);
        lupdateover.setVisible(true);
        updateover2.setVisible(true);
        updateover.setVisible(false);

    }

    public void onClick$updateoverf() throws Exception {
        ConnSql conectar = new ConnSql();

        int idParte = conectar.traerIdParte(comodin.getTitle().toString());
        List rf = conectar.obtenerFasesFalla(idParte);

        ListModelArray list = new ListModelArray(rf);
        fallasParte.setModel(list);
        ParteFalla(self);
        fallasParte.setVisible(true);
        lupdateover2.setVisible(true);
        updateoverf2.setVisible(true);
        updateoverf.setVisible(false);

    }

    public void onClick$updateover2() {
        caracParte.setVisible(false);
        lupdateover.setVisible(false);
        updateover.setVisible(true);
        updateover2.setVisible(false);
    }

    public void onClick$updateoverf2() {
        fallasParte.setVisible(false);
        lupdateover2.setVisible(false);
        updateoverf.setVisible(true);
        updateoverf2.setVisible(false);
    }

    public void ParteCaracR(Component comp) throws Exception {
        super.doAfterCompose(comp);


        caracParte.setItemRenderer(new ListitemRenderer() {

            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                CaraFases r = (CaraFases) data;

                item.appendChild(new Listcell(r.getNombreFase()));
                item.appendChild(new Listcell(String.valueOf(r.getNumeroFase())));
                item.appendChild(new Listcell(r.getDescripcion()));

            }
        });

    }

    public void ParteFalla(Component comp) throws Exception {
        super.doAfterCompose(comp);
        fallasParte.setItemRenderer(new ListitemRenderer() {
            public void render(Listitem item, Object data) throws Exception {
                if (data == null) {
                    return;
                }
                FallaParte r = (FallaParte) data;
                item.appendChild(new Listcell(r.getNombreFase()));
                item.appendChild(new Listcell(r.getModoFalla()));
                item.appendChild(new Listcell(r.getEfectoFalla()));
                item.appendChild(new Listcell(r.getCausaFalla()));
            }
        });

    }
    public void onClick$crearTroquel() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Troqueles/AltaTroquel.zul");
    }
    public void onClick$altaPer() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        persona.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Persona/AltaPersona.zul");
    }
    public void onClick$elabApro() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        persona.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Formato/AltaElabApro.zul");
    }
    public void onClick$aprobadorEq() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        persona.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Formato/EquipoEla.zul");
    }

    public void onClick$altaEq() {

        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        persona.setVisible(false);
        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Persona/AltaEquipo.zul");

    }

    public void onClick$altaComp() {
        alta.setVisible(true);
        baja.setVisible(false);
        editar.setVisible(false);
        fallaComp.setVisible(false);
        persona.setVisible(false);
        materiales.setVisible(false);
        planControl.setVisible(false);

    }

    public void onClick$bajaComp() {
        baja.setVisible(true);
        alta.setVisible(false);
        editar.setVisible(false);
        fallaComp.setVisible(false);
        persona.setVisible(false);
        materiales.setVisible(false);
        planControl.setVisible(false);
    }

    public void onClick$editComp() {
        editar.setVisible(true);
        baja.setVisible(false);
        alta.setVisible(false);
        fallaComp.setVisible(false);
        persona.setVisible(false);
        materiales.setVisible(false);
        planControl.setVisible(false);

    }

    public void onClick$editFalla() {
        fallaComp.setVisible(true);
        persona.setVisible(false);
        editar.setVisible(false);
        baja.setVisible(false);
        alta.setVisible(false);
        materiales.setVisible(false);
        planControl.setVisible(false);


    }

    public void onClick$elaboraComp() {
        persona.setVisible(true);
        editar.setVisible(false);
        baja.setVisible(false);
        alta.setVisible(false);
        fallaComp.setVisible(false);
        materiales.setVisible(false);
        planControl.setVisible(false);

    }

    public void onClick$editMat() {
        materiales.setVisible(true);
        fallaComp.setVisible(false);
        persona.setVisible(false);
        editar.setVisible(false);
        baja.setVisible(false);
        alta.setVisible(false);
        planControl.setVisible(false);
    }

    public void onClick$planCtrl() {
        planControl.setVisible(true);
        alta.setVisible(false);
        baja.setVisible(false);
        editar.setVisible(false);
        fallaComp.setVisible(false);
        persona.setVisible(false);
        materiales.setVisible(false);


    }

    public void onClick$crearModoF() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaModo.zul");


    }

    public void onClick$crearEfectoF() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaEfecto.zul");


    }

    public void onClick$crearCausaF() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaCausa.zul");


    }

    public void onClick$crearControlPF() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaPrevencion.zul");


    }

    public void onClick$crearControlDF() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaDeteccion.zul");


    }

    public void onClick$crearGrado() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);

        parteCrear.setVisible(true);

        parteCrear.setSrc("/opc/asistente/Falla/AltaGrado.zul");


    }

    public void onClick$altaClient() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Cliente/AltaCliente.zul");

    }

    public void onClick$altaUsuario() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Usuario/AltaUsuario.zul");
    }

    public void onClick$crearProceso() {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Proceso/AltaProceso.zul");


    }

    public void onClick$crearCarac() throws SQLException {
        asistbox.setVisible(false);
        alta.setVisible(false);
        baja.setVisible(false);
        fallaComp.setVisible(false);
        parteCrear.setVisible(true);
        parteCrear.setSrc("/opc/asistente/Caracteristica/AltaCaracteristica.zul");


    }

    public void onDrop$leftf() throws Exception {
        recargarFas();
    }

    public void onDrop$rightf() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoInsp() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoTrans() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoPrepara() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoBlanking() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoTandem() throws Exception {
        recargarFas();
    }

    public void onDrop$regresoTransfer() throws Exception {
        recargarFas();
    }

    public boolean historial(String tipo, String componente, String razon, java.util.Date fecha, String descripcion, final int aa, final int bb, final int cc, final int dd, final int ee, final int ff, final String a, final String b, final String c, final String d, final String e, final Listbox leff) throws InterruptedException, SQLException {
        conexion.ConnSql conectar = new ConnSql();
        final String tipof = tipo;
        final String componentef = componente;
        final String razonf = razon;
        final java.util.Date fechaf = fecha;
        final String descripcionf = descripcion;
        final Window win = (Window) Executions.createComponents(
                "/login.zul", null, null);

        if ("AltaParte".equals(tipof)) {
            boolean test2 = false;
            test2 = conectar.insertarNumeroParte(componentef, descripcionf, aa, bb);
            int idP = conectar.traerIdParte(b);
            conectar.insertarAmef(idP, a);

        } else {




            win.getChildren().clear();

            windo.getChildren().clear();
            Grid gr = new Grid();
            gr.getChildren().clear();

            windo.setTitle("Verificación para Historial");
            win.appendChild(windo);

            windo.setWidth("400px");
            Columns colp = new Columns();
            colp.getChildren().clear();
            Column nombre = new Column();
            nombre.getChildren().clear();
            Column password = new Column();
            password.getChildren().clear();
            Rows rowss = new Rows();
            rowss.getChildren().clear();
            Row ro = new Row();
            rowss.appendChild(ro);
            colp.appendChild(nombre);
            colp.appendChild(password);
            gr.appendChild(colp);
            gr.appendChild(rowss);
            windo.appendChild(gr);
            nombre.appendChild(new Label("Nombre"));
            password.appendChild(new Label("Password"));

            ro.appendChild(nom);
            ro.appendChild(pss);

            nom.setWidth("180px");
            pss.setWidth("180px");
            pss.setType("password");

            Button btni = new Button("Verificar");
            windo.appendChild(btni);
            btni.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                public void onEvent(Event event) throws Exception {

                    String n = nom.getValue();
                    String p = pss.getValue();
                    conexion.ConnSql conectar = new ConnSql();
                    xd = conectar.inicioLog(n, p);


                    if (xd == true) {
                        int idu = conectar.obtenerIdUsuario(n);
                        windo.onClose();
                        conectar.historial(idu, componentef, razonf, fechaf);
                        if ("AltaModelo".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaModelo(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Modelo añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaModo".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaModo(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Modo añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaEfecto".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaEfecto(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Efecto - Falla añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaPrevención".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaPrevencion(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Prevención - Falla añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaDetección".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaDeteccion(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Detección - Falla añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaCausa".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaCausa(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Causa - Falla añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaDepartamento".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaDepartamento(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Departamento añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaGrado".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaGrado(componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Grado - Falla añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaTroquel".equals(tipof)) {
                            boolean test2 = false;
                            //     test2 = conectar.altaTroquel(componentef, aa, bb, cc, dd, ee, ff, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Troquel añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaUsuario".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaUsuario(aa, componentef, a);

                            if (test2 == true) {
                                Messagebox.show("Usuario añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaProceso".equals(tipof)) {
                            boolean test2 = false;
                            boolean test3 = false;


                            test2 = conectar.altaProceso(componentef, a, descripcionf);
                            int idpro = Integer.valueOf(conectar.obtenerIdFase(componentef));
                            int idtipopro = conectar.obtenerIdTipoProceso(b);
                            conectar.altaTipoProceso(idpro, idtipopro);



                            if (test2 == true) {
                                Messagebox.show("Proceso añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaProcesoMaq".equals(tipof)) {
                            boolean test2 = false;



                            test2 = conectar.altaProceso(componentef, a, descripcionf);
                            int idpro = Integer.valueOf(conectar.obtenerIdFase(componentef));
                            int idtipopro = conectar.obtenerIdTipoProceso(b);
                            conectar.altaTipoProceso(idpro, idtipopro);
                            int tipoMaq = conectar.obtenerIdTipoMaquina(c);
                            conectar.altaProcesoMaquina(idpro, tipoMaq);

                            if (test2 == true) {
                                Messagebox.show("Proceso añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }

                        if ("AltaCliente".equals(tipof)) {
                            boolean test2 = false;

                            test2 = conectar.altaCliente(componentef, descripcionf, a);

                            if (test2 == true) {
                                Messagebox.show("Cliente añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaCaracteristica".equals(tipof)) {
                            boolean test2 = false;
                            try {

                                List<String> numFa = new ArrayList<String>();

                                test2 = conectar.altaCaracteristica(componentef, aa, a, b, c, bb);

                                int idCara = conectar.obtenerIdCara(componentef, bb);
                                int x = leff.getChildren().size();
                                for (int rb = 0; leff.getChildren().size() > rb; rb++) {
                                    leff.setSelectedIndex(rb);
                                    String auxs = conectar.obtenerIdFase(leff.getSelectedItem().getLabel().toString());
                                    numFa.add(auxs);
                                    int auxs2 = Integer.valueOf(auxs);
                                    conectar.altaCaracteristicaProceso(auxs2, idCara, bb);
                                }
                            } catch (Exception ex) {
                                Messagebox.show("Error interno, reinicie su sesión o consulte al desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);

                            }


                            if (test2 == true) {
                                Messagebox.show("Característica añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }



                        if ("AltaNivelIng".equals(tipof)) {
                            boolean test2 = false;



                            boolean alt = conectar.altaNivelIng(descripcionf);

                            if (alt = true) {
                                int IdNivIng = conectar.obtenerIdNivIng(descripcionf);
                                boolean res = conectar.cambioNivelIng(IdNivIng, componentef);
                                if (res = true) {

                                    Messagebox.show("Nivel de Ingeniería actualizado a " + descripcionf, "Realizado", Messagebox.OK, Messagebox.INFORMATION);

                                }

                                if (test2 == true) {
                                    Messagebox.show("Nivel de Ingeniería añadido", "Información", Messagebox.OK, Messagebox.INFORMATION);
                                }
                            }


                        }

                        if ("AltaPersona".equals(tipof)) {
                            boolean test2 = false;

                            int idDep = conectar.obtenerIdDepto(a);

                            test2 = conectar.altaPersona(idDep, componentef, descripcionf);

                            if (test2 == true) {
                                Messagebox.show("Persona añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }


                        if ("AltaEquipo".equals(tipof)) {
                            boolean test2 = false;
                            try {

                                List<String> numFa = new ArrayList<String>();

                                test2 = conectar.altaEquipo(componentef, descripcionf);
                                int idEq = conectar.obtenerIdEq(componentef);

                                for (int rb = 0; leff.getChildren().size() > rb; rb++) {
                                    leff.setSelectedIndex(rb);
                                    String auxs = conectar.obtenerIdPersona(leff.getSelectedItem().getLabel().toString());
                                    numFa.add(auxs);
                                    int auxs2 = Integer.valueOf(auxs);
                                    conectar.altaPersonaEquipo(auxs2, idEq);
                                }
                            } catch (Exception ex) {
                                Messagebox.show("Error interno, reinicie su sesión o consulte al desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);

                            }
                            if (test2 == true) {
                                Messagebox.show("Equipo creado exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }



                        if ("AltaElaApro".equals(tipof)) {
                            boolean test2 = false;

                            //Obtener Id Parte
                            int idP = conectar.traerIdParte(componentef);
                            //Obtener id Formato
                            int idF = conectar.traerIdFormato(b);
                            //Obtener id Persona Elaboración
                            int idPE = Integer.valueOf(conectar.obtenerIdPersona(c));
                            //Obtener id Persona Aprobación
                            int idPA = Integer.valueOf(conectar.obtenerIdPersona(d));
                            conectar.borrarFormatoParteAnt(idF, idP);

                            test2 = conectar.altaFormatoParte(idF, idP, idPE, idPA);

                            if (test2 == true) {
                                Messagebox.show("Relación Formato Parte añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }


                        if ("CambioParte".equals(tipof)) {
                            boolean test2 = false;



                            test2 = conectar.cambiarNumPart(a, b);

                            if (test2 == true) {
                                Messagebox.show("Cambio de número de parte exitoso!", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("ParteEquipo".equals(tipof)) {
                            boolean test2 = false;

                            //Obtener Id Parte
                            int idP = conectar.traerIdParte(componentef);
                            //Obtener id Formato (En este caso el formato es 3 porque este numero apunta
                            //al AMEF en la base de datos.

                            int idF = 3;
                            //Obtener id Equipo Elaboración
                            int idPE = Integer.valueOf(conectar.obtenerIdEquipo(b));
                            //Obtener id Persona Aprobación
                            conectar.borrarFormatoParteAnt(idF, idP);

                            test2 = conectar.altaFormatoParteEq(idF, idP, idPE);

                            if (test2 == true) {
                                Messagebox.show("Relación Formato Parte añadida", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }

                        if ("AltaMaterialSolo".equals(tipof)) {
                            boolean test2 = false;



                            test2 = conectar.altaMaterialSolo(a, b);

                            if (test2 == true) {
                                Messagebox.show("Alta de Material exitoso!", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaMaterialMeca".equals(tipof)) {
                            boolean test2 = false;



                            test2 = conectar.altaMaterialMeca(descripcionf, componentef, a, b, e);

                            if (test2 == true) {
                                Messagebox.show("Alta de Material exitoso!", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }
                        if ("AltaMaterialQuim".equals(tipof)) {
                            boolean test2 = false;



                            test2 = conectar.altaMaterialQuim(a, b, aa, bb, cc, dd, ee);

                            if (test2 == true) {
                                Messagebox.show("Alta de Material exitoso!", "Información", Messagebox.OK, Messagebox.INFORMATION);
                            }
                        }




                    } else {
                        windo.onClose();
                        Messagebox.show("Usuario y/o contraseña incorrectos, el procedimiento no puede continuar", "Error", Messagebox.OK, Messagebox.ERROR);
                    }
                }
            });
            windo.setClosable(true);
            windo.doModal();
        }
        return xd;

    }

    public boolean historialEspecial(String tipo, String componente, String razon, java.util.Date fecha, String descripcion, final int aa, final int bb, final int cc, final int dd, final int ee, final int ff, final int gg, final int hh, final int ii, final int jj, final int kk, final int ll, final String a, final String b, final String c, final String d, final String e, final String f, final String g, final String h, final String i, final String j, final String k, final Listbox leff) throws InterruptedException {
        final String tipof = tipo;

        final String componentef = componente;
        final String razonf = razon;
        final java.util.Date fechaf = fecha;
        final String descripcionf = descripcion;
        final Window win = (Window) Executions.createComponents(
                "/login.zul", null, null);



        Textbox nombr;
        Textbox passwor;


        win.getChildren().clear();

        windo.getChildren().clear();
        Grid gr = new Grid();
        gr.getChildren().clear();

        windo.setTitle("Verificación para Historial (Ver E)");
        win.appendChild(windo);

        windo.setWidth("400px");
        Columns colp = new Columns();
        colp.getChildren().clear();
        Column nombre = new Column();
        nombre.getChildren().clear();
        Column password = new Column();
        password.getChildren().clear();
        Rows rowss = new Rows();
        rowss.getChildren().clear();
        Row ro = new Row();
        rowss.appendChild(ro);
        colp.appendChild(nombre);
        colp.appendChild(password);
        gr.appendChild(colp);
        gr.appendChild(rowss);
        windo.appendChild(gr);
        nombre.appendChild(new Label("Nombre"));
        password.appendChild(new Label("Password"));

        ro.appendChild(nom);
        ro.appendChild(pss);

        nom.setWidth("180px");
        pss.setWidth("180px");
        pss.setType("password");

        Button btni = new Button("Verificar");
        windo.appendChild(btni);
        btni.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

            public void onEvent(Event event) throws Exception {

                String n = nom.getValue();
                String p = pss.getValue();
                conexion.ConnSql conectar = new ConnSql();
                xd = conectar.inicioLog(n, p);


                if (xd == true) {
                    int idu = conectar.obtenerIdUsuario(n);
                    windo.onClose();
                    conectar.historial(idu, componentef, razonf, fechaf);

                    if ("AltaMaterialQuiMec".equals(tipof)) {
                        boolean test2 = false;



                        test2 = conectar.altaMaterialQuiMec(a, b, ff, gg, hh, aa, bb, cc, dd, ee);

                        if (test2 == true) {

                            Messagebox.show("Alta de Material exitoso!", "Información", Messagebox.OK, Messagebox.INFORMATION);
                        }
                    }


                } else {
                    windo.onClose();
                    Messagebox.show("Usuario y/o contraseña incorrectos", "Error", Messagebox.OK, Messagebox.ERROR);
                }
            }
        });
        windo.setClosable(true);
        windo.doModal();

        return xd;

    }
}
