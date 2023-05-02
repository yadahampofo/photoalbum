package model.photoalbum;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;

/**
 * Represents the PhotoAlbumModel Class which implements the IPhotoAlbum interface.
 */
public class PhotoAlbumModel implements IPhotoAlbum {


  /**
   * Represents the PhotoAlbum model constructor.
   */
  public PhotoAlbumModel() {
  }

  /**
   * Creates a new Photo Shape list to store all the photo Shapes created by the user.
   */
  ArrayList<IPhotoShape> photoShapeList = new ArrayList<>();

  /**
   * Creates a PhotoShape upon a users requests.
   * Immediately Adds Photo shape to Photo shape list upon creation.
   * @param name      a string name to uniquely  identify photo shape. Not null or empty.
   * @param type      a String to identify the type of  shape being created. Not null or empty.
   * @param center    A center object with the center coordinates  for the photo shape to be positioned
   * @param dimension A dimension object with the dimensions  for the photo shape to have
   * @param color     A color object with RGB numbers for the photo shape to have.
   * @return photoShapeObj returns a photo shape object.
   */
  public IPhotoShape createPhotoShape(String name, String type, Center center,
                                      Dimension dimension, Color color) throws IllegalArgumentException {
    if (center == null || dimension == null || color == null || name == null || type == null ||
            name.isEmpty() || type.isEmpty()) {
      throw new IllegalArgumentException("Invalid Parameter");
    }
    if (!photoShapeList.stream().map(IPhotoShape::getName).filter(name::equals).findFirst().isPresent()) {
      if (type.equalsIgnoreCase("Oval")) {
        Oval OvalObj = new Oval(name, type, center, dimension, color);
        photoShapeList.add(OvalObj);
        return OvalObj;
      } else if (type.equalsIgnoreCase("Rectangle")) {
        Rectangle RectangleObj = new Rectangle(name, type, center, dimension, color);
        photoShapeList.add(RectangleObj);
        return RectangleObj;
      }
      return null;
    }
    return null;
  }

  /**
   * Removes a specific photo shape(identified by name) from the photo shape list.
   *
   * @param NameofShapeTobeRemoved a string name to identify photo shape from list.
   *                               Not null or empty.
   */
  @Override
  public void removeShape(String NameofShapeTobeRemoved) {
    for (IPhotoShape photoShape : photoShapeList) {
      if (photoShape.getName() == NameofShapeTobeRemoved)
        photoShapeList.remove(photoShape); //
    }
  }

  /**
   * Moves a specific photo shape to a new location specified by the User.
   *
   * @param NameofShapeTobeColorChanged a string name to identify photo shape from list.
   *                                    Not null or empty.
   * @param newColor                    A color object with RGB numbers that
   *                                    the photo shape would be changed to.
   */
  @Override
  public void changeShapeColor(String NameofShapeTobeColorChanged, Color newColor) {
    for (IPhotoShape photoShape : photoShapeList) {
      if (photoShape.getName() == NameofShapeTobeColorChanged) {
        photoShape.changeColor(newColor);
      }
    }
  }


  /**
   * Moves a specific photo shape to a new location specified by the User.
   *
   * @param NameofShapeTobeMoved a string name to identify photo shape from list. Not negative.
   * @param newCenter            a double type coordinate to move the shape to. Not negative.
   */
  @Override
  public void moveShape(String NameofShapeTobeMoved, Center newCenter) {
    for (IPhotoShape photoShape : photoShapeList) {
      if (photoShape.getName() == NameofShapeTobeMoved) {
        photoShape.move(newCenter);
      }
    }
  }

  /**
   * Scales a specific photo shape based on dimensions chosen by the user.
   *
   * @param NameofShapeTobeScaled a string name to identify photo shape from list. Not null or empty.
   * @param newDimension          a double dimension measurement for the photo to be scaled to
   *                              . Not negative.
   */
  @Override
  public void scaleShape(String NameofShapeTobeScaled, Dimension newDimension) {
    for (IPhotoShape photoShape : photoShapeList) {
      if (photoShape.getName() == NameofShapeTobeScaled) {
        photoShape.scale(newDimension);
      }
    }
  }

  /**
   * Created a new Array List to store snapshots of the system.
   */
  ArrayList<Snapshot> snapshotsList = new ArrayList<>();

  /**
   * Takes a snapshot with a custom Description and a custom Snapshot ID created by a counter.
   * Also adds snapshot to  Snapshot List.
   *
   * @param customDescription a description for the screenshot taken. Not null
   *                          or empty.
   */
  @Override
  public Snapshot takeSnapshot(String customDescription) {
    int snapshotID = 0;
    LocalDateTime myCurrDateObj = LocalDateTime.now();
    DateTimeFormatter myDayMonthYearFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String myTimeStamp = myCurrDateObj.format(myDayMonthYearFormatObj);
    for (int i = 0; i <= snapshotsList.size(); i++) {
      snapshotID = i;
    }
    snapshotID++;
    ArrayList<IPhotoShape> copy = new ArrayList<IPhotoShape>();
    copy.addAll(photoShapeList);
    Snapshot justTaken = new Snapshot(String.format("%d", snapshotID), customDescription, myTimeStamp, copy);
    //justTaken.setShapes(this.photoShapeList);
    snapshotsList.add(justTaken); // Adds the new snapshot to the list immediately
    return justTaken;
  }

  /**
   * Deletes a snapshot based on its unique Snapshot ID.
   * Removes snapshot from Snapshot List.
   *
   * @param snapshotID an integer number to identify snapshot from list. Not negative.
   */
  @Override
  public void deleteSnapShot(int snapshotID) {
    for (ISnapshot snapshot : snapshotsList) {
      if (snapshot.getSnapshotID().equals(String.format("%d", snapshotID))) {
        snapshotsList.remove(snapshot);
        break;
      }
    }
  }


  /**
   * Return a list of the Snapshots taken by the user.
   *
   * @return the snapshots
   */
  @Override
  public List<Snapshot> getSnapshotList() {
    return snapshotsList;
  }

  /**
   * Return a list of the Photo Shapes created by the user.
   *
   * @return the photo shapes
   */
  @Override
  public List<IPhotoShape> getPhotoShapeList() {
    return photoShapeList;
  }


  /**
   * Method to create PhotoShape Log in order to keep track of all the changes demanded by the user.
   */
  static List<String> PhotoShapeLog = new ArrayList<>();

  /**
   * Method to add to log.
   */
  public static void addToLog(String Changes) throws IllegalArgumentException {
    if (Changes == null || Changes.isEmpty()) {
      throw new IllegalArgumentException();
    }
    PhotoShapeLog.add(Changes);
  }

  /**
   * The getPhotoShapeLog() method returns a List of String that is
   * the "Photo Albums log" of changes.
   * each log entry is a element in the list.
   *
   * @return Photo shape log.
   */
  public List<String> getPhotoShapeLog() {
    return PhotoShapeLog;
  }

}
