package model.photoalbum;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;

/**
 * Represents the IPhotoShape interface for each Photo shape created by the user.
 */
public interface IPhotoShape {
  /**
   * Changes a specific photo shape to a new color specified by the User.
   * @param newColor A color object with RGB numbers that the photo shape would be changed to.
   */
  void changeColor(Color newColor);


  /**
   * Moves a specific photo shape to a new location specified by the User.
   * @param newCenter an integer number to identify snapshot from list. Not negative.
   */
  void move(Center newCenter);


  /**
   * Scales a specific photo shape based on dimensions chosen by the user.
   *
   * @param newDimension an integer number to identify snapshot from list. Not negative.
   */
  void scale(Dimension newDimension);


  /**
   * Return the color Object of each photo shape.
   * @return the color of the Photo shape.
   */
  Color getColor();

  /**
   * Return the First Dimension Parameter.
   * @return the first dimension.
   */
  int getDimension01();

  /**
   * Return the Second Dimension Parameter.
   * @return the Second dimension.
   */
  int getDimension02();

  /**
   * Return unique name assigned to each shape.
   * @return the unique name.
   */
  String getName();

  /**
   * Return the center Object of each photo shape.
   * @return the center/corner of the Photo shape.
   */
  Center getCenter();


  /**
   * Return  photo shape type.
   * @return  the type.
   */
  String getType();

  /**
   * The appropriate String to format all the details of a snapshot taken by the user.
   * @return the string description with all details.
   */
  String toString();
}
