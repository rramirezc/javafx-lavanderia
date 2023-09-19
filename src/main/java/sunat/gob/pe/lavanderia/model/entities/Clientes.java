/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ovelarde
 */
public class Clientes {

    private StringProperty tipo_documento;
    private StringProperty numero_documento;
    private StringProperty nombres;
    private StringProperty apellidos;
    private ObjectProperty<Date> fecha_nacimiento;
    private StringProperty sexo;
    private StringProperty telefono;
    private StringProperty email;
    private StringProperty direccion;

    public Clientes() {
    }

    public Clientes(String tipo_documento, String numero_documento, String nombres, String apellidos, Date fecha_nacimiento, String sexo, String telefono, String email, String direccion) {
        this.tipo_documento = new SimpleStringProperty(tipo_documento);
        this.numero_documento = new SimpleStringProperty(numero_documento);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.fecha_nacimiento = new SimpleObjectProperty<>();
        this.sexo = new SimpleStringProperty(sexo);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.direccion = new SimpleStringProperty(direccion);
    }

    public String getTipo_documento() {
        return tipo_documento.get();
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento.set(tipo_documento);
    }

    public StringProperty getTipo_documento1() {
        return tipo_documento;
    }

    public void setTipo_documento1(StringProperty tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento.get();
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento.set(numero_documento);
    }

    public StringProperty getNumero_documento1() {
        return numero_documento;
    }

    public void setNumero_documento1(StringProperty numero_documento) {
        this.numero_documento = numero_documento;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento.get();
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento.set(fecha_nacimiento);
    }

    public ObjectProperty<Date> getFecha_nacimiento1() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento1(ObjectProperty<Date> fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty getTelefono1() {
        return telefono;
    }

    public void setTelefono1(StringProperty telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty getEmail1() {
        return email;
    }

    public void setEmail1(StringProperty email) {
        this.email = email;
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

}
