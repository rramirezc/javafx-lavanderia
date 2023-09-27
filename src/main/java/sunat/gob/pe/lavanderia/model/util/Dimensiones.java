/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.lavanderia.model.util;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import sunat.gob.pe.lavanderia.App;

/**
 *
 * @author Herman
 */
public class Dimensiones {

  public static void redimensionar(Scene scene) {
    scene.getWindow().setHeight(650.0);
    scene.getWindow().setWidth(1280.0);
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    scene.getWindow().setX((primScreenBounds.getWidth() - scene.getWindow().getWidth()) / 2);
    scene.getWindow().setY((primScreenBounds.getHeight() - scene.getWindow().getHeight()) / 2);
  }
}
