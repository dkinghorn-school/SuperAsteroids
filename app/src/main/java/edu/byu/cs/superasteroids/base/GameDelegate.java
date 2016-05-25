package edu.byu.cs.superasteroids.base;

import android.graphics.PointF;
import android.graphics.Rect;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.components.BackgroundObject;
import edu.byu.cs.superasteroids.components.Bullet;
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
    PointF fingerPointScreen = InputManager.movePoint;
    if(fingerPointScreen != null){
      if(InputManager.firePressed){
        ship.fire();
      } else {
        PointF shipPos = LevelCoordinates.convertCoordinatesToScreen(ship.position);
        PointF fingerPoint = new PointF();
        fingerPoint.x = shipPos.x - fingerPointScreen.x;
        fingerPoint.y = shipPos.y - fingerPointScreen.y;
        double radians = Math.atan2(fingerPoint.x, fingerPoint.y);
//      radians-=(3.14159/2);
        int toTurn = -(int) GraphicsUtils.radiansToDegrees(radians);
        ship.turn(toTurn);
      }
    }
    ship.move();
    level.moveAsteroids();
    for(Bullet bullet:ship.bullets){

    }
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
    if(level.musicId <1){
      try {
        level.musicId = content.loadLoopSound(level.getMusic());
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    content.playLoop(level.musicId);
    LevelCoordinates.levelSize = new PointF(level.getWidth(),level.getHeight());
    System.out.println('s');
    ship = new Ship(AsteroidSingleton.cannon,AsteroidSingleton.engine,AsteroidSingleton.extraPart,AsteroidSingleton.mainBody,AsteroidSingleton.powerCore);
    ship.position = new PointF(LevelCoordinates.levelSize.x/2,LevelCoordinates.levelSize.y/2);
    ship.rotation = 0;
    level.populateAsteroidField(ship.position);

  }

  @Override
  public void unloadContent(ContentManager content) {
    content.pauseLoop(level.musicId);

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
