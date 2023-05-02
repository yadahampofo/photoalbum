package test;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;
import model.photoalbum.IPhotoShape;
import model.photoalbum.PhotoAlbumModel;
import model.photoalbum.Snapshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test cases for the Photo Album model. Verifying that PhotoAlbum is properly managed, and
 * all transformations are properly validated.
 */
public class PhotoAlbumModelTest {
  private PhotoAlbumModel PhotoAlbum01;
  private String Date01;

  /**
   * This represents the setup for the for PhotoAlbum model.
   */
  @Before
  public void SetUp() {
    LocalDateTime myCurrDateObj = LocalDateTime.now();
    DateTimeFormatter myDayMonthYearFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Date01 = myCurrDateObj.format(myDayMonthYearFormatObj);
    PhotoAlbum01 = new PhotoAlbumModel();
  }

  /**
   * Test case for CreatePhotoShape.
   */
  @Test
  public void testCreatePhotoShape() {
    PhotoAlbum01.createPhotoShape("Shape2011", "Oval", new Center(500, 100),
            new Dimension(60, 30), new Color(0, 0, 1));
    PhotoAlbum01.createPhotoShape("Shape5044", "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    assertEquals("[        <ellipse id=\"Shape2011\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,1)\" visibility=\"visible\">\n" +
                    "        </ellipse>\n" +
                    ",         <rect id=\"Shape5044\" x=\"500\" y=\"100\" width=\"20\" height=\"30\" fill=\"rgb(0,0,1)\" visibility=\"visible\">\n" +
                    "        </rect>\n" +
"]", PhotoAlbum01.getPhotoShapeList().toString());
  }


  /**
   * Test case for Change Photo color.
   */
  @Test
  public void testChangeShapeColor() {
    IPhotoShape photoShape01 = PhotoAlbum01.createPhotoShape("Shape2011",
            "Oval", new Center(500, 100), new Dimension(60, 30), new Color(0, 0, 1));
    IPhotoShape photoShape02 = PhotoAlbum01.createPhotoShape("Shape5044",
            "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    Color newColor = new Color(0, 0, 1);
    PhotoAlbum01.changeShapeColor("Shape2011", newColor);
    assertTrue(photoShape01.getColor().equals(newColor));
  }


  /**
   * Test case for Remove Photo shape.
   */
  @Test
  public void testRemoveShape() {
    IPhotoShape photoShape01 = PhotoAlbum01.createPhotoShape("Shape2011",
            "Oval", new Center(500, 100), new Dimension(60,30), new Color(0, 0, 1));
    IPhotoShape photoShape02 = PhotoAlbum01.createPhotoShape("Shape5044",
            "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    PhotoAlbum01.removeShape("Shape2011");
    assertEquals("[        <rect id=\"Shape5044\" x=\"500\" y=\"100\" width=\"20\" height=\"30\" fill=\"rgb(0,0,1)\" visibility=\"visible\">\n" +
                    "        </rect>\n" + "]",
            PhotoAlbum01.getPhotoShapeList().toString());

  }

  /**
   * Test case for Scale Photo shape.
   */
  @Test
  public void testScaleShape() {
    IPhotoShape photoShape01 = PhotoAlbum01.createPhotoShape("Shape2011",
            "Oval", new Center(500, 100), new Dimension(60, 30), new Color(0, 0, 1));
    IPhotoShape photoShape02 = PhotoAlbum01.createPhotoShape("Shape5044",
            "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    PhotoAlbum01.scaleShape("Shape2011", new Dimension(3, 5));
    assertEquals(3, photoShape01.getDimension01(), 0.01);
    assertEquals(5, photoShape01.getDimension02(), 0.01);

  }

  /**
   * Test case for Move Photo shape.
   */
  @Test
  public void testMoveShape() {
    IPhotoShape photoShape01 = PhotoAlbum01.createPhotoShape("Shape2011",
            "Oval", new Center(500, 100), new Dimension(60, 30), new Color(0, 0, 1));
    IPhotoShape photoShape02 = PhotoAlbum01.createPhotoShape("Shape5044",
            "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    Center newCenterLocation = new Center(405, 506);
    PhotoAlbum01.moveShape("Shape2011", newCenterLocation);
    assertTrue(photoShape01.getCenter().equals(newCenterLocation));
  }

  /**
   * Test case for Take Snapshot.
   */
  @Test
  public void takeSnapshot() {
    PhotoAlbum01.createPhotoShape("O", "Oval", new Center(500, 100),
            new Dimension(60, 30), new Color(0, 0, 1));
    PhotoAlbum01.createPhotoShape("R", "Rectangle", new Center(100, 400),
            new Dimension(20, 30), new Color(0, 1, 1));
    PhotoAlbum01.takeSnapshot("1st Snapshot");
    PhotoAlbum01.takeSnapshot("2nd Snapshot");
    assertEquals("[Snapshot ID: 1\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:1st Snapshot\n," +
            " Snapshot ID: 2\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:2nd Snapshot\n]", PhotoAlbum01.getSnapshotList().toString());
  }

  /**
   * Test case for delete Snapshot.
   */
  @Test
  public void deleteSnapShot() {
    PhotoAlbum01.createPhotoShape("Shape2011", "Oval", new Center(500, 100),
            new Dimension(60, 30), new Color(0, 0, 1));
    PhotoAlbum01.createPhotoShape("Shape5044", "Rectangle", new Center(100, 400),
            new Dimension(20, 30), new Color(0, 1, 1));
    Snapshot Snapshot01 = PhotoAlbum01.takeSnapshot("1st Snapshot");
    Snapshot Snapshot02 = PhotoAlbum01.takeSnapshot("2nd Snapshot");
    Snapshot Snapshot03 = PhotoAlbum01.takeSnapshot("3rd Snapshot");
    PhotoAlbum01.deleteSnapShot(1);
    assertEquals("[Snapshot ID: 2\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:2nd Snapshot\n," +
            " Snapshot ID: 3\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:3rd Snapshot\n]", PhotoAlbum01.getSnapshotList().toString());
  }

  /**
   * Test case for get Snapshot List.
   */
  @Test
  public void getSnapshotList() {
    PhotoAlbum01.createPhotoShape("O", "Oval", new Center(500, 100),
            new Dimension(60, 30), new Color(0, 0, 1));
    PhotoAlbum01.createPhotoShape("R", "Rectangle", new Center(100, 400),
            new Dimension(20, 30), new Color(0, 1, 1));
    Snapshot Snapshot01 = PhotoAlbum01.takeSnapshot("1st Snapshot");
    Snapshot Snapshot02 = PhotoAlbum01.takeSnapshot("2st Snapshot");
    Snapshot Snapshot03 = PhotoAlbum01.takeSnapshot("3st Snapshot");
    assertEquals("[Snapshot ID: 1\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:1st Snapshot\n," +
            " Snapshot ID: 2\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:2st Snapshot\n," +
            " Snapshot ID: 3\n" +
            "Timestamp: " + Date01 + "\n" +
            "Description:3st Snapshot\n]", PhotoAlbum01.getSnapshotList().toString());
  }

  /**
   * Test case for get PhotoShape List.
   */
  @Test
  public void getPhotoShapeList() {
    PhotoAlbum01.createPhotoShape("O", "Oval", new Center(500, 100),
            new Dimension(60, 30), new Color(0, 0, 1));
    PhotoAlbum01.createPhotoShape("R", "Rectangle", new Center(100, 400),
            new Dimension(20, 30), new Color(0, 1, 1));
    assertEquals("[        <ellipse id=\"O\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,1)\" visibility=\"visible\">\n" +
                    "        </ellipse>\n" +
                    ",         <rect id=\"R\" x=\"100\" y=\"400\" width=\"20\" height=\"30\" fill=\"rgb(0,1,1)\" visibility=\"visible\">\n" +
                    "        </rect>\n]"
            , PhotoAlbum01.getPhotoShapeList().toString());
  }

  /**
   * The Test case for getPhotoShapeLog()
   */
  @Test
  public void getPhotoShapeLog() {
    IPhotoShape photoShape01 = PhotoAlbum01.createPhotoShape("Shape2011",
            "Oval", new Center(500, 100), new Dimension(60, 30),
            new Color(0, 0, 1));
    IPhotoShape photoShape02 = PhotoAlbum01.createPhotoShape("Shape5044",
            "Rectangle", new Center(500, 100),
            new Dimension(20, 30), new Color(0, 0, 1));
    Center newCenterLocation = new Center(405, 506);
    PhotoAlbum01.moveShape("Shape2011", newCenterLocation);
    assertEquals("[Shape2011 moves from (500,100) to (405,506)]",
            PhotoAlbum01.getPhotoShapeLog().toString());

  }
}