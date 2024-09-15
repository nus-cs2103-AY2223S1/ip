package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
  ToDo todo = new ToDo("description");
  @Test
  public void createTodoObjectTest(){
    assertEquals(todo.toString(), "[T][ ] " + "description");
  }

  @Test
  public void markTodoTest(){
    todo.mark();
    assertEquals(todo.getStatusIcon(), "X");
  }

  @Test
  public void unmarkTodoTest(){
    todo.unmark();
    assertEquals(todo.getStatusIcon(), " ");
  }
}
