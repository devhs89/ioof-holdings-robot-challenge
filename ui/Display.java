package ui;

import Models.Table;

import java.util.Scanner;

public class Display {
  public static void main(String[] args) {
    System.out.println("Welcome to Robot Game");
    System.out.println("Please enter the size of the table. (Ex. 5x5)");

    Scanner scanner = new Scanner(System.in);
    var tableSizeInput = scanner.next();

    var stripSpaces = tableSizeInput.replaceAll(" ", "");
    var tableDimensions = stripSpaces.split("x");
    var tableWidth = Integer.parseInt(tableDimensions[0]);
    var tableHeight = Integer.parseInt(tableDimensions[1]);

    Table table = new Table(tableWidth, tableHeight);

    table.displayTable();
  }
}
