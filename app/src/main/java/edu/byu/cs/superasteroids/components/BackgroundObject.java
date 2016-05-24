package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;

/**
 * Background objects are objects in the backdrop of the level, such as a planet
 * Created by devonkinghorn on 5/16/16.
 */
public class BackgroundObject {
  private String position;
  private String image;
  private double scale;
  public int imageId;
  private float xposition;
  private float yposition;
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

    String strarray[] = position.split(",");
    xposition = Integer.parseInt(strarray[0]);
   yposition = Integer.parseInt(strarray[1]);
  }

  /**
   * draws itself
   */
  public void draw(){
    PointF newPosition = LevelCoordinates.convertCoordinatesToScreen(new PointF(xposition,yposition));
    DrawingHelper.drawImage(imageId,newPosition.x,newPosition.y,0.0f,(float)scale,(float)scale,255);
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
