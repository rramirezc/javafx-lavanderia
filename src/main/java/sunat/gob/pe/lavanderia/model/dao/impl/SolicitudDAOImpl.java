/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sunat.gob.pe.lavanderia.model.dao.ISolicitudDAO;
import sunat.gob.pe.lavanderia.model.entities.Solicitud;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author Daniel
 */
public class SolicitudDAOImpl implements ISolicitudDAO{

    @Override
    public void registrarSolicitud(Solicitud solicitud) {
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            String insertSql = "Insert into solicitud(tipo_documento, numero_documento, id_usuario, id_tipo_prenda, "
                    + "cantidad_prendas, peso, precio_total, fecha_solicitud, fecha_entrega) "
                    + " VALUES(?,?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(insertSql);
            pstmt.setString(1, solicitud.getTipoDocumento());
            pstmt.setString(2, solicitud.getNumeroDocumento());
            pstmt.setInt(3, 2);
            pstmt.setInt(4, solicitud.getTipoPrenda());
            pstmt.setInt(5, solicitud.getCantidad());
            pstmt.setDouble(6, solicitud.getPeso());
            pstmt.setDouble(7, solicitud.getPrecioSoles());
            pstmt.setDate(8, new Date (solicitud.getFechaSolicitud().getTime()));
            pstmt.setDate(9, new Date(solicitud.getFechaEntrega().getTime()));
            System.out.println(pstmt.getParameterMetaData().toString());
            pstmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Solicitud> listarSolicitudes() {
        List<Solicitud> lstSolicitudes = new ArrayList();
        return lstSolicitudes;
//throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Solicitud consultarSolicitud(int idSolicitud) {
        Solicitud sol=new Solicitud(); return sol;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    
    }
    
}
