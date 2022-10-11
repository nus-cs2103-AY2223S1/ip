package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void createTodo_basicTodo_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.addTask(
                "abc", "todo", false, false),
                        "Got it. I've added this task:\n"
                                + "  [T][ ] abc\n"
                                + "Now you have 1 tasks in the list."
        );
    }

    @Test
    public void createDeadline_basicDeadline_success() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(taskList.addTask(
                "def /by 2020-12-12", "deadline", false, false), (
                        "Got it. I've added this task:\n"
                                + "  [D][ ] def (by: Dec 12 2020)\n"
                                + "Now you have 1 tasks in the list.")
        );
    }
}
