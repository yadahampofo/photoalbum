package controller;

import model.photoalbum.IPhotoAlbum;

/**
 * This represents the IPhotoAlbumController.
 */
public interface IPhotoAlbumController {

  /**
   * Execute a PhotoAlbum Application given a PhotoAlbum Model. When the user clicks exit,
   * the go method ends.
   * @param  model non-null Photo Album Model.
   *
   */
  void go(IPhotoAlbum model);
}
