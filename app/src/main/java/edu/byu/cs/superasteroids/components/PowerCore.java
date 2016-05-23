package edu.byu.cs.superasteroids.components;

/**
 * i'm not sure what this even is
 * Created by devonkinghorn on 5/16/16.
 */
public class PowerCore {
  private int cannonBoost;
  private int engineBoost;
  private String image;
  public int imageId;

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
}
