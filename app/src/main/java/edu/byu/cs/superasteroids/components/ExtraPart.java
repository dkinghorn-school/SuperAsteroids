package edu.byu.cs.superasteroids.components;

/**
 * Also known as left wing,
 * Created by devonkinghorn on 5/16/16.
 */
public class ExtraPart {
  private String attachPoint;
  private String image;
  private int imageWidth;
  private int imageHeight;
  public int imageId;
  /**
   *
   * @param attachPoint point where it attaches
   * @param image path to image file
   * @param imageWidth width of image
   * @param imageHeight height of image
   */
  public ExtraPart(String attachPoint, String image, int imageWidth, int imageHeight) {
    this.attachPoint = attachPoint;
    this.image = image;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
  }

  public String getAttachPoint() {
    return attachPoint;
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
