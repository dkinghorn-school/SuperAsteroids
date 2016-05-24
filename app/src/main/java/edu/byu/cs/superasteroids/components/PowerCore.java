package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * i'm not sure what this even is
 * Created by devonkinghorn on 5/16/16.
 */
public class PowerCore {
  private int cannonBoost;
  private int engineBoost;
  private String image;
  public int imageId;
  public int attachX;
  public int attachY;

  /**
   *
   * @param cannonBoost boost given to cannon
   * @param engineBoost boost given to engine
   * @param image path to image file
   */
  public PowerCore(int cannonBoost, int engineBoost, String image) {
    this.cannonBoost = cannonBoost;
    this.engineBoost = engineBoost;
    this.image = image;
  }

  public int getCannonBoost() {
    return cannonBoost;
  }

  public int getEngineBoost() {
    return engineBoost;
  }

  public String getImage() {
    return image;
  }
  /**
   * this draws the ship with the center of the ship
   * @param point center of mainBody image
   * @param rotation degree of rotation
   * @param scale scale of the ship
   */
  public void draw(PointF point, int rotation, float scale){
    float x = point.x;
    float y = point.y;
    DrawingHelper.drawImage(imageId,x,y,0,scale,scale,100);
  }
}
