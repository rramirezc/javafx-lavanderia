/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Aldo Malaver
 */
public class DashboardController {

  @FXML
  private Label lblBienvenido;
  
  public void setMensaje(String usuario){
      lblBienvenido.setText("Bienvenido al aplicativo " + usuario);
  }
    
}
