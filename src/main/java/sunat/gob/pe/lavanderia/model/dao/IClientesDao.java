/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao;

import java.util.List;
import sunat.gob.pe.lavanderia.model.entities.Clientes;
import sunat.gob.pe.lavanderia.model.entities.Documentos;

/**
 *
 * @author ovelarde
 */
public interface IClientesDao {

    void guardarClientes(Clientes clientes);

    List<Clientes> listarClientes();

   // Clientes buscarClientesPorId(String tipo_documento, String numero_documento);

   // void actualizarClientes(Clientes clientes);

    //void eliminarClientes(String tipo_documento, String numero_documento);
    //List<Documentos> listarDocumentos();
}
