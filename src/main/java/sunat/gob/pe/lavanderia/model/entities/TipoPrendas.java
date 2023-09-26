/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

/**
 *
 * @author caest
 */
public class TipoPrendas {
    
    private int tipPrenda;
    private String descripcion;
    private double precio;

    public TipoPrendas(int tipPrenda, String descripcion, double precio) {
        this.tipPrenda = tipPrenda;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getTipPrenda() {
        return tipPrenda;
    }

    public void setTipPrenda(int tipPrenda) {
        this.tipPrenda = tipPrenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
        
}
