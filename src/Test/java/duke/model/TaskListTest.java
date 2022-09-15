package duke.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_success() {
        List<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        taskList.add(new Deadline("fishing", "2021-05-04"));
        assertEquals(taskList.toStorage(), "D | 0 | fishing | 2021-05-04\n");
    }

    @Test
    public void delete_success() {
        List<Task> list = new ArrayList<>();
        list.add(new Deadline("fishing", "2021-05-04"));
        TaskList taskList = new TaskList(list);
        taskList.delete(1);
        assertEquals(taskList.toString(), "\tYou do not have any tasks!");
    }

    @Test
    public void mark_success() throws DukeException {
        List<Task> list = new ArrayList<>();
        list.add(new Deadline("fishing", "2021-05-04"));
        TaskList taskList = new TaskList(list);
        taskList.mark(1);
        assertEquals(taskList.toStorage(), "D | 1 | fishing | 2021-05-04\n");
    }

    @Test
    public void unmark_success() throws DukeException {
        List<Task> list = new ArrayList<>();
        list.add(new Event("jumping jacks", "2019-08-19", "2020-07-10"));
        TaskList taskList = new TaskList(list);
        taskList.mark(1);
        assertEquals(taskList.toStorage(), "E | 1 | jumping jacks | 2019-08-19 | 2020-07-10\n");
    }
}
