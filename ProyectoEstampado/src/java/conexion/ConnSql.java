/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

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
import dbo.ListaRegreso;
import dbo.NumeroParte;
import dbo.Persona;
import java.sql.*;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author Alejandro.Montes
 */
public class ConnSql {

    private Connection conn = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "proyectoest";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "estymex";
    public static void main(String[] args) {
        System.out.println("MySQL en consulta.");
    }

    public void conectar() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Disconnected from database");
        }
    }

    public boolean inicioLog(String nombre, String passw) throws SQLException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from usuario where nombre =? and pass = ?");
        consulta.setString(1, nombre);
        consulta.setString(2, passw);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombre");
            obt2 = rs.getString("pass");
            resp = true;
        }
        desconectar();
        consulta.close();
        return resp;
    }

    public ArrayReturn buscarAmef() throws SQLException {
        ArrayReturn busqueda = new ArrayReturn();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("SELECT * FROM   parte NATURAL JOIN amef");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            busqueda.getNumero().add(rs.getString("numeroParte"));
            busqueda.getNombre().add(rs.getString("nombreParte"));
            busqueda.getAmef().add(rs.getString("codeAmef"));
            busqueda.getId().add(rs.getString("idParte"));
        }
        desconectar();
        return busqueda;
    }

    public ArrayReturn traerConcidencias(List<String> coinc) throws SQLException {
        int n = 0;
        ArrayReturn coincidencias = new ArrayReturn();
        conectar();
        try {
            while (coinc.size() > n) {
                
                int aux = Integer.valueOf(coinc.get(n));
                PreparedStatement consulta = conn.prepareStatement("SELECT * FROM   parte NATURAL JOIN amef where idParte = " + aux);
                ResultSet rs = consulta.executeQuery();
                while (rs.next()) {
                    coincidencias.getNumero().add(rs.getString("numeroParte"));
                    coincidencias.getNombre().add(rs.getString("nombreParte"));
                    coincidencias.getAmef().add(rs.getString("codeAmef"));
                }
                
                n++;
            }
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        desconectar();
        return coincidencias;
    }

    public List procesosAmef(String a) throws SQLException {
        java.util.List info = new java.util.ArrayList();
        FasesReturn fases = new FasesReturn();
        conectar();
        PreparedStatement consulta = conn.prepareStatement(""
                + "Select faseParte.numeroFase, faseparte.idFases, faseParte.idParte, Parte.numeroParte, fases.nombreFase "
                + "from faseParte "
                + ""
                + "inner join Parte on faseparte.idParte = parte.idParte "
                + "inner join fases on fases.idFases = faseParte.idFases "
                + "where parte.numeroParte = ?");
        consulta.setString(1, a);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {

            info.add(new FasesReturn(rs.getString("numeroFase"), rs.getString("nombreFase")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List fallaParte(String a, String b) throws SQLException {
        java.util.List info = new java.util.ArrayList();
        FallasReturn fallas = new FallasReturn();
        int ba = traerIdParte(b);
        conectar();
        PreparedStatement consulta = conn.prepareStatement(""
                + "Select falla.idFalla, falla.idFases, modofalla.modo, "
                + "efectofalla.efecto, causafalla.falla, prevencionfalla.prevencion,"
                + "deteccionfalla.deteccion, accionfalla.accion, gradofalla.grado, "
                + "fallaparte.sev, fallaparte.ocu, fallaparte.det from "
                + "falla "
                + "inner join fases on fases.idFases = falla.idFases "
                + "inner join modofalla on modofalla.idModoFalla = falla.idModoFalla "
                + "inner join efectofalla on efectofalla.idEfectoFalla = falla.idEfectoFalla "
                + "inner join causafalla on causafalla.idCausaFalla = falla.idCausaFalla "
                + "inner join prevencionfalla on prevencionfalla.idPrevencionFalla = falla.idPrevencionFalla "
                + "inner join deteccionfalla on deteccionfalla.idDeteccionFalla = falla.idDeteccionFalla "
                + "inner join accionfalla on accionfalla.idAccionFalla = falla.idAccionFalla "
                + "inner join gradofalla on gradofalla.idGradoFalla = falla.idGradoFalla "
                + "inner join faseparte on faseparte.idFases = falla.idFases "
                + "inner join parte on parte.idParte = faseparte.idParte "
                + "inner join fallaparte on fallaparte.idFalla = falla.idFalla  "
                + "where parte.idParte = ? and faseparte.numeroFase = ? ");
        consulta.setInt(1, ba);
        consulta.setString(2, a);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new FallasReturn(rs.getString("modo"), rs.getString("efecto"), rs.getString("falla"), rs.getString("prevencion"), rs.getString("deteccion"), rs.getString("accion"), rs.getString("grado"), rs.getInt("sev"), rs.getInt("ocu"), rs.getInt("det")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public ArrayReturnHisto buscarHistorial() throws SQLException {
        ArrayReturnHisto busqueda = new ArrayReturnHisto();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from historial natural join usuario");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            busqueda.getUsuario().add(rs.getString("nombre"));
            busqueda.getRazon().add(rs.getString("razon"));
            busqueda.getFecha().add(rs.getString("fecha"));
            busqueda.getPieza().add(rs.getString("componente"));
        }
        desconectar();
        return busqueda;
    }

    public boolean buscarDuplicidadParte(String x) throws SQLException {
        String obt;
        boolean resp = true;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from parte where numeroParte = ?");
        consulta.setString(1, x);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("numeroParte");

            resp = false;
        }
       consulta.close();
        desconectar();
        
        return resp;
    }
    
    public boolean insertarNumeroParte(String nombre, String numero, int idNivIng, int idModelo) throws SQLException, InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into parte (idModelo, idNivelIng, nombreParte, numeroParte) values (?, ?, ?, ?)");
            insert.setInt(1, idModelo);
            insert.setInt(2, idNivIng);
            insert.setString(3, nombre);
            insert.setString(4, numero);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public void eliminarParte(String x) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from Parte where numeroParte = ?");
        insert.setString(1, x);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public List obteneraracteristicas(String numeroParte) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement insert = conn.prepareStatement("select * from procesosparte "
                    + "natural join parte "
                    + "natural join procesos "
                    + "where parte.numeroParte = ?");
            insert.setString(1, numeroParte);
            ResultSet rs = insert.executeQuery();
            while (rs.next()) {
//                regreso.add(new Caracteristicas(rs.getString("descripcion"), rs.getInt("numero"), rs.getString("grado"), rs.getString("observaciones"), rs.getString("tipo")));
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenercaracteristicas(String numeroParte) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement insert = conn.prepareStatement("select * from matrizcaracteristicas "
                    + "inner join parte on matrizcaracteristicas.idParte = parte.idParte "
                    + "inner join caracteristicas on caracteristicas.idMatrizCaracteristicas = matrizcaracteristicas.idMatrizCaracteristicas "
                    + "inner join caracteristicaprocesos on caracteristicaprocesos.idCaracteristicas = caracteristicas.idCaracteristicas "
                    + "inner join procesos on procesos.idProcesos = caracteristicaprocesos.idProcesos "
                    + "inner join procesosparte on procesosparte.idProcesos = procesos.idProcesos "
                    + "where numeroParte = ?");
            insert.setString(1, numeroParte);
            ResultSet rs = insert.executeQuery();
            while (rs.next()) {
//                regreso.add(new Caracteristicas(rs.getString("descripcion"), rs.getInt("numero"), rs.getString("grado"), rs.getString("observaciones"), rs.getString("tipo")));
                regreso.add(new Caracteristicas(rs.getString("descripcion"), rs.getInt("numero"), rs.getString("grado"), rs.getString("observaciones"), rs.getString("tipo")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public String obteneraracteristicas2(String cara) {
        String nombreR = null;
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from procesos where nombreProceso = ?");
            consulta.setString(1, cara);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                nombreR = rs.getString("descripcion");
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return nombreR;
    }

    public int traerIdParte(String parte) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from parte where numeroParte = ?");
        consulta.setString(1, parte);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idParte");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerIdCara(String cara, int x) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from caracteristicas where descripcion = ? and idMatrizCaracteristicas = ?");
        consulta.setString(1, cara);
        consulta.setInt(2, x);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idCaracteristicas");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void relacionCaraParte(int idParte, List<String> numCa) throws SQLException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        for (int f = 0; numCa.size() > f; f++) {
            PreparedStatement insert = conn.prepareStatement("Insert into caracteristicasparte (idCaracteristica, idParte) Values(?, ?)");
            insert.setInt(1, Integer.parseInt(numCa.get(f)));
            insert.setInt(2, idParte);
            insert.executeUpdate();
            insert.close();
        }
        desconectar();
    }

    public void eliminarCaracteristicasProcesos(String parte) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from caracteristicaprocesos where idMatrizCaracteristicas = ?");
        insert.setString(1, parte);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarCaracteristicas(String parte) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from caracteristicas where idMatrizCaracteristicas = ?");
        insert.setString(1, parte);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarMatrizCaracteristicas(String parte) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from matrizcaracteristicas where idMatrizCaracteristicas = ?");
        insert.setString(1, parte);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public List obtenerfases() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from procesos");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesRecAl() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Almacén y Entradas'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesInsp() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();

        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Inspecciones'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesTrans() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Transporte'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesPrepa() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Preparaciones'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesPrensas(int idParte) throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement(""
                    + "select * from procesosparte "
                    + "inner join procesos on procesosparte.idProcesos = procesos.idProcesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "inner join maquinaproceso on procesosparte.idProcesos = maquinaproceso.idProcesos "
                    + "inner join tipomaquina on maquinaproceso.idMaquina = tipomaquina.idTipoMaquina "
                    + "where idParte = ? "
                    + "and  "
                    + "( "
                    + "tipo = 'Blanking' "
                    + "or tipo = 'Tandem' "
                    + ")");
            consulta.setInt(1, idParte);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreMaquina")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesPrensasTF1500(int idParte) throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement(""
                    + "select * from procesosparte "
                    + "inner join procesos on procesosparte.idProcesos = procesos.idProcesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "inner join maquinaproceso on procesosparte.idProcesos = maquinaproceso.idProcesos "
                    + "inner join tipomaquina on maquinaproceso.idMaquina = tipomaquina.idTipoMaquina "
                    + "where idParte = ? "
                    + "and  "
                    + "( "
                    + "nombreMaquina = 'Transfer 1500' "
                    + ")");
            consulta.setInt(1, idParte);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreMaquina")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesPrensasTF500(int idParte) throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement(""
                    + "select * from procesosparte "
                    + "inner join procesos on procesosparte.idProcesos = procesos.idProcesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "inner join maquinaproceso on procesosparte.idProcesos = maquinaproceso.idProcesos "
                    + "inner join tipomaquina on maquinaproceso.idMaquina = tipomaquina.idTipoMaquina "
                    + "where idParte = ? "
                    + "and  "
                    + "( "
                    + "nombreMaquina = 'Transfer 500' "
                    + ")");
            consulta.setInt(1, idParte);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreMaquina")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesBlank() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Blanking'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesTan() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Tandem'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerfasesTran() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select * from procesos "
                    + "inner join procesostipo on procesos.idProcesos = procesostipo.idProcesos "
                    + "inner join tipoproceso on procesostipo.idTipoProceso = tipoproceso.idTipoProceso "
                    + "where tipo = 'Transfer'");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public String obtenerIdFase(String fase) throws SQLException {
        String obt = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from procesos where nombreProceso = ?");
        consulta.setString(1, fase);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = String.valueOf(rs.getInt("idProcesos"));
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void relacionFaseParte(int idParte, List<String> numFa) throws SQLException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        for (int f = 0; numFa.size() > f; f++) {
            PreparedStatement insert = conn.prepareStatement("Insert into procesosparte (idProcesos, idParte, numeroProceso) Values(?, ?, ?)");
            insert.setInt(1, Integer.parseInt(numFa.get(f)));
            insert.setInt(2, idParte);
            insert.setInt(3, f + 1);
            insert.executeUpdate();
            insert.close();
        }
        desconectar();
    }

    public void eliminarFases(int parte) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from procesosparte where idParte = ?");
        insert.setInt(1, parte);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void insertarAmef(int idParte, String amef) throws SQLException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        PreparedStatement insert = conn.prepareStatement("Insert into amef (idParte, idFormato,  codeAmef) Values(?, 3, ?)");
        insert.setInt(1, idParte);
        insert.setString(2, amef);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarAmef(int parte) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from amef where idParte = ?");
        insert.setInt(1, parte);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public List obteneraracteristicasFases(int id) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement(""
                    + "Select fases.idFases, fases.nombreFase, caracteristicas.numero, caracteristicas.descripcion "
                    + "from caracteristicas "
                    + "inner join caracteristifasfases on caracteristicas.idCaracteristica = caracteristifasfases.idCaracteristicas "
                    + "inner join fases on fases.idFases = caracteristifasfases.idFases "
                    + "inner join faseparte on fases.idFases = faseparte.idFases "
                    + "inner join parte on faseparte.idParte = parte.idParte "
                    + "where parte.idParte = ? "
                    + "order by idFases");
            consulta.setInt(1, id);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new CaraFases(rs.getString("nombreFase"), rs.getInt("numero"), rs.getString("descripcion")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerFasesFalla(int id) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement(""
                    + "Select fases.nombreFase, modofalla.modo, efectofalla.efecto, "
                    + "causafalla.falla "
                    + "from falla "
                    + "inner join fases on fases.idFases = falla.idFases "
                    + "inner join modofalla on modofalla.idModoFalla = falla.idModoFalla "
                    + "inner join efectofalla on efectofalla.idEfectoFalla = falla.idEfectoFalla "
                    + "inner join causafalla on causafalla.idCausaFalla = falla.idCausaFalla "
                    + "inner join prevencionfalla on prevencionfalla.idPrevencionFalla = falla.idPrevencionFalla "
                    + "inner join deteccionfalla on deteccionfalla.idDeteccionFalla = falla.idDeteccionFalla "
                    + "inner join accionfalla on accionfalla.idAccionFalla = falla.idAccionFalla "
                    + "inner join gradofalla on gradofalla.idGradoFalla = falla.idGradoFalla "
                    + "inner join faseparte on faseparte.idFases = falla.idFases "
                    + "inner join parte on parte.idParte = faseparte.idParte "
                    + "where parte.idParte = ?");
            consulta.setInt(1, id);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new FallaParte(rs.getString("nombreFase"), rs.getString("modo"), rs.getString("efecto"), rs.getString("falla")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public int traerTipo(String us) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from usuario where nombre = ?");
        consulta.setString(1, us);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idUsuarioTipo");
        }
        consulta.close();
        desconectar();
         return obt;
    }

    public boolean altaModelo(String modelo, String descripcion) throws SQLException, InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into Modelo (nombreModelo, descripcion) values (?, ?)");
            insert.setString(1, modelo);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Modelo no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadModelo(String modelo) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from modelo where nombreModelo = ?");
        consulta.setString(1, modelo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreModelo");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadTroquel(String troquel) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from troquel where nombreTroquel = ?");
        consulta.setString(1, troquel);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreTroquel");
            resp = true;
        }
        desconectar();
        return resp;
    }
    public void eliminarTroquel(String troquel) throws SQLException, InterruptedException {
                conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Delete from troquel where nombreTroquel = ?");
            insert.setString(1, troquel);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo eliminar el troquel", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public boolean altaTroquel(int idParte, String troquel, Double FR, Double LR, Double H, Double Ps, Double Pt, int PGol, String desc) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into Troquel (idparte, nombreTroquel, dFR, dLR, dH, pesoSup, pesoTotal, piezaGolpe, descripcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idParte);
            insert.setString(2, troquel);
            insert.setDouble(3, FR);
            insert.setDouble(4, LR);
            insert.setDouble(5, H);
            insert.setDouble(6, Ps);
            insert.setDouble(7, Pt);
            insert.setInt(8, PGol);
            insert.setString(9, desc);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Modelo no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadModo(String modo) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from modoFalla where modoFalla = ?");
        consulta.setString(1, modo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("modoFalla");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean altaModo(String modo, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into modofalla (modoFalla, descripcion) values (?, ?)");
            insert.setString(1, modo);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Modo no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadEfecto(String efecto) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from efectoFalla where efectoFalla = ?");
        consulta.setString(1, efecto);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("efectoFalla");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean altaEfecto(String efecto, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into efectofalla (efectoFalla, descripcion) values (?, ?)");
            insert.setString(1, efecto);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();

        } catch (Exception ex) {
            Messagebox.show("Efecto no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadCausa(String causa) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from causafalla where causaFalla = ?");
        consulta.setString(1, causa);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("causaFalla");
            resp = true;
        }
        desconectar();
        
        return resp;
    }

    public boolean altaCausa(String causa, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into causafalla (causaFalla, descripcion) values (?, ?)");
            insert.setString(1, causa);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Causa - Falla no insertada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadPrevencion(String prevencion) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from controlprevencion where controlPrevencion = ?");
        consulta.setString(1, prevencion);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("controlPrevencion");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadDeteccion(String deteccion) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from controldeteccion where controlDeteccion = ?");
        consulta.setString(1, deteccion);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("controlDeteccion");

            resp = true;
        }
        desconectar();
        return resp;
        
    }

    public boolean altaPrevencion(String prevencion, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into controlprevencion (controlPrevencion, descripcion) values (?, ?)");
            insert.setString(1, prevencion);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Control Prevención - Falla no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaDeteccion(String deteccion, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into controldeteccion (controlDeteccion, descripcion) values (?, ?)");
            insert.setString(1, deteccion);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Control Detección - Falla no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadGrado(String grado) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from gradofalla where gradoFalla = ?");
        consulta.setString(1, grado);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("gradoFalla");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean altaGrado(String grado, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into gradofalla (gradoFalla, descripcion) values (?, ?)");
            insert.setString(1, grado);
            insert.setString(2, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Grado - Falla no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadProceso(String proceso) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from procesos where nombreProceso = ?");
        consulta.setString(1, proceso);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreProceso");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public boolean altaProceso(String proceso, String procesoAbr, String descripcion) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into procesos (nombreProceso, nombreAbr, descripcion) values (?, ?, ?)");
            insert.setString(1, proceso);
            insert.setString(2, procesoAbr);
            insert.setString(3, descripcion);
            insert.executeUpdate();
            resp = true;
            insert.close();

        } catch (Exception ex) {
            Messagebox.show("Proceso no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadDepartamento(String departamento) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from departamento where nombreDepartamento = ?");
        consulta.setString(1, departamento);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreDepartamento");
            resp = true;
        }
       desconectar();
        return resp;
        
    }

    public boolean altaDepartamento(String departamento, String departamentoAbr) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into departamento (nombreDepartamento, nombreAbr) values (?, ?)");
            insert.setString(1, departamento);
            insert.setString(2, departamentoAbr);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Departamento no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadCliente(String cliente) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from clientes where nombreCliente = ?");
        consulta.setString(1, cliente);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreCliente");
            resp = true;
        }
         desconectar();   
        return resp;
    }

    public boolean altaCliente(String cliente, String direccion, String telefono) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into clientes (nombreCliente, telefono, ubicacion) values (?, ?, ?)");
            insert.setString(1, cliente);
            insert.setString(2, telefono);
            insert.setString(3, direccion);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Cliente no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaUsuario(int tUser, String usuario, String passus) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into usuario (idUsuarioTipo, nombre, pass) values (?, ?, ?)");
            insert.setInt(1, tUser);
            insert.setString(2, usuario);
            insert.setString(3, passus);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Usuario no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadUsuario(String usuario) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from usuario where nombre = ?");
        consulta.setString(1, usuario);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombre");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public List obtenerPartes() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("SELECT * FROM   parte ");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("numeroParte")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public boolean duplicidadCaracter(String desc, int idM) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from caracteristicas where descripcion = ? and idMatrizCaracteristicas = ?");
        consulta.setString(1, desc);
        consulta.setInt(2, idM);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("descripcion");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public int traerIdMatriz(int idP) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from matrizcaracteristicas where idParte = ?");
        consulta.setInt(1, idP);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idMatrizCaracteristicas");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean altaCaracteristica(String descr, int numca, String grado, String obs, String tipo, int idM) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into caracteristicas (descripcion, numero, grado, observaciones, tipo, idMatrizCaracteristicas) values (?, ?, ?, ?, ?, ?)");
            insert.setString(1, descr);
            insert.setInt(2, numca);
            insert.setString(3, grado);
            insert.setString(4, obs);
            insert.setString(5, tipo);
            insert.setInt(6, idM);
            insert.executeUpdate();
            resp = true;
            insert.close();

        } catch (Exception ex) {
            Messagebox.show("Característica no insertada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public int obtenerIdUsuario(String n) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from usuario where nombre = ?");
        consulta.setString(1, n);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idUsuario");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void historial(int idu, String comp, String razon, java.util.Date fecha) throws SQLException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        String dat = formatter.format(fecha);
        PreparedStatement insert = conn.prepareStatement("Insert into historial (idUsuario, componente, razon, fecha) values (?, ?, ?, ?)");
        insert.setInt(1, idu);
        insert.setString(2, comp);
        insert.setString(3, razon);
        insert.setString(4, dat);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public boolean altaCaracteristicaProceso(int auxs, int idCara, int idM) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into caracteristicaprocesos (idCaracteristicas, idProcesos, idMatrizCaracteristicas) values (?, ?, ?)");
            insert.setInt(1, idCara);
            insert.setInt(2, auxs);
            insert.setInt(3, idM);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Relación de Características procesos no anexada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public String obtenerNivIng(String seleccion) throws SQLException {
        String obt = "Error de Conexión";
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from parte "
                + "inner join niveling on parte.idNivelIng = niveling.idNivelIng "
                + "where numeroParte = ?");
        consulta.setString(1, seleccion);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nivelIngenieria");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean altaNivelIng(String nuevo) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into niveling (nivelIngenieria) values (?)");
            insert.setString(1, nuevo);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el nivel de Ing. ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public int obtenerIdNivIng(String nuevo) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from nivelIng where nivelIngenieria = ?");
        consulta.setString(1, nuevo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idNivelIng");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean cambioNivelIng(int IdNivIng, String Nparte) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("update parte Set parte.idNivelIng = ? where numeroParte = ? ");
            insert.setInt(1, IdNivIng);
            insert.setString(2, Nparte);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo cambiar el nivel de Ing. ERROR INTERNO, consulte al Desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);

        }
        desconectar();
        return resp;
    }

    public boolean duplicidadNivelIng(String nuevo) throws SQLException {
        String obt;
        boolean resp = true;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from niveling where nivelIngenieria = ?");
        consulta.setString(1, nuevo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nivelIngenieria");
            resp = false;
        }
        consulta.close();
        desconectar();
        
        
        return resp;
    }

    public List obtenerDepartamentos() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("SELECT * FROM departamento");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new Departamento(rs.getString("nombreDepartamento"), rs.getString("nombreAbr")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public boolean duplicidadPersona(String person) throws SQLException {
        String obt;
        boolean resp = true;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from persona where nombrePersona = ?");
        consulta.setString(1, person);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombrePersona");
            resp = false;
        }
        consulta.close();
        desconectar();
        
        return resp;
    }

    public int obtenerIdDepto(String depto) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from departamento where nombreDepartamento = ?");
        consulta.setString(1, depto);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idDepartamento");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean altaPersona(int idDep, String componentef, String descripcionf) throws SQLException, InterruptedException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into persona (idDepartamento, nombrePersona, nombreAbr) Values(?, ?, ?)");
            insert.setInt(1, idDep);
            insert.setString(2, componentef);
            insert.setString(3, descripcionf);
            insert.executeUpdate();
            resp = true;
            insert.close();
            desconectar();
        } catch (Exception ex) {
            Messagebox.show("No se pudo insertar la persona. ERROR INTERNO (Conexión), consulte al Desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        return resp;
    }

    public List obtenerPersonas() {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from persona");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Persona(rs.getInt("idPersona"), rs.getInt("idDepartamento"), rs.getString("nombrePersona"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;

        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public boolean duplicidadEquipo(String nombreEquipo) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from equipo where nombreEquipo = ?");
        consulta.setString(1, nombreEquipo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreEquipo");

            resp = true;
        }
        desconectar();
        
        return resp;
    }

    public boolean altaEquipo(String componentef, String descripcionf) throws SQLException, InterruptedException {
        String obt;
        String obt2;
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into equipo (nombreEquipo, descripcion) Values(?, ?)");
            insert.setString(1, componentef);
            insert.setString(2, descripcionf);
            insert.executeUpdate();
            resp = true;
            insert.close();
            desconectar();

        } catch (Exception ex) {
            Messagebox.show("No se pudo insertar el Equipo. ERROR INTERNO (Conexión), consulte al Desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        return resp;
    }

    public int obtenerIdEq(String componentef) throws SQLException {
        int reg = 0;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from equipo where nombreEquipo = ?");
        consulta.setString(1, componentef);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            reg = rs.getInt("idEquipo");

            resp = true;
        }
        return reg;
    }

    public String obtenerIdPersona(String Persona) throws SQLException {
        String obt = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from persona where nombrePersona = ?");
        consulta.setString(1, Persona);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = String.valueOf(rs.getInt("idPersona"));
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean altaPersonaEquipo(int auxs2, int idEq) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into personaequipo (idPersona, idEquipo) values (?, ?)");
            insert.setInt(1, auxs2);
            insert.setInt(2, idEq);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Relación de Persona - Equipo no anexada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public List obtenerFormatoParte(String parte) throws SQLException {
        java.util.List info = new java.util.ArrayList();
        String relleno1 = " ";
        String relleno2 = " ";
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from formato");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            //Esto con la idea de no imprimir el amef, ya que
            //este es elaborado por un equipo
            if (rs.getString("tipoFormato").equals("AMEF")) {
            } else {
                info.add(new FormatoParte(rs.getString("tipoFormato"), rs.getString("codigoRevision"), rs.getString("mapaProceso"), relleno1, relleno2));
            }
        }
        desconectar();
        consulta.close();
        return info;
    }

    public int traerIdFormato(String b) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from formato where tipoFormato = ?");
        consulta.setString(1, b);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idFormato");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean altaFormatoParte(int idF, int idP, int idPE, int idPA) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into formatoparte (idFormato, idParte, idElaboro, idAprobo) values (?, ?, ?, ?)");
            insert.setInt(1, idF);
            insert.setInt(2, idP);
            insert.setInt(3, idPE);
            insert.setInt(4, idPA);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Relación del formato no insertada, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();

        return resp;
    }

    public boolean altaFormatoParteEq(int idF, int idP, int idPE) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into formatoparte (idFormato, idParte, idEquipo) values (?, ?, ?)");
            insert.setInt(1, idF);
            insert.setInt(2, idP);
            insert.setInt(3, idPE);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Relación del formato no insertada, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public void borrarFormatoParteAnt(int idF, int idP) throws SQLException, InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Delete from formatoparte where idParte = ? and idFormato = ?");
            insert.setInt(1, idP);
            insert.setInt(2, idF);
            insert.executeUpdate();
            Messagebox.show("Se borro correctamente el formato - parte anterior", "Anterior", Messagebox.OK, Messagebox.INFORMATION);
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo eliminar el formato - parte anterior, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public String traerNombreParte(String num) throws SQLException {
        String obt = " ";
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from parte where numeroParte = ?");
        consulta.setString(1, num);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreParte");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean cambiarNumPart(String viejo, String nuevo) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("update parte Set parte.numeroParte = ? where numeroParte = ? ");
            insert.setString(1, nuevo);
            insert.setString(2, viejo);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo cambiar el número de parte. ERROR INTERNO, consulte al Desarrollador", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public List obtenerNivelesIng() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("SELECT * FROM  niveling");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("nivelIngenieria")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List obtenerEquipos() {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            int r1 = 0;
            int r2 = 0;
            String r3 = " ";
            PreparedStatement consulta = conn.prepareStatement("Select * from equipo");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Persona(r1, r2, rs.getString("nombreEquipo"), r3));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public String obtenerIdEquipo(String Eq) throws SQLException {
        String obt = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from equipo where nombreEquipo = ?");
        consulta.setString(1, Eq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = String.valueOf(rs.getInt("idEquipo"));
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public List obtenerPersonasEquipo(String equipo) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        int r1 = 0;
        int r2 = 0;
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("select persona.nombrePersona, equipo.nombreEquipo from personaequipo "
                    + "inner join persona on personaequipo.idPersona = persona.idPersona "
                    + "inner join equipo on personaequipo.idEquipo = equipo.idEquipo "
                    + "where equipo.nombreEquipo = ?");
            consulta.setString(1, equipo);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new Persona(r1, r2, rs.getString("nombrePersona"), rs.getString("nombreEquipo")));
            }
            desconectar();
            n++;

        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public boolean altaMaterialSolo(String a, String b) throws InterruptedException {
        boolean resp = false;

        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into material (proveedor, nombreMaterial) values (?, ?)");
            insert.setString(1, a);
            insert.setString(2, b);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Material no insertado, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaMaterialMeca(String descripcionf, String componentef, String a, String b, String e) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into material (proveedor, nombreMaterial, ts, yp, el) values (?, ?, ?, ?, ?)");
            insert.setString(1, descripcionf);
            insert.setString(2, componentef);
            insert.setInt(3, Integer.valueOf(a));
            insert.setInt(4, Integer.valueOf(b));
            insert.setInt(5, Integer.valueOf(e));
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Material no insertado, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaMaterialQuim(String a, String b, int aa, int bb, int cc, int dd, int ee) throws InterruptedException {
        boolean resp = false;

        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into material (proveedor, nombreMaterial, periodC, periodSi, periodMn, periodP, periodS) values (?, ?, ?, ?, ?, ?, ?)");
            insert.setString(1, a);
            insert.setString(2, b);
            insert.setInt(3, aa);
            insert.setInt(4, bb);
            insert.setInt(5, cc);
            insert.setInt(6, dd);
            insert.setInt(7, ee);
            insert.executeUpdate();
            resp = true;
            insert.close();

        } catch (Exception ex) {
            Messagebox.show("Material no insertado, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaMaterialQuiMec(String a, String b, int ff, int gg, int hh, int aa, int bb, int cc, int dd, int ee) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into material (proveedor, nombreMaterial, ts, yp, el, periodC, periodSi, periodMn, periodP, periodS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setString(1, a);
            insert.setString(2, b);
            insert.setInt(3, ff);
            insert.setInt(4, gg);
            insert.setInt(5, hh);
            insert.setInt(6, aa);
            insert.setInt(7, bb);
            insert.setInt(8, cc);
            insert.setInt(9, dd);
            insert.setInt(10, ee);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Material no insertado, ERROR INTERNO, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadMaterial(String material) throws SQLException {
        String obt;
        boolean resp = true;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where nombreMaterial = ?");
        consulta.setString(1, material);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreMaterial");
            resp = false;
            // Imprimir fila...
        }
       consulta.close();
        desconectar();
        
        return resp;
    }

    public List obtenerModelos() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("SELECT * FROM  modelo");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {

            info.add(new NumeroParte(rs.getString("nombreModelo")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public int obtenerIdModelo(String modelo) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from modelo where nombreModelo = ? ");
        consulta.setString(1, modelo);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idModelo");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public List obtenerProcesosParte(String parte) {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement insert = conn.prepareStatement("select * from procesosparte "
                    + "natural join parte "
                    + "natural join procesos "
                    + "where parte.numeroParte = ?");
            insert.setString(1, parte);
            ResultSet rs = insert.executeQuery();
            while (rs.next()) {
//                regreso.add(new Caracteristicas(rs.getString("descripcion"), rs.getInt("numero"), rs.getString("grado"), rs.getString("observaciones"), rs.getString("tipo")));
                regreso.add(new Fase(rs.getString("nombreProceso"), rs.getString("nombreAbr")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerTipoProceso() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipoProceso");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("tipo")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List obtenerTipoMaquina() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipoMaquina");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("nombreMaquina")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List obtenerTipoMaquinaBl() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipomaquina "
                + "where nombreMaquina = 'Blanking 200' or "
                + "nombreMaquina = 'Blanking 400' or "
                + "nombreMaquina = 'Blanking 800'");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("nombreMaquina")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List obtenerTipoMaquinaTd() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipomaquina "
                + "where nombreMaquina = 'Tandem 200 (Tipo A)' or "
                + "nombreMaquina = 'Tandem 200 (Tipo B)' or "
                + "nombreMaquina = 'Tandem 400' or "
                + "nombreMaquina = 'Tandem 800'");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("nombreMaquina")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public List obtenerTipoMaquinaTf() throws SQLException {
        java.util.List info = new java.util.ArrayList();
        NumeroParte parte = new NumeroParte();
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipomaquina "
                + "where nombreMaquina = 'Transfer 500' or "
                + "nombreMaquina = 'Transfer 1500'");
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            info.add(new NumeroParte(rs.getString("nombreMaquina")));
        }
        desconectar();
        consulta.close();
        return info;
    }

    public int obtenerIdTipoProceso(String b) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipoproceso where tipo = ?");
        consulta.setString(1, b);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTipoProceso");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerIdTipoMaquina(String b) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from tipomaquina where nombreMaquina = ?");
        consulta.setString(1, b);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTipoMaquina");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void altaTipoProceso(int idpro, int idtipopro) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into procesostipo (idProcesos, idTipoProceso) values (?, ?)");
            insert.setInt(1, idpro);
            insert.setInt(2, idtipopro);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Proceso no se pudo víncular con un tipo, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void altaProcesoMaquina(int idpro, int maquina) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into maquinaproceso (idProcesos, idMaquina) values (?, ?)");
            insert.setInt(1, idpro);
            insert.setInt(2, maquina);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Proceso no se pudo víncular con prensa, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public boolean insertarMatrizCara(int idP) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into matrizcaracteristicas (idParte, idFormato) values (?, 1)");
            insert.setInt(1, idP);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo anexar a la matriz de características, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadMatriz(String par) throws SQLException {
        String obt;
        int parte = traerIdParte(par);
        boolean resp = true;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from matrizcaracteristicas where idParte = ?");
        consulta.setInt(1, parte);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("idParte");
            resp = false;
        }
        desconectar();
        return resp;
    }

    public boolean buscarMaquinasProceso(String idFas) throws SQLException {
        String obt;
        boolean resp = false;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from maquinaproceso where idProcesos = ?");
        consulta.setInt(1, Integer.valueOf(idFas));
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("idProceso");
            resp = true;
        }
        desconectar();
        return resp;
    }

    public String traerModelo(String parte) throws SQLException {
        String obt = null;
        String dt = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from parte where numeroParte = ?");
        consulta.setString(1, parte);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("idModelo");
        }
        desconectar();
        consulta.close();
        dt = nombreModelo(obt);
        return dt;
    }

    public String nombreModelo(String obt) throws SQLException {
        String modelo = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from modelo where idModelo = ?");
        consulta.setString(1, obt);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            modelo = rs.getString("nombreModelo");
        }
        desconectar();
        consulta.close();
        return modelo;
    }

    public boolean altaRevisionF2(String aux1, java.util.Date aux2, String aux3, String ing, String asCal, String producc, String Parte) throws SQLException, InterruptedException {
        boolean resp = false;

        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        String dat = formatter.format(aux2);
        int idp = traerIdParte(Parte);
        int idRI = Integer.valueOf(obtenerIdPersona(ing));
        int idRC = Integer.valueOf(obtenerIdPersona(asCal));
        int idRP = Integer.valueOf(obtenerIdPersona(producc));
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into revision (idParte, rev, fecha, motivo, idRevIngenieria, idRevCalidad, idRevProduccion) values (?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idp);
            insert.setString(2, aux1);
            insert.setString(3, dat);
            insert.setString(4, aux3);
            insert.setInt(5, idRI);
            insert.setInt(6, idRC);
            insert.setInt(7, idRP);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Revisión no insertada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean altaRevisionF1(String aux1, java.util.Date aux2, String aux3, String ing, String asCal, String producc, String Parte) throws SQLException, InterruptedException {
        boolean resp = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        String dat = formatter.format(aux2);
        int idp = traerIdParte(Parte);
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into revision (idParte, rev, fecha, motivo) values (? , ? , ?, ?)");
            insert.setInt(1, idp);
            insert.setString(2, aux1);
            insert.setString(3, dat);
            insert.setString(4, aux3);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Control Prevención - Falla no insertado, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public boolean duplicidadRevision(String numPa, String aux1) throws SQLException {
        String obt;
        boolean resp = true;
        int idp = traerIdParte(numPa);
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from revision where idParte = ? and rev = ?");
        consulta.setInt(1, idp);
        consulta.setString(2, aux1);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("motivo");
            resp = false;
        }
       consulta.close();
        desconectar();
        
        return resp;
    }

    public void eliminarRevisiones(String numParte) throws SQLException {
        int idp = traerIdParte(numParte);
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from revision where idParte = ?");
        insert.setInt(1, idp);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public List obtenerClientes() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from clientes");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new ListaRegreso(rs.getString("nombreCliente")));
            }
            desconectar();
            n++;

        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public List obtenerMateriales() throws SQLException {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from material");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new ListaRegreso(rs.getString("nombreMaterial")));
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public int obtenerEspesorMat(String material) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where nombreMaterial = ? ");
        consulta.setString(1, material);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("espesor");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerAnchoMat(String material) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where nombreMaterial = ? ");
        consulta.setString(1, material);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("anchoRollo");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public String obtenerProvedorMat(String material) throws SQLException {
        String obt = null;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where nombreMaterial = ? ");
        consulta.setString(1, material);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("proveedor");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerIdClientes(String cliente) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("select * from clientes where nombreCliente = ?");
        consulta.setString(1, cliente);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idClientes");

        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean asignarCliente(int idCliente, int idP) throws InterruptedException {
        boolean resp = false;

        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into clienteParte (idParte, idClientes) values (?, ?)");
            insert.setInt(1, idP);
            insert.setInt(2, idCliente);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo conectar la parte con el cliente, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public int obtenerIdMaterial(String material) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where nombreMaterial = ?");
        consulta.setString(1, material);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idMaterial");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public boolean insertarEspecificación(int idP, int idM) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into especificacionLamina (idParte, idFormato, idMaterial) values (?, 8, ?)");
            insert.setInt(1, idP);
            insert.setInt(2, idM);
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la especificación de la lámina, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;
    }

    public int obtenerIdEspecificacion(int idP, int idM) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from especificacionlamina where idParte = ? and idMaterial = ?");
        consulta.setInt(1, idP);
        consulta.setInt(2, idM);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idEspecificacionLamina");
        }
        desconectar();
        consulta.close();
        return obt;



    }

    public boolean materialEsp(int idEsp, double pasAl, double pesoBl, double pesoParte, double AprovMat, double espesor, double anchoRollo, double aprovMatT, double pesoT) throws InterruptedException {

        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into materialespecificacion (idEspecificacionLamina, PasoAlimentacion, PesoBlanking, PesoParte, AprovMaterial, Espesor, AnchoRollo, AprovTotal, PesoTotal) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idEsp);
            insert.setDouble(2, pasAl);
            insert.setDouble(3, pesoBl);
            insert.setDouble(4, pesoParte);
            insert.setDouble(5, AprovMat);
            insert.setDouble(6, espesor);
            insert.setDouble(7, anchoRollo);
            insert.setDouble(8, aprovMatT);
            insert.setDouble(9, pesoT);
            insert.executeUpdate();
            resp = true;
            insert.close();
            
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la especificación del material, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        return resp;

    }

    public String obtenerUltimaRevision(int idp) {
        String rev = null;
        String fecha = null;
        String res = null;
        int n = 0;
        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM revision "
                    + "where idParte = ? "
                    + "ORDER BY idRevision DESC LIMIT 1");
            consulta.setInt(1, idp);
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                rev = rs.getString("rev");
                fecha = rs.getString("fecha");
                res = rev + " " + fecha;
            }
            desconectar();
            n++;
        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return res;
    }

    public void eliminarClientesParte(int idp) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from clienteParte where idParte = ?");
        insert.setInt(1, idp);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarEspLam(int idp) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from especificacionlamina where idParte = ?");
        insert.setInt(1, idp);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public int traerIdEspLam(int idp) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from especificacionlamina where idParte = ?");
        consulta.setInt(1, idp);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idEspecificacionLamina");

        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void eliminarMaterialEsp(int idEspLam) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from materialespecificacion where idEspecificacionLamina = ?");
        insert.setInt(1, idEspLam);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public double obtenerPesoBlanking(int idEspLam) throws SQLException {
        double obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from materialespecificacion where idEspecificacionLamina = ?");
        consulta.setInt(1, idEspLam);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getDouble("PesoBlanking");

        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerMaterial(int idp) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from especificacionlamina where idParte = ?");
        consulta.setInt(1, idp);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idMaterial");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public String obtenerNombreMat(int mat) throws SQLException {
        String obt = " ";
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from material where idMaterial = ?");
        consulta.setInt(1, mat);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreMaterial");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerAnchoMat(int mat) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from materialespecificacion where idEspecificacionLamina = ?");
        consulta.setInt(1, mat);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("AnchoRollo");

        }
        desconectar();
        consulta.close();

        return obt;
    }

    public double obtenerPasoAlim(int idEspLam) throws SQLException {
        double obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from materialespecificacion where idEspecificacionLamina = ?");
        consulta.setInt(1, idEspLam);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getDouble("PasoAlimentacion");

        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int obtenerEspesor(int idEspLam) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from materialespecificacion where idEspecificacionLamina = ?");
        consulta.setInt(1, idEspLam);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("Espesor");

        }
        desconectar();
        consulta.close();
        return obt;
    }

    public List obtenerTroqueles() {
        int n = 0;
        java.util.List regreso = new java.util.ArrayList();

        try {
            conectar();
            PreparedStatement consulta = conn.prepareStatement("Select * from troquel");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                regreso.add(new ListaRegreso(rs.getString("nombreTroquel")));
            }
            desconectar();
            n++;

        } catch (Exception ex) {
            System.out.println("Error Conexion");
        }
        return regreso;
    }

    public int obtenerIdProceso(String proceso) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from procesos where nombreProceso = ?");
        consulta.setString(1, proceso);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idProcesos");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public String ObtenerMaqProceso(int idPro) throws SQLException {

        String obt = null;
        int obd = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from maquinaProceso where idProcesos = ?");
        consulta.setInt(1, idPro);


        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obd = rs.getInt("idMaquina");

        }
        desconectar();

        obt = traerNombreMaquina(obd);
        consulta.close();

        return obt;

    }

    public String traerNombreMaquina(int obd) throws SQLException {
        String obt = null;
        conectar();

        PreparedStatement consulta = conn.prepareStatement("Select * from tipomaquina where idTipoMaquina = ?");
        consulta.setInt(1, obd);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("nombreMaquina");
        }
        return obt;
    }

    public double obtenerPesoParte(int idEspLam) throws SQLException {
        double obt = 0;
        conectar();

        PreparedStatement consulta = conn.prepareStatement("Select * from materialespecificacion where idEspecificacionLamina = ?");
        consulta.setInt(1, idEspLam);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getDouble("PesoParte");
        }
        desconectar();
        consulta.close();

        return obt;
    }

    public boolean duplicidadParteHC(int idp) throws SQLException {
        String obt;

        boolean resp = true;
        conectar();

        PreparedStatement consulta = conn.prepareStatement("Select * from hojacondiciones where idParte = ?");
        consulta.setInt(1, idp);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getString("idHojaCondiciones");
            resp = false;
            // Imprimir fila...
        }
                consulta.close();

        desconectar();
        return resp;
    }

    public void crearHojaCond(int idp) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into hojacondiciones (idParte) values (?)");
            insert.setInt(1, idp);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Hoja de Condiciones no insertada, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public int traerIdHojaCond(int idp) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from hojacondiciones where idParte = ?");
        consulta.setInt(1, idp);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idHojaCondiciones");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int traerIdTroquel(String troquel) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from troquel where nombreTroquel = ?");
        consulta.setString(1, troquel);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTroquel");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public void altaBlanking200(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double RPM, double alturaTroq, String liberac, String presionLibEn, String presionLibSal, String SujecA, String SujeAb, String observa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into bl200 (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, RPM, alturaTroquel, liberacion, presionNiveladorEnt, "
                    + "presionNiveladorSal, sujecionTroquelSup, sujecionTroquelInf, "
                    + "Observaciones) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, RPM);
            insert.setDouble(10, alturaTroq);
            insert.setString(11, liberac);
            insert.setString(12, presionLibEn);
            insert.setString(13, presionLibSal);
            insert.setString(14, SujecA);
            insert.setString(15, SujeAb);
            insert.setString(16, observa);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void eliminarDuplicidadBlanking200(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl200 where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();

    }

    public void altaBlanking400(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double RPM, String piLotRol, String anguloAl, double alturaTroq, String presionLibEn, String presionLibSal, String SujecA, String SujeAb, String observa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into bl400 (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, RPM, alturaTroquel, angAlim, pilotRoll, presionNiveladorEnt, "
                    + "presionNiveladorSal, sujecionTroquelSup, sujecionTroquelInf, "
                    + "Observaciones) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, RPM);
            insert.setDouble(10, alturaTroq);
            insert.setString(11, anguloAl);
            insert.setString(12, piLotRol);
            insert.setString(13, presionLibEn);
            insert.setString(14, presionLibSal);
            insert.setString(15, SujecA);
            insert.setString(16, SujeAb);
            insert.setString(17, observa);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void eliminarDuplicidadBlanking400(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl400 where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarDuplicidadBlanking800(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl800 where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void altaBlanking800(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double RPM, String piLotRol, String anguloAl, String liberac, double alturaTroq, String presionLibEn, String presionLibSal, String SujecA, String SujeAb, String alineacion, String speedSet, String observa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into bl800 (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, RPM, alturaTroquel, angAlim, pilotRoll, presionNiveladorEnt, "
                    + "presionNiveladorSal, sujecionTroquelSup, sujecionTroquelInf, speedSetting, alineacion, liberacion, "
                    + "Observaciones) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, RPM);
            insert.setDouble(10, alturaTroq);
            insert.setString(11, anguloAl);
            insert.setString(12, piLotRol);
            insert.setString(13, presionLibEn);
            insert.setString(14, presionLibSal);
            insert.setString(15, SujecA);
            insert.setString(16, SujeAb);
            insert.setString(17, speedSet);
            insert.setString(18, alineacion);
            insert.setString(19, liberac);
            insert.setString(20, observa);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void altaTandem200A(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double alturaTroq, String presionDeColchon, String AceiteEmbutido, String SujecA, String SujeAb, String longitudPerno, String PersonalEstam, String observa, String prensa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into td200a (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, alturaTroquel, presionColchon, aceiteEmbutido, sujecionTroqSup, "
                    + "sujecionTroquInf, longPerno, personalEstampado, observaciones, prensa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, alturaTroq);
            insert.setString(10, presionDeColchon);
            insert.setString(11, AceiteEmbutido);
            insert.setString(12, SujecA);
            insert.setString(13, SujeAb);
            insert.setString(14, longitudPerno);
            insert.setString(15, PersonalEstam);
            insert.setString(16, observa);
            insert.setString(17, prensa);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void eliminarDuplicidadTD200A(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200a where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();

    }

    public void altaTandem200B(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double alturaTroq, String presionDeColchon, String AceiteEmbutido, String SujecA, String SujeAb, String longitudPerno, String PersonalEstam, String observa, String prensa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into td200b (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, alturaTroquel, presionColchon, aceiteEmbutido, sujecionTroqSup, "
                    + "sujecionTroquInf, longPerno, personalEstampado, observaciones, prensa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, alturaTroq);
            insert.setString(10, presionDeColchon);
            insert.setString(11, AceiteEmbutido);
            insert.setString(12, SujecA);
            insert.setString(13, SujeAb);
            insert.setString(14, longitudPerno);
            insert.setString(15, PersonalEstam);
            insert.setString(16, observa);
            insert.setString(17, prensa);
            insert.executeUpdate();
            insert.close();

        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void eliminarDuplicidadTD200B(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200b where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarHojaCond(int idp) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from hojacondiciones where idParte = ?");
        insert.setInt(1, idp);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarTodasPrensas(int idp) throws SQLException {
        int idHC = traerIdHojaCond(idp);
        borrarBl200(idHC);
        borrarBl400(idHC);
        borrarBl800(idHC);
        borrarTd200A(idHC);
        borrarTd200B(idHC);
        borrarTd400800(idHC);
    }

    private void borrarBl200(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl200 where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void borrarBl400(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl400 where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void borrarBl800(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl800 where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void borrarTd200A(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200a where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void borrarTd200B(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200b where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void borrarTd400800(int idHC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td400800 where idHojaCondiciones = ?");
        insert.setInt(1, idHC);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void altaTandem400800(int numeroMaq, int numeroFormato, int idp, int idHC, int idTroq, int idDepa, int sec, int idMat, double presionB, double alturaTroq, String presionDeColchon, String AceiteEmbutido, String SujecA, String SujeAb, String longitudPerno, String PersonalEstam, String observa, String prensa) throws InterruptedException {
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into td400800 (idMaquina, "
                    + "idHojaCondiciones, idTroquel, idSecuencia, idFormato, idDepartamento, idMaterial,"
                    + " presionBalanza, alturaTroquel, presionColchon, aceiteEmbutido, sujecionTroqSup, "
                    + "sujecionTroquInf, longPerno, personalEstampado, observaciones, prensa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, numeroMaq);
            insert.setInt(2, idHC);
            insert.setInt(3, idTroq);
            insert.setInt(4, sec);
            insert.setInt(5, numeroFormato);
            insert.setInt(6, idDepa);
            insert.setInt(7, idMat);
            insert.setDouble(8, presionB);
            insert.setDouble(9, alturaTroq);
            insert.setString(10, presionDeColchon);
            insert.setString(11, AceiteEmbutido);
            insert.setString(12, SujecA);
            insert.setString(13, SujeAb);
            insert.setString(14, longitudPerno);
            insert.setString(15, PersonalEstam);
            insert.setString(16, observa);
            insert.setString(17, prensa);
            insert.executeUpdate();
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
    }

    public void eliminarDuplicidadTD400800(int idHC, int sec) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td400800 where idHojaCondiciones = ? and idSecuencia = ?");
        insert.setInt(1, idHC);
        insert.setInt(2, sec);
        insert.executeUpdate();
        insert.close();
        desconectar();

    }

    public void eliminarBl200Tr(int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl200 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    public void eliminarBl400Tr(int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl400 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarBl800Tr(int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from bl800 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    
    public void eliminarTD200A(int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200a where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    public void eliminarTD200B(int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td200b where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    public void eliminarTD400800 (int dsds) throws SQLException {
       conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from td400800 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    
    public void registroSecu(int itemCount, int idparte) throws InterruptedException {
            
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into SecuAux (idParte, secuencia) values (?, ?)");
            insert.setInt(1, idparte);
            insert.setInt(2, itemCount);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("Error al establecer secuencia", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        
        
    }

    public void eliminarSecuencia(int idp) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from SecuAux where idParte = ? ");
        insert.setInt(1, idp);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public int obtenerSigSecuencia(int idp) throws SQLException {
        int obt = 0;
        conectar();
        
        PreparedStatement consulta = conn.prepareStatement("Select * from secuaux where idParte = ?");
        consulta.setInt(1, idp);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("secuencia");
        }
        desconectar();
        
        obt++;
        
        consulta.close();
        return obt;
        
        
    }

   
    public void altaTF1(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d1 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF1, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        
        
    }
    
    public void altaTF2(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d2 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF2, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        
        
    }
    
    public void altaTF3(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d3 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF3, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    
    public void altaTF4(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d4 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF4, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    
    public void altaTF5(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d5 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF5, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    public void altaTF6(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d6 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF6, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    public void altaTF7(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500d7 (idTroquel, idSecuencia, longPerno, presCilindroNitrogeno, sujecionTroqSup, sujecionTroqInf) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idTroq);
            insert.setInt(2, secuencia);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionSup);
            insert.setString(6, sujecionInf);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF7, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    
    
     public void altaTF15001(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d1 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF1, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        
        
    }
    
    public void altaTF15002(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
       conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d2 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF2, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
        
        
    }
    
    public void altaTF15003(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
       conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d3 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF3, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    
    public void altaTF15004(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
       conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d4 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF4, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    
    public void altaTF15005(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
       conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d5 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
           
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF5, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
    public void altaTF15006(int idTroq, int secuencia, String longPerno, String presCili, String sujecionSup, String sujecionInf) throws InterruptedException {
        
       conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500d6 (idSecuencia, idTroquel, longPerno, presCn, sujecionTroqInf, sujecionTroqSup) values (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, secuencia);
            insert.setInt(2, idTroq);
            insert.setString(3, longPerno);
            insert.setString(4, presCili);
            insert.setString(5, sujecionInf);
            insert.setString(6, sujecionSup);
            insert.executeUpdate();
            
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear TF6, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
               
    }
  
    

    public int traerIdTF1(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d1 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D1");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF2(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d2 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D2");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF3(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d3 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D3");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF4(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d4 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D4");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF5(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d5 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D5");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF6(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d6 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D6");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF7(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf500d7 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF500D7");
        }
        desconectar();
        consulta.close();
        return obt;
    }

    public int traerIdTF15001(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d1 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D1");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF15002(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d2 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D2");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF15003(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d3 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D3");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF15004(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d4 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D4");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF15005(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d5 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D5");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    public int traerIdTF15006(int idTroq) throws SQLException {
        int obt = 0;
        conectar();
        PreparedStatement consulta = conn.prepareStatement("Select * from tf1500d6 where idTroquel = ?");
        consulta.setInt(1, idTroq);
        ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            obt = rs.getInt("idTF1500D6");
        }
        desconectar();
        consulta.close();
        return obt;
    }
    
    public void eliminarTodoTF500(int dsds) throws SQLException {
        eliminarTF1(dsds);
        eliminarTF2(dsds);
        eliminarTF3(dsds);
        eliminarTF4(dsds);
        eliminarTF5(dsds);
        eliminarTF6(dsds);
        eliminarTF7(dsds);
        
    }

    private void eliminarTF1(int dsds) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d1 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF2(int dsds) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d2 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();    }

    private void eliminarTF3(int dsds) throws SQLException {
     conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d3 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF4(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d4 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF5(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d5 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF6(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d6 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF7(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500d7 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }


    public void altaTF500(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idTF5, int idTF6, int idTF7, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
      boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idTF500D3, idTF500D4, idTF500D5, idTF500D6, idTF500D7, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idTF3);
            insert.setInt(6, idTF4);
            insert.setInt(7, idTF5);
            insert.setInt(8, idTF6);
            insert.setInt(9, idTF7);
            insert.setInt(10, idDepa);
            insert.setInt(11, idFormato);
            insert.setString(12, aceiteEmb);
            insert.setString(13, frecuenciaRoc);
            insert.setString(14, distanciaAbra);
            insert.setDouble(15, carreraAlim);
            insert.setDouble(16, carreraAlza);
            insert.setDouble(17, carreraDedos);
            insert.setString(18, rack);
            insert.setDouble(19, cantidad);
            insert.setString(20, deteccionDb);
            insert.setString(21, carreraBandaMag);
            insert.setDouble(22, alturaTroq);
            insert.setInt(23, rpm);
            insert.setString(24, presionSobreC);
            insert.setDouble(25, presionBalanza);
            insert.setString(26, aut1on);
            insert.setString(27, aut1off);
            insert.setString(28, aut2on);
            insert.setString(29, aut2off);
            insert.setString(30, aut3on);
            insert.setString(31, aut3off);
            insert.setString(32, aut4on);
            insert.setString(33, aut4off);
            
           
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
   
    }

    public void altaTF500mod1(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idTF5, int idTF6, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
  boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idTF500D3, idTF500D4, idTF500D5, idTF500D6, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idTF3);
            insert.setInt(6, idTF4);
            insert.setInt(7, idTF5);
            insert.setInt(8, idTF6);
            insert.setInt(9, idDepa);
            insert.setInt(10, idFormato);
            insert.setString(11, aceiteEmb);
            insert.setString(12, frecuenciaRoc);
            insert.setString(13, distanciaAbra);
            insert.setDouble(14, carreraAlim);
            insert.setDouble(15, carreraAlza);
            insert.setDouble(16, carreraDedos);
            insert.setString(17, rack);
            insert.setDouble(18, cantidad);
            insert.setString(19, deteccionDb);
            insert.setString(20, carreraBandaMag);
            insert.setDouble(21, alturaTroq);
            insert.setInt(22, rpm);
            insert.setString(23, presionSobreC);
            insert.setDouble(24, presionBalanza);
            insert.setString(25, aut1on);
            insert.setString(26, aut1off);
            insert.setString(27, aut2on);
            insert.setString(28, aut2off);
            insert.setString(29, aut3on);
            insert.setString(30, aut3off);
            insert.setString(31, aut4on);
            insert.setString(32, aut4off);
            
           
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       }

    public void altaTF500mod2(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idTF5, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idTF500D3, idTF500D4, idTF500D5, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idTF3);
            insert.setInt(6, idTF4);
            insert.setInt(7, idTF5);
            insert.setInt(8, idDepa);
            insert.setInt(9, idFormato);
            insert.setString(10, aceiteEmb);
            insert.setString(11, frecuenciaRoc);
            insert.setString(12, distanciaAbra);
            insert.setDouble(13, carreraAlim);
            insert.setDouble(14, carreraAlza);
            insert.setDouble(15, carreraDedos);
            insert.setString(16, rack);
            insert.setDouble(17, cantidad);
            insert.setString(18, deteccionDb);
            insert.setString(19, carreraBandaMag);
            insert.setDouble(20, alturaTroq);
            insert.setInt(21, rpm);
            insert.setString(22, presionSobreC);
            insert.setDouble(23, presionBalanza);
            insert.setString(24, aut1on);
            insert.setString(25, aut1off);
            insert.setString(26, aut2on);
            insert.setString(27, aut2off);
            insert.setString(28, aut3on);
            insert.setString(29, aut3off);
            insert.setString(30, aut4on);
            insert.setString(31, aut4off);
            
           
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();    }

    public void altaTF500mod3(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4,  int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idTF500D3, idTF500D4, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idTF3);
            insert.setInt(6, idTF4);
            insert.setInt(7, idDepa);
            insert.setInt(8, idFormato);
            insert.setString(9, aceiteEmb);
            insert.setString(10, frecuenciaRoc);
            insert.setString(11, distanciaAbra);
            insert.setDouble(12, carreraAlim);
            insert.setDouble(13, carreraAlza);
            insert.setDouble(14, carreraDedos);
            insert.setString(15, rack);
            insert.setDouble(16, cantidad);
            insert.setString(17, deteccionDb);
            insert.setString(18, carreraBandaMag);
            insert.setDouble(19, alturaTroq);
            insert.setInt(20, rpm);
            insert.setString(21, presionSobreC);
            insert.setDouble(22, presionBalanza);
            insert.setString(23, aut1on);
            insert.setString(24, aut1off);
            insert.setString(25, aut2on);
            insert.setString(26, aut2off);
            insert.setString(27, aut3on);
            insert.setString(28, aut3off);
            insert.setString(29, aut4on);
            insert.setString(30, aut4off);
            
           
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();    }

    public void altaTF500mod4(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3,  int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idTF500D3, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idTF3);
            insert.setInt(6, idDepa);
            insert.setInt(7, idFormato);
            insert.setString(8, aceiteEmb);
            insert.setString(9, frecuenciaRoc);
            insert.setString(10, distanciaAbra);
            insert.setDouble(11, carreraAlim);
            insert.setDouble(12, carreraAlza);
            insert.setDouble(13, carreraDedos);
            insert.setString(14, rack);
            insert.setDouble(15, cantidad);
            insert.setString(16, deteccionDb);
            insert.setString(17, carreraBandaMag);
            insert.setDouble(18, alturaTroq);
            insert.setInt(19, rpm);
            insert.setString(20, presionSobreC);
            insert.setDouble(21, presionBalanza);
            insert.setString(22, aut1on);
            insert.setString(23, aut1off);
            insert.setString(24, aut2on);
            insert.setString(25, aut2off);
            insert.setString(26, aut3on);
            insert.setString(27, aut3off);
            insert.setString(28, aut4on);
            insert.setString(29, aut4off);
            
           
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar(); 
    }

    public void altaTF500mod5(int idMaq, int idHojaC, int idTF1, int idTF2, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idTF500D2, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idTF2);
            insert.setInt(5, idDepa);
            insert.setInt(6, idFormato);
            insert.setString(7, aceiteEmb);
            insert.setString(8, frecuenciaRoc);
            insert.setString(9, distanciaAbra);
            insert.setDouble(10, carreraAlim);
            insert.setDouble(11, carreraAlza);
            insert.setDouble(12, carreraDedos);
            insert.setString(13, rack);
            insert.setDouble(14, cantidad);
            insert.setString(15, deteccionDb);
            insert.setString(16, carreraBandaMag);
            insert.setDouble(17, alturaTroq);
            insert.setInt(18, rpm);
            insert.setString(19, presionSobreC);
            insert.setDouble(20, presionBalanza);
            insert.setString(21, aut1on);
            insert.setString(22, aut1off);
            insert.setString(23, aut2on);
            insert.setString(24, aut2off);
            insert.setString(25, aut3on);
            insert.setString(26, aut3off);
            insert.setString(27, aut4on);
            insert.setString(28, aut4off);
                     
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar(); 
    }

    public void altaTF500mod6(int idMaq, int idHojaC, int idTF1, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, String distanciaAbra, double carreraAlim, double carreraAlza, double carreraDedos, String rack, double cantidad, String deteccionDb, String carreraBandaMag, double alturaTroq, int rpm, String presionSobreC, double presionBalanza, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf500 (idMaquina, idHojaCondiciones, idTF500D1, idDepartamento, idFormato, aceiteEmbutido, frecuenciaRociado, distanciaAbrazadera, carreraAlimentacion, carreraAlza, carreraDedos, rack, cantidad, deteccionDb, carreraBandaMag, alturaTroquel, rpm, presionSobreCarga, presionBalanza, aut1on, aut1off, aut2on, aut2off, aut3on, aut3off, aut4on, aut4off) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idMaq);
            insert.setInt(2, idHojaC);
            insert.setInt(3, idTF1);
            insert.setInt(4, idDepa);
            insert.setInt(5, idFormato);
            insert.setString(6, aceiteEmb);
            insert.setString(7, frecuenciaRoc);
            insert.setString(8, distanciaAbra);
            insert.setDouble(9, carreraAlim);
            insert.setDouble(10, carreraAlza);
            insert.setDouble(11, carreraDedos);
            insert.setString(12, rack);
            insert.setDouble(13, cantidad);
            insert.setString(14, deteccionDb);
            insert.setString(15, carreraBandaMag);
            insert.setDouble(16, alturaTroq);
            insert.setInt(17, rpm);
            insert.setString(18, presionSobreC);
            insert.setDouble(19, presionBalanza);
            insert.setString(20, aut1on);
            insert.setString(21, aut1off);
            insert.setString(22, aut2on);
            insert.setString(23, aut2off);
            insert.setString(24, aut3on);
            insert.setString(25, aut3off);
            insert.setString(26, aut4on);
            insert.setString(27, aut4off);
                     
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear el TF500, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar(); 
    }

    public void eliminarTF500(int idHojaC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf500 where idHojaCondiciones = ? ");
        insert.setInt(1, idHojaC);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    
    public void eliminarTF1500(int idHojaC) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500 where idHojaCondiciones = ? ");
        insert.setInt(1, idHojaC);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }
    public void altaTF1500mod1(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idTF5, int idTF6, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idTF1500D2, idTF1500D3, idTF1500D4, idTF1500D5, idTF1500D6, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idTF2);
            insert.setInt(6, idTF3);
            insert.setInt(7, idTF4);
            insert.setInt(8, idTF5);
            insert.setInt(9, idTF6);
            insert.setInt(10, idFormato);
            insert.setDouble(11, dimenBarra);
            insert.setDouble(12, carreraAlim);
            insert.setDouble(13, carreraElevaci);
            insert.setDouble(14, alturaTroq);
            insert.setDouble(15, carreraSuje);
            insert.setDouble(16, presionBalanza);
            insert.setInt(17, rpm);
            insert.setInt(18, numeroMatriz);
            insert.setString(19, codigoSegunPc);
            insert.setString(20, rack);
            insert.setDouble(21, cantidad);
            insert.setString(22, deteccionDb);
            insert.setString(23, deteccionDb2);
            insert.setString(24, aceiteEmb);        
            insert.setString(25, frecuenciaRoc);
            insert.setString(26, CopasSucc);
            insert.setString(27, aut1off);
            insert.setString(28, aut1on);
            insert.setString(29, aut2off);
            insert.setString(30, aut2on);
            insert.setString(31, aut3off);
            insert.setString(32, aut3on);
            insert.setString(33, aut4off);
            insert.setString(34, aut4on);
            insert.setString(35, aut5off);
            insert.setString(36, aut5on);
            insert.setString(37, aut6off);
            insert.setString(38, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
        
    }

    public void altaTF1500mod2(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idTF5, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
      boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idTF1500D2, idTF1500D3, idTF1500D4, idTF1500D5, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idTF2);
            insert.setInt(6, idTF3);
            insert.setInt(7, idTF4);
            insert.setInt(8, idTF5);
            insert.setInt(9, idFormato);
            insert.setDouble(10, dimenBarra);
            insert.setDouble(11, carreraAlim);
            insert.setDouble(12, carreraElevaci);
            insert.setDouble(13, alturaTroq);
            insert.setDouble(14, carreraSuje);
            insert.setDouble(15, presionBalanza);
            insert.setInt(16, rpm);
            insert.setInt(17, numeroMatriz);
            insert.setString(18, codigoSegunPc);
            insert.setString(19, rack);
            insert.setDouble(20, cantidad);
            insert.setString(21, deteccionDb);
            insert.setString(22, deteccionDb2);
            insert.setString(23, aceiteEmb);        
            insert.setString(24, frecuenciaRoc);
            insert.setString(25, CopasSucc);
            insert.setString(26, aut1off);
            insert.setString(27, aut1on);
            insert.setString(28, aut2off);
            insert.setString(29, aut2on);
            insert.setString(30, aut3off);
            insert.setString(31, aut3on);
            insert.setString(32, aut4off);
            insert.setString(33, aut4on);
            insert.setString(34, aut5off);
            insert.setString(35, aut5on);
            insert.setString(36, aut6off);
            insert.setString(37, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
        
    }

    public void altaTF1500mod3(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idTF4, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idTF1500D2, idTF1500D3, idTF1500D4, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idTF2);
            insert.setInt(6, idTF3);
            insert.setInt(7, idTF4);
            insert.setInt(8, idFormato);
            insert.setDouble(9, dimenBarra);
            insert.setDouble(10, carreraAlim);
            insert.setDouble(11, carreraElevaci);
            insert.setDouble(12, alturaTroq);
            insert.setDouble(13, carreraSuje);
            insert.setDouble(14, presionBalanza);
            insert.setInt(15, rpm);
            insert.setInt(16, numeroMatriz);
            insert.setString(17, codigoSegunPc);
            insert.setString(18, rack);
            insert.setDouble(19, cantidad);
            insert.setString(20, deteccionDb);
            insert.setString(21, deteccionDb2);
            insert.setString(22, aceiteEmb);        
            insert.setString(23, frecuenciaRoc);
            insert.setString(24, CopasSucc);
            insert.setString(25, aut1off);
            insert.setString(26, aut1on);
            insert.setString(27, aut2off);
            insert.setString(28, aut2on);
            insert.setString(29, aut3off);
            insert.setString(30, aut3on);
            insert.setString(31, aut4off);
            insert.setString(32, aut4on);
            insert.setString(33, aut5off);
            insert.setString(34, aut5on);
            insert.setString(35, aut6off);
            insert.setString(36, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
            }

    public void altaTF1500mod4(int idMaq, int idHojaC, int idTF1, int idTF2, int idTF3, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idTF1500D2, idTF1500D3, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idTF2);
            insert.setInt(6, idTF3);
            insert.setInt(7, idFormato);
            insert.setDouble(8, dimenBarra);
            insert.setDouble(9, carreraAlim);
            insert.setDouble(10, carreraElevaci);
            insert.setDouble(11, alturaTroq);
            insert.setDouble(12, carreraSuje);
            insert.setDouble(13, presionBalanza);
            insert.setInt(14, rpm);
            insert.setInt(15, numeroMatriz);
            insert.setString(16, codigoSegunPc);
            insert.setString(17, rack);
            insert.setDouble(18, cantidad);
            insert.setString(19, deteccionDb);
            insert.setString(20, deteccionDb2);
            insert.setString(21, aceiteEmb);        
            insert.setString(22, frecuenciaRoc);
            insert.setString(23, CopasSucc);
            insert.setString(24, aut1off);
            insert.setString(25, aut1on);
            insert.setString(26, aut2off);
            insert.setString(27, aut2on);
            insert.setString(28, aut3off);
            insert.setString(29, aut3on);
            insert.setString(30, aut4off);
            insert.setString(31, aut4on);
            insert.setString(32, aut5off);
            insert.setString(33, aut5on);
            insert.setString(34, aut6off);
            insert.setString(35, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
            }

    public void altaTF1500mod5(int idMaq, int idHojaC, int idTF1, int idTF2, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idTF1500D2, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idTF2);
            insert.setInt(6, idFormato);
            insert.setDouble(7, dimenBarra);
            insert.setDouble(8, carreraAlim);
            insert.setDouble(9, carreraElevaci);
            insert.setDouble(10, alturaTroq);
            insert.setDouble(11, carreraSuje);
            insert.setDouble(12, presionBalanza);
            insert.setInt(13, rpm);
            insert.setInt(14, numeroMatriz);
            insert.setString(15, codigoSegunPc);
            insert.setString(16, rack);
            insert.setDouble(17, cantidad);
            insert.setString(18, deteccionDb);
            insert.setString(19, deteccionDb2);
            insert.setString(20, aceiteEmb);        
            insert.setString(21, frecuenciaRoc);
            insert.setString(22, CopasSucc);
            insert.setString(23, aut1off);
            insert.setString(24, aut1on);
            insert.setString(25, aut2off);
            insert.setString(26, aut2on);
            insert.setString(27, aut3off);
            insert.setString(28, aut3on);
            insert.setString(29, aut4off);
            insert.setString(30, aut4on);
            insert.setString(31, aut5off);
            insert.setString(32, aut5on);
            insert.setString(33, aut6off);
            insert.setString(34, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
        
    }

    public void altaTF1500mod6(int idMaq, int idHojaC, int idTF1, int idDepa, int idFormato, String aceiteEmb, String frecuenciaRoc, double carreraAlim, String rack, double cantidad, String deteccionDb, String deteccionDb2, double alturaTroq, int rpm, double presionBalanza, String CopasSucc, double dimenBarra, double carreraSuje, double carreraElevaci, int numeroMatriz, String codigoSegunPc, String aut1on, String aut1off, String aut2on, String aut2off, String aut3on, String aut3off, String aut4on, String aut4off, String aut5on, String aut5off, String aut6on, String aut6off) throws InterruptedException {
        boolean resp = false;
        conectar();
        try {
            PreparedStatement insert = conn.prepareStatement("Insert into tf1500 (idHojaCondiciones, idMaquina, idDepartamento, idTF1500D1, idFormato, dimCerradaBarra, carreraAlimentador, carreraElevacion, alturaTroquel, carreraSujetador, presionBalanza, RPM, numeroMatriz, codigoPc, snpRack, snpCantidad, deteccionDb1, deteccionDb2, aceiteEmbutido,  frecuenciaRociado, copasSuccion, aut1off, aut1on, aut2off, aut2on, aut3off, aut3on, aut4off, aut4on, aut5off, aut5on, aut6off, aut6on) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, idHojaC);
            insert.setInt(2, idMaq);
            insert.setInt(3, idDepa);
            insert.setInt(4, idTF1);
            insert.setInt(5, idFormato);
            insert.setDouble(6, dimenBarra);
            insert.setDouble(7, carreraAlim);
            insert.setDouble(8, carreraElevaci);
            insert.setDouble(9, alturaTroq);
            insert.setDouble(10, carreraSuje);
            insert.setDouble(11, presionBalanza);
            insert.setInt(12, rpm);
            insert.setInt(13, numeroMatriz);
            insert.setString(14, codigoSegunPc);
            insert.setString(15, rack);
            insert.setDouble(16, cantidad);
            insert.setString(17, deteccionDb);
            insert.setString(18, deteccionDb2);
            insert.setString(19, aceiteEmb);        
            insert.setString(20, frecuenciaRoc);
            insert.setString(21, CopasSucc);
            insert.setString(22, aut1off);
            insert.setString(23, aut1on);
            insert.setString(24, aut2off);
            insert.setString(25, aut2on);
            insert.setString(26, aut3off);
            insert.setString(27, aut3on);
            insert.setString(28, aut4off);
            insert.setString(29, aut4on);
            insert.setString(30, aut5off);
            insert.setString(31, aut5on);
            insert.setString(32, aut6off);
            insert.setString(33, aut6on);
            
            insert.executeUpdate();
            resp = true;
            insert.close();
        } catch (Exception ex) {
            Messagebox.show("No se pudo crear la parte, error conexión", "Error", Messagebox.OK, Messagebox.ERROR);
        }
        desconectar();
       
        
    }
    
    
    public void eliminarTodoTF1500(int dsds) throws SQLException {
        eliminarTF15001(dsds);
        eliminarTF15002(dsds);
        eliminarTF15003(dsds);
        eliminarTF15004(dsds);
        eliminarTF15005(dsds);
        eliminarTF15006(dsds);
      
        
    }

    private void eliminarTF15001(int dsds) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d1 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF15002(int dsds) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d2 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();    }

    private void eliminarTF15003(int dsds) throws SQLException {
     conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d3 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF15004(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d4 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF15005(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d5 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    private void eliminarTF15006(int dsds) throws SQLException {
    conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from tf1500d6 where idTroquel = ? ");
        insert.setInt(1, dsds);
   
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    public void eliminarTodosTroquelesParte(int idp) throws SQLException {
        conectar();
        PreparedStatement insert = conn.prepareStatement("Delete from troquel where idParte = ?");
        insert.setInt(1, idp);
        insert.executeUpdate();
        insert.close();
        desconectar();
    }

    
    }



