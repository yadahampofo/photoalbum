package model.photoalbum;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;


/**
 * Represents the AbstractPhotoShape Class which implements the IPhotoShape interface.
 */
public abstract class AbstractPhotoShape implements IPhotoShape{
  private String type;
  private Center center;
  private Color color;
  private Dimension dimension;
  private String name;

  /**
   * Represents the Abstract Photo shape Constructor.
   *
   * @param name      a string name to uniquely  identify photo shape. Not null or empty.
   * @param type      a String to identify the type of  shape being created. Not null or empty.
   * @param center    A center object with the center coordinates  for the photo shape to be positioned
   * @param dimension A dimension object with the dimensions  for the photo shape to have
   * @param color     A color object with RGB numbers for the photo shape to have
   */
  public AbstractPhotoShape(String name, String type, Center center, Dimension dimension, Color color) {
    this.name = name;
    this.center = center;
    this.dimension = dimension;
    this.color = color;
    this.type = type;
  }

  /**
   * Changes a specific photo shape to a new color specified by the User.
   *
   * @param newColor A color object with RGB numbers that the photo shape would be changed to.
   */
  public void changeColor(Color newColor) {
    PhotoAlbumModel.addToLog(this.getName()
            + " " + "changes color from" + " " + "(" + this.color.toString() + ")"
            + " " + "to" + " " + "(" + newColor.toString() + ")");
    this.color = newColor;
  }

  /**
   * Return the center Object of each photo shape.
   *
   * @return the center/corner of the Photo shape.
   */
  public Center getCenter() {
    return this.center;
  }

  /**
   * Moves a specific photo shape to a new location specified by the User.
   *
   * @param newCenter A color object with RGB numbers that the photo shape would be changed to.
   */
  public void move(Center newCenter) {
    PhotoAlbumModel.addToLog(this.getName()
            + " " + "moves from" + " " + "(" + this.center.toString() + ")"
            + " " + "to" + " " + "(" + newCenter.toString() + ")");
    this.center = newCenter;
  }


  /**
   * Scales a specific photo shape based on dimensions chosen by the user.
   *
   * @param newDimension an integer number to identify snapshot from list. Not negative.
   */
  public void scale(Dimension newDimension) {
    PhotoAlbumModel.addToLog(this.getName()
            + " " + "scales from" + " " + this.dimension.toString()
            + " " + "to" + " " + newDimension.toString());
    this.dimension = newDimension;
  }

  /**
   * Return the color Object of each photo shape.
   *
   * @return the color of the Photo shape.
   */
  public Color getColor() {
    return this.color;
  }


  /**
   * Return the First Dimension Parameter.
   *
   * @return the first dimension.
   */
  public int getDimension01() {
    return this.dimension.getDimension01();
  }


  /**
   * Return the Second Dimension Parameter.
   *
   * @return the Second dimension.
   */
  public int getDimension02() {
    return this.dimension.getDimension02();
  }


  /**
   * Return unique name assigned to each shape.
   *
   * @return the unique name.
   */
  public String getName() {
    return this.name;
  }


  /**
   * Return  photo shape type.
   *
   * @return the type.
   */
  public String getType() {
    return this.type;
  }

}
