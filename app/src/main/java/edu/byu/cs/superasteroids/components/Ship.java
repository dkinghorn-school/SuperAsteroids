package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;
/**
 * Created by devonkinghorn on 5/24/16.
 */
public class Ship {
  public Cannon cannon;
  public Engine engine;
  public ExtraPart extraPart;
  public MainBody mainBody;
  public PowerCore powerCore;
  public PointF position;
  public int rotation; //degrees
  public RectF box = new RectF();
  private float scale = .2f;
  private List<Bullet> bullets = new ArrayList();
  public Ship(Cannon cannon, Engine engine, ExtraPart extraPart, MainBody mainBody, PowerCore powerCore) {
    this.cannon = cannon;
    this.engine = engine;
    this.extraPart = extraPart;
    this.mainBody = mainBody;
    this.powerCore = powerCore;

  }

  public void turn(int degrees){
//    while(degrees < -50){
//      degrees += 360;
//    }
    this.rotation = degrees;
  }
  private int recharge = 0;
  public void fire(){
    if(recharge <= 0) {
      recharge = 2;
      PointF bulletOrigin = new PointF();
      double radians =  GraphicsUtils.degreesToRadians(rotation);

      bulletOrigin.x = (mainBody.getImageWidth()/2 - mainBody.cannonAttachX + cannon.emit.x- cannon.attachX ) * scale;
      bulletOrigin.y = (mainBody.getImageHeight()/2 -mainBody.cannonAttachY + cannon.emit.y- cannon.attachY ) * scale;
      bulletOrigin = GraphicsUtils.rotate(bulletOrigin,radians);
      bulletOrigin.x += position.x;// +
      bulletOrigin.y += position.y;// +
      bullets.add(new Bullet(bulletOrigin,rotation,cannon.getAttackImageWidth(),cannon.getAttackImageHeight()));

    }
  }
  /**
   * moves the ship and updates the direction of the ship
   */
  public void move(){
    if (recharge > 0)
      recharge--;
    float left = position.x-scale*(mainBody.getImageWidth()/2-mainBody.extraAttachX) - scale*(extraPart.getImageWidth()-extraPart.attachX);
    float right = position.x + scale*(mainBody.getImageWidth()/2-mainBody.cannonAttachX) + scale*(cannon.getImageWidth() - cannon.attachX);
    float top = position.y - scale*(mainBody.getImageHeight()/2);
    float bottom = position.y + scale*(mainBody.getImageHeight()/2-mainBody.engineAttachY) + scale*(engine.getImageHeight() - engine.attachY);
    box.set(left,top,right,bottom);
    rotation = LevelCoordinates.checkCollision(box,rotation);

    float radians = (float)GraphicsUtils.degreesToRadians(rotation);
    position.x = position.x + (float)engine.getBaseSpeed()*(float)Math.sin(radians)/10;
    position.y = position.y - (float)engine.getBaseSpeed()*(float)Math.cos(radians)/10;
    for(Bullet bullet:bullets){
      bullet.move();
    }
  }
  /**
   * draws the ship with all of its parts
   * @param alpha the transparency of the ship
   */
  public void draw(int alpha){
    for(Bullet bullet:bullets){
      bullet.draw();
    }
    float cannonAttachX;
    float cannonAttachY;
    float engineAttachX;
    float engineAttachY;
    float extraAttachX;
    float extraAttachY;
    PointF coordinates = LevelCoordinates.convertCoordinatesToScreen(position);
    float shipCenterX = coordinates.x;
    float shipCenterY = coordinates.y;



    PointF center = new PointF(shipCenterX,shipCenterY);
    PointF engineAttachPoint;
    PointF extraPartAttachPoint;
    PointF cannonAttachPoint;
    double radians = 0;
//    int rotation = 0;

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

    float x = center.x + engineAttachPoint.x*scale;
    float y = center.y + engineAttachPoint.y*scale;
    PointF attachPoint = new PointF(x,y);
    engine.draw(attachPoint,rotation,scale,alpha);
//      DrawingHelper.drawImage(engine.imageId,(shipCenterX+scale*engineAttachX), shipCenterY+scale*engineAttachY,0,scale,scale,255);

    x = center.x + extraPartAttachPoint.x*scale;
    y = center.y + extraPartAttachPoint.y*scale;
    extraPart.draw(new PointF(x,y),rotation,scale,alpha);

    x = center.x + cannonAttachPoint.x*scale;
    y = center.y + cannonAttachPoint.y*scale;
    cannon.draw(new PointF(x,y),rotation,scale,alpha);



  }
}
