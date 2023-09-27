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
public class ConsultaPendientes {

    private ObjectProperty<BigInteger> solicitud;

    private StringProperty cliente;

    private StringProperty prenda;

    private ObjectProperty<Integer> cantidad_prendas;

    private StringProperty fecha_entrega;

    public ConsultaPendientes() {
        this.solicitud = new SimpleObjectProperty(0);
        this.cliente = new SimpleStringProperty("");
        this.prenda = new SimpleStringProperty("");
        this.cantidad_prendas = new SimpleObjectProperty<>(0);
        this.fecha_entrega = new SimpleStringProperty("");
    }

    public ConsultaPendientes(BigInteger solicitud, String cliente, String prenda, Integer cantidad_prendas, String fecha_entrega) {
        this.solicitud = new SimpleObjectProperty<>(solicitud);
        this.cliente = new SimpleStringProperty(cliente);
        this.prenda = new SimpleStringProperty(prenda);
        this.cantidad_prendas = new SimpleObjectProperty<>(cantidad_prendas);
        this.fecha_entrega = new SimpleStringProperty(fecha_entrega);
    }

    public ObjectProperty<BigInteger> getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(ObjectProperty<BigInteger> solicitud) {
        this.solicitud = solicitud;
    }

    public StringProperty getCliente() {
        return cliente;
    }

    public void setCliente(StringProperty cliente) {
        this.cliente = cliente;
    }

    public StringProperty getPrenda() {
        return prenda;
    }

    public void setPrenda(StringProperty prenda) {
        this.prenda = prenda;
    }

    public ObjectProperty<Integer> getCantidad_prendas() {
        return cantidad_prendas;
    }

    public void setCantidad_prendas(ObjectProperty<Integer> cantidad_prendas) {
        this.cantidad_prendas = cantidad_prendas;
    }

    public StringProperty getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(StringProperty fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    
    

}
