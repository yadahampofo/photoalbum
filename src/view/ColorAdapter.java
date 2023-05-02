package view;

import model.photo.Color;
/**
 * This represents the Color Adapter Class.
 */
public class ColorAdapter {
  /**
   * This represents the transformation from model color object to Java AWT Color.
   */
  public static java.awt.Color myColorToJavaRGB(Color color) {
    return new java.awt.Color(color.getC1(), color.getC2(), color.getC3());
  }

  /**
   * This represents the transformation from Java AWT Color to the model color object.
   */
  public static Color JavaRGBTomyColor(java.awt.Color color){
    return new Color(color.getRed(), color.getGreen(), color.getBlue());
  }
}
