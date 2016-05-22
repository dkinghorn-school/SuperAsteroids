package edu.byu.cs.superasteroids.components;

/**
 * Background objects are objects in the backdrop of the level, such as a planet
 * Created by devonkinghorn on 5/16/16.
 */
public class BackgroundObject {
  private String position;
  private String image;
  private double scale;

  /**
   *
   * @param position position on game display
   * @param image location of image path
   * @param scale scale of object
   */
  public BackgroundObject(String position, String image, double scale) {
    this.position = position;
    this.image = image;
    this.scale = scale;
  }

  /**
   *
   * @return position
   */
  public String getPosition() {
    return position;
  }

  /**
   *
   * @return path to image
   */
  public String getImage() {
    return image;
  }

  /**
   *
   * @return scale of image
   */
  public double getScale() {
    return scale;
  }
}
