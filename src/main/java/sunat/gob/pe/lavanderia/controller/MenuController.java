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
import sunat.gob.pe.lavanderia.App;


/**
 * FXML Controller class
 *
 * @author Aldo Malaver
 */
public class MenuController{

  @FXML
  private Button btnDashboard;
  
  @FXML
  private Button btnProductos;
  
  @FXML
  private Button btnPedidos;
  
  @FXML
  private Button btnHistorial;
  
  @FXML
  public void cambiarDashboard(ActionEvent event) throws IOException{
       Parent dashboard = App.loadFXML("dashboard");
       App.scene.setRoot(dashboard);
  }
  
  @FXML
  public void cambiarProducto(ActionEvent event) throws IOException{
       Parent productos = App.loadFXML("productos");
       App.scene.setRoot(productos);
  }
  
  @FXML
  public void cambiarPedido(ActionEvent event) throws IOException{
       Parent pedidos = App.loadFXML("pedidos");
       App.scene.setRoot(pedidos);
  }
  
  @FXML
  public void cambiarHistorial(ActionEvent event) throws IOException{
       Parent historial = App.loadFXML("historial");
       App.scene.setRoot(historial);
  }
}
