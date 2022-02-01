package controllers;

import constants.Direction;
import constants.Rotate;
import models.Robot;
import models.Slot;
import models.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableController {
  static Table table;
  static Robot robot;

  //Create a table with certain dimensions
  public void createTable(int width, int height) {
    table = new Table(width, height);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        table.getSlots().add(new Slot(i, j));
      }
    }
  }

  //Place robot on table once a table has been created
  public Boolean placeRobot(int xPos, int yPos, Direction direction) throws Exception {
    //Make sure the slot (unit) is not already occupied by a robot
    if (table.getRobots().size() > 0) {
      for (Robot r : table.getRobots()) {
        if (r.getXPos() == xPos && r.getYPos() == yPos) {
          throw new Exception("\nError:\tSlot already occupied");
        }
      }
    }

    Slot slotExits = null;

    //  Make sure the slot exists
    for (Slot s : table.getSlots()) {
      if (s.getXCords() == xPos && s.getYCords() == yPos) {
        slotExits = s;
      }
    }

    //If slot exists place robot and make other robots not active
    if (slotExits != null) {
      for (Robot r : table.getRobots()) {
        r.setActive(false);
      }

      robot = new Robot();
      robot = createRobot(xPos, yPos, direction);
      table.getRobots().add(robot);
    } else {
      throw new Exception("\nError:\tSlot not present on table. Please try again.");
    }
    return true;
  }

  private Robot createRobot(int robotXPos, int robotYPos, Direction direction) {
    robot.setIdentifier(("robot" + (table.getRobots().size() + 1)));
    robot.setActive(true);
    robot.setXPos(robotXPos);
    robot.setYPos(robotYPos);
    robot.setDirection(direction);
    return robot;
  }

  public ArrayList<Robot> getRobotsOnTable() {
    return table.getRobots();
  }

  //Perform move action
  public void moveRobot() throws Exception {
    var activeRobot = getActiveRobot();

    if (activeRobot == null) throw new Exception("\nError:\tNo active robot found. Please place one.");

    var tableMaxWidth = table.getDimensions().get("width");
    var tableMaxHeight = table.getDimensions().get("height");
    int nextX = activeRobot.getXPos();
    int nextY = activeRobot.getYPos();

    switch (activeRobot.getDirection()) {
      case NORTH:
        nextY = activeRobot.getYPos() + 1;
        break;
      case EAST:
        nextX = activeRobot.getXPos() + 1;
        break;
      case SOUTH:
        nextY = activeRobot.getYPos() - 1;
        break;
      case WEST:
        nextX = activeRobot.getXPos() - 1;
        break;
    }

    //If a robot is already occupying the next slot, prompt to change direction first
    for (Robot r : table.getRobots()) {
      if (r.getXPos() == nextX && r.getYPos() == nextY) {
        throw new Exception("\nError:\tSlot occupied by '" + r.getIdentifier() + "'. Change direction.");
      }
    }

    //If next move is not a valid move, throw exception; else move robot
    if (nextX >= 0 && nextY >= 0) {
      if (nextX <= (tableMaxWidth - 1) && nextY <= (tableMaxHeight - 1)) {
        activeRobot.setXPos(nextX);
        activeRobot.setYPos(nextY);
      }
    } else {
      throw new Exception("\nError:\tAt the end of table. Change direction.");
    }
  }

  //Rotate robot clockwise or counter-clockwise
  public void rotateRobot(Rotate rotate) throws Exception {
    var activeRobot = getActiveRobot();

    if (activeRobot == null) throw new Exception("\nError:\tNo active robot found. Please place one.");

    List<Direction> dirList = Arrays.asList(Direction.values());

    var index = dirList.indexOf(activeRobot.getDirection());

    //Rotate robot left or right
    switch (rotate) {
      case LEFT:
        activeRobot.setDirection(dirList.get((index - 1) < 0 ? (dirList.size() - 1) : (index - 1)));
        break;
      case RIGHT:
        activeRobot.setDirection(dirList.get((index + 1) > (dirList.size() - 1) ? 0 : (index + 1)));
        break;
    }
  }

  //Get robot status, i.e. identifier, x-cords, y-cords and current direction
  public void reportStatus() throws Exception {
    var activeRobot = getActiveRobot();

    if (activeRobot == null) throw new Exception("\nError:\tNo active robot found. Please place one.");

    System.out.printf("\n[Active Robot]\nName: %s\nX-Position: %d\nY-Position: %d\nFacing: %s\n", activeRobot.getIdentifier(), activeRobot.getXPos(), activeRobot.getYPos(), activeRobot.getDirection());
  }

  //Set robot status active
  public void setActiveRobot(String identifier) throws Exception {
    var success = false;

    for (Robot r : table.getRobots()) {
      if (r.getIdentifier().equals(identifier)) {
        table.getRobots().forEach(item -> item.setActive(false));
        r.setActive(true);
        success = true;
        break;
      }
    }

    if (!success) {
      throw new Exception("\nError:\tNo robot with given name found.");
    }
  }

  //Get active robot
  private Robot getActiveRobot() {
    Robot activeRobot = null;

    for (Robot r : table.getRobots()) {
      if (r.getActive()) {
        activeRobot = r;
      }
    }
    return activeRobot;
  }
}
