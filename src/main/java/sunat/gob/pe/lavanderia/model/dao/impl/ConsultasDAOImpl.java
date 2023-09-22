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
import sunat.gob.pe.lavanderia.model.util.Conexion;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author caest
 */
public class ConsultasDAOImpl implements IConsultaDao {

    @Override
    public List<Consultas> listarConsulta() {

        Connection connection = null;
        PreparedStatement pstmt = null;
        List<Consultas> listaConsulta = new ArrayList<>();
        ResultSet rs = null;

        try {

            connection = ConnectionPoolMySQL.getInstance().getConnection();

            String sql = "SELECT s.id_solicitud , t.descripcion_corta, c.numero_documento ,CONCAT(c.nombres,\" \",c.apellidos)  ,tp.descripcion ,s.cantidad_prendas,s.precio_total"
                    + " FROM SOLICITUD s "
                    + " inner join CLIENTE c on s.tipo_documento = c.tipo_documento and s.numero_documento = c.numero_documento "
                    + " inner join TIPO_DOCUMENTO t on t.tipo_documento = s.tipo_documento "
                    + " inner join TIPO_PRENDA tp on tp.id_tipo_prenda = s.id_tipo_prenda "
                    + " where 0=0";
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

}
