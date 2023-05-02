package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;
import model.photoalbum.IPhotoAlbum;
import view.GraphicalSwingWindow;
import view.IPhotoAlbumView;
import view.WebSVGView;


/**
 * This represents the PhotoAlbum Controller Class which implements IPhotoAlbumController.
 */
public class PhotoAlbumController implements IPhotoAlbumController {
  private static IPhotoAlbum model;
  private GraphicalSwingWindow graphicView; //Variable for the Graphical Interface
  private WebSVGView webView; //Variable for the  web view
  private IPhotoAlbumView view;
  private static int height;
  private static int width;
  private static String filename;
  private static String output;
  private int currIndex = 0;

  /**
   * This represents the Photo Album Controller constructor.
   * @param filename a string name to uniquely identify the file being passed in. Not null or empty.
   * @param output   a string name to uniquely  identify the output file requested. optional.
   * @param model    a model object passed in to manage the data. Not null.
   * @param height   an integer height requested by the user.Optional..
   * @param view     a string identifier to uniquely  identify what type of view was requested.Not empty.
   * @param width    an integer width requested by the user.Optional.
   */
  public PhotoAlbumController(IPhotoAlbum model, String filename, int height, int width,
                              String output, String view)
          throws IllegalArgumentException {
    if (model == null || filename == null || view == null || view.isEmpty()
            ||height < 0 || width < 0) {
      throw new IllegalArgumentException("Controller arg issue");
    }
    this.model = model;
    this.filename = filename;
    this.height = height; //Passed in from the user
    this.width = width; //Passed in from the user
    this.output = output;
    if (view.equals("web")) {
      webView = new WebSVGView(this.output, this.width, this.height);
    } else {
      graphicView = new GraphicalSwingWindow(this.model, this.width, this.height);
      graphicView.addQuitButtonListener(new QuitListener());
      graphicView.addSelectButtonListener(new SelectListener());
      graphicView.addNextButtonListener(new NextListener());
      graphicView.addPrevButtonListener(new PrevListener());
      graphicView.addOkButtonListener(new okListener());
    }
  }

  /**
   * Gets the screen width.
   *
   * @return width  an integer width requested by the user.
   */
  public static int getScreenWidth() {
    return width;
  }

  /**
   * Gets the screen height.
   *
   * @return width  an integer height requested by the user.
   */
  public static int getScreenHeight() {
    return height;
  }

  /**
   * Gets the filename.
   *
   * @return filename input  an integer height requested by the user.
   */
  public static String getFilename() {
    return filename;
  }

  /**
   * Gets the model.
   *
   * @return model object.
   */
  public static IPhotoAlbum getModel() {
    return model;
  }

  /**
   * The go method for the controller.
   *
   * @param model the model object for the program.
   */
  @Override
  public void go(IPhotoAlbum model) {
    try {
      parser(this.filename);
      if (graphicView != null) {
        graphicView.Draw(this.model);
      }
      if (webView != null) {
        webView.Draw(this.model);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * The file parser used to read the information from files.
   *
   * @param filename file name that the user inputs.
   */
  public void parser(String filename) throws IOException {
    List<String> outputArray = new ArrayList<String>();
    File file = new File(filename);
    BufferedReader reader;
    reader = new BufferedReader(new FileReader(file));
    String line = reader.readLine();
    while (line != null) {
      String[] split_info = line.toString().trim().replaceAll("\\s+", " ").split(" ");
      List resultant_info = Arrays.asList(split_info);
      if (!resultant_info.contains("#")) {
        if (resultant_info.size() >= 1) {
          outputArray = resultant_info;
          if (outputArray.get(0).equalsIgnoreCase("shape")) {
            this.model.createPhotoShape(outputArray.get(1), outputArray.get(2),
                    new Center(Integer.valueOf(outputArray.get(3)),
                            Integer.valueOf(outputArray.get(4))),
                    new Dimension(Integer.valueOf(outputArray.get(5)),
                            Integer.valueOf(outputArray.get(6))),
                    new Color(Integer.valueOf(outputArray.get(7)),
                            Integer.valueOf(outputArray.get(8)),
                            Integer.valueOf(outputArray.get(9))));
          }
          if (outputArray.get(0).equalsIgnoreCase("snapshot")) {
            if (outputArray.size() == 1) {
              this.model.takeSnapshot("");
            } else {
              int i;
              StringBuilder str = new StringBuilder();
              for (i = 1; i < outputArray.size() - 1; i++) {
                str.append(outputArray.get(i)).append(" ");
              }
              this.model.takeSnapshot(str.toString());
            }
          }
          if (outputArray.get(0).equalsIgnoreCase("move")) {
            this.model.moveShape(outputArray.get(1), new Center(Integer.valueOf(outputArray.get(2)),
                    Integer.valueOf(outputArray.get(3))));

          }
          if (outputArray.get(0).equalsIgnoreCase("color")) {
            this.model.changeShapeColor(outputArray.get(1),
                    new Color(Integer.valueOf(outputArray.get(2)),
                            Integer.valueOf(outputArray.get(3)),
                            Integer.valueOf(outputArray.get(4))));
          }
          if (outputArray.get(0).equalsIgnoreCase("resize")) {
            this.model.scaleShape(outputArray.get(1),
                    new Dimension(Integer.valueOf(outputArray.get(2)),
                            Integer.valueOf(outputArray.get(3))));
          }
          if (outputArray.get(0).equalsIgnoreCase("remove")) {
            this.model.removeShape(outputArray.get(1));
          }
        }
      }
      line = reader.readLine(); // read next line
    }
    reader.close();
  }


  /**
   * This class represents the QuitListener for the Quit Button.
   */
  public class QuitListener implements ActionListener {
    /**
     * The quit action performed when user clicks on the Quit Button.
     */
    public void actionPerformed(ActionEvent e) {
      graphicView.Dispose();
      System.exit(0);
    }
  }

  /**
   * This class represents the SelectListener for the Select Button.
   */
  public class SelectListener implements ActionListener {
    /**
     * The select action performed when user clicks on the select Button.
     * Creates a show menu box.
     */
    public void actionPerformed(ActionEvent e) {
      graphicView.showMenuWindow(model.getSnapshotList());
    }
  }

  /**
   * This class represents the NextListener for the Next Button.
   */
  public class NextListener implements ActionListener {
    /**
     * The next action performed when user clicks on the Next Button.
     * Goes to the next snapshot in the list.
     */
    public void actionPerformed(ActionEvent e) {
      try {
        graphicView.goForward(currIndex + 1); // Clear what is currently there;
        currIndex = currIndex + 1;
      } catch (IndexOutOfBoundsException ex) {
        graphicView.showNoNextError();
      }
    }
  }


  /**
   * This class represents the PrevListener for the Previous Button.
   */
  public class PrevListener implements ActionListener {
    /**
     * The previous action performed when user clicks on the Next Button.
     * Goes to the previous snapshot in the list.
     */
    public void actionPerformed(ActionEvent e) {
      try {
        graphicView.goBackward(currIndex - 1);
        currIndex = currIndex - 1;
      } catch (IndexOutOfBoundsException ex) {
        graphicView.showNoPrevError();
      }
    }
  }

  /**
   * This class represents the OKListener for the Previous Button.
   */
  public class okListener implements ActionListener {
    /**
     * The okay action performed when user clicks on the OK Button.
     * Goes to the snapshot selected int the menu box.
     */
    public void actionPerformed(ActionEvent e) {
      currIndex = graphicView.updateCurrSnapshotIndex();
      graphicView.goDirect(currIndex);
    }
  }
}



