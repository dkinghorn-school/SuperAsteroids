package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class AsteroidField {
  public List<AsteroidInstance> asteroids = new ArrayList();

  public void add(AsteroidInstance asteroid){
    asteroids.add(asteroid);
  }

  private void populate(PointF avoid, int distance){
    for(AsteroidInstance asteroid : asteroids){
      asteroid.timesDestroyed = 0;
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
