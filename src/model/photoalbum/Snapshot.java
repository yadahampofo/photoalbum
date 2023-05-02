package model.photoalbum;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the Snapshot Class which implements the ISnapshot interface.
 */
public class Snapshot implements ISnapshot{
  private final ArrayList<IPhotoShape> copy;
  private String snapshotID;
  private String description;
  private String timeStamp;
  private ArrayList<IPhotoShape> shapeList;

  /**
   * Represents the Snapshot constructor.
   *
   * @param snapshotID  a string name to uniquely identify the snapshot. Not null or empty.
   * @param description a String description for the snapshot. Not null or empty.
   * @param timeStamp   A string time stamp representing the date snapshot was taken.Not null or empty
   **/
  public Snapshot(String snapshotID, String description, String timeStamp, ArrayList shapeList)
          throws IllegalArgumentException {
    if (snapshotID == null  || timeStamp == null
            || snapshotID.isEmpty() || timeStamp.isEmpty()) {
      throw new IllegalArgumentException("Invalid Parameter");
    }
    this.snapshotID = snapshotID;
    this.description = description;
    this.timeStamp = timeStamp;
    //this.shapeList = new ArrayList<>();
    this.shapeList = shapeList; //Make a deep copy  //save an entirely new thing
    this.copy = this.shapeList;
  }

  /**
   * Return the description attached to a snapshot.
   *
   * @return the description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Return the timeStamp of a snapshot.
   *
   * @return the timeStamp.
   */
  public String getTimestamp() {
    return this.timeStamp;
  }

  /**
   * Return the snapshotID.
   *
   * @return the snapshotID.
   */
  public String getSnapshotID() {
    return this.snapshotID;
  }

  public void setShapes(ArrayList<IPhotoShape> shapes){
    this.shapeList = Stream.concat(shapes.stream(), this.copy.stream())
            .collect(Collectors.toCollection(ArrayList::new));
  }

  public ArrayList<IPhotoShape> getShapes() {
    return this.shapeList;
  }



  /**
   * Represents the appropriate String format for the Snapshot and all its attributes.
   * It includes the snapshotID, timestamp and the description.
   */
  @Override
  public String toString() {
    return "Snapshot ID: " + this.getSnapshotID()
            + "\nTimestamp: " + this.getTimestamp()
            + "\nDescription:" + this.getDescription() + "\n";
  }
}
