package edu.byu.cs.superasteroids.components;

import org.json.JSONArray;

import java.util.Set;

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
    this.width = width;
    this.music = music;
    this.backgroundObjects = backgroundObjects;
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
  public void setNumber(int number) {
    this.number = number;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setMusic(String music) {
    this.music = music;
  }

  public void setObjects(Set<BackgroundObject> objects) {
    this.backgroundObjects = objects;
  }
}
