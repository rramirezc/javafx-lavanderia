/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sunat.gob.pe.lavanderia.model.dao.IClientesDao;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.dao.ISolicitudDAO;
import sunat.gob.pe.lavanderia.model.dao.impl.ClientesDaoImpl;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultasDAOImpl;
import sunat.gob.pe.lavanderia.model.dao.impl.SolicitudDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.Clientes;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.entities.Solicitud;
import sunat.gob.pe.lavanderia.model.entities.TipoPrendas;

/**
 * FXML Controller class
 *
 * @author ovelarde
 */
public class SolicitudController implements Initializable {
    
    @FXML
    private ComboBox<Documentos> cmbTipoDocumento = new ComboBox();
    
    @FXML
    private ComboBox<TipoPrendas> cmbTipoPrenda = new ComboBox();
    
    private ObservableList<Documentos> consultaDataDocumentos = FXCollections.observableArrayList();

    private ObservableList<TipoPrendas> consultaDataPrendas = FXCollections.observableArrayList();
    
    @FXML
    Label lblUsuario = new Label();
    
    @FXML
    TextField txtNumeroDocumento = new TextField("");
    
    @FXML
    TextField txtCantidad = new TextField("");
    
    @FXML
    TextField txtPeso = new TextField("");
    
    @FXML
    TextField txtPrecio = new TextField("");
    
    @FXML
    TextField txtFechaSolicitud = new TextField("");
    
