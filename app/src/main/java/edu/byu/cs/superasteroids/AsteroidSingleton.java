package edu.byu.cs.superasteroids;

import java.util.Set;

import edu.byu.cs.superasteroids.components.Asteroid;
import edu.byu.cs.superasteroids.components.Cannon;
import edu.byu.cs.superasteroids.components.Engine;
import edu.byu.cs.superasteroids.components.ExtraPart;
import edu.byu.cs.superasteroids.components.Level;
import edu.byu.cs.superasteroids.components.MainBody;
import edu.byu.cs.superasteroids.components.PowerCore;
import edu.byu.cs.superasteroids.importer.GameDataImporter;

/**
 * Created by devonkinghorn on 5/22/16.
 */
public class AsteroidSingleton {
  public static Set<Cannon> cannons;
  public static Set<ExtraPart> extraParts;
  public static Set<Engine> engines;
  public static Set<Asteroid> asteroids;
  public static Set<Level> levels;
  public static Set<MainBody> mainBodies;
  public static Set<PowerCore> powerCores;
  public static Engine engine;
  public static Cannon cannon;
  public static ExtraPart extraPart;
  public static MainBody mainBody;
  public static PowerCore powerCore;

  public static Set<Cannon> getCannons() {
    return cannons;
  }

  public static Set<ExtraPart> getExtraParts() {
    return extraParts;
  }

  public static Set<Engine> getEngines() {
    return engines;
  }

  public static Set<Asteroid> getAsteroids() {
    return asteroids;
  }

  public static Set<Level> getLevels() {
    return levels;
  }

  public static Set<MainBody> getMainBodies() {
    return mainBodies;
  }

  public static Set<PowerCore> getPowerCores() {
    return powerCores;
  }

  public static void setCannons(Set<Cannon> cannons) {
    AsteroidSingleton.cannons = cannons;
  }

  public static void setExtraParts(Set<ExtraPart> extraParts) {
    AsteroidSingleton.extraParts = extraParts;
  }

  public static void setEngines(Set<Engine> engines) {
    AsteroidSingleton.engines = engines;
  }

  public static void setAsteroids(Set<Asteroid> asteroids) {
    AsteroidSingleton.asteroids = asteroids;
  }

  public static void setLevels(Set<Level> levels) {
    AsteroidSingleton.levels = levels;
  }

  public static void setMainBodies(Set<MainBody> mainBodies) {
    AsteroidSingleton.mainBodies = mainBodies;
  }

  public static void setPowerCores(Set<PowerCore> powerCores) {
    AsteroidSingleton.powerCores = powerCores;
  }
}
