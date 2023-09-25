package sunat.gob.pe.lavanderia.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.PopupWindow;
import sunat.gob.pe.lavanderia.App;
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
  AnchorPane anchRoot;

  @FXML
  public void autenticarUsuario(ActionEvent actionEvent) throws IOException {

    if (validarDatos()) {
      FXMLLoader loader = App.getFXMLLoader("dashboard");
      Parent dashboard = loader.load();
      App.scene.setRoot(dashboard);
      DashboardController dashboardController = loader.<DashboardController>getController();
      dashboardController.setMensaje(txtUsuario.getText());
      App.scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
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

    boolean resultado = usuarioDao.login(txtUsuario.getText(), txtPassword.getText());
    if (!resultado) {
      mostrarAlertas("Datos Incorrectos", "Usuario y/o Password Incorrectos", Alert.AlertType.ERROR);
    }

    return resultado;
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

  public void handleMouseEvent(MouseEvent mouseEvent) throws IOException {
    if (mouseEvent.getSource() == btnIconClose) {
      new animatefx.animation.FadeOut(anchRoot).play();
      System.exit(0);
    } else if (mouseEvent.getSource() == btnIconIniciar) {
      new ZoomIn(pnlFormSesion).play();
      pnlFormSesion.toFront();
      btnIniciar.requestFocus();
    } else if (mouseEvent.getSource() == btnIconRecuperar) {
      new ZoomIn(pnlFormClave).play();
      pnlFormClave.toFront();
      btnGenerar.requestFocus();
    } else if (mouseEvent.getSource() == btnImageBack) {
      new ZoomIn(pnlFormSesion).play();
      pnlFormSesion.toFront();
      btnIniciar.requestFocus();
    }
  }

  public void handleButtonAction(ActionEvent actionEvent) throws IOException {
    if (actionEvent.getSource().equals(btnIniciar)) {
      autenticarUsuario(actionEvent);
    } else if (actionEvent.getSource().equals(btnGenerar)) {
      enviarCorreo();
    } else if (actionEvent.getSource().equals(btnClose)) {
      System.exit(0);
    }
  }

  public void handleLinkAction(ActionEvent actionEvent) throws IOException {
    new ZoomIn(pnlFormClave).play();
    pnlFormClave.toFront();
    btnGenerar.requestFocus();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    System.out.println("initialize");
    Tooltip t = new Tooltip("Cerrar");
    Tooltip.install(btnIconClose, t);
  }

  public void enviarCorreo() {
    if (validarEmail()) {

      Usuario user = usuarioDao.obtenerUsuarioByEmail(txtEmail.getText().trim());
      if (user != null) {
        String nuevaClave = generarClave(6);
        boolean update = usuarioDao.cambiarClave(txtEmail.getText(), nuevaClave);
        if (update) {
          String mensaje = "Estimado "+ user.getNombres() +", se ha generado una contraseña temporal en el sistema Clean Wash.\n";
          mensaje += "Ingrese al sistema con los siguientes datos:\n\n";
          mensaje += "Usuario: "+ user.getUsuario()+"\n";
          mensaje += "Clave: "+ nuevaClave + "\n\n";
          mensaje += "Debera cambiar su contraseña al ingresar por primera vez.";
          MailService mailService = new MailService();
          boolean send = mailService.enviarConGMail(txtEmail.getText(), "Recuperar Clave", mensaje);
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
}
