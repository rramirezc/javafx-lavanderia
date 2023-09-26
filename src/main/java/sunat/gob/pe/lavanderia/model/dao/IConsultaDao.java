/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao;

import java.util.List;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.entities.TipoPrendas;

/**
 *
 * @author caest
 */
public interface IConsultaDao {
    
    List<Consultas> listarConsulta(String tpDocumento,String nrDocumento,int nroSolicitud,String nombres, int prenda); 
    List<Documentos> listarDocumentos();
    List<TipoPrendas> listaPrendas();
    
}
