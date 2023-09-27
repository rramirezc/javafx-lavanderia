/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public final class Solicitud implements Serializable{

    public Solicitud() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Solicitud(double tipoCambio) {
        setTipoCambio(tipoCambio);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(int tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecioSoles() {
        return precioSoles;
    }

    public void setPrecioSoles(double precioSoles) {
        this.precioSoles = precioSoles;
    }

    public double getPrecioDolares() {
       if(precioSoles>0) precioDolares = this.precioSoles/this.getTipoCambio();
        return precioDolares;
    }

    public void setPrecioDolares(double precioDolares) {
        this.precioDolares = precioDolares;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    String tipoDocumento;
    String numeroDocumento;
    int tipoPrenda;
    int cantidad;
    double peso;
    double precioSoles;
    double precioDolares;
    Date fechaSolicitud;
    Date fechaEntrega;
    double tipoCambio;

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    public Solicitud(String tipoDocumento, String numeroDocumento,
            int tipoPrenda, int cantidad, double peso, double precioSoles,
            double tipoCambio){
        
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setTipoPrenda(tipoPrenda);
        setCantidad(cantidad);
        setPeso(peso);
        setPrecioSoles(precioSoles);
        setTipoCambio(tipoCambio);
    }
    
}
