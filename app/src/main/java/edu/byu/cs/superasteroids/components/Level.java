package edu.byu.cs.superasteroids.components;

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
  private Set<BackgroundObject> objects;

  /**
   *
   * @param number level number
   * @param title title of level
   * @param hint hint at beginning of level
   * @param Width width of arena
   * @param Height height of arena
   * @param music music to be played durring level
   * @param objects objects in background
   */
  public Level(int number, String title, String hint, int Width, int Height,String music, Set<Object> objects){

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
    this.objects = objects;
  }
}
