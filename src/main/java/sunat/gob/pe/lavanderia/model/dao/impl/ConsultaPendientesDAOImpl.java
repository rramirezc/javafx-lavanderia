/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.dao.IConsultaPendientesDao;
import sunat.gob.pe.lavanderia.model.entities.ConsultaPendientes;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.util.Conexion;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author caest
 */
public class ConsultaPendientesDAOImpl implements IConsultaPendientesDao {

    @Override
    public List<ConsultaPendientes> listarPendientes() {
        List<ConsultaPendientes> listaConsulta = new ArrayList<>();

        String sql = "select s.id_solicitud solicitud, CONCAT(c.nombres,\" \",c.apellidos) cliente,tp.descripcion prenda, s.cantidad_prendas cantidad_prendas, s.fecha_entrega fecha_entrega"
                + " from SOLICITUD s"
                + " inner join CLIENTE c on s.numero_documento = c.numero_documento"
                + " inner join TIPO_PRENDA tp on s.id_tipo_prenda = tp.id_tipo_prenda"
                + " where s.pendiente = '1'"
                + " order by s.fecha_entrega";

        try (
                Connection connection = ConnectionPoolMySQL.getInstance().getConnection(); Statement st = connection.createStatement();) {

            try (ResultSet rs = st.executeQuery(sql);) {
                while (rs.next()) {
                    listaConsulta.add(new ConsultaPendientes(rs.getBigDecimal(1).toBigInteger(), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return listaConsulta;
    }

}
