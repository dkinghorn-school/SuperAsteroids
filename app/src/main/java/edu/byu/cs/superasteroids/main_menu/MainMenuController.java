package edu.byu.cs.superasteroids.main_menu;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.AsteroidSingleton;
import edu.byu.cs.superasteroids.Database.Doa;
import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.components.Asteroid;
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
    ContentManager content = ContentManager.getInstance();

    AsteroidSingleton.setCannons(doa.getCannons());
    AsteroidSingleton.setExtraParts(doa.getExtraParts());
    AsteroidSingleton.setEngines(doa.getEngines());
    AsteroidSingleton.setAsteroids(doa.getAsteroids());
    for(Asteroid aster: AsteroidSingleton.asteroids){
      if(aster.imageId == 0) {
        aster.imageId = content.loadImage(aster.getImage());
      }
    }
    AsteroidSingleton.setLevels(doa.getLevels());
    AsteroidSingleton.setMainBodies(doa.getMainBodies());
    AsteroidSingleton.setPowerCores(doa.getPowerCores());


    Set<Engine> engines = AsteroidSingleton.getEngines();
    List<Integer> ids = new LinkedList();
    for(Engine eng : engines ){
      String image = eng.getImage();
      if(eng.imageId == 0) {
        eng.imageId = content.loadImage(image);
      }
      ids.add(eng.imageId);
    }

    ids = new LinkedList();
    Set<MainBody> mainBodies = AsteroidSingleton.getMainBodies();
    for(MainBody mainBody : mainBodies){
      if(mainBody.imageId == 0) {
        mainBody.imageId = content.loadImage(mainBody.getImage());
      }
      ids.add(mainBody.imageId);
    }

    ids = new LinkedList();
    Set<ExtraPart> extraParts = AsteroidSingleton.getExtraParts();
    for(ExtraPart extraPart : extraParts){
      if(extraPart.imageId == 0) {
        extraPart.imageId = content.loadImage(extraPart.getImage());
      }
      ids.add(extraPart.imageId);
    }

    ids = new LinkedList<Integer>();
    Set<Cannon> cannons = AsteroidSingleton.getCannons();
    for(Cannon cannon: cannons){
      if(cannon.imageId == 0) {
        cannon.imageId = content.loadImage(cannon.getImage());
      }
      ids.add(cannon.imageId);
    }

    ids = new LinkedList<Integer>();
    Set<PowerCore> powerCores = AsteroidSingleton.getPowerCores();
    for(PowerCore powerCore : powerCores){
      if(powerCore.imageId == 0) {
        powerCore.imageId = content.loadImage(powerCore.getImage());
      }
      ids.add(powerCore.imageId);
    }
    for(Asteroid aster: AsteroidSingleton.asteroids){
      if(aster.imageId == 0) {
        aster.imageId = content.loadImage(aster.getImage());
      }
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
