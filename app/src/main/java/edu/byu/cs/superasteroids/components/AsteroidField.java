package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Iterator;
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
  public boolean asteroidCollision(RectF spaceObject){
    Iterator<AsteroidInstance> iterator = asteroids.iterator();
    while(iterator.hasNext()){
      AsteroidInstance temp = iterator.next();
      if(temp.box.intersect(spaceObject)){
          switch(temp.asteroid.getName()) {
            case "octeroid":
              if(temp.timesDestroyed == 1){
                iterator.remove();
              }else {
                temp.timesDestroyed++;
                temp.scale /=1.8;
                temp.direction = (int)(Math.random()*364);
                for(int i = 0; i < 7; i++){
                  AsteroidInstance newAsteroid = new AsteroidInstance(temp.asteroid);
                  newAsteroid.scale = temp.scale;
                  newAsteroid.direction = (int)(Math.random()*364);
                  newAsteroid.position = new PointF(temp.position.x,temp.position.y);
                  newAsteroid.timesDestroyed = temp.timesDestroyed;
                  asteroids.add(newAsteroid);
                }
              }

                return true;
            default:
              if(temp.timesDestroyed == 1){
                asteroids.remove(temp);
              }else {
                temp.timesDestroyed++;
                temp.scale /=1.3;
                AsteroidInstance newAsteroid = new AsteroidInstance(temp.asteroid);
                newAsteroid.scale = temp.scale;
                temp.direction = (int)(Math.random()*364);
                newAsteroid.direction = (int)(Math.random()*364);
                newAsteroid.position = new PointF(temp.position.x,temp.position.y);
                newAsteroid.timesDestroyed = temp.timesDestroyed;
                asteroids.add(newAsteroid);
              }
          }
        System.out.println("help");
        return true;
      }
    }
    return false;

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
