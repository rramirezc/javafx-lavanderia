/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao;

import java.util.List;
import sunat.gob.pe.lavanderia.model.entities.Clientes;
import sunat.gob.pe.lavanderia.model.entities.Solicitud;

/**
 *
 * @author Daniel
 */
public interface ISolicitudDAO {
    
    void registrarSolicitud(Solicitud solicitud);

    List<Solicitud> listarSolicitudes();

    Solicitud consultarSolicitud(int idSolicitud);    
}
