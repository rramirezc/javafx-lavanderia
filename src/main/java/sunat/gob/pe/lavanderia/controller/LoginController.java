package sunat.gob.pe.lavanderia.controller;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import sunat.gob.pe.lavanderia.App;
import sunat.gob.pe.lavanderia.controller.enums.EstadoEnum;
import sunat.gob.pe.lavanderia.controller.service.MailService;
import sunat.gob.pe.lavanderia.model.dao.IUsuarioDao;
import sunat.gob.pe.lavanderia.model.dao.impl.UsuarioDAOImpl;
import sunat.gob.pe.lavanderia.model.entities.Usuario;

public class LoginController implements Initializable {

  private final IUsuarioDao usuarioDao = new UsuarioDAOImpl();

  @FXML
  private TextField txtUsuario;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private TextField txtEmail;
  @FXML
  private Pane pnlFormClave;
  @FXML
  private Pane pnlFormSesion;
  @FXML
  private Pane pnlFormCambio;
  @FXML
  Circle btnIconClose;
  @FXML
  Circle btnIconIniciar;
  @FXML
  Circle btnIconRecuperar;
  @FXML
  Button btnIniciar;
  @FXML
  Button btnGenerar;
  @FXML
  ImageView btnImageBack;
  @FXML
  Button btnClose;
  @FXML
  Button btnCloseClave;
  @FXML
  Button btnCambiar;
  @FXML
  AnchorPane anchRoot;
  @FXML
  private PasswordField txtPasswordActual;
  @FXML
  private PasswordField txtPasswordNuevo;
  @FXML
  private PasswordField txtPasswordNuevoRep;
  
  private Usuario usuarioLogin=null;
  

  @FXML
  public void autenticarUsuario(ActionEvent actionEvent) throws IOException {

    if (validarDatos()) {
      FXMLLoader loader = App.getFXMLLoader("dashboard");
      Parent dashboard = loader.load();
      App.scene.setRoot(dashboard);
      DashboardController dashboardController = loader.<DashboardController>getController();
      dashboardController.setMensaje(txtUsuario.getText());
      App.scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
      Window window = App.scene.getWindow();
      window.setWidth(1280);
      window.setHeight(600);
      Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
      Stage stage = App.primaryStage;
      stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
      stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

  }

  private boolean validarDatos() {

    if (txtUsuario.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese nombre de usuario", Alert.AlertType.WARNING);
      txtUsuario.requestFocus();
      return false;
    }

    if (txtPassword.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese password válido", Alert.AlertType.WARNING);
      txtPassword.requestFocus();
      return false;
    }

    Usuario resultado = usuarioDao.login(txtUsuario.getText(), txtPassword.getText());
    if (resultado == null) {
      mostrarAlertas("Datos Incorrectos", "Usuario y/o Password Incorrectos", Alert.AlertType.ERROR);
      return false;
    }else{
      if(String.valueOf(EstadoEnum.BLOQUEADO.getValor()).equals(resultado.getEstado())){
        resultado.setPassword(txtPassword.getText());
        System.out.println("Actualizar Contraseña");
        mostrarAlertas("Actualizar clave", "Ingreso una clave temporal, actualice su contraseña", Alert.AlertType.INFORMATION);
        pnlFormCambio.toFront();
        usuarioLogin = new Usuario();
        usuarioLogin = resultado;
        return false;
      }else if(!String.valueOf(EstadoEnum.ACTIVO.getValor()).equals(resultado.getEstado())){
        mostrarAlertas("No Activo", "Estimado usuario usted no se encuentra activo", Alert.AlertType.ERROR);
        return false;
      }
    }
    return true;
  }

  private void mostrarAlertas(String header, String content, Alert.AlertType type) {
    Alert dialogo = new Alert(type);
    dialogo.setHeaderText(header);
    dialogo.setContentText(content);
    dialogo.show();
  }

  public void handleKeyPressedSesion(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
      autenticarUsuario(new ActionEvent());
    }
  }

