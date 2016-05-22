package edu.byu.cs.superasteroids.importer;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import edu.byu.cs.superasteroids.Database.DbOpenHelper;
import edu.byu.cs.superasteroids.Database.Doa;
import edu.byu.cs.superasteroids.components.Asteroid;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;

/**
 * Created by devonkinghorn on 5/17/16.
 */
public class GameDataImporter implements IGameDataImporter {


  /**
   * Imports the data from the .json file the given InputStreamReader is connected to. Imported data
   * should be stored in a SQLite database for use in the ship builder and the game.
   * @param dataInputReader The InputStreamReader connected to the .json file needing to be imported.
   * @return TRUE if the data was imported successfully, FALSE if the data was not imported due
   * to any error.
   */
  Doa doa;

  public boolean importData(InputStreamReader dataInputReader){

    Doa tempDoa = Doa.getSingleton();
    if(tempDoa == null)
      return false;
    doa = tempDoa;
    doa.clearAll();
    try {
//      String inputStr;
//      while ((inputStr = streamReader.readLine()) != null)
//        responseStrBuilder.append(inputStr);
      BufferedReader reader = new BufferedReader(dataInputReader);
      StringBuilder jsonString = new StringBuilder();
      String str;
      while((str = reader.readLine()) != null){
        jsonString.append(str);
      }

      //parse the object

//      JSONParser parser = new JSONParser();
      JSONObject asteroidsJson =  new JSONObject(jsonString.toString()).getJSONObject("asteroidsGame");
//      Object json = parser.parse(jsonString.toString());

      addAsteroids(asteroidsJson.getJSONArray("asteroids"));
      addLevels(asteroidsJson.getJSONArray("levels"));
      addMainBodies(asteroidsJson.getJSONArray("mainBodies"));
      addCannons(asteroidsJson.getJSONArray("cannons"));
      addExtraParts(asteroidsJson.getJSONArray("extraParts"));
      addEngines(asteroidsJson.getJSONArray("engines"));
      addPowerCores(asteroidsJson.getJSONArray("powerCores"));
      addBackgroundObjects(asteroidsJson.getJSONArray("objects"));
      Set<Cannon> cannons = doa.getCannons();
      Set<ExtraPart> eps = doa.getExtraParts();
      Set<Engine> engines = doa.getEngines();
      Set<Asteroid> asteroids = doa.getAsteroids();
      Set<Level> levels = doa.getLevels();
      Set<MainBody> mainBodies = doa.getMainBodies();
      Set<PowerCore> powerCores = doa.getPowerCores();
      System.out.println("l");


      return true;


    }catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }
  private void addBackgroundObjects(JSONArray backgroundObjects){
    for(int i = 0; i < backgroundObjects.length(); i++){
      try{
        doa.addBackgroundObjectImage(backgroundObjects.getString(i),i);
      } catch (Exception e){

      }
    }
  }
  private void addPowerCores(JSONArray powerCores){
    for(int i = 0; i < powerCores.length(); i++){
      try{
        doa.addPowerCore(powerCores.getJSONObject(i));
      }catch (Exception e){

      }
    }
  }
  private void addEngines(JSONArray engines){
    for(int i = 0; i < engines.length(); i++){
      try{
        doa.addEngine(engines.getJSONObject(i));
      }catch (Exception e){

      }
    }
  }
  private void addExtraParts(JSONArray extraParts){
    for(int i = 0; i < extraParts.length(); i++){
      try{
        doa.addExtraParts(extraParts.getJSONObject(i));
      }catch (Exception e){

      }
    }
  }
  private void addCannons(JSONArray cannons){
    for(int i = 0; i < cannons.length(); i++){
      try{
        doa.addCannon(cannons.getJSONObject(i));
      }catch (Exception e){

      }
    }
  }
  private void addMainBodies(JSONArray mainBodies){
    for(int i = 0; i < mainBodies.length(); i++){
      try{
        doa.addMainBody(mainBodies.getJSONObject(i));
      }catch (Exception e){

      }
    }
  }
  private void addLevels(JSONArray levels){
    for(int i = 0; i < levels.length(); i++){
      try{
        doa.addLevel(levels.getJSONObject(i));
        this.addLevelObjects(levels.getJSONObject(i).getJSONArray("levelObjects"),levels.getJSONObject(i).getInt("number"));
        this.addLevelAsteroids(levels.getJSONObject(i).getJSONArray("levelAsteroids"),levels.getJSONObject(i).getInt("number"));
      }catch (Exception e){

      }
    }
  }
  private void addLevelAsteroids(JSONArray levelAsteroids, int level){
    for(int i = 0; i < levelAsteroids.length(); i++){
      try{
        doa.addLevelAsteroid(levelAsteroids.getJSONObject(i),level);
      }catch (Exception e){

      }
    }
  }
  private void addLevelObjects(JSONArray levelObjects, int level){
    for(int i = 0; i < levelObjects.length(); i++){
      try{
        doa.addLevelObject(levelObjects.getJSONObject(i),level);
      }catch (Exception e){

      }
    }
  }
  private void addAsteroids(JSONArray asteroids){
    for(int i = 0; i < asteroids.length(); i++){
      try {
        asteroids.getJSONObject(i).put("id",i+1);
        doa.addAsteroid(asteroids.getJSONObject(i));
      }catch(Exception e){

      }
    }
  }
}
