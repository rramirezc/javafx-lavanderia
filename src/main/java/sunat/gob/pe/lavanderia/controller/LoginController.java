/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import sunat.gob.pe.lavanderia.model.util.Dimensiones;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sunat.gob.pe.lavanderia.App;
import sunat.gob.pe.lavanderia.model.dao.IAlumnoDao;
import sunat.gob.pe.lavanderia.model.dao.IUsuarioDao;
import sunat.gob.pe.lavanderia.model.dao.impl.AlumnoDaoImpl;
import sunat.gob.pe.lavanderia.model.dao.impl.UsuarioDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.Alumno;
import sunat.gob.pe.lavanderia.model.entities.Usuario;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author Aldo Malaver
 */
public class LoginController {

    private IUsuarioDao usuarioDao = new UsuarioDAOImpl();

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    public static Scene scene;

    @FXML
    public void autenticarUsuario(ActionEvent actionEvent) throws IOException {

        if (validarDatos()) {

            StringProperty primerNombre = new SimpleStringProperty("Aldo");
            StringProperty apellidoPaterno = new SimpleStringProperty("Malaver");

            System.out.println("PN:::" + primerNombre.get());
            System.out.println("AP:::" + apellidoPaterno.get());
            apellidoPaterno.bindBidirectional(primerNombre);
            System.out.println("AP:::" + apellidoPaterno.get());

            primerNombre.set("Pepito");
            apellidoPaterno.set("Juancito");
            System.out.println("PN:::" + primerNombre.get());
            System.out.println("AP:::" + apellidoPaterno.get());

            FXMLLoader loader = App.getFXMLLoader("dashboard");
            Parent dashboard = loader.load();

            /*Redimensionamos y ubicamos la App*/
            /*
             App.scene.getWindow().setHeight(800.0);
             App.scene.getWindow().setWidth(1280.0);
             App.scene.getWindow().setX(120.0);
             App.scene.getWindow().setY(10.0);
             */
            Dimensiones.redimensionar(App.scene);

            App.scene.setRoot(dashboard);
            //App.scene.get
            DashboardController dashboardController = loader.<DashboardController>getController();
            dashboardController.setMensaje(txtUsuario.getText());
            App.scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        }

    }

    private boolean validarDatos() {

        if (txtUsuario.getText().isEmpty()) {
            System.out.println("Ingrese usuario válido");
            return false;
        }

        if (txtPassword.getText().isEmpty()) {
            System.out.println("Ingrese password válido");
            return false;
        }

        boolean resultado = usuarioDao.login(txtUsuario.getText(), txtPassword.getText());
        System.out.println("Resultado: " + resultado);
        if (!resultado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Usuario y/o Password inválidos");
            alert.show();
        }

        return resultado;
    }

}
