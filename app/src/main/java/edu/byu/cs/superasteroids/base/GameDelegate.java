package edu.byu.cs.superasteroids.base;

import edu.byu.cs.superasteroids.content.ContentManager;

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

    System.out.println('s');
  }

  @Override
  public void unloadContent(ContentManager content) {

    System.out.println('s');
  }

  @Override
  public void draw() {

    System.out.println('s');
  }
}
