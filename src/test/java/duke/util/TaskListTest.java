package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskStatusOutput1(){
        String expected = "[T][ ] read book";
        Todo todo = new Todo("read book");
        TaskList tl = new TaskList();
        tl.addTask(todo);
        String actual = tl.printTaskStatus(0);
        assertEquals(expected, actual);
    }

    @Test
    public void taskStatusOutput2(){
        String expected = "[E][ ] project meeting (at: Aug 6th 2-4pm)";
        Event event = new Event("project meeting", "Aug 6th 2-4pm");
        TaskList tl = new TaskList();
        tl.addTask(event);
        String actual = tl.printTaskStatus(0);
        assertEquals(expected, actual);
    }

    @Test
    public void taskStatusOutput3(){
        String expected = "[D][ ] return book (by: Aug 6th 2-4pm)";
        Deadline deadline = new Deadline("return book", "Aug 6th 2-4pm");
        TaskList tl = new TaskList();
        tl.addTask(deadline);
        String actual = tl.printTaskStatus(0);
        assertEquals(expected, actual);
    }
}