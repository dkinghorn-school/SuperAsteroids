package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.widget.Switch;

import java.util.HashSet;
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
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.ship_builder.IShipBuildingView.ViewDirection;
/**
 * Created by devonkinghorn on 5/22/16.
 */
public class ShipBuildingController implements IShipBuildingController {
  ShipBuildingActivity context;

  public ShipBuildingController(ShipBuildingActivity context){
    super();
    this.context = context;
  }
  public void unloadContent(ContentManager content){

  }
  public void update(double d){
    if(AsteroidSingleton.powerCore != null && AsteroidSingleton.engine != null && AsteroidSingleton.cannon != null && AsteroidSingleton.extraPart != null && AsteroidSingleton.mainBody != null){
      context.setStartGameButton(true);
    }
    //leave empty
  }
  public void draw(){
    PointF shipPosition = new PointF(DrawingHelper.getGameViewWidth()/2,DrawingHelper.getGameViewHeight()/2);
    AsteroidSingleton.drawShip(shipPosition,0,255);
    System.out.println();
  }
  public void setView(IView view){
    System.out.println();
  }
  public IView getView(){
    return null;
  }
  private IShipBuildingView.PartSelectionView globalview;
  public void onViewLoaded(IShipBuildingView.PartSelectionView partView){
    globalview = partView;
    try {
      switch (partView) {
        case MAIN_BODY:
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.LEFT, true, "Power Core");
          context.setArrow(IShipBuildingView.PartSelectionView.ENGINE, ViewDirection.RIGHT, true, "Engine");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.UP, false, "");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.DOWN, false, "");
          break;
        case EXTRA_PART:
          context.setArrow(IShipBuildingView.PartSelectionView.CANNON, ViewDirection.LEFT, true, "Cannon");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.RIGHT, true, "Power Core");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.UP, false, "");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.DOWN, false, "");
          break;
        case CANNON:
          context.setArrow(IShipBuildingView.PartSelectionView.ENGINE, ViewDirection.LEFT, true, "Engine");
          context.setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, ViewDirection.RIGHT, true, "Extra Part");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.UP, false, "");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.DOWN, false, "");
          break;
        case ENGINE:
          context.setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, ViewDirection.LEFT, true, "Main Body");
          context.setArrow(IShipBuildingView.PartSelectionView.CANNON, ViewDirection.RIGHT, true, "Cannon");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.UP, false, "");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.DOWN, false, "");
          break;
        case POWER_CORE:
          context.setArrow(IShipBuildingView.PartSelectionView.EXTRA_PART, ViewDirection.LEFT, true, "Extra Part");
          context.setArrow(IShipBuildingView.PartSelectionView.MAIN_BODY, ViewDirection.RIGHT, true, "Main Body");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.UP, false, "");
          context.setArrow(IShipBuildingView.PartSelectionView.POWER_CORE, ViewDirection.DOWN, false, "");
          break;
      }
    }catch (Exception e){
      System.out.println(e);
    }
    System.out.println('s');
  }
  public void loadContent(ContentManager content){
    Doa doa = Doa.getSingleton();
    AsteroidSingleton.setCannons(doa.getCannons());
    AsteroidSingleton.setExtraParts(doa.getExtraParts());
    AsteroidSingleton.setEngines(doa.getEngines());
    AsteroidSingleton.setAsteroids(doa.getAsteroids());
    AsteroidSingleton.setLevels(doa.getLevels());
    AsteroidSingleton.setMainBodies(doa.getMainBodies());
    AsteroidSingleton.setPowerCores(doa.getPowerCores());
    Set<Engine> engines = AsteroidSingleton.getEngines();
    List<Integer> ids = new LinkedList();
    for(Engine eng : engines ){
      String image = eng.getImage();
      eng.imageId = content.loadImage(image);
      ids.add(eng.imageId);
    }
    ((ShipBuildingActivity)this.context).setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE,ids);

    ids = new LinkedList();
    Set<MainBody> mainBodies = AsteroidSingleton.getMainBodies();
    for(MainBody mainBody : mainBodies){
      mainBody.imageId = content.loadImage(mainBody.getImage());
      ids.add(mainBody.imageId);
    }
    ((ShipBuildingActivity)this.context).setPartViewImageList(IShipBuildingView.PartSelectionView.MAIN_BODY,ids);

    ids = new LinkedList();
    Set<ExtraPart> extraParts = AsteroidSingleton.getExtraParts();
    for(ExtraPart extraPart : extraParts){
      extraPart.imageId = content.loadImage(extraPart.getImage());
      ids.add(extraPart.imageId);
    }
    ((ShipBuildingActivity)this.context).setPartViewImageList(IShipBuildingView.PartSelectionView.EXTRA_PART,ids);

    ids = new LinkedList<Integer>();
    Set<Cannon> cannons = AsteroidSingleton.getCannons();
    for(Cannon cannon: cannons){
      cannon.imageId = content.loadImage(cannon.getImage());
      ids.add(cannon.imageId);
    }
    ((ShipBuildingActivity)this.context).setPartViewImageList(IShipBuildingView.PartSelectionView.CANNON,ids);

    ids = new LinkedList<Integer>();
    Set<PowerCore> powerCores = AsteroidSingleton.getPowerCores();
    for(PowerCore powerCore : powerCores){
      powerCore.imageId = content.loadImage(powerCore.getImage());
      ids.add(powerCore.imageId);
    }
    for(Asteroid aster: AsteroidSingleton.asteroids){
      aster.imageId = content.loadImage(aster.getImage());
    }
    ((ShipBuildingActivity)this.context).setPartViewImageList(IShipBuildingView.PartSelectionView.POWER_CORE,ids);
