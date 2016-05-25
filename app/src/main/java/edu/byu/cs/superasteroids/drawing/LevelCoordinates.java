package edu.byu.cs.superasteroids.drawing;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class LevelCoordinates {
  public static PointF screenSize;
  public static PointF levelSize;
  public static PointF viewPortCorner;

  /**
   * This will position the screen on the level according to the ship and the level boundaries
   * use only after all objects have been updated
   */
  public static void centerScreen(PointF shipPosition){
    screenSize = new PointF(DrawingHelper.getGameViewWidth(),DrawingHelper.getGameViewHeight());
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
  private static void setViewPort(PointF point){
    viewPortCorner = point;
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
   * gives an angle and a box to see if it collides with the wall,
   * then it changes the angle if it needs to be changed
   * @param component the box of an object
   * @param angle angle the object is moving
   * @return
   */
  public static int  checkCollision(RectF component,int angle){
    while(angle < 0){
      angle += 360;
    }
    while(angle >=360 ){
      angle -=360;
    }
    if(component.top <= 0){
      if(angle < 90)
        return 180-angle;
      if(angle > 270)
        return 180 + (angle-270);
    }
    if(component.left <= 0){
      if(angle>180){
        return 360-angle;
      }
    }
    if(component.right >= levelSize.x){
      if(angle < 180){
        return 360-angle;
      }

    }
    if(component.bottom >= levelSize.y){
      if(angle > 90 && angle <= 180)
        return 180 - angle;
      if(angle < 270)
        return 180 + angle;
    }
    return angle;
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
