package view;
import java.awt.*;
import java.util.List;
import javax.swing.*;

import controller.PhotoAlbumController;
import model.photoalbum.IPhotoAlbum;
import model.photoalbum.IPhotoShape;
import model.photoalbum.Snapshot;


/**
 * This represents the GraphicalSwingWindow Class which extends JFrame and implements IPhotoAlbumView.
 */
public class GraphicalSwingWindow extends JFrame implements IPhotoAlbumView {
  static final int ERROR_CODE = 0;
  static final int ZERO = 0;
  static final int jFrame_X = 350;
  static final int jFrame_Y = 70;
  static final int MenuFrame_D1 = 600;
  static final int MenuFrame_D2 = 500;
  static final int MenuFrame_X = 950;
  static final int MenuFrame_Y = 400;
  static final int Y_PANEL_LOC = 4;

  private IPhotoAlbum model;
  private Snapshot currSnapshot;
  private JButton quitButton = new JButton("xx Quit xx");
  private JButton selectButton = new JButton("^^ Select ^^");
  private JButton prevButton = new JButton("<< Prev <<");
  private JButton nextButton = new JButton(">> Next >>");
  private JButton okButton = new JButton("OK");
  private JComboBox<String> cb = new JComboBox<String>();
  private int currIndex;
  private JPanel pinkPanel = new JPanel();
  private static int screenWidth;
  private static int screenHeight;


  /**
   * This represents the Graphical Swing window
   * @param model model object which manages the data passed in from the file.Not null or empty.
   * @param screenWidth  an integer width requested by the user.
   * @param screenHeight an integer width requested by the user.
   *
   */
  public GraphicalSwingWindow(IPhotoAlbum model, int screenWidth, int screenHeight) {
    this.model = model;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.setSize(this.screenWidth, this.screenHeight);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Ignore the x window
    this.setTitle("cs 5004 Shapes Photo Album Viewer");
  }

  /**
   * Draw Method responsible for drawing the default snapshot and all other subsequent snapshots
   * requested by the user.
   * @param model the model object passed in.
   */
  @Override
  public void Draw(IPhotoAlbum model) {
    JPanel blackPanel = new JPanel();
    java.awt.Color awtColor = ColorAdapter.myColorToJavaRGB
            (new model.photo.Color(this.model.getSnapshotList().get(ZERO).getShapes().get(ZERO).getColor().getC1(),
                    this.model.getSnapshotList().get(ZERO).getShapes().get(ZERO).getColor().getC2(),
                    this.model.getSnapshotList().get(ZERO).getShapes().get(ZERO).getColor().getC3()));

    blackPanel.setBackground(awtColor);
    JPanel yellowPanel = new JPanel();
    yellowPanel.setBackground(Color.ORANGE);
    yellowPanel.setLayout(new GridLayout(ZERO, Y_PANEL_LOC));
    yellowPanel.add(prevButton); //Prev Button Code
    yellowPanel.add(selectButton); //Select Button Code
    yellowPanel.add(nextButton); //Next Button Code
    yellowPanel.add(quitButton);//Quit Button Code
    currSnapshot = this.model.getSnapshotList().get(ZERO);
    DrawPanel panel = new DrawPanel(currSnapshot);
    pinkPanel.add(new JLabel("Snapshot ID : " + currSnapshot.getSnapshotID().toString()));
    pinkPanel.add(new JLabel("\n Description : " + currSnapshot.getDescription().toString()));
    pinkPanel.setBackground(Color.PINK);
    this.add(blackPanel, BorderLayout.CENTER);
    this.add(yellowPanel, BorderLayout.PAGE_END);
    this.add(pinkPanel,BorderLayout.PAGE_START);
    this.add(panel);
    super.setLocation(jFrame_X, jFrame_Y);
    super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Ignore the x window
    this.setVisible(true);
  }


  /**
   * Method to add the Quit Listener to the Quit Button in Graphic view.
   * @param quitListener The Quit listener.
   */
  public void addQuitButtonListener(PhotoAlbumController.QuitListener quitListener) {
    quitButton.addActionListener(quitListener);
  }



