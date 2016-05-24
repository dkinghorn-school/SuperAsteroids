package edu.byu.cs.superasteroids.base;

import android.graphics.PointF;
import android.graphics.Rect;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.components.BackgroundObject;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;

/**
 * Created by devonkinghorn on 5/23/16.
 */
public class GameDelegate implements IGameDelegate {

  PointF shipPosition;
  @Override
  public void update(double elapsedTime) {

    System.out.println('s');
  }

  @Override
  public void loadContent(ContentManager content) {
    if(AsteroidSingleton.spaceImage == -1){
      AsteroidSingleton.spaceImage = content.loadImage("images/space.bmp");
      for(Level level: AsteroidSingleton.levels){
        for(BackgroundObject obj: level.getBackgroundObjects()){
          obj.imageId = content.loadImage(obj.getImage());
        }
      }
    }
    AsteroidSingleton.levelNumber++;
    AsteroidSingleton.level = null;
    for(Level lev: AsteroidSingleton.levels){
      if(lev.getNumber() == AsteroidSingleton.levelNumber){
        AsteroidSingleton.level = lev;
      }
    }
    LevelCoordinates.levelSize = new PointF(AsteroidSingleton.level.getWidth(),AsteroidSingleton.level.getHeight());
    LevelCoordinates.screenSize = new PointF(DrawingHelper.getGameViewWidth(),DrawingHelper.getGameViewHeight());
    System.out.println('s');
  }

  @Override
  public void unloadContent(ContentManager content) {

    System.out.println('s');
  }
  public Rect getViewport(){
    int width = DrawingHelper.getGameViewWidth();
    int height = DrawingHelper.getGameViewHeight();
    Rect viewport = new Rect();
    if(shipPosition.y + height/2 > AsteroidSingleton.level.getHeight()){
      viewport.bottom = AsteroidSingleton.level.getHeight();
      viewport.top = AsteroidSingleton.level.getHeight()+height;
    }


    return viewport;

  }
  @Override
  public void draw() {

    AsteroidSingleton.level.draw();

    System.out.println('s');
  }
}
