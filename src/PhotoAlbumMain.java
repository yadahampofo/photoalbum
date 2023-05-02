import com.sun.jdi.IntegerValue;

import java.util.ArrayList;
import java.util.List;

import controller.IPhotoAlbumController;
import controller.PhotoAlbumController; //8:09
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.PhotoAlbumModel;


public class PhotoAlbumMain {

  static final int DEFAULT_HEIGHT = 1000;
  static final int DEFAULT_WIDTH = 1000;
  static final int ERROR_CODE = 0;
  static final int ZERO = 0;


  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList<Integer>();
    IPhotoAlbum model = new PhotoAlbumModel();
    String viewType = "";
    IPhotoAlbumController controller;
    String output = null;
    String filename = null;

    for (String s : args) {
      if (s.contains("txt")) {
        filename = s;
      }
      if (s.contains("web")) {
        viewType = "web";
      }
      if (s.contains("graphical")) {
        viewType = "graphical";
      }
      if (s.contains("html")) {
        output = s;
      }
      if (s.matches("\\d+")) {
        numbers.add(Integer.valueOf(s));
      }
    }
    if(numbers.size() == ZERO) {
       controller = new PhotoAlbumController(model, filename, DEFAULT_HEIGHT,
              DEFAULT_WIDTH, output, viewType);
    } else {
       controller = new PhotoAlbumController(model, filename, numbers.get(0),
              numbers.get(1), output, viewType);
    }
    controller.go(model);

  }
}
