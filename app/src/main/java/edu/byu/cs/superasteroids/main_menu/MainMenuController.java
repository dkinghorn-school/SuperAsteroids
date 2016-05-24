package edu.byu.cs.superasteroids.main_menu;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.Database.Doa;
import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.ship_builder.IShipBuildingView;
import edu.byu.cs.superasteroids.ship_builder.ShipBuildingActivity;

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
    ContentManager content = ContentManager.getInstance();

    Set<Engine> engines = AsteroidSingleton.getEngines();
    List<Integer> ids = new LinkedList();
    for(Engine eng : engines ){
      String image = eng.getImage();
      eng.imageId = content.loadImage(image);
      ids.add(eng.imageId);
    }

    ids = new LinkedList();
    Set<MainBody> mainBodies = AsteroidSingleton.getMainBodies();
    for(MainBody mainBody : mainBodies){
      mainBody.imageId = content.loadImage(mainBody.getImage());
      ids.add(mainBody.imageId);
    }

    ids = new LinkedList();
    Set<ExtraPart> extraParts = AsteroidSingleton.getExtraParts();
    for(ExtraPart extraPart : extraParts){
      extraPart.imageId = content.loadImage(extraPart.getImage());
      ids.add(extraPart.imageId);
    }

    ids = new LinkedList<Integer>();
    Set<Cannon> cannons = AsteroidSingleton.getCannons();
    for(Cannon cannon: cannons){
      cannon.imageId = content.loadImage(cannon.getImage());
      ids.add(cannon.imageId);
    }

    ids = new LinkedList<Integer>();
    Set<PowerCore> powerCores = AsteroidSingleton.getPowerCores();
    for(PowerCore powerCore : powerCores){
      powerCore.imageId = content.loadImage(powerCore.getImage());
      ids.add(powerCore.imageId);
    }

    AsteroidSingleton.mainBody = (MainBody)AsteroidSingleton.mainBodies.toArray()[0];
    AsteroidSingleton.powerCore = (PowerCore) AsteroidSingleton.powerCores.toArray()[0];
    AsteroidSingleton.extraPart = (ExtraPart) AsteroidSingleton.extraParts.toArray()[0];
    AsteroidSingleton.cannon = (Cannon)AsteroidSingleton.cannons.toArray()[0];
    AsteroidSingleton.engine = (Engine)AsteroidSingleton.engines.toArray()[0];
    activity.startGame();
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
