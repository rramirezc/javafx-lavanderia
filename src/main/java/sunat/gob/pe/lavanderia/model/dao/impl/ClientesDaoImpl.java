/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sunat.gob.pe.lavanderia.model.dao.IClientesDao;
import sunat.gob.pe.lavanderia.model.entities.Clientes;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.util.Conexion;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

/**
 *
 * @author ovelarde
 */
public class ClientesDaoImpl implements IClientesDao {

    @Override
    public void guardarClientes(Clientes clientes) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //Conexion conexion = new Conexion();
        //Connection conn = conexion.getConexion();
        //PreparedStatement pstmt = null;

        try {
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            String sql = "Insert into CLIENTE(tipo_documento, numero_documento, nombres, apellidos, fecha_nacimiento, "
                    + "sexo, telefono, email, direccion) VALUES(?,?,?,?,?,?,?,?,?)";

            //pstmt = conn.prepareStatement(sql);
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, clientes.getTipo_documento());
            pstmt.setString(2, clientes.getNumero_documento());
            pstmt.setString(3, clientes.getNombres());
            pstmt.setString(4, clientes.getApellidos());
            pstmt.setDate(5, clientes.getFecha_nacimiento());
            //pstmt.setString(5, clientes.getFecha_nacimiento().toString());
            //pstmt.setString(5, clientes.getFecha_nacimiento());
            pstmt.setString(6, clientes.getSexo());
            pstmt.setString(7, clientes.getTelefono());
            pstmt.setString(8, clientes.getEmail());
            pstmt.setString(9, clientes.getDireccion());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConnectionPoolMySQL.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    @Override
    public List<Clientes> listarClientes() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        List<Clientes> listaClientes = new ArrayList<>();
        ResultSet rs = null;
        //Conexion conexion = new Conexion();
        //Connection conn = conexion.getConexion();
        //PreparedStatement pstmt = null;
        //List<Clientes> listaClientes = new ArrayList<>();
        //ResultSet rs = null;

        try {
            connection = ConnectionPoolMySQL.getInstance().getConnection();
            String sql = "Select tipo_documento, numero_documento, nombres, apellidos, "
                    + "fecha_nacimiento,sexo, telefono, email, direccion from CLIENTE";
            //pstmt = conn.prepareStatement(sql);
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                listaClientes.add(new Clientes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
                //      listaClientes.add(new Clientes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                //            rs.getDate(5), rs.getString(6),
                //          rs.getString(7), rs.getString(8), rs.getString(9)));
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

        return listaClientes;
    }
/*
    @Override
    public Clientes buscarClientesPorId(String tipo_documento, String numero_documento) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        Clientes clientes = null;
        ResultSet rs = null;
        try {

            String sql = "Select tipo_documento, numero_documento, nombres, apellidos, "
                    + "fecha_nacimiento,sexo, telefono, email, direccion from Cliente where tipo_documento = ?, numero_documento = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tipo_documento);
            pstmt.setString(2, numero_documento);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                clientes = new Clientes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
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

        return clientes;
    }

    @Override
    public void actualizarClientes(Clientes clientes) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        try {

            String sql = "Update CLIENTE set nombres = ?, apellidos = ?,fecha_nacimiento = ?,sexo = ?, telefono = ?, "
                    + "email = ?, direccion = ? where tipo_documento = ?, numero_documento = ?,";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, clientes.getNombres());
            pstmt.setString(2, clientes.getApellidos());
            //pstmt.setString(2, clientes.getFecha_nacimiento());
            pstmt.setDate(3, clientes.getFecha_nacimiento());
            pstmt.setString(4, clientes.getSexo());
            pstmt.setString(5, clientes.getTelefono());
            pstmt.setString(6, clientes.getEmail());
            pstmt.setString(7, clientes.getDireccion());
            pstmt.setString(8, clientes.getTipo_documento());
            pstmt.setString(9, clientes.getNumero_documento());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
    }

    @Override
    public void eliminarClientes(String tipo_documento, String numero_documento) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConexion();
        PreparedStatement pstmt = null;
        try {

            String sql = "Delete CLIENTE where tipo_documento = ?, numero_documento = ?,";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tipo_documento);
            pstmt.setString(2, numero_documento);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
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
*/

}
