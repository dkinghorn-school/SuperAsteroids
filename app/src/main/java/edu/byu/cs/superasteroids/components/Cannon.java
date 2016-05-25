package edu.byu.cs.superasteroids.components;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * also known as a right wing, this has all the information about a cannon
 * Created by devonkinghorn on 5/16/16.
 */
public class Cannon extends IShipPart{
  private String attachPoint;
  private String emitPoint;
  private String image;
//  private int imageWidth;
//  private int imageHeight;
  private String attackImage;
  private int attackImageWidth;
  private int attackImageHeight;
  private String attackSound;
  private int damage;
//  public int imageId;
//  public int attachX;
//  public int attachY;
  public PointF attach;
  public PointF emit = new PointF();
  public int soundId;

  /**
   *
   * @param attachPoint attachment point for cannon
   * @param emitPoint emit point for cannon
   * @param image path to canon image
   * @param imageWidth width of part
   * @param imageHeight height of part
   * @param attackImage path to attackImage
   * @param attackImageWidth width of attack
   * @param attackImageHeight height of attack
   * @param attackSound path to sound effect
   * @param damage damage dealt by hit
   */
  public Cannon(String attachPoint, String emitPoint, String image, int imageWidth, int imageHeight, String attackImage, int attackImageWidth, int attackImageHeight, String attackSound, int damage) {
    this.attachPoint = attachPoint;
    this.emitPoint = emitPoint;
    this.image = image;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.attackImage = attackImage;
    this.attackImageWidth = attackImageWidth;
    this.attackImageHeight = attackImageHeight;
    this.attackSound = attackSound;
    this.damage = damage;
  }

  public String getAttachPoint() {
    return attachPoint;
  }

  public String getEmitPoint() {
    return emitPoint;
  }

  public String getImage() {
    return image;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public String getAttackImage() {
    return attackImage;
  }

  public int getAttackImageWidth() {
    return attackImageWidth;
  }

  public int getAttackImageHeight() {
    return attackImageHeight;
  }

  public String getAttackSound() {
    return attackSound;
  }

  public int getDamage() {
    return damage;
  }

}
