package edu.byu.cs.superasteroids;

import android.graphics.PointF;

import java.util.Set;

import edu.byu.cs.superasteroids.components.Asteroid;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.importer.GameDataImporter;

/**
 * Created by devonkinghorn on 5/22/16.
 */
public class AsteroidSingleton {
  public static Set<Cannon> cannons;
  public static Set<ExtraPart> extraParts;
  public static Set<Engine> engines;
  public static Set<Asteroid> asteroids;
  public static Set<Level> levels;
  public static Set<MainBody> mainBodies;
  public static Set<PowerCore> powerCores;
  public static Engine engine;
  public static Cannon cannon;
  public static ExtraPart extraPart;
  public static MainBody mainBody;
  public static PowerCore powerCore;
  public static int spaceImage = -1;
  public static Level level;
  public static int levelNumber = 0;

  private static float scale = .2f;
//  private static int rotation = 30;

  /**
   * draws the ship with all of its parts
   * @param position where on the screen the ship should appear
   * @param rotation degrees how rotated the ship is
   * @param alpha the transparency of the ship
   */
  public static void drawShip(PointF position, int rotation, int alpha){

    float cannonAttachX = 190;
    float cannonAttachY = 227;
    float engineAttachX = 102;
    float engineAttachY = 392;
    float extraAttachX = 6;
    float extraAttachY = 253;
    float shipCenterX = position.x;
    float shipCenterY = position.y;

    PointF center = new PointF(shipCenterX,shipCenterY);
    PointF engineAttachPoint = center;
    PointF extraPartAttachPoint = center;
    PointF cannonAttachPoint = center;
    double radians = 0;
//    int rotation = 0;
    if(powerCore != null){

      powerCore.draw(center,rotation,scale);
    }
    if(mainBody != null){
      radians = GraphicsUtils.degreesToRadians(rotation);
      cannonAttachX = mainBody.cannonAttachX;
      cannonAttachY = mainBody.cannonAttachY;
      engineAttachX = mainBody.engineAttachX;
      engineAttachY = mainBody.engineAttachY;
      extraAttachX = mainBody.extraAttachX;
      extraAttachY = mainBody.extraAttachY;
      engineAttachPoint = GraphicsUtils.rotate(new PointF(engineAttachX,engineAttachY),radians);
      extraPartAttachPoint = GraphicsUtils.rotate(new PointF(extraAttachX,extraAttachY),radians);
      cannonAttachPoint = GraphicsUtils.rotate(new PointF(cannonAttachX,cannonAttachY),radians);
      mainBody.draw(center,rotation, scale);
//      DrawingHelper.drawImage(mainBody.imageId,shipCenterX,shipCenterY,0,scale,scale,255);
    }
    if(engine != null){
      float x = center.x + engineAttachPoint.x*scale;
      float y = center.y + engineAttachPoint.y*scale;
      PointF attachPoint = new PointF(x,y);
      engine.draw(attachPoint,rotation,scale,255);
//      DrawingHelper.drawImage(engine.imageId,(shipCenterX+scale*engineAttachX), shipCenterY+scale*engineAttachY,0,scale,scale,255);
    }
    if(extraPart != null){
      float a = center.x + extraPartAttachPoint.x*scale;
      float b = center.y + extraPartAttachPoint.y*scale;
      extraPart.draw(new PointF(a,b),rotation,scale,255);
    }
    if(cannon != null){
      float a = center.x + cannonAttachPoint.x*scale;
      float b = center.y + cannonAttachPoint.y*scale;
      cannon.draw(new PointF(a,b),rotation,scale,255);
    }

  }

  public static Set<Cannon> getCannons() {
    return cannons;
  }

  public static Set<ExtraPart> getExtraParts() {
    return extraParts;
  }

  public static Set<Engine> getEngines() {
    return engines;
  }

  public static Set<Asteroid> getAsteroids() {
    return asteroids;
  }

  public static Set<Level> getLevels() {
    return levels;
  }

  public static Set<MainBody> getMainBodies() {
    return mainBodies;
  }

  public static Set<PowerCore> getPowerCores() {
    return powerCores;
  }

  public static void setCannons(Set<Cannon> cannons) {
    for(Cannon part: cannons){
      String strarray[] = part.getAttachPoint().split(",");
      part.attachX = Integer.parseInt(strarray[0]);
      part.attachY = Integer.parseInt(strarray[1]);
      strarray = part.getEmitPoint().split(",");
      part.emit.x = Integer.parseInt(strarray[0]);
      part.emit.y = Integer.parseInt(strarray[1]);
    }
    AsteroidSingleton.cannons = cannons;
  }

  public static void setExtraParts(Set<ExtraPart> extraParts) {
    for(ExtraPart part: extraParts){
      String strarray[] = part.getAttachPoint().split(",");
      part.attachX = Integer.parseInt(strarray[0]);
      part.attachY = Integer.parseInt(strarray[1]);
    }
    AsteroidSingleton.extraParts = extraParts;
  }

  public static void setEngines(Set<Engine> engines) {
    for(Engine part: engines){
      String strarray[] = part.getAttachPoint().split(",");
      part.attachX = Integer.parseInt(strarray[0]);
      part.attachY = Integer.parseInt(strarray[1]);
      part.attach = new PointF(Float.parseFloat(strarray[0]),Float.parseFloat((strarray[1])));
    }
    AsteroidSingleton.engines = engines;
  }

  public static void setAsteroids(Set<Asteroid> asteroids) {
    AsteroidSingleton.asteroids = asteroids;
  }

  public static void setLevels(Set<Level> levels) {
    AsteroidSingleton.levels = levels;
  }

  public static void setMainBodies(Set<MainBody> mainBodySet) {
    AsteroidSingleton.mainBodies = mainBodySet;
    for(MainBody mainBody: mainBodies){
      int centerX = mainBody.getImageWidth()/2;
      int centerY = mainBody.getImageHeight()/2;

      String strarray[] = mainBody.getCannonAttach().split(",");
      mainBody.cannonAttachX = Integer.parseInt(strarray[0])-centerX;
      mainBody.cannonAttachY = Integer.parseInt(strarray[1])-centerY;
      strarray = mainBody.getEngineAttach().split(",");
      mainBody.engineAttachX = (Integer.parseInt(strarray[0]))-centerX;
      mainBody.engineAttachY = (Integer.parseInt(strarray[1]))-centerY;
      strarray = mainBody.getExtraAttach().split(",");
      mainBody.extraAttachX = Integer.parseInt(strarray[0])-centerX;
      mainBody.extraAttachY = Integer.parseInt(strarray[1])-centerY;
    }
  }

  public static void setPowerCores(Set<PowerCore> powerCores) {
    AsteroidSingleton.powerCores = powerCores;
  }
}
