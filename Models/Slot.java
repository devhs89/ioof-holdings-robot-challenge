package Models;

import constants.Directions;

public class Slot {
  int XCords;
  int YCords;
  Directions direction;

  public int getXCords() {
    return XCords;
  }

  public int getYCords() {
    return YCords;
  }

  public Directions getDirection() {
    return direction;
  }

  public Slot(int XCords, int YCords, Directions direction) {
    this.XCords = XCords;
    this.YCords = YCords;
    this.direction = direction;
  }
}
