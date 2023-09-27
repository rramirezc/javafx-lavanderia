package sunat.gob.pe.lavanderia;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sunat.gob.pe.lavanderia.model.entities.Usuario;

/**
 * JavaFX App
 */
public class App extends Application {

  public static Scene scene;

  @Override
  public void start(Stage stage) {

    try {
      Parent login = App.loadFXML("login");
      scene = new Scene(login);
      //stage.initStyle(StageStyle.TRANSPARENT);
      stage.setScene(scene);
      new animatefx.animation.FadeIn(login).play();
      stage.setTitle("Bienvenido al programa");
      Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
      stage.getIcons().add(icon);
      stage.show();
      //primaryStage = stage;
    } catch (IOException ie) {
      System.out.println(ie.getMessage());
      ie.printStackTrace();
    }

  }

  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
  }

  //captura una excepción try { }catch
  //lanzo una excepción
  public static void main(String[] args) {
    launch();
  }

}
