package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.byu.cs.superasteroids.components.Asteroid;
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
      String[] intKeys = {"imageWidth","imageHeight"};
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
    Cursor cursor = db.rawQuery("s",null);
    try {
      cursor.moveToFirst();
//      while(cursor)
    }catch (Exception e){

    }
    return null;
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

  /**
   *
   * @return returns set of all levels
   */
  public Set<Level> getLevel(){
    return null;
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
    return null;
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

  /**
   *
   * @return Set of all cannons
   */
  public Set<Cannon> getCannons(){
    final String SQL = "select * from cannons;";
    Set<Cannon> cannons = new HashSet();
    Cursor cursor = db.rawQuery(SQL,null);
//    final String SQL = "select id, title, author, genre " +
//            "from book " +
//            "where author = ?";
//
//    Set<Book> result = new HashSet<>();
//
//    Cursor cursor = db.rawQuery(SQL, new String[]{author});
//    try {
//      cursor.moveToFirst();
//      while (!cursor.isAfterLast()) {
//        Book book = new Book();
//
//        book.setID(cursor.getLong(0));
//        book.setTitle(cursor.getString(1));
//        book.setAuthor(cursor.getString(2));
//        book.setGenre(Genre.valueOf(cursor.getString(3)));
//
//        result.add(book);
//
//        cursor.moveToNext();
//      }
//    }
//    finally {
//      cursor.close();
//    }
//
//    return result;

    return null;
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
    return null;
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
    return null;
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
  public Set<PowerCore> getPowerCores(){
    return null;
  }

}
