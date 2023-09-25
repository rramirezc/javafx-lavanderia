/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author caest
 */
public class Consultas {
    
    private ObjectProperty<Long> idSolicitud;

    private StringProperty tpDocumento;

    private StringProperty nrDocumento;

    private StringProperty nombres;

    private StringProperty tpPrenda;

    private ObjectProperty<Integer> ctPrendas;

    private ObjectProperty<Double> total;

    public Consultas() {
        this.idSolicitud = new SimpleObjectProperty<>(0L);
        this.tpDocumento = new SimpleStringProperty("");
        this.nrDocumento = new SimpleStringProperty("");
        this.nombres = new SimpleStringProperty("");
        this.tpPrenda = new SimpleStringProperty("");
        this.ctPrendas = new SimpleObjectProperty<>(0);
        this.total = new SimpleObjectProperty<>(0.00);
    }

    public Consultas(Long idSolicitud, String tpDocumento, String nrDocumento, String nombres, String tpPrenda, int ctPrendas, Double total) {
        this.idSolicitud = new SimpleObjectProperty<>(idSolicitud);
        this.tpDocumento = new SimpleStringProperty(tpDocumento);
        this.nrDocumento = new SimpleStringProperty(nrDocumento);
        this.nombres = new SimpleStringProperty(nombres);
        this.tpPrenda = new SimpleStringProperty(tpPrenda);
        this.ctPrendas = new SimpleObjectProperty<>(ctPrendas);
        this.total = new SimpleObjectProperty<>(total);
    }
    
   public Long getIdSolicitud() {
        return idSolicitud.get();
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud.set(idSolicitud);
    }
    
     public ObjectProperty<Long> getIdSolicitud1() {
        return idSolicitud;
    }

    public void setIdSolicitud1(ObjectProperty<Long> idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    public String getTpDocumento() {
        return tpDocumento.get();
    }

    public void setTpDocumento(String tpDocumento) {
        this.tpDocumento.set(tpDocumento);
    }
    
    public StringProperty getTpDocumento1() {
        return tpDocumento;
    }

    public void setTpDocumento1(StringProperty tpDocumento) {
        this.tpDocumento = tpDocumento;
    }
    
    public String getNrDocumento() {
        return nrDocumento.get();
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento.set(nrDocumento);
    }
    
    public StringProperty getNrDocumento1() {
        return nrDocumento;
    }

    public void setNrDocumento1(StringProperty nrDocumento) {
        this.nrDocumento = nrDocumento;
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
    
    public String getTpPrenda() {
        return tpPrenda.get();
    }

    public void setTpPrenda(String tpPrenda) {
        this.tpPrenda.set(tpPrenda);
    }
    
    public StringProperty getTpPrenda1() {
        return tpPrenda;
    }

    public void setTpPrenda1(StringProperty tpPrenda) {
        this.tpPrenda = tpPrenda;
    }
    
    public Integer getCtPrendas() {
        return ctPrendas.get();
    }

    public void setCtPrendas(Integer ctPrendas) {
        this.ctPrendas.set(ctPrendas);
    }
    
    public ObjectProperty<Integer> getCtPrendas1() {
        return ctPrendas;
    }

    public void setCtPrendas1(ObjectProperty<Integer> ctPrendas) {
        this.ctPrendas = ctPrendas;
    }
    
    public Double getTotal() {
        return total.get();
    }

    public void setTotal(Double total) {
        this.total.set(total);
    }
    
    public ObjectProperty<Double> getTotal1() {
        return total;
    }

    public void setTotal1(ObjectProperty<Double> total) {
        this.total = total;
    }
    
}
