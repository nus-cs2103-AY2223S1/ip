package components;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
  @Test
  public void testToDo() {
    assertEquals("[T][ ] borrow book", new Todo("borrow book").toString());
  }

}