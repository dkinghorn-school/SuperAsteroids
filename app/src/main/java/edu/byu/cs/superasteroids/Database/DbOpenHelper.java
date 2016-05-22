package edu.byu.cs.superasteroids.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devonkinghorn on 5/16/16.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
  private static final String DB_Name = "asteroids.sqlite";
  public static final int DB_Version = 1;

  /**Creates or opens a SQLite database
   *
   * @param context things that are given
   */
  public DbOpenHelper(Context context){
    super(context,DB_Name,null, DB_Version);
  }
  //todo:create databases
  private static final String CREATE_LEVELS =
          "create table levels " +
                  "(levelNumber integer not null primary key" +
                  ", title varchar(255) not null, " +
                  "hint varchar(255) not null, " +
                  "width integer not null, height integer not null, " +
                  "music varchar(255) not null)";
  private static final String CREATE_LEVELOBJECTS =
          "create table levelObjects " +
                  "( levelNumber integer not null, " +
                  "position varchar(255) not null, " +
                  "objectId integer not null, " +
                  "scale numeric not null)";

  public static final String CREATE_BACKGROUNDOBJECTS =
          "create table backgroundObjects " +
                  "( objectNumber integer not null, " +
                  "imagePath varchar(255) not null)";

  public static final String CREATE_ASTEROIDS =
          "create table asteroids " +
                  "(name varchar(255) not null, " +
                  "image varchar(255) not null, " +
                  "imageWidth integer not null, " +
                  "imageHeight integer not null)";

  public static final String CREATE_MAINBODIES =
          "create table mainBodies " +
                  "(cannonAttach varchar(255) not null, " +
                  "engineAttach varchar(255) not null, " +
                  "extraAttach varchar(255) not null, " +
                  "image varchar(255) not null, " +
                  "imageWidth integer not null, " +
                  "imageHeight integer not null)";

  public static final String CREATE_CANNONS =
          "create table cannons " +
                  "(attachPoint varchar(255) not null, " +
                  "emitPoint varchar(255) not null, " +
                  "image varchar(255) not null, " +
                  "imageWidth integer not null, " +
                  "imageHeight integer not null, " +
                  "attackImage varchar(255) not null, " +
                  "attackImageWidth integer not null, " +
                  "attackImageHeight not null, " +
                  "attackSound varchar(255) not null, " +
                  "damage integer not null)";

  public static final String CREATE_EXTRAPARTS =
          "create table extraParts " +
                  "(attachPoint varchar(255) not null, " +
                  "image varchar(255) not null, " +
                  "imageWidth integer not null, " +
                  "imageHeight integer not null)";

  public static final String CREATE_ENGINES =
          "create table engines " +
                  "( baseSpeed integer not null, " +
                  "baseTurnRate integer not null, " +
                  "attachPoint varchar(255) not null, " +
                  "image varchar(255) not null, " +
                  "imageWidth integer not null, " +
                  "imageHeight integer not null)";

  public static final String CREATE_POWERCORES =
          "create table powerCores " +
                  "( cannonBoost integer not null, " +
                  "engineBoost integer not null, " +
                  "image varchar(255) not null)";
  public static final String CREATE_LEVELASTEROIDS =
          "create table levelAsteroids " +
                  "( levelNumber integer not null, " +
                  "number integer not null, " +
                  "asteroidId integer not null)";


  /** creates the database
   *
   * @param db
   */
  public void onCreate(SQLiteDatabase db){
    db.execSQL(CREATE_LEVELS);
    db.execSQL(CREATE_ASTEROIDS);
    db.execSQL(CREATE_BACKGROUNDOBJECTS);
    db.execSQL(CREATE_CANNONS);
    db.execSQL(CREATE_LEVELOBJECTS);
    db.execSQL(CREATE_ENGINES);
    db.execSQL(CREATE_POWERCORES);
    db.execSQL(CREATE_EXTRAPARTS);
    db.execSQL(CREATE_MAINBODIES);
    db.execSQL(CREATE_LEVELASTEROIDS);
  }
  public void reset(SQLiteDatabase db){
    for(int i = 0; i < tables.length; i++){
      db.execSQL(dropTableString(tables[i]));
    }
    this.onCreate(db);
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
  private String dropTableString(String table){
    return "drop table if exists "+table;
  }

  //does nothing
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {return;}


}

