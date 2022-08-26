package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
  @Test
  public void toStringTest() {
    assertEquals(new ToDo("Test").toString(),
        "[T][ ] Test");
  }

  @Test
  public void getStorageStringTest() {
    assertEquals(new ToDo("Test").getStorageString(),
        "T >> 0 >> Test");
  }
}
