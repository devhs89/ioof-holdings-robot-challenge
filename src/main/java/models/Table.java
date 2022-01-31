package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {
  static HashMap<String, Integer> dimensions;
  static ArrayList<Slot> slots;
  static ArrayList<Robot> robots;

  public Table(int width, int height) {
    dimensions = new HashMap<>();
    dimensions.put("width", width);
    dimensions.put("height", height);

    slots = new ArrayList<>();
    robots = new ArrayList<>();
  }

  public HashMap<String, Integer> getDimensions() {
    return dimensions;
  }

  public ArrayList<Slot> getSlots() {
    return slots;
  }

  public ArrayList<Robot> getRobots() {
    return robots;
  }
}
