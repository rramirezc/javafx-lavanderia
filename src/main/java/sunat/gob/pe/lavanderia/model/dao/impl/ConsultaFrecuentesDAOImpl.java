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
import sunat.gob.pe.lavanderia.model.dao.IConsultaFrecuentesDao;
import sunat.gob.pe.lavanderia.model.dao.IConsultaPendientesDao;
import sunat.gob.pe.lavanderia.model.entities.ConsultaFrecuentes;
import sunat.gob.pe.lavanderia.model.entities.ConsultaPendientes;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.util.Conexion;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author caest
 */
public class ConsultaFrecuentesDAOImpl implements IConsultaFrecuentesDao {
    
    @Override
    public List<ConsultaFrecuentes> listarClientesFrecuentes() {
        List<ConsultaFrecuentes> listaConsulta = new ArrayList<>();

        String sql = " select CONCAT(c.nombres,\" \",c.apellidos) cliente, c.tipo_documento,frec.numero_documento nro_documento, c.fecha_nacimiento fecha_nacimiento,"
                + " c.telefono telefono, c.email correo, frec.visitas visitas"
                + " from (select s.numero_documento numero_documento,count(s.numero_documento) visitas"
                + " from SOLICITUD s"
                + " group by s.numero_documento"
                + " order by visitas desc) frec"
                + " inner join CLIENTE c on frec.numero_documento = c.numero_documento"
                + " order by frec.visitas desc"
                + " limit 2";

        try (
                Connection connection = ConnectionPoolMySQL.getInstance().getConnection(); Statement st = connection.createStatement();) {

            try (ResultSet rs = st.executeQuery(sql);) {
                while (rs.next()) {
                    listaConsulta.add(new ConsultaFrecuentes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
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
