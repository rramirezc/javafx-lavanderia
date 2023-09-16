/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.dao;

import java.util.List;
import sunat.gob.pe.lavanderia.model.entities.Alumno;

/**
 *
 * @author Aldo Malaver
 */
public interface IAlumnoDao {
    
    void guardarAlumno(Alumno alumno);
    
    List<Alumno> listarAlumno();
    
    Alumno buscarAlumnoPorId(Long idAlumno);
    
    void actualizarAlumno(Alumno alumno);
    
    void eliminarAlumno(Long idAlumno);
    
}
