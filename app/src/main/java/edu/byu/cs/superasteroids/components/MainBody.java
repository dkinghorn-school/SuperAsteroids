package edu.byu.cs.superasteroids.components;

/**
 * center of spacecraft
 * Created by devonkinghorn on 5/16/16.
 */
public class MainBody {
  private String cannonAttach;
  private String engineAttach;
  private String extraAttach;
  private String image;
  private int imageWidth;
  private int imageHeight;

  /**
   *
   * @param cannonAttach point where cannon attaches
   * @param engineAttach point where engine attaches
   * @param extraAttach where extra attaches
   * @param image path to image file
   * @param imageWidth width of image
   * @param imageHeight height of image
   */
  public MainBody(String cannonAttach, String engineAttach, String extraAttach, String image, int imageWidth, int imageHeight) {
    this.cannonAttach = cannonAttach;
    this.engineAttach = engineAttach;
    this.extraAttach = extraAttach;
    this.image = image;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
  }
  public String getCannonAttach() {
    return cannonAttach;
  }

  public String getEngineAttach() {
    return engineAttach;
  }

  public String getExtraAttach() {
    return extraAttach;
  }

  public String getImage() {
    return image;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }







}
