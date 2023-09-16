/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Aldo Malaver
 */
public class Alumno {

    private ObjectProperty<Long> idAlumno;

    private StringProperty nombres;

    private StringProperty apellidos;

    private StringProperty sexo;

    private StringProperty direccion;

    private StringProperty correo;

    private ObjectProperty<Integer> estado;

    public Alumno() {
        this.idAlumno = new SimpleObjectProperty<>(0L);
        this.nombres = new SimpleStringProperty("");
        this.apellidos = new SimpleStringProperty("");
        this.sexo = new SimpleStringProperty("");
        this.direccion = new SimpleStringProperty("");
        this.correo = new SimpleStringProperty("");
        this.estado = new SimpleObjectProperty<>(0);
        
    }

    public Alumno(Long idAlumno, String nombres, String apellidos, String sexo, String direccion, String correo, Integer estado) {
        this.idAlumno = new SimpleObjectProperty<>(idAlumno);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.sexo = new SimpleStringProperty(sexo);
        this.direccion = new SimpleStringProperty(direccion);
        this.correo = new SimpleStringProperty(correo);
        this.estado = new SimpleObjectProperty<>(estado);
    }

    public Long getIdAlumno() {
        return idAlumno.get();
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno.set(idAlumno);
    }
    
     public ObjectProperty<Long> getIdAlumno1() {
        return idAlumno;
    }

    public void setIdAlumno1(ObjectProperty<Long> idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombres() {
        return nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }
    
    public StringProperty getNombres1() {
        return nombres;
    }

    public void setNombres1(StringProperty nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }
    
    public StringProperty getApellidos1() {
        return apellidos;
    }

    public void setApellidos1(StringProperty apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }
    
    public StringProperty getSexo1() {
        return sexo;
    }

    public void setSexo1(StringProperty sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public StringProperty getDireccion1() {
        return direccion;
    }

    public void setDireccion1(StringProperty direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
    
    public StringProperty getCorreo1() {
        return correo;
    }

    public void setCorreo1(StringProperty correo) {
        this.correo = correo;
    }

    public Integer getEstado() {
        return estado.get();
    }

    public void setEstado(Integer estado) {
        this.estado.set(estado);
    }
    
     public ObjectProperty<Integer> getEstado1() {
        return estado;
    }

    public void setEstado1(ObjectProperty<Integer> estado) {
        this.estado = estado;
    }

}