    @FXML
    DatePicker txtFechaEntrega = new DatePicker();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarFormulario();
        
    }

    public void iniciarFormulario(){
        
        Date fechaActual = Calendar.getInstance().getTime();
        txtFechaSolicitud.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
        TextField txtFecEntrega = txtFechaEntrega.getEditor();
        if(txtFecEntrega !=null) txtFecEntrega.setText("");
        
        IConsultaDao consultaDao = new ConsultasDAOImpl();
        consultaDataDocumentos.addAll(consultaDao.listarDocumentos());
        consultaDataPrendas.addAll(consultaDao.listaPrendas());
        
        cmbTipoDocumento.setButtonCell(
                new ListCell<Documentos>() {
            @Override
            public void updateItem(Documentos doc, boolean empty) {
                super.updateItem(doc, empty);
                if (doc != null) setText(doc.getDescripcionCorta());
                else setText(null);
             }
        });
        cmbTipoDocumento.setCellFactory(
                (ListView<Documentos> e) -> {
                    final ListCell<Documentos> listCell = new ListCell<>() {
                @Override
                public void updateItem(Documentos doc, boolean empty) {
                    super.updateItem(doc, empty);
                    if (doc != null)setText(doc.getDescripcionCorta());
                    else setText(null);
                    }
                 };
                   return listCell;
                });
        cmbTipoPrenda.setButtonCell(
                new ListCell<TipoPrendas>() {
            @Override
            public void updateItem(TipoPrendas doc, boolean empty) {
                super.updateItem(doc, empty);
                if (doc != null) setText(doc.getDescripcion());
                else setText(null);
             }
        });
        cmbTipoPrenda.setCellFactory(
                (ListView<TipoPrendas> e) -> {
                    final ListCell<TipoPrendas> listCel = new ListCell<>() {
                @Override
                public void updateItem(TipoPrendas doc, boolean empty) {
                    super.updateItem(doc, empty);
                    if (doc != null)setText(doc.getDescripcion());
                    else setText(null);
                    }
                 };
                   return listCel;
                }
        );
        
        consultaDataDocumentos.add(0, new Documentos("-1", "Seleccione..."));
        consultaDataPrendas.add(0,new TipoPrendas(-1, "Seleccione...", 0));
        cmbTipoDocumento.setItems(consultaDataDocumentos);
        cmbTipoPrenda.setItems(consultaDataPrendas);
        cmbTipoDocumento.getSelectionModel().select(0);
        cmbTipoPrenda.getSelectionModel().select(0);
    }
    
    
    public boolean validarDatos(){
        
        Documentos valorTipoDocumento = cmbTipoDocumento.getSelectionModel().getSelectedItem();
        if("-1".equals(valorTipoDocumento.getTpDocumento())){ 
                mostrarAlertas("Registro de solicitud", "Debe seleccionar el tipo de documento.", Alert.AlertType.INFORMATION);
                    return false;
        }
        if("".equals(txtNumeroDocumento.getText())){
            mostrarAlertas("Registro de solicitud", "Debe ingresar el numero de documento.", Alert.AlertType.INFORMATION);
                return false;
        }
        
        if(!validarClientes(valorTipoDocumento.getTpDocumento(), txtNumeroDocumento.getText())){
            mostrarAlertas("Registro de solicitud", "Es necesario que registre al cliente para continuar la solicitud.", Alert.AlertType.INFORMATION);
                return false;
        }
        
        TipoPrendas valorTipoPrenda = cmbTipoPrenda.getSelectionModel().getSelectedItem();
        if(valorTipoPrenda.getTipPrenda()==-1){ 
                mostrarAlertas("Registro de solicitud", "Debe seleccionar el tipo de prenda.", Alert.AlertType.INFORMATION);
                    return false;
        }
        
        if("".equals(txtCantidad.getText())){
            mostrarAlertas("Registro de solicitud", "Debe ingresar la cantidad de prendas.", Alert.AlertType.INFORMATION);
                return false;
        }
        
        if("".equals(txtPeso.getText())){
            mostrarAlertas("Registro de solicitud", "Debe ingresar el peso estimado de las prendas.", Alert.AlertType.INFORMATION);
                return false;
        }
        if("".equals(txtPrecio.getText())){
            mostrarAlertas("Registro de solicitud", "Debe ingresar el precio de esta solictud.", Alert.AlertType.INFORMATION);
                return false;
        }
        
        if("".equals(txtFechaEntrega.getEditor().getText())){
            mostrarAlertas("Registro de solicitud", "Debe ingresar la fecha de entrega.", Alert.AlertType.INFORMATION);
                return false;
        }
        
        return true;
    }
    
    public void registrarSolicitud(ActionEvent event) {
        
        Solicitud solicitud = new Solicitud(3.72);
        ISolicitudDAO solicitudDao = new SolicitudDAOImpl();
        
        if(validarDatos()){
            solicitud.setTipoDocumento(cmbTipoDocumento.getSelectionModel().getSelectedItem().getTpDocumento());
            solicitud.setNumeroDocumento(txtNumeroDocumento.getText());
            solicitud.setTipoPrenda(cmbTipoPrenda.getSelectionModel().getSelectedItem().getTipPrenda());
            solicitud.setCantidad(Integer.valueOf(txtCantidad.getText()));
            solicitud.setPeso(Double.valueOf(txtPeso.getText()));
            solicitud.setPrecioSoles(Double.valueOf(txtPrecio.getText()));
            solicitud.setFechaSolicitud(Calendar.getInstance().getTime());
            LocalDate localDate = txtFechaEntrega.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date fechaEntrega = Date.from(instant);
            solicitud.setFechaEntrega(fechaEntrega);
            solicitudDao.registrarSolicitud(solicitud);
            mostrarAlertas("Registro de solicitud", "La solicitud se registró satisfactoriamente", Alert.AlertType.INFORMATION);
        }
    }
    
    private boolean validarClientes(String tipoDocumento, String numeroDocumento){
        boolean existe = false;
        Clientes cliente = null;
        IClientesDao clientesDAO = new ClientesDaoImpl();
        cliente = clientesDAO.buscarClientesPorId(tipoDocumento, numeroDocumento);
        
        if(cliente!=null) existe = true;
        return existe;
    }
    
    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
    
    public void limpiarFormulario(ActionEvent event){
        cmbTipoDocumento.getSelectionModel().select(0);
        txtNumeroDocumento.setText("");
        cmbTipoPrenda.getSelectionModel().select(0);
        txtCantidad.setText("");
        txtPeso.setText("");
        txtPrecio.setText("");
        txtFechaEntrega.getEditor().setText("");
        
    }
}
