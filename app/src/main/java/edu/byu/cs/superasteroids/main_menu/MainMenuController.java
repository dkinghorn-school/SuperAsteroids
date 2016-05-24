package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.Database.Doa;
import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;

/**
 * Created by devonkinghorn on 5/17/16.
 */
public class MainMenuController implements IMainMenuController {
  MainActivity activity;
  public MainMenuController(MainActivity activity){
    this.activity = activity;
  }
  //// TODO: 5/17/16
  public void onQuickPlayPressed(){
    Doa doa = Doa.getSingleton();
    AsteroidSingleton.setCannons(doa.getCannons());
    AsteroidSingleton.setExtraParts(doa.getExtraParts());
    AsteroidSingleton.setEngines(doa.getEngines());
    AsteroidSingleton.setAsteroids(doa.getAsteroids());
    AsteroidSingleton.setLevels(doa.getLevels());
    AsteroidSingleton.setMainBodies(doa.getMainBodies());
    AsteroidSingleton.setPowerCores(doa.getPowerCores());
    AsteroidSingleton.mainBody = (MainBody)AsteroidSingleton.mainBodies.toArray()[0];
    AsteroidSingleton.powerCore = (PowerCore) AsteroidSingleton.powerCores.toArray()[0];
    AsteroidSingleton.extraPart = (ExtraPart) AsteroidSingleton.extraParts.toArray()[0];
    AsteroidSingleton.cannon = (Cannon)AsteroidSingleton.cannons.toArray()[0];
    AsteroidSingleton.engine = (Engine)AsteroidSingleton.engines.toArray()[0];

  }
  /**
   * Gets the controller's view
   * @return the controller's view
   */
  public IView getView(){
    return null;
  }

  /**
   * Sets the controller's view
   * @param view the view to set
   */
  public void setView(IView view){
  }
}
