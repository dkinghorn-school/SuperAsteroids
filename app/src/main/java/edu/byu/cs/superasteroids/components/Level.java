package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import org.json.JSONArray;

import java.util.Set;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.drawing.LevelCoordinates;

/**
 * This has all of the information about the specific level
 * Created by devonkinghorn on 5/16/16.
 */
public class Level {
  private int number;
  private String title;
  private String hint;
  private int width;
  private int height;
  private String music;
  private Set<BackgroundObject> backgroundObjects;
  public int imageId;
  public int attachX;
  public int attachY;

  /**
   *
   * @param number level number
   * @param title title of level
   * @param hint hint at beginning of level
   * @param width width of arena
   * @param height height of arena
   * @param music music to be played durring level
   * @param backgroundObjects objects in background
   */
  public Level(int number, String title, String hint, int width, int height, String music, Set<BackgroundObject> backgroundObjects){
    this.number = number;
    this.title = title;
    this.hint = hint;
    this.height = height;
    this.width = width;
    this.music = music;
    this.backgroundObjects = backgroundObjects;
  }
  public void draw(){
    LevelCoordinates.centerScreen(new PointF(1500,1500));
    PointF center = new PointF(width/2,height/2);
    PointF screenCoord = LevelCoordinates.convertCoordinatesToScreen(center);
    float widthScale = ((float)width)/(float)2048;
    float heightScale = ((float)height)/(float)2048;
    DrawingHelper.drawImage(AsteroidSingleton.spaceImage, screenCoord.x, screenCoord.y , 0.0f, widthScale, heightScale, 255);
    for(BackgroundObject obj: backgroundObjects){
      obj.draw();
    }
  }
  @Override
  public int hashCode(){
    return this.number;
  }
  @Override
  public boolean equals(Object o){
    if(o.getClass() == this.getClass() && ((Level)o).number == this.number){
      return true;
    }
    return false;
  }

  public int getNumber() {
    return number;
  }

  public String getTitle() {
    return title;
  }

  public String getHint() {
    return hint;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getMusic() {
    return music;
  }

  public Set<BackgroundObject> getBackgroundObjects() {
    return backgroundObjects;
  }

  public int getImageId() {
    return imageId;
  }

  public int getAttachX() {
    return attachX;
  }

  public int getAttachY() {
    return attachY;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

//  public void setWidth(int width) {
//    this.width = width;
//  }

//  public void setHeight(int height) {
//    this.height = height;
//  }

  public void setMusic(String music) {
    this.music = music;
  }

  public void setObjects(Set<BackgroundObject> objects) {
    this.backgroundObjects = objects;
  }
}
