package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import controller.PhotoAlbumController;
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.IPhotoShape;
import model.photoalbum.PhotoAlbumModel;
import model.photoalbum.Snapshot;

public class WebSVGView implements IPhotoAlbumView {
  private final int width;
  private final int height;
  private String outputFile;
  private IPhotoAlbum model;

  public WebSVGView(String outputFile, int width, int height){
    this.outputFile = outputFile;
    this.width = width;
    this.height = height;
  }

  @Override
  public void Draw(IPhotoAlbum model) {
    this.model = model;
    File f = new File("/Users/yadahampofo/Desktop/CS_5004/Homework/Homework_09/src/view/"
            + outputFile);
    try {
      StringBuilder strBuilder = new StringBuilder();
      strBuilder.append("<!DOCTYPE html>\n");
      strBuilder.append("<html>\n");
      strBuilder.append("<head>\n");
      strBuilder.append("<style>\n");
      strBuilder.append("  .snapshot { \n" +
              "        border: 5px outset red;\n" +
              "        background-color: lightblue;}\n");
      strBuilder.append("</style>\n");
      strBuilder.append("</head>\n");
      strBuilder.append("<body>\n");
      strBuilder.append("<h1>HTML with SVG for the Evening!!</h1>\n");

      for (Snapshot s : this.model.getSnapshotList()) {
        strBuilder.append(" <div class=\"snapshot\">\n" +
                "    <h2>" + "SNAPSHOT ID: " + s.getSnapshotID().toString() + "</h2>\n" +
                "    <h3>" + "Description:" + " " + s.getDescription().toString() + "</h3>\n" +
                "    <svg width=\"" + this.width + "\" height=\"" + this.height + "\">\n");
        for (IPhotoShape photoShape : s.getShapes()) {
          strBuilder.append(photoShape.toString());
        }

        strBuilder.append("   </svg>\n");
        strBuilder.append("</div>\n");
      }
      strBuilder.append("</body>\n");
      strBuilder.append("</html>\n");

      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      bw.write(String.valueOf(strBuilder));
      bw.close();
    } catch (
            IOException e) {
      throw new RuntimeException(e);
    }

  }



}