  public void handleKeyPressedClave(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
    }
  }
  
  public void handleKeyPressedCambio(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
      cambiarClaveNueva(new ActionEvent());
    }
  }

  public void handleMouseEvent(MouseEvent mouseEvent) throws IOException {
    if (mouseEvent.getSource() == btnIconClose) {
      new animatefx.animation.FadeOut(anchRoot).play();
      System.exit(0);
    } else if (mouseEvent.getSource() == btnIconIniciar) {
      new FadeIn(pnlFormSesion).play();
      pnlFormSesion.toFront();
      btnIniciar.requestFocus();
    } else if (mouseEvent.getSource() == btnIconRecuperar) {
      new FadeIn(pnlFormClave).play();
      pnlFormClave.toFront();
      btnGenerar.requestFocus();
    } else if (mouseEvent.getSource() == btnImageBack) {
      new FadeIn(pnlFormSesion).play();
      pnlFormSesion.toFront();
      btnIniciar.requestFocus();
    }
  }

  public void handleButtonAction(ActionEvent actionEvent) throws IOException {
    if (actionEvent.getSource().equals(btnIniciar)) {
      autenticarUsuario(actionEvent);
    } else if (actionEvent.getSource().equals(btnGenerar)) {
      enviarCorreo();
    } else if (actionEvent.getSource().equals(btnClose)
            || actionEvent.getSource().equals(btnCloseClave)) {
      System.exit(0);
    }else if (actionEvent.getSource().equals(btnCambiar)) {
      cambiarClaveNueva(actionEvent);
    }
  }

  public void handleLinkAction(ActionEvent actionEvent) throws IOException {
    new FadeIn(pnlFormClave).play();
    pnlFormClave.toFront();
    btnGenerar.requestFocus();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    System.out.println("initialize");
    Tooltip t = new Tooltip("Cerrar");
    Tooltip.install(btnIconClose, t);
    pnlFormSesion.toFront();
  }

  public void enviarCorreo() {
    if (validarEmail()) {

      Usuario user = usuarioDao.obtenerUsuarioByEmail(txtEmail.getText().trim());
      if (user != null) {
        String nuevaClave = generarClave(6);
        boolean update = usuarioDao.cambiarClave(txtEmail.getText(), nuevaClave
                ,String.valueOf(EstadoEnum.BLOQUEADO.getValor()));
        if (update) {
          String mensaje = "Estimado "+ user.getNombres() +", se ha generado una contraseña temporal en el sistema Clean Wash.\n";
          mensaje += "Ingrese al sistema con los siguientes datos:\n\n";
          mensaje += "Usuario: "+ user.getUsuario()+"\n";
          mensaje += "Clave: "+ nuevaClave + "\n\n";
          mensaje += "Debera cambiar su contraseña al ingresar por primera vez.";
          MailService mailService = new MailService();
          String asunto = "CLEAN WASH - Recuperar Clave";
          boolean send = mailService.enviarConGMail(txtEmail.getText(), asunto, mensaje);
          if (send) {
            mostrarAlertas("Envio de Correo Correcto", "Revise su bandeja de correo electronico", Alert.AlertType.CONFIRMATION);
            txtEmail.setText("");
            pnlFormSesion.toFront();
            txtUsuario.requestFocus();
          } else {
            mostrarAlertas("Error", "Ocurrio un error en el envio de correo", Alert.AlertType.ERROR);
          }
        } else {
          mostrarAlertas("Error", "Ocurrio un error en la actualizacion de correo", Alert.AlertType.ERROR);
        }
      } else {
        mostrarAlertas("Error", "No se encontro el correo en la lista de usuarios", Alert.AlertType.ERROR);
      }

    }
  }

  public boolean validarEmail() {
    if (txtEmail.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese Email", Alert.AlertType.WARNING);
      txtEmail.requestFocus();
      return false;
    }
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    String email = txtEmail.getText();
    Matcher mather = pattern.matcher(email);
    if (!mather.find()) {
      mostrarAlertas("Datos Incorrectos", "Formato de correo inválido", Alert.AlertType.ERROR);
      return false;
    }

    return true;
  }

  public String generarClave(int n) {
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      int index
              = (int) (AlphaNumericString.length()
              * Math.random());
      sb.append(AlphaNumericString
              .charAt(index));
    }
    return sb.toString();
  }
  
  public void cambiarClaveNueva(ActionEvent actionEvent){
    if(validarCambioClave()){
      System.out.println("usuarioLogin: " + usuarioLogin.toString());
      boolean update = usuarioDao.cambiarClaveById(usuarioLogin.getUsuario()
              , txtPasswordNuevo.getText()
              ,String.valueOf(EstadoEnum.ACTIVO.getValor()));
      if(update){
        mostrarAlertas("Cambio Satisfactorio"
                , "Se actualizo su clave correctamente"
                , Alert.AlertType.INFORMATION);
        pnlFormSesion.toFront();
        txtUsuario.setText("");
        txtPassword.setText("");
      }else{
        mostrarAlertas("Error", "Ocurrio un error al actualizar su clave", Alert.AlertType.ERROR);
        txtPasswordActual.requestFocus();
      }
    }
  }
  
  public boolean validarCambioClave() {
    if (txtPasswordActual.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese clave actual", Alert.AlertType.WARNING);
      txtPasswordActual.requestFocus();
      return false;
    }
    if (txtPasswordNuevo.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese clave nueva", Alert.AlertType.WARNING);
      txtPasswordNuevo.requestFocus();
      return false;
    }
    if (txtPasswordNuevoRep.getText().isEmpty()) {
      mostrarAlertas("Datos Incorrectos", "Ingrese confirmacion de clave", Alert.AlertType.WARNING);
      txtPasswordNuevoRep.requestFocus();
      return false;
    }
    if (!txtPasswordActual.getText().equals(usuarioLogin.getPassword())) {
      mostrarAlertas("Datos Incorrectos", "Clave actual incorrecta", Alert.AlertType.WARNING);
      txtPasswordActual.requestFocus();
      return false;
    }
    if(!txtPasswordNuevo.getText().equals(txtPasswordNuevoRep.getText())){
      mostrarAlertas("Datos Incorrectos", "Las claves nuevas deben ser iguales", Alert.AlertType.WARNING);
      txtPasswordNuevoRep.requestFocus();
      return false;
    }
    if(txtPasswordNuevo.getText().length()<6){
      mostrarAlertas("Datos Incorrectos", "la clave nueva debe tener por lo menos 6 caracteres", Alert.AlertType.WARNING);
      txtPasswordNuevo.requestFocus();
      return false;
    }
    return true;
  }
}
