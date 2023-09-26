package sunat.gob.pe.lavanderia;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
      stage.show();

    } catch (IOException ie) {
      System.out.println(ie.getMessage());
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
