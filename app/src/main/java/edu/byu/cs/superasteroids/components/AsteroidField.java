package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.byu.cs.superasteroids.core.GraphicsUtils;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class AsteroidField {
  public List<AsteroidInstance> asteroids = new ArrayList();

  public void add(AsteroidInstance asteroid){
    asteroids.add(asteroid);
  }

  public void populate(PointF mapSize, PointF avoid, int distance){
    for(AsteroidInstance asteroid : asteroids){
      asteroid.timesDestroyed = 0;
      asteroid.direction = (int)(Math.random()*364);
      asteroid.scale = 1;
      asteroid.position = new PointF();
      do{
        asteroid.position.x = (float)Math.random()*mapSize.x;
        asteroid.position.y = (float)Math.random()*mapSize.y;

      }while(GraphicsUtils.distance(asteroid.position,avoid) < distance);

    }
  }
  /**
   * moves every asteroid in the level
   */
  public void move(){
    if(asteroids != null) {
      for (AsteroidInstance aster : asteroids) {
        aster.move();
      }
    }
  }

  /**
   * draws all asteroids
   */
  public void draw(){
    if(asteroids != null){
      for(AsteroidInstance aster: asteroids){
        aster.draw();
      }
    }
  }
}
