package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;

import java.util.Set;

import edu.byu.cs.superasteroids.Database.Doa;
import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.components.Asteroid;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.ship_builder.IShipBuildingView.ViewDirection;
/**
 * Created by devonkinghorn on 5/22/16.
 */
public class ShipBuildingController implements IShipBuildingController {
  Context context;
  Set<Cannon> cannons;
  Set<ExtraPart> eps;
  Set<Engine> engines;
  Set<Asteroid> asteroids;
  Set<Level> levels;
  Set<MainBody> mainBodies;
  Set<PowerCore> powerCores;
  public ShipBuildingController(Context context){
    super();
    Doa doa = Doa.getSingleton();
    cannons = doa.getCannons();
    eps = doa.getExtraParts();
    engines = doa.getEngines();
    asteroids = doa.getAsteroids();
    levels = doa.getLevels();
    mainBodies = doa.getMainBodies();
    powerCores = doa.getPowerCores();
    this.context = context;
  }
  public void unloadContent(ContentManager content){

  }
  public void update(double d){
    //leave empty
  }
  public void draw(){

  }
  public void setView(IView view){

  }
  public IView getView(){
    return null;
  }
  public void onViewLoaded(IShipBuildingView.PartSelectionView partView){

  }
  public void loadContent(ContentManager content){

    System.out.println('s');
  }
  public void onSlideView(ViewDirection direction){

  }
  public void onPartSelected(int index){

  }
  public void onStartGamePressed(){

  }
  public void onResume(){

  }
}
