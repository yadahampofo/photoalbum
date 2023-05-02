package model.photoalbum;

import java.util.ArrayList;
import java.util.List;
import model.photo.Center;
import model.photo.Color;
import model.photo.Dimension;

/**
 * Represents the IPhotoAlbum interface for each transformation commanded by the user.
 */
public interface IPhotoAlbum {
        /**
         * Creates a PhotoShape based on the Inputs passed in by the User.
         *
         * @return the shapes
         */
        IPhotoShape createPhotoShape(String name, String type, Center center,
                                     Dimension dimension, Color color);
        /**
         * Return a list of the Snapshots taken by the user.
         *
         * @return the snapshots
         */
        List<Snapshot> getSnapshotList();

        /**
         * Return a list of the Photo Shapes created by the user.
         *
         * @return the photo shapes
         */
        List<IPhotoShape> getPhotoShapeList();


        /**
         * The getPhotoShapeLog() method returns a List of String that is
         * the "Photo Albums log" of changes.
         * each log entry is a element in the list.
         */
        List<String> getPhotoShapeLog();

        /**
         * Removes a specific photo shape(identified by name) from the photo shape list.
         *
         * @param NameofShapeTobeRemoved a string name to identify photo shape from list.
         *                               Not null or empty.
         */
        void removeShape(String NameofShapeTobeRemoved);

        /**
         * Moves a specific photo shape to a new location specified by the User.
         *
         * @param NameofShapeTobeColorChanged a string name to identify photo shape from list.
         *                                    Not null or empty.
         * @param newColor                    A color object with RGB numbers that the photo shape would be changed to.
         */
        void changeShapeColor(String NameofShapeTobeColorChanged, Color newColor);

        /**
         * Moves a specific photo shape to a new location specified by the User.
         *
         * @param NameofShapeTobeMoved an integer number to identify snapshot from list. Not negative.
         * @param newCenter            an integer number to identify snapshot from list. Not negative.
         */
        void moveShape(String NameofShapeTobeMoved, Center newCenter);

        /**
         * Scales a specific photo shape based on dimenensions chosen by the user.
         *
         * @param NameofShapeTobeScaled an integer number to identify snapshot from list. Not negative.
         * @param newDimension          an integer number to identify snapshot from list. Not negative.
         */
        void scaleShape(String NameofShapeTobeScaled, Dimension newDimension);

        /**
         * Deletes a snapshot based on its unique Snapshot ID.
         * Removes snapshot from Snapshot List.
         *
         * @param snapshotID an integer number to identify snapshot from list. Not negative.
         */
        void deleteSnapShot(int snapshotID);

        /**
         * Takes a snapshot with a custom Description and a custom Snapshot ID created by a counter.
         * Also adds snapshot to  Snapshot List.
         *
         * @param customDescription a description for the screenshot taken. Not null
         *                          or empty.
         */
        Snapshot takeSnapshot(String customDescription);

}
