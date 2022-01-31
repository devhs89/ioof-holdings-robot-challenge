import constants.Direction;
import constants.Rotate;
import controllers.TableController;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableControllerTest {
  static TableController tc;

  public TableControllerTest() {
    tc = new TableController();
  }

  @Test
  @Order(1)
  void testCreateTable() {
    tc.createTable(5, 5);
  }

  @Test
  @Order(2)
  void testPlaceRobot() throws Exception {
    assertEquals(true, tc.placeRobot(0, 0, Direction.SOUTH));
  }

  @Test
  @Order(3)
  void testOverlapPlaceRobot() {
    assertThrows(Exception.class, () -> tc.placeRobot(0, 0, Direction.NORTH));
  }

  @Test
  @Order(4)
  void testPlaceAnotherRobot() throws Exception {
    assertEquals(true, tc.placeRobot(2, 2, Direction.SOUTH));
  }

  @Test
  @Order(5)
  void testMoveRobot() throws Exception {
    tc.reportStatus();

    Exception ex = assertThrows(Exception.class, () -> {
      for (int i = 0; i < 9; i++) {
        tc.moveRobot();
      }
    });

    tc.reportStatus();
    assertTrue(ex.getMessage().contains("end of table"));
  }

  @Test
  @Order(6)
  void testRotateRobot() throws Exception {
    tc.reportStatus();
    tc.rotateRobot(Rotate.LEFT);
//      tc.rotateRobot(Rotate.RIGHT);
    tc.reportStatus();
  }

  @Test
  @Order(7)
  void testSetActiveRobot() throws Exception {
    tc.setActiveRobot("robot1");
    tc.reportStatus();
  }

  @Test
  @Order(7)
  void testAgainMoveRobot() throws Exception {
    tc.reportStatus();

    Exception ex = assertThrows(Exception.class, () -> {
      for (int i = 0; i < 6; i++) {
        tc.moveRobot();
      }
    });

    tc.reportStatus();
    assertTrue(ex.getMessage().contains("end of table"));
  }
}

