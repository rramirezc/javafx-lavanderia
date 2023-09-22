/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sunat.gob.pe.lavanderia.model.dao.IConsultaDao;
import sunat.gob.pe.lavanderia.model.dao.impl.ConsultasDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.Consultas;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultaTable.setItems(consultaData);

        idSolicitud.setCellValueFactory(rowData -> rowData.getValue().getIdSolicitud1());
        tpDocumento.setCellValueFactory(rowData -> rowData.getValue().getTpDocumento1());
        nrDocumento.setCellValueFactory(rowData -> rowData.getValue().getNrDocumento1());
        nombres.setCellValueFactory(rowData -> rowData.getValue().getNombres1());
        tpPrenda.setCellValueFactory(rowData -> rowData.getValue().getTpPrenda1());
        ctPrendas.setCellValueFactory(rowData -> rowData.getValue().getCtPrendas1());
        total.setCellValueFactory(rowData -> rowData.getValue().getTotal1());

        IConsultaDao consultaDao = new ConsultasDAOImpl();
        consultaData.addAll(consultaDao.listarConsulta());
    }

}