//    activity.setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE,ids);
//    System.out.println('s');
  }
  public void onSlideView(ViewDirection direction){
    IShipBuildingView.PartSelectionView right = IShipBuildingView.PartSelectionView.MAIN_BODY;
    IShipBuildingView.PartSelectionView left = IShipBuildingView.PartSelectionView.MAIN_BODY;
    switch (globalview){
      case MAIN_BODY:
        right = IShipBuildingView.PartSelectionView.ENGINE;
        left = IShipBuildingView.PartSelectionView.POWER_CORE;
        break;
      case EXTRA_PART:
        right = IShipBuildingView.PartSelectionView.POWER_CORE;
        left = IShipBuildingView.PartSelectionView.CANNON;
        break;
      case CANNON:
        right = IShipBuildingView.PartSelectionView.EXTRA_PART;
        left = IShipBuildingView.PartSelectionView.ENGINE;
        break;
      case ENGINE:
        right = IShipBuildingView.PartSelectionView.CANNON;
        left = IShipBuildingView.PartSelectionView.MAIN_BODY;
        break;
      case POWER_CORE:
        right = IShipBuildingView.PartSelectionView.MAIN_BODY;
        left = IShipBuildingView.PartSelectionView.EXTRA_PART;
        break;

    }
    switch (direction){
      case RIGHT:
        context.animateToView(left,ViewDirection.LEFT);
        break;
      case LEFT:
        context.animateToView(right,ViewDirection.RIGHT);

    }

    System.out.println();
  }
  public void onPartSelected(int index){
    switch (globalview){
      case MAIN_BODY:
        AsteroidSingleton.mainBody = (MainBody)AsteroidSingleton.getMainBodies().toArray()[index];
        break;
      case EXTRA_PART:
        AsteroidSingleton.extraPart = (ExtraPart) AsteroidSingleton.getExtraParts().toArray()[index];
        break;
      case CANNON:
        AsteroidSingleton.cannon = (Cannon)AsteroidSingleton.getCannons().toArray()[index];
        break;
      case ENGINE:
        AsteroidSingleton.engine = (Engine) AsteroidSingleton.getEngines().toArray()[index];
        break;
      case POWER_CORE:
        AsteroidSingleton.powerCore = (PowerCore) AsteroidSingleton.getPowerCores().toArray()[index];
        break;
    }
//    System.out.println();

  }
  public void onStartGamePressed(){
    AsteroidSingleton.levelNumber = 0;
    context.startGame();
  }
  public void onResume(){
    AsteroidSingleton.levelNumber = 0;
    System.out.println();
  }
}
