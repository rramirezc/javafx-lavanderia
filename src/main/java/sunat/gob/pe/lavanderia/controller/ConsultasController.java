/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultasDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.Consultas;
import sunat.gob.pe.lavanderia.model.entities.Documentos;
import sunat.gob.pe.lavanderia.model.entities.TipoPrendas;

/**
 * FXML Controller class
 *
 * @author caest
 */
public class ConsultasController implements Initializable {

    @FXML
    private TableView<Consultas> consultaTable;

    @FXML
    private TableColumn<Consultas, Long> idSolicitud;

    @FXML
    private TableColumn<Consultas, String> tpDocumento;

    @FXML
    private TableColumn<Consultas, String> nrDocumento;

    @FXML
    private TableColumn<Consultas, String> nombres;

    @FXML
    private TableColumn<Consultas, String> tpPrenda;

    @FXML
    private TableColumn<Consultas, Integer> ctPrendas;

    @FXML
    private TableColumn<Consultas, Double> total;

    private ObservableList<Consultas> consultaData = FXCollections.observableArrayList();

    private ObservableList<Documentos> consultaDataDocumentos = FXCollections.observableArrayList();

    private ObservableList<TipoPrendas> consultaDataPrendas = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<Documentos> cboTpDocumento = new ComboBox<>();
    
    @FXML
    private ComboBox<TipoPrendas> cboTpPrendas = new ComboBox<>();

    @FXML
    private TextField txtDocumento, txtSolicitud, txtNombres;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        llenarDatosEnVista();
        enlazarTabla();

        cboTpDocumento.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            if (t1 != null) {
                tipoDocumento = t1.getTpDocumento();
            }
        });
        
        cboTpPrendas.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            if (t1 != null) {
                tipoPrenda = t1.getTipPrenda();
            }
        });


    }

    String tipoDocumento;
    int tipoPrenda = 0;

    public void buscarSolicitud(ActionEvent event) {

        String nombresCompleto = txtNombres.getText();
        String documento = txtDocumento.getText();
        String solicitud = txtSolicitud.getText();
        int nrSolicitud = 0;

        if (!solicitud.isEmpty()) {
            if (!isNumeric(solicitud)) {
                mostrarAlertas("Warning", "Ingrese solo numeros", Alert.AlertType.WARNING);
                limpiarTabla();
                return;
            } else {
                nrSolicitud = Integer.parseInt(solicitud);
            }
        }

        if (!documento.isEmpty()) {
            if (tipoDocumento == null || tipoDocumento.equals("-1")) {
                mostrarAlertas("Warning", "Seleccione el tipo de documento", Alert.AlertType.WARNING);
                limpiarTabla();
                return;
            }
        }

        System.err.println(" nombresCompleto :" + nombresCompleto);
        System.err.println(" documento :" + documento);
        System.err.println(" solicitud :" + nrSolicitud);
        System.err.println(" tipoDocumento :" + tipoDocumento);
        System.err.println(" TipoPrenda :" + tipoPrenda);

        limpiarTabla();

        IConsultaDao consultaDao = new ConsultasDAOImpl();
        consultaData.addAll(consultaDao.listarConsulta(tipoDocumento, documento, nrSolicitud, nombresCompleto,tipoPrenda));

    }

    public void limpiarConsulta(ActionEvent event) {

        txtNombres.setText("");
        txtDocumento.setText("");
        txtSolicitud.setText("");
        tipoDocumento = null;
        tipoPrenda = 0;
        limpiarTabla();

        cboTpDocumento.getItems().clear();
        cboTpPrendas.getItems().clear();
        // llenar combo
        llenarDatosEnVista();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void enlazarTabla() {
        consultaTable.setItems(consultaData);

        idSolicitud.setCellValueFactory(rowData -> rowData.getValue().getIdSolicitud1());
        tpDocumento.setCellValueFactory(rowData -> rowData.getValue().getTpDocumento1());
        nrDocumento.setCellValueFactory(rowData -> rowData.getValue().getNrDocumento1());
        nombres.setCellValueFactory(rowData -> rowData.getValue().getNombres1());
        tpPrenda.setCellValueFactory(rowData -> rowData.getValue().getTpPrenda1());
        ctPrendas.setCellValueFactory(rowData -> rowData.getValue().getCtPrendas1());
        total.setCellValueFactory(rowData -> rowData.getValue().getTotal1());

        // llenar combo
        cboTpDocumento.setItems(consultaDataDocumentos);
        cboTpPrendas.setItems(consultaDataPrendas);

    }

    private void llenarDatosEnVista() {
        IConsultaDao consultaDao = new ConsultasDAOImpl();
        //consultaData.addAll(consultaDao.listarConsulta(null,null,0,null));
        //consultaDataDocumentos.addAll(new Documentos("-1", "Seleccione..."));
        consultaDataDocumentos.addAll(consultaDao.listarDocumentos());
        consultaDataPrendas.addAll(consultaDao.listaPrendas());
        //cboTpDocumento.setPromptText("Seleccione...");
        cboTpDocumento.setButtonCell(
                new ListCell<Documentos>() {
            @Override
            public void updateItem(Documentos doc, boolean empty
            ) {
                super.updateItem(doc, empty);
                if (doc != null) {
                    setText(doc.getDescripcionCorta());
                } else {
                    setText(null);
                }

            }
        }
        );
        cboTpDocumento.setCellFactory(
                (ListView<Documentos> e) -> {
                    final ListCell<Documentos> listCell = new ListCell<>() {

                @Override
                public void updateItem(Documentos doc, boolean empty) {
                    super.updateItem(doc, empty);
                    if (doc != null) {
                        setText(doc.getDescripcionCorta());

                    } else {
                        setText(null);
                    }

                }
            };
                    return listCell;
                }
        );
        
        cboTpPrendas.setButtonCell(
                new ListCell<TipoPrendas>() {
            @Override
            public void updateItem(TipoPrendas doc, boolean empty
            ) {
                super.updateItem(doc, empty);
                if (doc != null) {
                    setText(doc.getDescripcion());
                } else {
                    setText(null);
                }

            }
        }
        );
        cboTpPrendas.setCellFactory(
                (ListView<TipoPrendas> e) -> {
                    final ListCell<TipoPrendas> listCell = new ListCell<>() {

                @Override
                public void updateItem(TipoPrendas doc, boolean empty) {
                    super.updateItem(doc, empty);
                    if (doc != null) {
                        setText(doc.getDescripcion());

                    } else {
                        setText(null);
                    }

                }
            };
                    return listCell;
                }
        );

    }

    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }

    private void limpiarTabla() {
        consultaTable.getItems().clear();
    }

}
