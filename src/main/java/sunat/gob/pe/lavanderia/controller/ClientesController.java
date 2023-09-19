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
import sunat.gob.pe.lavanderia.model.dao.IClientesDao;
import sunat.gob.pe.lavanderia.model.dao.impl.ClientesDaoImpl;
import sunat.gob.pe.lavanderia.model.entities.Clientes;

/**
 * FXML Controller class
 *
 * @author ovelarde
 */
public class ClientesController implements Initializable {

    @FXML
    private TableView<Clientes> clientesTable;
    @FXML
    private TableColumn<Clientes, String> numDocColumn;
    @FXML
    private TableColumn<Clientes, String> nombreColumn;
    @FXML
    private TableColumn<Clientes, String> apellidoColumn;
    @FXML
    private TableColumn<Clientes, String> telefonoColumn;
    @FXML
    private TableColumn<Clientes, String> emailColumn;

    private ObservableList<Clientes> clientesData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clientesTable.setItems(clientesData);
        numDocColumn.setCellValueFactory(rowData -> rowData.getValue().getNumero_documento1());
        nombreColumn.setCellValueFactory(rowData -> rowData.getValue().getNombres1());
        apellidoColumn.setCellValueFactory(rowData -> rowData.getValue().getApellidos1());
        telefonoColumn.setCellValueFactory(rowData -> rowData.getValue().getTelefono1());
        emailColumn.setCellValueFactory(rowData -> rowData.getValue().getEmail1());

        IClientesDao clientesDao = new ClientesDaoImpl();
        clientesData.addAll(clientesDao.listarClientes());
    }

}
