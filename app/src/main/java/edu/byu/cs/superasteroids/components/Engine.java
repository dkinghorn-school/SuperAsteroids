package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by devonkinghorn on 5/16/16.
 */
public class Engine {
  private int baseSpeed;
  private int baseTurnRate;
  private String attachPoint;
  private String image;
  private int imageWidth;
  private int imageHeight;
  public int imageId;
  public int attachX;
  public int attachY;
  public PointF attach;


  /**
   *
   * @param imageHeight height of the image
   * @param baseSpeed speed of the engine
   * @param baseTurnRate turn rate for ship
   * @param attachPoint where it attaches on main body
   * @param image path to image file
   * @param imageWidth width of image
   */
  public Engine(int imageHeight, int baseSpeed, int baseTurnRate, String attachPoint, String image, int imageWidth) {
    this.imageHeight = imageHeight;
    this.baseSpeed = baseSpeed;
    this.baseTurnRate = baseTurnRate;
    this.attachPoint = attachPoint;
    this.image = image;
    this.imageWidth = imageWidth;
  }

  public int getBaseSpeed() {
    return baseSpeed;
  }

  public int getBaseTurnRate() {
    return baseTurnRate;
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
  public void draw(PointF point,int rotation, float scale){
    float x = point.x+(imageWidth/2-attachX)*scale;
    float y = point.y+(imageHeight/2-attachY)*scale;
    DrawingHelper.drawImage(imageId,x,y,0,scale,scale,255);
  }
}
