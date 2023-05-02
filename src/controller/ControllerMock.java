package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.PhotoAlbumModel;
import view.GraphicalSwingWindow;
import view.IPhotoAlbumView;
import view.WebSVGView;

/**
 * This represents the Controller Mock Class that implements the IPhotoAlbumController
 */
public class ControllerMock implements IPhotoAlbumController {
  private IPhotoAlbum model;
  private GraphicalSwingWindow graphicView; //Variable for the Graphical Interface
  private WebSVGView webView; //Variable for the  web view
  private IPhotoAlbumView view;
  private String filename;
  private static int height;
  private static int width;
  private String output;
  private int currIndex = 0;


  /**
   * This represents the Controller Mock constructor.
   *  @param filename a string name to uniquely identify the file being passed in. Not null or empty.
   *  @param output   a string name to uniquely  identify the output file requested. optional.
   *  @param model    a model object passed in to manage the data. Not null.
   *  @param height   an integer height requested by the user.Optional..
   *  @param view     a string identifier to uniquely  identify what type of view was requested.Not empty.
   *  @param width    an integer width requested by the user.Optional.
   */
  public ControllerMock(PhotoAlbumModel model, String filename, int height, int width,
                        String output, String view) {
    this.model = model;
    this.filename = filename;
    this.height = height;
    this.width = width;
    this.output = output;
    if (view.equals("web")) {
      webView = new WebSVGView(this.output, this.width, this.height);
    } else {
      graphicView = new GraphicalSwingWindow(this.model, this.width, this.height);
    }
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
              for(i = 1 ; i < outputArray.size() - 1 ; i++ ){
                str.append(outputArray.get(i)).append(" ");
              }
              System.out.print(str);
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

  }
