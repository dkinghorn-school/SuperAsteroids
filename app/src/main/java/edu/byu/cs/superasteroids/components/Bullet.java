package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class Bullet {
  private static int velocity = 100;
  public PointF position;
  public int direction;
  public int attackWidth;
  public int attackHeight;

  public Bullet(PointF position, int direction, int width, int height) {
    this.position = position;
    this.direction = direction;
  }
  public void move(){
    float radians = (float) GraphicsUtils.degreesToRadians(direction);
    position.x = position.x + velocity*(float)Math.sin(radians);
    position.y = position.y - velocity*(float)Math.cos(radians);
  }
  public void draw(){
    PointF coordinatesOnScreen = LevelCoordinates.convertCoordinatesToScreen(position);
//    DrawingHelper.
    DrawingHelper.drawFilledCircle(coordinatesOnScreen,10,500,255);
  }
}
