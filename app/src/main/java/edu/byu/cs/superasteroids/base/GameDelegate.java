package edu.byu.cs.superasteroids.base;

import android.graphics.Rect;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by devonkinghorn on 5/23/16.
 */
public class GameDelegate implements IGameDelegate {
  @Override
  public void update(double elapsedTime) {

    System.out.println('s');
  }

  @Override
  public void loadContent(ContentManager content) {
    if(AsteroidSingleton.spaceImage == -1){
      AsteroidSingleton.spaceImage = content.loadImage("images/space.bmp");
    }
    AsteroidSingleton.levelNumber++;
    AsteroidSingleton.level = null;
    for(Level lev: AsteroidSingleton.levels){
      if(lev.getNumber() == AsteroidSingleton.levelNumber){
        AsteroidSingleton.level = lev;
      }
    }
    System.out.println('s');
  }

  @Override
  public void unloadContent(ContentManager content) {

    System.out.println('s');
  }

  @Override
  public void draw() {
    float widthScale = AsteroidSingleton.level.getHeight()/2048;
    float heightScale = AsteroidSingleton.level.getWidth()/2048;
    DrawingHelper.drawImage(AsteroidSingleton.spaceImage, 0, 0 , 0.0f, widthScale, heightScale, 255);
    AsteroidSingleton.drawShip();
    System.out.println('s');
  }
}
