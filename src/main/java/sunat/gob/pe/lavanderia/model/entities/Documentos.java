/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.entities;

/**
 *
 * @author caest
 */
public class Documentos {
    
    private String tpDocumento;
    private String descripcionCorta;

    public Documentos() {
    }

    public Documentos(String tpDocumento, String descripcionCorta) {
        this.tpDocumento = tpDocumento;
        this.descripcionCorta = descripcionCorta;
    }
    
    public String getTpDocumento() {
        return tpDocumento;
    }

    public void setTpDocumento(String tpDocumento) {
        this.tpDocumento = tpDocumento;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }
    
    
    
}
