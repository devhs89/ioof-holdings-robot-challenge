package ui;

import constants.Commands;
import constants.Direction;
import constants.Rotate;
import controllers.TableController;

import java.util.Scanner;

public class Main {
  static final Scanner scanner = new Scanner(System.in);
  static final TableController tableController = new TableController();
  private static String userInput = "";

  public static void main(String[] args) {
    System.out.println("Welcome to Robot Game\n");
    System.out.println("Enter 'q' to quit\n");

    try {
      userInput = getInput("Please enter the size of the table. (Ex. 5x5) -\nType here: ");

      if (!userInput.strip().matches("\\dx\\d")) throw new Exception("\nError:\tInvalid Input");

      var tableDimens = userInput.split("x");
      var tableWidth = Integer.parseInt(tableDimens[0]);
      var tableHeight = Integer.parseInt(tableDimens[1]);

      tableController.createTable(tableWidth, tableHeight);

      performAction(tableWidth, tableHeight);

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private static void performAction(int tableWidth, int tableHeight) {
    do {
      try {
        userInput = getInput("\nEnter command (PLACE, MOVE, LEFT, RIGHT, ACTIVATE, REPORT) -\nType here: ").toUpperCase();

        switch (Commands.valueOf(userInput)) {
          case PLACE:
            placeRobotAction(tableWidth, tableHeight);
            continue;
          case MOVE:
            robotExists();
            tableController.moveRobot();
            tableController.reportStatus();
            continue;
          case LEFT:
            robotExists();
            tableController.rotateRobot(Rotate.LEFT);
            tableController.reportStatus();
            continue;
          case RIGHT:
            robotExists();
            tableController.rotateRobot(Rotate.RIGHT);
            tableController.reportStatus();
            continue;
          case ACTIVATE:
            robotExists();
            userInput = getInput("\nEnter robot name: ");
            tableController.setActiveRobot(userInput.toLowerCase());
            tableController.reportStatus();
            continue;
          case REPORT:
            robotExists();
            tableController.reportStatus();
            continue;
          default:
            System.out.println("\nError:\tInvalid command!");
        }
      } catch (Exception ex) {
        if (ex instanceof IllegalArgumentException) {
          System.out.println("\nError:\tCommand does not match. Please try again.");
        } else {
          System.out.println(ex.getMessage());
        }
      }
    } while (!userInput.equalsIgnoreCase("q"));
  }

  private static void placeRobotAction(int tableWidth, int tableHeight) throws Exception {
    System.out.println("\nPlace Robot -");
    var robotXPos = Integer.parseInt(getInput("Enter X Position: "));

    if (robotXPos >= tableWidth) throw new Exception("\nError:\tX Position greater than table length");

    var robotYPos = Integer.parseInt(getInput("\nEnter Y Position: "));

    if (robotYPos >= tableHeight) throw new Exception("\nError:\tY Position greater than table length");

    String directionInput = getInput("\nDirection Facing (NORTH, EAST, SOUTH, WEST) -\nType here: ").toUpperCase();

    var response = tableController.placeRobot(robotXPos, robotYPos, Direction.valueOf(directionInput));

    if (response) {
      tableController.reportStatus();
    } else {
      throw new Exception("\nError:\tFailed to place robot on table. Please try again.");
    }
  }

  private static void robotExists() throws Exception {
    if (tableController.getRobotsOnTable().size() <= 0) {
      throw new Exception("\nError:\tNo robot on table, please place one first.");
    }
  }

  private static String getInput(String message) {
    System.out.print(message);

    var userInput = scanner.next();

    exitProgram(userInput);
    return userInput;
  }

  private static void exitProgram(String input) {
    if (input.equalsIgnoreCase("q")) Runtime.getRuntime().exit(0);
  }
}