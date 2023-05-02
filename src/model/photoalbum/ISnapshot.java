package model.photoalbum;

/**
 * Represents the ISnapshot interface for each Snapshot taken by the user.
 */
public interface ISnapshot {
  /**
   * Return the description attached to a snapshot.
   *
   * @return the description.
   */
  String getDescription();

  /**
   * Return the timeStamp of a snapshot.
   *
   * @return the timeStamp.
   */
  String getTimestamp();


  /**
   * Get the snapshotID.
   *
   * @return the snapshotID.
   */
  String getSnapshotID();
}
