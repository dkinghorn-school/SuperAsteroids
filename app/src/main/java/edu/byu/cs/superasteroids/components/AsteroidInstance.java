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
  public Asteroid type;

  public int velocity;

  /**
   * position in the map
   */
  PointF position;

  /**
   *
   * @param type the type of asteroid it is
   */
  public AsteroidInstance(Asteroid type) {
    this.type = type;
    timesDestroyed = 0;
  }

  /**
   * draws itself
   */
  public void draw(){

  }
}