  /**
   * Method to add the Select Listener to the Select Button in Graphic view.
   * @param selectListener The Select listener.
   */
  public void addSelectButtonListener(PhotoAlbumController.SelectListener selectListener) {
    selectButton.addActionListener(selectListener);
  }


  /**
   * Method to add the NextButton Listener to the Next Button in Graphic view.
   * @param nextListener The next listener.
   */
  public void addNextButtonListener(PhotoAlbumController.NextListener nextListener) {
    nextButton.addActionListener(nextListener);
  }

  /**
   * Method to add the PrevButton Listener to the Prev Button in Graphic view.
   * @param prevListener the Previous Listener.
   */
  public void addPrevButtonListener(PhotoAlbumController.PrevListener prevListener) {
    prevButton.addActionListener(prevListener);
  }

  /**
   * Method to add the OK Button Listener to the OK Button in Graphic view.
   * @param okListener the OK button Listener.
   */
  public void addOkButtonListener(PhotoAlbumController.okListener okListener) {
    okButton.addActionListener(okListener);
  }

  /**
   * Method to add the Snapshot List to the OK Button in Graphic view.
   * @param SnapshotList the list of snapshots objects created.
   */
  public void showMenuWindow(List SnapshotList) {
    JFrame menuFrame = new JFrame();
    menuFrame.setTitle("Menu");
    menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Ignore the x window
    menuFrame.setSize(MenuFrame_D1,MenuFrame_D2);
    menuFrame.pack();
    menuFrame.setVisible(true);

    JPanel menuPanel = new JPanel();
    menuFrame.setLocation(MenuFrame_X, MenuFrame_Y);
    menuFrame.add(menuPanel);
    JLabel chooseLabel = new JLabel("Choose \n");
    chooseLabel.setVisible(true);
    menuPanel.add(chooseLabel);

    String[] myDropDownArray = new String[model.getSnapshotList().size()];
    for (int i = ZERO; i < myDropDownArray.length; i++) {
      myDropDownArray[i] = model.getSnapshotList().get(i).getSnapshotID().toString();
    }
    cb = new JComboBox<String>(myDropDownArray);
    cb.setVisible(true);
    menuPanel.add(cb);
    menuFrame.add(menuPanel);
    menuPanel.add(okButton);
    menuFrame.pack();
  }



  /**
   * Updates the current index based on what the user selects from the dropdown menu.
   * @return the index of the snapshot selected.
   */
  public int updateCurrSnapshotIndex() {
    this.currIndex = cb.getSelectedIndex();
    return this.currIndex;
  }

  /**
   * Method to go forward to the next snapshot in the list.
   * @param currIndex  current index in the list.
   */
    public void goForward(int currIndex) {
      repaint();
      pinkPanel.removeAll();
      currSnapshot = this.model.getSnapshotList().get(currIndex);
      DrawPanel panel = new DrawPanel(currSnapshot);
      pinkPanel.getPreferredSize();
      pinkPanel.add(new JLabel("Snapshot ID : " + currSnapshot.getSnapshotID().toString()));
      pinkPanel.add(new JLabel("\n Description : " + currSnapshot.getDescription().toString()));
      pinkPanel.setBackground(Color.PINK);
      this.add(pinkPanel,BorderLayout.PAGE_START);
      this.add(panel);
      this.setVisible(true);
  }

  /**
   * Method to go back to the previous snapshot in the list.
   * @param currIndex current index in the list.
   */
    public void goBackward(int currIndex) {
      repaint();
      pinkPanel.removeAll();
      currSnapshot = this.model.getSnapshotList().get(currIndex);
      DrawPanel panel = new DrawPanel(currSnapshot);
      pinkPanel.setBackground(Color.PINK);
      pinkPanel.add(new JLabel("Snapshot ID : " + currSnapshot.getSnapshotID().toString()));
      pinkPanel.add(new JLabel("\n Description : " + currSnapshot.getDescription().toString()));
      this.add(panel);
      this.setVisible(true);
  }

