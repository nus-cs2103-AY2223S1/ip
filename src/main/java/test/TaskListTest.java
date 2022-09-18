package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;


class TaskListTest {

    @Test
    public void mark() {
        try {
            TaskList taskList = new TaskList(
                    Arrays.asList(
                            new Todo("todo"),
                            new Deadline("deadline", "2020-01-01"),
                            new Event("event", "2020-02-02")
                    )
            );
            taskList.mark(0);
            taskList.mark(1);
            taskList.mark(2);
            assertEquals(taskList.toString(),
                    "T | X | todo\n" + "D | X | deadline | 2020-01-01\n" + "E | X | event | 2020-02-02\n"
            );
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void unmark() {
        try {
            TaskList taskList = new TaskList(
                    Arrays.asList(
                            new Todo("todo"),
                            new Deadline("deadline", "2020-01-01"),
                            new Event("event", "2020-02-02")
                    )
            );
            taskList.mark(0);
            taskList.mark(1);
            taskList.mark(2);
            taskList.unmark(0);
            taskList.unmark(1);
            taskList.unmark(2);
            assertEquals(taskList.toString(),
                    "T |   | todo\n" + "D |   | deadline | 2020-01-01\n" + "E |   | event | 2020-02-02\n"
            );
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

    }
}
