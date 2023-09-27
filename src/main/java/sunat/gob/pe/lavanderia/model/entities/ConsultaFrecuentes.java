/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import java.math.BigInteger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Herman
 */
public class ConsultaFrecuentes {

    private StringProperty cliente_frecuente;

    private StringProperty tipo_documento;

    private StringProperty nro_documento;

    private StringProperty fecha_nacimiento;

    private StringProperty telefono;

    private StringProperty correo;

    public ConsultaFrecuentes() {
        this.cliente_frecuente = new SimpleStringProperty("");
        this.tipo_documento = new SimpleStringProperty("");
        this.nro_documento = new SimpleStringProperty("");
        this.fecha_nacimiento = new SimpleStringProperty("");
        this.telefono = new SimpleStringProperty("");
        this.correo = new SimpleStringProperty("");

    }

    public ConsultaFrecuentes(String cliente_frecuente, String tipo_documento, String nro_documento, String fecha_nacimiento, String telefono, String correo) {

        this.cliente_frecuente = new SimpleStringProperty(cliente_frecuente);
        this.tipo_documento = new SimpleStringProperty(tipo_documento);
        this.nro_documento = new SimpleStringProperty(nro_documento);
        this.fecha_nacimiento = new SimpleStringProperty(fecha_nacimiento);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        
    }

    public StringProperty getCliente_frecuente() {
        return cliente_frecuente;
    }

    public void setCliente_frecuente(StringProperty cliente_frecuente) {
        this.cliente_frecuente = cliente_frecuente;
    }

    public StringProperty getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(StringProperty tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public StringProperty getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(StringProperty nro_documento) {
        this.nro_documento = nro_documento;
    }

    public StringProperty getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(StringProperty fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public StringProperty getTelefono() {
        return telefono;
    }

    public void setTelefono(StringProperty telefono) {
        this.telefono = telefono;
    }

    public StringProperty getCorreo() {
        return correo;
    }

    public void setCorreo(StringProperty correo) {
        this.correo = correo;
    }

   

}
