package cheese.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
  @Test
  public void testFileStringConversion() {
    Todo todo = new Todo(true, "Drink water");
    assertEquals("todo // T // Drink water", todo.toFileString());
  }

  @Test
  public void testStringConversion() {
    Todo todo = new Todo(true, "Drink water");
    assertEquals("[T][X] Drink water", todo.toString());
  }
}
