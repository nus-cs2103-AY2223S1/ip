package cheese.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cheese.task.Todo;
import cheese.task.Deadline;
import cheese.task.Event;

public class TaskListTest {
  @Test
  public void toStringConversion() {
    TaskList taskList = new TaskList();
    taskList.add(new Todo(true, "Drink water"));
    taskList.add(new Deadline(false, "Finish homework", "2022-12-07 12:00"));
    taskList.add(new Event(false, "Concert night", "2022-12-07 18:00"));
    String expected = "1. [T][X] Drink water\n" +
            "2. [D][ ] Finish homework (by: 12-07-2022 12:00)\n" +
            "3. [E][ ] Concert night (at: 12-07-2022 18:00)";
    assertEquals(expected, taskList.toString());
  }
}