package model.photoalbum;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;


/**
 * Represents the Rectangle Class which implements the AbstractPhotoShape interface.
 */
public class Rectangle extends AbstractPhotoShape {
  /**
   * Represents the Rectangle constructor .
   *
   * @param name      a string name to uniquely  identify photo shape. Not null or empty.
   * @param type      a String to identify the type of  shape being created. Not null or empty.
   * @param center    A center object with the center coordinates  for the photo shape to be positioned
   * @param dimension A dimension object with the dimensions  for the photo shape to have
   * @param color     A color object with RGB numbers for the photo shape to have
   **/
  public Rectangle(String name, String type, Center center, Dimension dimension, Color color) {
    super(name, type, center, dimension, color);
  }


  /**
   * To String Method for the Description of Rectangle.
   * It includes the name, type, dimensions and color.
   */
  @Override
  public String toString() {
    return "        <" + "rect" + " " + "id=\"" + this.getName().toString() +"\" x=\"" + this.getCenter().getPoint1() + "\" y=\"" + this.getCenter().getPoint2() + "\" width=\"" + this.getDimension01() + "\" height=\"" + this.getDimension02() + "\" fill=\"rgb(" + this.getColor().getC1() + "," + this.getColor().getC2() + "," + this.getColor().getC3() + ")\" visibility=\"visible\">\n" +
            "        </" + "rect" + ">\n";

  }
}
