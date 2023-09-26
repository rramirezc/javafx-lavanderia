/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public final class TipoPrendas implements Serializable{
    
    int tipoPrenda;
    String descripcionTipo;
    double precio;
    

    public int getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(int tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoPrendas(int tipoPrenda, String descripcion, double precio){
        setTipoPrenda(tipoPrenda);
        setDescripcionTipo(descripcion);
        setPrecio(precio);
    }
    
    
    
}
