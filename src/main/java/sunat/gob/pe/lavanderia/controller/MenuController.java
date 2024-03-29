/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Window;
import sunat.gob.pe.lavanderia.App;
import sunat.gob.pe.lavanderia.model.util.Dimensiones;

/**
 * FXML Controller class
 *
 * @author ovelarde
 */
public class MenuController {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnSolicitud;
    @FXML
    private Button btnHistorial;
    @FXML
    private Button btnConsultas;

    @FXML
    public void cambiarDashboard(ActionEvent event) throws IOException {
        Parent dashboard = App.loadFXML("dashboard");
        App.scene.setRoot(dashboard);
        //Dimensiones.redimensionar(App.scene);
    }

    @FXML
    public void cambiarUsuarios(ActionEvent event) throws IOException {
        Parent usuarios = App.loadFXML("usuarios");
        App.scene.setRoot(usuarios);
        //Dimensiones.redimensionar(App.scene);
    }

    @FXML
    public void cambiarClientes(ActionEvent event) throws IOException {
        Parent clientes = App.loadFXML("clientes");
        App.scene.setRoot(clientes);
        //Dimensiones.redimensionar(App.scene);
    }

    @FXML
    public void cambiarSolicitud(ActionEvent event) throws IOException {
        Parent solicitud = App.loadFXML("solicitud");
        App.scene.setRoot(solicitud);
        //Dimensiones.redimensionar(App.scene);
    }

    @FXML
    public void cambiarHistorial(ActionEvent event) throws IOException {
        Parent historial = App.loadFXML("historial");
        App.scene.setRoot(historial);
        //Dimensiones.redimensionar(App.scene);
    }

    @FXML
    public void cambiarConsultas(ActionEvent event) throws IOException {
        Parent historial = App.loadFXML("consultas");
        App.scene.setRoot(historial);
        //Dimensiones.redimensionar(App.scene);
    }

}
