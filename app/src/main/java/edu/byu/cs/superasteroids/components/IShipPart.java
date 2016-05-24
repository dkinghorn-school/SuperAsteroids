package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by devonkinghorn on 5/23/16.
 */
public abstract class IShipPart {
  public int imageId;
  public int attachX;
  public int attachY;
  public int imageWidth;
  public int imageHeight;
  /**
   * this draws the ship with the center of the ship
   * @param point center of mainBody image
   * @param rotation degree of rotation
   * @param scale scale of the ship
   */
  public void draw(PointF point, int rotation, float scale){
    PointF attachPoint = GraphicsUtils.rotate(new PointF(imageWidth/2-attachX,imageHeight/2-attachY),GraphicsUtils.degreesToRadians(rotation));
    float x = point.x+(attachPoint.x)*scale;
    float y = point.y+(attachPoint.y)*scale;
    DrawingHelper.drawImage(imageId,(float)x,(float)y,rotation,scale,scale,255);
  }
}
