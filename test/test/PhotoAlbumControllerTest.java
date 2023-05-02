package test;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import controller.IPhotoAlbumController;
import controller.PhotoAlbumController;
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.PhotoAlbumModel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PhotoAlbumControllerTest {

  //You should be able to test your HTML view sufficiently by parameterizing it over
  // alternate input and output sources.

  /**
   * Tests an Alternate input with Buildings.txt provided.
   * Outputs to test1.html in Java View Package
   */
  @Test
  public void testGoHTMLWithBuildingsTxt() {
    StringBuilder photoLog  = new StringBuilder();
    IPhotoAlbum model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, "buildings.txt",
            800,  800 ,  "test1.html", "web" );
    controller.go(model);
    //HTML file successfully created in view package
  }

  /**
   * Tests an Alternate input with hoops.txt provided.
   * Outputs to test2.html in Java View Package
   */
  @Test
  public void testGoHTMLWithHoopsTxt() {
    StringBuilder photoLog  = new StringBuilder();
    IPhotoAlbum model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, "hoops.txt",
            1000,  1000 ,  "test2.html", "web" );
    controller.go(model);
    //HTML file successfully created in view package

  }

  /**
   * Tests an Alternate input with Teris_wallpaper.txt provided.
   * Outputs to test3.html in Java View Package
   */
  @Test
  public void testGoHTMLIWithTerisTxt() {
    StringBuilder photoLog  = new StringBuilder();
    IPhotoAlbum model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, "teris_wallpaper.txt",
            800,  800 ,  "test3.html", "web" );
    controller.go(model);
    //HTML file successfully created in view package
  }


  /**
   * Test case for getting uses desired screen height.
   */
  @Test
  public void testGetScreenWidth() {
    StringBuilder photoLog = new StringBuilder();
    Reader in = new StringReader("shape window000 rectangle 100 500 20 20 255 255 255");
    PhotoAlbumModel model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, "buildings.txt",
            800,  800 ,  "web.html", "web" );
    assertEquals(800, PhotoAlbumController.getScreenWidth());//inputs reached the controller correctly

  }

  /**
   * Test case for getting uses desired screen height.
   */
  @Test
  public void testGetScreenHeight() {
    StringBuilder photoLog = new StringBuilder();
    Reader inTXT = new StringReader("shape window000 rectangle 100 500 20 20 255 255 255");
    PhotoAlbumModel model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, "inTXT",
            800,  700 ,  "web.html", "web" );
    assertEquals(800, PhotoAlbumController.getScreenHeight());//inputs reached the controller correctly
  }


  /////////////////////////////////Testing Bad Controller inputs ///////////////////////////

  /**
   * Test null model input.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    PhotoAlbumModel model = null;
    IPhotoAlbumController controller = new PhotoAlbumController(null, "buildings.txt",
            800,  800 ,  "test1.html", "web" );
    assertEquals(null,PhotoAlbumController.getModel());

  }

  /**
   * Test null File name input.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNullFilenameInput() {
    PhotoAlbumModel model = new PhotoAlbumModel();
    IPhotoAlbumController controller = new PhotoAlbumController(model, null,
            800,  800 ,  "test1.html", "web" );
    assertEquals(null,PhotoAlbumController.getFilename());

  }
  /**
   * Test negative Dimension inputs.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testNegativeScreenDimensionsInput() {
    PhotoAlbumModel model = null;
    IPhotoAlbumController controller = new PhotoAlbumController(model, "buildings.txt",
            -800,  -800 ,  "test1.html", "web" );
    assertEquals(-800,PhotoAlbumController.getScreenHeight());
  }


}
