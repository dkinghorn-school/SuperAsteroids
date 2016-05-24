package edu.byu.cs.superasteroids.drawing;

import android.graphics.PointF;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class LevelCoordinates {
  public static PointF screenSize;
  public static PointF levelSize;
  private static PointF viewPortCorner;

  /**
   * This will position the screen on the level according to the ship and the level boundaries
   * use only after all objects have been updated
   */
  public static void centerScreen(PointF shipPosition){
    PointF screenCorner = new PointF(shipPosition.x-screenSize.x/2,shipPosition.y-screenSize.y/2);
    if(shipPosition.x < screenSize.x/2){
      screenCorner.x = 0;
    }
    if(shipPosition.x + screenSize.x/2 > levelSize.x){
      screenCorner.x = levelSize.x - screenSize.x;
    }
    if(shipPosition.y < screenSize.y/2){
      screenCorner.y = 0;
    }
    if(shipPosition.y + screenSize.y/2 > levelSize.y){
      screenCorner.y = levelSize.y - screenSize.y;
    }
    viewPortCorner = screenCorner;
  }
  /**
   * converts from level coordinates to screen coordinates
   * @param position location of
   * @return
   */
  public static PointF convertCoordinatesToScreen(PointF position){
    PointF screenCoordinates = new PointF();
    screenCoordinates.x = position.x - viewPortCorner.x;
    screenCoordinates.y = position.y - viewPortCorner.y;
    return screenCoordinates;
  }

  /**
   * takes position on screen to grid coordinates used for finger input
   * @param position position of screen coordinates
   * @return
   */
  public static PointF convertCoordinatesToGrid(PointF position){
    PointF gridCoordinates = new PointF();
    gridCoordinates.x = position.x + viewPortCorner.x;
    gridCoordinates.y = position.y + viewPortCorner.y;
    return gridCoordinates;
  }
}
