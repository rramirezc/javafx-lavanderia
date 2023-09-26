/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.dao.IConsultaFrecuentesDao;
import sunat.gob.pe.lavanderia.model.dao.IConsultaPendientesDao;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultaFrecuentesDAOImpl;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultaPendientesDAOImpl;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultasDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.ConsultaFrecuentes;
import sunat.gob.pe.lavanderia.model.entities.ConsultaPendientes;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;

/**
 * FXML Controller class
 *
 * @author Aldo Malaver
 */
public class DashboardController implements Initializable {

    @FXML
    private Label lblBienvenido;

    @FXML
    private TableView<ConsultaPendientes> dashboardTablePendientes;

    @FXML
    private TableView<ConsultaFrecuentes> dashboardTableFrecuentes;

    private ObservableList<ConsultaPendientes> consultaData = FXCollections.observableArrayList();

    private ObservableList<ConsultaFrecuentes> consultaDataFrecuentes = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ConsultaPendientes, BigInteger> solicitud;

    @FXML
    private TableColumn<ConsultaPendientes, String> cliente;

    @FXML
    private TableColumn<ConsultaPendientes, String> prenda;

    @FXML
    private TableColumn<ConsultaPendientes, Integer> cantidad_prendas;

    @FXML
    private TableColumn<ConsultaPendientes, String> fecha_entrega;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> cliente_frecuente;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> tipo_documento;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> nro_documento;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> fecha_nacimiento;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> telefono;

    @FXML
    private TableColumn<ConsultaFrecuentes, String> correo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enlazarTabla();
        llenarDatosEnVista();

        enlazarTablaFrecuentes();
        llenarDatosEnVistaFrecuentes();
    }

    public void setMensaje(String usuario) {
        lblBienvenido.setText("Bienvenido al aplicativo " + usuario);
    }

    private void enlazarTabla() {
        dashboardTablePendientes.setItems(consultaData);

        solicitud.setCellValueFactory(rowData -> rowData.getValue().getSolicitud());
        cliente.setCellValueFactory(rowData -> rowData.getValue().getCliente());
        prenda.setCellValueFactory(rowData -> rowData.getValue().getPrenda());
        cantidad_prendas.setCellValueFactory(rowData -> rowData.getValue().getCantidad_prendas());
        fecha_entrega.setCellValueFactory(rowData -> rowData.getValue().getFecha_entrega());

    }

    private void llenarDatosEnVista() {
        IConsultaPendientesDao consultaDao = new ConsultaPendientesDAOImpl();
        consultaData.addAll(consultaDao.listarPendientes());
    }
    
    private void enlazarTablaFrecuentes() {
        dashboardTableFrecuentes.setItems(consultaDataFrecuentes);

        cliente_frecuente.setCellValueFactory(rowData -> rowData.getValue().getCliente_frecuente());
        tipo_documento.setCellValueFactory(rowData -> rowData.getValue().getTipo_documento());
        nro_documento.setCellValueFactory(rowData -> rowData.getValue().getNro_documento());
        fecha_nacimiento.setCellValueFactory(rowData -> rowData.getValue().getFecha_nacimiento());
        telefono.setCellValueFactory(rowData -> rowData.getValue().getTelefono());
        correo.setCellValueFactory(rowData -> rowData.getValue().getCorreo());

    }
    
    private void llenarDatosEnVistaFrecuentes() {
        IConsultaFrecuentesDao consultaDao = new ConsultaFrecuentesDAOImpl();
        consultaDataFrecuentes.addAll(consultaDao.listarClientesFrecuentes());
    }


}