  /**
   * Method to go directly to the snapshot selected in the Menu drop down.
   * @param currIndex current index in the list.
   */
  public void goDirect(int currIndex){
    repaint();
    pinkPanel.removeAll();
    currSnapshot = this.model.getSnapshotList().get(currIndex);
    DrawPanel panel = new DrawPanel(currSnapshot);
    pinkPanel.setBackground(Color.PINK);
    System.out.println("go direct label update" + currSnapshot.getSnapshotID().toString());
    pinkPanel.add(new JLabel("Snapshot ID : " + currSnapshot.getSnapshotID().toString()));
    pinkPanel.add(new JLabel("\n Description : " + currSnapshot.getDescription().toString()));
    this.add(panel);
    this.add(pinkPanel, BorderLayout.PAGE_START);
    this.setVisible(true);

  }

  /**
   * Method to Exit the System when the user clicks on the quit button.
   */
  public void Dispose() {
    dispose();
    System.exit(ERROR_CODE);
  }

  /**
   * Method to show no next Error popup.
   */
  public void showNoNextError() {
    JOptionPane.showMessageDialog
            (this,
                    "End of the Photo Album. No snapshots to show beyond this one.");
  }

  /**
   * Method to show no next Error popup.
   */
  public void showNoPrevError() {
    JOptionPane.showMessageDialog
            (this,
                    "No snapshots before this one.");
  }

  /**
   * This class represents the Draw Panel Class which extends Jpanel
   */
  class DrawPanel extends JPanel {
    private Snapshot currSnapshot;

    /**
     * This represents the DrawPanel constructor.
     * @param currSnapshot the current snapshot that needs to be created.
     */
    public DrawPanel(Snapshot currSnapshot) {
      this.currSnapshot = currSnapshot;
    }

    /**
     * The paint component method for adding colors specs from the input to our shapes.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      java.awt.Color backgroundAwtColor = ColorAdapter.myColorToJavaRGB
              (new model.photo.Color(currSnapshot.getShapes().get(ZERO).getColor().getC1(),
                      currSnapshot.getShapes().get(ZERO).getColor().getC2(),
                      currSnapshot.getShapes().get(ZERO).getColor().getC3()));
      setBackground(backgroundAwtColor);
      for (IPhotoShape photoShape : currSnapshot.getShapes()) {
        if (photoShape.getType().equalsIgnoreCase("rectangle")
                && photoShape.getName().contains("B")) {
          java.awt.Color awtColor = ColorAdapter.myColorToJavaRGB
                  (new model.photo.Color(photoShape.getColor().getC1(),
                          photoShape.getColor().getC2(),photoShape.getColor().getC3()));
          g.setColor(awtColor);
          g.fillRect(photoShape.getCenter().getPoint1(), photoShape.getCenter().getPoint2(),
                  photoShape.getDimension01(), photoShape.getDimension02());
        }
        if (photoShape.getType().equalsIgnoreCase("rectangle")
                && photoShape.getName().contains("window")) {
          java.awt.Color awtColor = ColorAdapter.myColorToJavaRGB
                  (new model.photo.Color(photoShape.getColor().getC1(),
                          photoShape.getColor().getC2(),photoShape.getColor().getC3()));
          g.setColor(awtColor);
          g.fillRect(photoShape.getCenter().getPoint1(), photoShape.getCenter().getPoint2(),
                  photoShape.getDimension01(), photoShape.getDimension02());
        }
        if (photoShape.getType().equalsIgnoreCase("oval")) {
          java.awt.Color awtColor = ColorAdapter.myColorToJavaRGB
                  (new model.photo.Color(photoShape.getColor().getC1(),
                          photoShape.getColor().getC2(),photoShape.getColor().getC3()));
          g.setColor(awtColor);
          g.fillOval(photoShape.getCenter().getPoint1(), photoShape.getCenter().getPoint2(),
                  photoShape.getDimension01(), photoShape.getDimension02());
        }
      }
    }
  }
}

