package Models;

import constants.Directions;
import Models.Slot;

import java.util.ArrayList;

public class Table {
  Slot slot;
  ArrayList<Slot> slots;

  public Table(int width, int height) {
    slots = new ArrayList<>();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        slots.add(new Slot(i, j, Directions.NORTH));
      }
    }
  }

  public void displayTable() {
    for (Slot value : slots) {
      System.out.printf("X: %d, Y: %d, Face: %s\n", value.getXCords(), value.getYCords(), value.getDirection());
    }
  }
}
