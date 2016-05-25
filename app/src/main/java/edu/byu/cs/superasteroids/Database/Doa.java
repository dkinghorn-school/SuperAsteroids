package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import edu.byu.cs.superasteroids.components.Asteroid;
import edu.byu.cs.superasteroids.components.AsteroidField;
import edu.byu.cs.superasteroids.components.AsteroidInstance;
import edu.byu.cs.superasteroids.components.BackgroundObject;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;

/**
 * Created by devonkinghorn on 5/16/16.
 * this is the DOA and does all commands within the sql database
 */
public class Doa {
  SQLiteDatabase db;
  public static Doa singleton;

  protected Doa(SQLiteDatabase db){
    this.db = db;
  }
  public static Doa createSingleton(SQLiteDatabase db){
    singleton = new Doa(db);
    return singleton;
  }
  public static Doa getSingleton(){

    return singleton;
  }

  /**
   *
    * @param path path to object
   */
  public void addBackgroundObjectImage(String path, int number){
    ContentValues values = new ContentValues();
    values.put("objectNumber", number);
    values.put("imagePath", path);
    db.insert("backgroundObjects",null,values);
  }



  /**
   *
   * @param name name of the file for the object
   * @param image path to the image of the asteroid
   * @param imageHeight height of image
   * @param imageWidth width of image
   *
   */
  public void addAsteroid(String name, String image, int imageWidth, int imageHeight){

  }
  private ContentValues getValues(String[] stringKeys, String[] intKeys,JSONObject json) throws JSONException {
    ContentValues values = new ContentValues();
    for(int i = 0; i < stringKeys.length; i++){
     values.put(stringKeys[i],json.getString(stringKeys[i]));
    }
    for(int i = 0; i < intKeys.length; i++){
      values.put(intKeys[i], json.getInt(intKeys[i]));
    }
    return values;
  }
  private void addToDB(JSONObject myObject, String[] stringKeys, String[] intKeys, String dbName){
    try {
      ContentValues values = getValues(stringKeys, intKeys, myObject);
      db.insert(dbName,null,values);
    }catch (Exception e){

    }
  }
  public void addPowerCore(JSONObject powerCore){
    String[] stringKeys = {
            "image",
    };
    String[] intKeys = {
            "cannonBoost",
            "engineBoost"
    };
    addToDB(powerCore,stringKeys,intKeys,"powerCores");
  }
  public void addEngine(JSONObject engine){
    String[] stringKeys = {
            "attachPoint",
            "image",
    };
    String[] intKeys = {
            "baseSpeed",
            "baseTurnRate",
            "imageWidth",
            "imageHeight",
    };
    addToDB(engine,stringKeys,intKeys,"engines");
  }
  public void addLevelAsteroid(JSONObject levelAsteroid, int level){
    String[] stringKeys = {
    };
    String[] intKeys = {
            "number",
            "asteroidId"
    };
    try {
      ContentValues values = getValues(stringKeys, intKeys, levelAsteroid);
      values.put("levelNumber",level);
      db.insert("levelAsteroids",null,values);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public void addLevelObject(JSONObject levelObject, int level){
    String[] stringKeys = {
            "position"
    };
    String[] intKeys = {
            "objectId",
            "scale"
    };
    try {
      ContentValues values = getValues(stringKeys, intKeys, levelObject);
      values.put("levelNumber",level);
      db.insert("levelObjects",null,values);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public void addExtraParts(JSONObject extraPart){
    String[] stringKeys = {
            "attachPoint",
            "image"
           };
    String[] intKeys = {
            "imageWidth",
            "imageHeight",
        };
    addToDB(extraPart,stringKeys,intKeys,"extraParts");
  }
  public void addCannon(JSONObject mainBody){
    String[] stringKeys = {
            "attachPoint",
            "emitPoint",
            "image",
            "attackImage",
            "attackSound"};
    String[] intKeys = {
            "imageWidth",
            "imageHeight",
            "attackImageWidth",
            "attackImageHeight",
            "damage"};
    addToDB(mainBody,stringKeys,intKeys,"cannons");
  }
  public void addMainBody(JSONObject mainBody){
    String[] stringKeys = {"cannonAttach","engineAttach","extraAttach","image"};
    String[] intKeys = {"imageWidth","imageHeight"};
    addToDB(mainBody,stringKeys,intKeys,"mainBodies");
  }
  public void addLevel(JSONObject level){
    StringBuilder sb = new StringBuilder();
    try{

      String[] stringKeys = {"title","hint","music"};
      String[] intKeys = {"width","height"};
      ContentValues values = getValues(stringKeys,intKeys,level);
      db.insert("levels",null,values);

    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public void addAsteroid(JSONObject asteroid){
    StringBuilder sb = new StringBuilder();
    try {
      String name = asteroid.getString("name");
      String image = asteroid.getString("image");
      int imageWidth = asteroid.getInt("imageWidth");
      int imageHeight = asteroid.getInt("imageHeight");

      String[] stringKeys = {"name","image"};
      String[] intKeys = {"imageWidth","imageHeight","id"};
      ContentValues values = getValues(stringKeys,intKeys,asteroid);
      long id = db.insert("asteroids",null,values);

//      ContentValues values = new ContentValues();
//      values.put("title", book.getTitle());
//      values.put("author", book.getAuthor());
//      values.put("genre", book.getGenre().toString());
//
//      long id = db.insert("book", null, values);
//      if (id >= 0) {
//        book.setID(id);
//        return true;
//      }
//      else {
//        return false;
//      }
//      db.execSQL("");
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   *
   * @return returns Set with all asteroids
   */
  public Set<Asteroid> getAsteroids(){
    Set<Asteroid> asteroids = new HashSet();
    JSONArray json = getJSONfromSQL("asteroids");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject asteroid = json.getJSONObject(i);
        asteroids.add(new Asteroid(asteroid.getString("name"), asteroid.getString("image"), asteroid.getInt("imageWidth"), asteroid.getInt("imageHeight"), asteroid.getInt("id")));
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return asteroids;
  }

  /**
   *
   * @param number sets level number
   * @param title title of the level
   * @param hint help to defeat the level
   * @param width width of level
   * @param height height of the level
   * @param music music that's played
   * @param objects Set of BackgroundObjects in level
   * @param asteroids Set of asteroids in the level
   */
  public void addLevel(int number, String title, String hint, int width, int height, String music, Set<BackgroundObject> objects, Set<Asteroid> asteroids){

  }

  private Set<BackgroundObject> getBackgroundObjects(int level){
    Set<BackgroundObject> backgroundObjects = new HashSet();
    JSONArray json = getJSONfromSQL("levelObjects");
    JSONArray filePaths = getJSONfromSQL("backgroundObjects");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject backgroundObject = json.getJSONObject(i);
        if(backgroundObject.getInt("levelNumber") == level) {
          String objectPath = "";
          for(int j = 0; j < filePaths.length(); j++){
            if(filePaths.getJSONObject(i).getInt("objectNumber") == i){
              objectPath = filePaths.getJSONObject(i).getString("imagePath");
            }
          }
          backgroundObjects.add(new BackgroundObject(backgroundObject.getString("position"), objectPath, backgroundObject.getDouble("scale")));
        }
      }catch (Exception e){
        e.printStackTrace();
        System.out.println("");
      }
    }
    return backgroundObjects;
  }

  private AsteroidField getLevelAsteroids(int level){
    AsteroidField field = new AsteroidField();
    Set<Asteroid> asteroidTypes = getAsteroids();
    JSONArray json = getJSONfromSQL("levelAsteroids","levelNumber",Integer.toString(level));
    for (int i = 0; i < json.length(); i++) {

      try {
        JSONObject asteroid = json.getJSONObject(i);
//        Asteroid[] asteroids = (Asteroid[])asteroidTypes.toArray();
        Asteroid correctAsteroid = new Asteroid();// = asteroids[0];
        for(Asteroid aster: asteroidTypes){
          if(asteroid.getInt("asteroidId") == aster.id){
            correctAsteroid = aster;
          }
        }
        if(correctAsteroid.getName() != null) {
          int numberToCopy = asteroid.getInt("number");
          for (int j = 0; j < numberToCopy; j++) {
            field.add(new AsteroidInstance(correctAsteroid));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return field;
  }
  /**
   *
   * @return returns set of all levels
   */
  public Set<Level> getLevels(){
    Set<Level> levels = new HashSet();
    JSONArray json = getJSONfromSQL("levels");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject level = json.getJSONObject(i);
        Level myLevel = new Level(level.getInt("levelNumber"), level.getString("title"), level.getString("hint"),level.getInt("width"),level.getInt("height"),level.getString("music"),this.getBackgroundObjects(level.getInt("levelNumber")));
        myLevel.asteroidField = getLevelAsteroids(level.getInt("levelNumber"));
        levels.add(myLevel);
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return levels;
  }

  /**
   *
   * @param cannonAttach gives location of cannon attachment point
   * @param engineAttach gives location of engine attachment point
   * @param extraAttach gives location of extraPart attachment point
   * @param image file path to image of the mainBody
   * @param imageWidth width of image
   * @param imageHeight height of image
   */
  public void addMainBody(String cannonAttach, String engineAttach, String extraAttach, String image, String imageWidth, String imageHeight){

  }

  /**
   *
   * @return returns set of all MainBodies
   */
  public Set<MainBody> getMainBodies(){
    Set<MainBody> mainBodies = new HashSet();
    JSONArray json = getJSONfromSQL("mainBodies");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject mainBody = json.getJSONObject(i);
        mainBodies.add(new MainBody(mainBody.getString("cannonAttach"), mainBody.getString("engineAttach"), mainBody.getString("extraAttach"),mainBody.getString("image"), mainBody.getInt("imageWidth"), mainBody.getInt("imageHeight")));
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return mainBodies;
  }

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

  public void addCannon(String attachPoint,
                        String emitPoint,
                        String image,
                        int imageWidth,
                        int imageHeight,
                        String attackImage,
                        int attackImageWidth,
                        int attackImageHeight,
                        String attackSound,
                        int damage){

  }
  private static final String[] tables = {
          "levels",
          "levelObjects",
          "levelAsteroids",
          "backgroundObjects",
          "asteroids",
          "mainBodies",
          "cannons",
          "extraParts",
          "engines",
          "powerCores"
  };
  public void clearAll(){
    for(int i = 0; i < tables.length; i++){
      db.delete(tables[i],null,null);
    }
  }

  /**
   *
   * @return Set of all cannons
   */
  private JSONArray getJSONfromSQL(String table){
    String query = "select * from " + table + ";";
    JSONArray resultSet     = new JSONArray();
    Cursor cursor = db.rawQuery(query,null);
    cursor.moveToFirst();
    while (cursor.isAfterLast() == false) {

      int totalColumn = cursor.getColumnCount();
      JSONObject rowObject = new JSONObject();

      for( int i=0 ;  i< totalColumn ; i++ )
      {
        if( cursor.getColumnName(i) != null )
        {
          try
          {
            if( cursor.getString(i) != null )
            {
              rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
            }
             else
            {
              rowObject.put( cursor.getColumnName(i) ,  cursor.getInt(i) );
            }
          }
          catch( Exception e )
          {
            e.printStackTrace();
          }
        }
      }
      resultSet.put(rowObject);
      cursor.moveToNext();
    }
    cursor.close();
//    Log.d("TAG_NAME", resultSet.toString() );
    return resultSet;
  }
  /**
   *
   * @return Set of all cannons
   */
  private JSONArray getJSONfromSQL(String table, String field, String value){
    String query = "select * from " + table + " where " + field + " = " + value + ";";
    JSONArray resultSet     = new JSONArray();
    Cursor cursor = db.rawQuery(query,null);
    cursor.moveToFirst();
    while (cursor.isAfterLast() == false) {

      int totalColumn = cursor.getColumnCount();
      JSONObject rowObject = new JSONObject();

      for( int i=0 ;  i< totalColumn ; i++ )
      {
        if( cursor.getColumnName(i) != null )
        {
          try
          {
            if( cursor.getString(i) != null )
            {
              rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
            }
            else
            {
              rowObject.put( cursor.getColumnName(i) ,  cursor.getInt(i) );
            }
          }
          catch( Exception e )
          {
            e.printStackTrace();
          }
        }
      }
      resultSet.put(rowObject);
      cursor.moveToNext();
    }
    cursor.close();
//    Log.d("TAG_NAME", resultSet.toString() );
    return resultSet;
  }
  public Set<Cannon> getCannons(){
    Set<Cannon> cannons = new HashSet();
    JSONArray json = getJSONfromSQL("cannons");
    for(int i = 0; i < json.length(); i++){
      try {
        cannons.add(new Cannon(json.getJSONObject(i).getString("attachPoint"), json.getJSONObject(i).getString("emitPoint"), json.getJSONObject(i).getString("image"), json.getJSONObject(i).getInt("imageWidth"), json.getJSONObject(i).getInt("imageHeight"), json.getJSONObject(i).getString("attackImage"), json.getJSONObject(i).getInt("attackImageWidth"), json.getJSONObject(i).getInt("attackImageHeight"), json.getJSONObject(i).getString("attackSound"), json.getJSONObject(i).getInt("damage")));
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return cannons;
  }



  /**
   *
   * @param attachPoint point where it attaches to main body
   * @param image path to image file
   * @param imageWidth width of part
   * @param imageHeight height of part
   */
  public void addExtraPart(String attachPoint, String image, int imageWidth, int imageHeight){

  }

  /**
   *
   * @return set of all extra parts
   */
  public Set<ExtraPart> getExtraParts(){
    Set<ExtraPart> extraParts = new HashSet();
    JSONArray json = getJSONfromSQL("extraParts");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject newObject = json.getJSONObject(i);
        extraParts.add(new ExtraPart(newObject.getString("attachPoint"), newObject.getString("image"), newObject.getInt("imageWidth"), newObject.getInt("imageHeight")));
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return extraParts;
  }

  /**
   *
   * @param baseSpeed base speed of engine
   * @param baseTurnRate turn rate of engine
   * @param attachPoint point of attachment
   * @param image path to image file
   * @param imageWidth width of part
   * @param imageHeight height of part
   */
  public void addEngine(int baseSpeed, int baseTurnRate, String attachPoint, String image, int imageWidth, int imageHeight){

  }

  /**
   *
   * @return set of all Engines
   */
  public Set<Engine> getEngines(){
    Set<Engine> engines = new HashSet();
    JSONArray json = getJSONfromSQL("engines");
    for(int i = 0; i < json.length(); i++){
      try {
        JSONObject engine = json.getJSONObject(i);
        engines.add(new Engine(engine.getInt("imageHeight"), engine.getInt("baseSpeed"),engine.getInt("baseTurnRate"),engine.getString("attachPoint"),engine.getString("image"), engine.getInt("imageWidth")));
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return engines;
  }

  /**
   *
   * @param cannonBoost boost given to cannon
   * @param engineBoost boost given to engine
   * @param image path to image
   */
  public void addPowerCore(int cannonBoost, int engineBoost, String image){

  }

  /**
   *
   * @return Set of all PowerCores
   */
  public Set<PowerCore> getPowerCores() {
    Set<PowerCore> powerCores = new HashSet();
    JSONArray json = getJSONfromSQL("powerCores");
    for (int i = 0; i < json.length(); i++) {
      try {
        JSONObject powerCore = json.getJSONObject(i);
        powerCores.add(new PowerCore(powerCore.getInt("cannonBoost"),powerCore.getInt("engineBoost"), powerCore.getString("image")));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return powerCores;
  }
}
