/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao;

import java.util.List;
import sunat.gob.pe.lavanderia.model.entities.ConsultaPendientes;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;

/**
 *
 * @author caest
 */
public interface IConsultaPendientesDao {
    
    List<ConsultaPendientes> listarPendientes(); 
    
    
}
