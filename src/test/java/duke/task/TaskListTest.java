package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
  @Test
  public void dummyTest() {
    assertEquals(2, 2);
  }

  // Tests if a TaskList can be initialised
  @Test
  public void initialiseTest() {
    TaskList tasks = new TaskList(new ArrayList<Task>());
  }

  @Test
  public void addTask_todo_success() {
    TaskList tasks = new TaskList(new ArrayList<Task>());
    tasks.addTask(new TodoTaskStub(), false);
  }

  @Test
  public void getSizeTest() {
    TaskList tasks = new TaskList(new ArrayList<Task>());
    tasks.addTask(new TodoTaskStub(), false);
    assertEquals(tasks.getSize(), 1); // addTask_todo_success() shouldve been run, thus size is 1.
  }

}
