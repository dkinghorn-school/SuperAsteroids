package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class AsteroidInstance {

  public int timesDestroyed;

  /**
   * this is the type of asteroid it is
   */
  public Asteroid asteroid;

  public int velocity = 7;
  public int direction;
  public float scale;
  public RectF box = new RectF();
  /**
   * position in the map
   */
  public PointF position;

  /**
   *
   * @param asteroid points to the type of asteroid it is
   */
  public AsteroidInstance(Asteroid asteroid) {
    this.asteroid = asteroid;
    timesDestroyed = 0;
  }

  public void move(){
    float left = position.x-scale*asteroid.getWidth()/2;
    float right = position.x + scale*asteroid.getWidth()/2;
    float top = position.y - scale*asteroid.getHeight()/2;
    float bottom = position.y + scale*asteroid.getHeight()/2;
    box.set(left,top,right,bottom);
    direction = LevelCoordinates.checkCollision(box,direction);

    float radians = (float) GraphicsUtils.degreesToRadians(direction);
    position.x = position.x + velocity*(float)Math.sin(radians);
    position.y = position.y - velocity*(float)Math.cos(radians);

    if(asteroid.getName().equals("growing") && scale < 2){
      scale += .01;
    }
  }


  /**
   * draws itself
   */
  public void draw(){
    PointF screenCoordinates = LevelCoordinates.convertCoordinatesToScreen(position);
    DrawingHelper.drawImage(asteroid.imageId,screenCoordinates.x,screenCoordinates.y,0,scale,scale,255);

  }
}
