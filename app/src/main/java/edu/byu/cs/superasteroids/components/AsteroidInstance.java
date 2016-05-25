package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

/**
 * Created by devonkinghorn on 5/24/16.
 */
public class AsteroidInstance {

  public int timesDestroyed;

  /**
   * this is the type of asteroid it is
   */
  public Asteroid asteroid;

  public int velocity;

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

  }
  /**
   * draws itself
   */
  public void draw(){

  }
}
