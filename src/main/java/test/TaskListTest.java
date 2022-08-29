package test;

import duke.Deadline;
import duke.DukeException;
import duke.TaskList;
import duke.Todo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    public void mark() {
        try {
            TaskList taskList = new TaskList(
                    Arrays.asList(new Todo("123")));
            taskList.mark(0);
            assertEquals(taskList.toString(), "T | X | 123\n");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void unmark() {
        try {
            TaskList taskList = new TaskList(
                    Arrays.asList(new Deadline("123", "2020-10-10")));
            taskList.mark(0);
            assertEquals(taskList.toString(), "D | X | 123 | 2020-10-10\n");
            taskList.unmark(0);
            assertEquals(taskList.toString(), "D |   | 123 | 2020-10-10\n");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

    }

//    @Test
//    void testToString() {
//    }
}