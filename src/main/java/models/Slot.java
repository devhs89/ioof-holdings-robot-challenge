package models;

public class Slot {
  final int xCords;
  final int yCords;

  public int getXCords() {
    return xCords;
  }

  public int getYCords() {
    return yCords;
  }

  public Slot(int xCords, int yCords) {
    this.xCords = xCords;
    this.yCords = yCords;
  }
}
