package model.photoalbum;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;

/**
 * Represents the Oval Class which implements the AbstractPhotoShape interface.
 */
public class Oval extends AbstractPhotoShape {
  /**
   * Represents the Oval constructor.
   *
   * @param name      a string name to uniquely  identify photo shape. Not null or empty.
   * @param type      a String to identify the type of  shape being created. Not null or empty.
   * @param center    A center object with the center coordinates  for the photo shape to be positioned
   * @param dimension A dimension object with the dimensions  for the photo shape to have
   * @param color     A color object with RGB numbers for the photo shape to have
   **/
  public Oval(String name, String type, Center center, Dimension dimension, Color color) {
    super(name, type, center, dimension, color);
  }


  /**
   * To String Method for the Description of Oval.
   * It includes the name, type, dimensions and color.
   */
  @Override
  public String toString() {
    return "        <" + "ellipse" + " " + "id=\"" + this.getName().toString() +"\" cx=\"" + this.getCenter().getPoint1() + "\" cy=\"" + this.getCenter().getPoint2() + "\" rx=\"" + this.getDimension01() + "\" ry=\"" + this.getDimension02() + "\" fill=\"rgb(" + this.getColor().getC1() + "," + this.getColor().getC2() + "," + this.getColor().getC3() + ")\" visibility=\"visible\">\n" +
            "        </" + "ellipse" + ">\n";

  }

}
