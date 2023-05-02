package view;

import model.photoalbum.IPhotoAlbum;


/**
 * This represents the IPhotoAlbumView interface.
 * Contains the only common method between web and graphical view.
 */
public interface IPhotoAlbumView {

  /**
   * Draws the web/graphical view based on the user input preference.
   */
  void Draw(IPhotoAlbum model);
}
