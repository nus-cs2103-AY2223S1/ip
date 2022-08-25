package duke.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void mark_success() {
        List<Task> list = new ArrayList<>();
        list.add(new Deadline("fishing", "2021-05-04"));
        TaskList taskList = new TaskList(list);
        taskList.mark(1);
        assertEquals(taskList.toStorage(), "D | 1 | fishing | 2021-05-04\n");
    }

    @Test
    public void unmark_success() {
        List<Task> list = new ArrayList<>();
        list.add(new Event("jumping jacks", "2019-08-19", "2020-07-10"));
        TaskList taskList = new TaskList(list);
        taskList.mark(1);
        assertEquals(taskList.toStorage(), "E | 1 | jumping jacks | 2019-08-19 | 2020-07-10\n");
    }
}