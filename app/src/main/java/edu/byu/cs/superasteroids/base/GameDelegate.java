package edu.byu.cs.superasteroids.base;

import android.graphics.PointF;
import android.graphics.Rect;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.components.BackgroundObject;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.Ship;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;
import edu.byu.cs.superasteroids.game.InputManager;

/**
 * Created by devonkinghorn on 5/23/16.
 */
public class GameDelegate implements IGameDelegate {
  Ship ship;
  PointF shipPosition;
  private Level level;
  @Override
  public void update(double elapsedTime) {
    PointF fingerPoint = InputManager.movePoint;
    if(fingerPoint != null){
      PointF shipPos = LevelCoordinates.convertCoordinatesToScreen(ship.position);
      fingerPoint.x = shipPos.x - fingerPoint.x;
      fingerPoint.y = shipPos.y - fingerPoint.y;
      double radians = Math.atan2(fingerPoint.x,fingerPoint.y);
//      radians-=(3.14159/2);
      ship.turn(-(int)GraphicsUtils.radiansToDegrees(radians));
    }
    ship.move();

    System.out.println('s');
  }

  @Override
  public void loadContent(ContentManager content) {
    if(AsteroidSingleton.spaceImage == -1){
      AsteroidSingleton.spaceImage = content.loadImage("images/space.bmp");
      for(Level level: AsteroidSingleton.levels){
        for(BackgroundObject obj: level.getBackgroundObjects()){
          if(obj.imageId == 0) {
            obj.imageId = content.loadImage(obj.getImage());
          }
        }
      }
    }
    AsteroidSingleton.levelNumber++;
    level = null;
    for(Level lev: AsteroidSingleton.levels){
      if(lev.getNumber() == AsteroidSingleton.levelNumber){
        level = lev;
      }
    }
    LevelCoordinates.levelSize = new PointF(level.getWidth(),level.getHeight());
    System.out.println('s');
    ship = new Ship(AsteroidSingleton.cannon,AsteroidSingleton.engine,AsteroidSingleton.extraPart,AsteroidSingleton.mainBody,AsteroidSingleton.powerCore);
    ship.position = new PointF(LevelCoordinates.levelSize.x/2,LevelCoordinates.levelSize.y/2);
    ship.rotation = 0;


  }

  @Override
  public void unloadContent(ContentManager content) {

    System.out.println('s');
  }
  public Rect getViewport(){
    int width = DrawingHelper.getGameViewWidth();
    int height = DrawingHelper.getGameViewHeight();
    Rect viewport = new Rect();
    if(shipPosition.y + height/2 > level.getHeight()){
      viewport.bottom = level.getHeight();
      viewport.top = level.getHeight()+height;
    }


    return viewport;

  }
  @Override
  public void draw() {
    LevelCoordinates.centerScreen(ship.position);
    level.draw();
    ship.draw(255);
    System.out.println('s');
  }
}
