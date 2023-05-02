package test;

import java.util.ArrayList;
import java.util.List;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.IPhotoShape;
import model.photoalbum.Oval;

import model.photoalbum.Rectangle;
import model.photoalbum.Snapshot;

/**
 * This represents the Mock Model Class for the Photo Album Application
 */
public class MockModel implements IPhotoAlbum {
  private StringBuilder log;
  private IPhotoShape shape;
  private ArrayList<IPhotoShape> shapeList;
  private ArrayList<Snapshot> snapshotList;
  private ArrayList<String> photoLogList;

  /**
   * This represents the Mock Model Constructor for the Photo Album Application
   */
  public MockModel(StringBuilder log) {
    this.log = log;
    this.shapeList = null;
    this.snapshotList = null;
    this.photoLogList = null;

  }

  /**
   * The Log for recording all the changes as the file is parsed.
   */
  public StringBuilder getLog(){
    return this.log;
  }
  @Override
  public void removeShape(String NameofShapeTobeRemoved) {
    log.append( NameofShapeTobeRemoved + "removed");
  }

  /**
   * Method to log a change of color.
   */
  @Override
  public void changeShapeColor(String NameofShapeTobeColorChanged, Color newColor) {
    log.append(NameofShapeTobeColorChanged + " " + "changed color to" + " " + "(" + newColor.getC1()
            + newColor.getC2() +  newColor.getC3() + ")" );
    log.append("changed color");
  }

  /**
   * Method to log the move of a shape .
   */
  @Override
  public void moveShape(String NameofShapeTobeMoved, Center newCenter) {
    log.append(NameofShapeTobeMoved + " " + "moved to" + " " + "(" + newCenter.getPoint1()
            + newCenter.getPoint2() + ")" );

  }

  /**
   * Method to log the scale of a shape .
   */
  @Override
  public void scaleShape(String NameofShapeTobeScaled, Dimension newDimension) {
    log.append(NameofShapeTobeScaled + " " + "scaled to" + " " + "(" + newDimension.getDimension01()
            + newDimension.getDimension02() + ")" );
  }

  /**
   * Method to log the deletion of a snapshot.
   */
  @Override
  public void deleteSnapShot(int snapshotID) {
    log.append( snapshotID + "deleted");
  }

  /**
   * Method to log  a snapshot being taken.
   */
  @Override
  public Snapshot takeSnapshot(String customDescription) {
    log.append("Snapshot taken");
    return null;
  }

  /**
   * Method to log the creation of a new Photoshape.
   */
  @Override
  public IPhotoShape createPhotoShape(String name, String type, Center center, Dimension dimension, Color color) {
    if(type.equalsIgnoreCase("rectangle")){
      log.append( "Rectangle created");
      shape = new Rectangle(name, type, center, dimension, color);
      shapeList.add(shape);
      log.append(shape);
    } else if (type.equalsIgnoreCase("oval")) {
      log.append("Oval created");
      shape = new Oval(name, type, center, dimension, color);
      shapeList.add(shape);
      log.append(new Oval(name, type, center, dimension, color));
    }
    return shape;
  }

  /**
   * This method returns a get snapshot list.
   */
  @Override
  public List<Snapshot> getSnapshotList() {
    return snapshotList;
  }

  /**
   * This method returns a get PhotoShape list.
   */
  @Override
  public List<IPhotoShape> getPhotoShapeList() {
    return shapeList;
  }

  /**
   * This method returns a get PhotoShape log.
   */
  @Override
  public List<String> getPhotoShapeLog() {
    return photoLogList;
  }


}
