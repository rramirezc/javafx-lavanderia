/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.entities.TipoPrendas;
import sunat.gob.pe.lavanderia.model.util.Conexion;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author caest
 */
public class ConsultasDAOImpl implements IConsultaDao {

    @Override
    public List<Consultas> listarConsulta(String tpDocumento, String nrDocumento, int nroSolicitud, String nombres, int prenda) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        List<Consultas> listaConsulta = new ArrayList<>();
        ResultSet rs = null;
        System.err.println("==================================");
        System.err.println("tpDocumento:" + tpDocumento);
        System.err.println("nrDocumento:" + nrDocumento);
        System.err.println("nroSolicitud:" + nroSolicitud);
        System.err.println("nombres:" + nombres);
        System.err.println("prenda:" + prenda);

        try {

            connection = ConnectionPoolMySQL.getInstance().getConnection();

            String sql = "SELECT s.id_solicitud , t.descripcion_corta, c.numero_documento ,CONCAT(c.nombres,\" \",c.apellidos)  ,tp.descripcion ,s.cantidad_prendas,s.precio_total"
                    + " FROM SOLICITUD s "
                    + " inner join CLIENTE c on s.tipo_documento = c.tipo_documento and s.numero_documento = c.numero_documento "
                    + " inner join TIPO_DOCUMENTO t on t.tipo_documento = s.tipo_documento "
                    + " inner join TIPO_PRENDA tp on tp.id_tipo_prenda = s.id_tipo_prenda "
                    + " where 0=0";

            if (tpDocumento != null) {
                sql += " and s.tipo_documento = " + tpDocumento;
                if (nrDocumento != null) {
                    if (nrDocumento.length() > 0) {
                        sql += " and s.numero_documento = " + nrDocumento;
                    }
                }
            }

            if (nroSolicitud > 0) {
                sql += " and s.id_solicitud = " + nroSolicitud;
            }

            if (nombres != null) {
                if (nombres.length() > 0) {
                    sql += " and UPPER(CONCAT(c.nombres,\" \",c.apellidos)) like UPPER('%" + nombres + "%')";
                }
            }
            
            if (prenda > 0) {
                sql += " and s.id_tipo_prenda = " + prenda;
            }

            System.err.println("sql ->" + sql);

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                listaConsulta.add(new Consultas(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getDouble(7)));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return listaConsulta;
    }

    @Override
    public List<Documentos> listarDocumentos() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        List<Documentos> listaDocumentos = new ArrayList<>();
        ResultSet rs = null;

        try {

            connection = ConnectionPoolMySQL.getInstance().getConnection();

            String sql = "select tipo_documento ,descripcion_corta  from TIPO_DOCUMENTO";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                listaDocumentos.add(new Documentos(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return listaDocumentos;
    }

    @Override
    public List<TipoPrendas> listaPrendas() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        List<TipoPrendas> listaTipoPrendas = new ArrayList<>();
        ResultSet rs = null;

        try {

            connection = ConnectionPoolMySQL.getInstance().getConnection();

            String sql = "select id_tipo_prenda, descripcion, precio from TIPO_PRENDA";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                listaTipoPrendas.add(new TipoPrendas(rs.getInt(1), rs.getString(2),rs.getDouble(3)));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        return listaTipoPrendas;
    }

}
