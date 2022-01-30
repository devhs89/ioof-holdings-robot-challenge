package models;

import constants.Direction;

public class Robot {
  String identifier;
  Boolean active;
  int xPos;
  int yPos;
  Direction direction;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public int getXPos() {
    return xPos;
  }

  public void setXPos(int xPos) {
    this.xPos = xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public void setYPos(int yPos) {
    this.yPos = yPos;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
