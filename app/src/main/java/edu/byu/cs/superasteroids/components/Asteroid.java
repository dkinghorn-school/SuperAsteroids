package edu.byu.cs.superasteroids.components;

/**
 * This class contains the information about a specific asteroid
 * Created by devonkinghorn on 5/16/16.
 */
public class Asteroid {

  private String name;
  private String image;
  private int width;
  private int height;
  public int imageId;
  public int id;

  /**
   *
   * @param name name of asteroid
   * @param image path to image file
   * @param width width of unscaled asteroid
   * @param height height of unscaled asteroid
   */
  public Asteroid(String name, String image, int width, int height, int id) {
    this.name = name;
    this.image = image;
    this.width = width;
    this.height = height;
    this.id = id;
  }
  public Asteroid(){}

  /**
   *
   * @return image path
   */
  public String getImage() {
    return image;
  }
  /**
   *
   * @return name
   */
  public String getName() {
    return name;
  }
  /**
   *
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   *
   * @return width
   */
  public int getWidth() {
    return width;
  }


}
