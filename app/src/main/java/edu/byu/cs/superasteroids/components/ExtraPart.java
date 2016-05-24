package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

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
  public int attachX;
  public int attachY;

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
  /**
   * this draws the ship with the center of the ship
   * @param point center of mainBody image
   * @param rotation degree of rotation
   * @param scale scale of the ship
   */
  public void draw(PointF point, int rotation, float scale){
    float x = point.x+(imageWidth/2-attachX)*scale;
    float y = point.y+(imageHeight/2-attachY)*scale;
    DrawingHelper.drawImage(imageId,x,y,0,scale,scale,255);
  }
}
