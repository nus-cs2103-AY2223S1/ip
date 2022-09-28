package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void initializeTaskList() {
        ArrayList<String[]> tmp = new ArrayList<>();
        tmp.add(new String[]{"T", "0", "do cs2100 tutorial"});
        tmp.add(new String[]{"E", "0", "cs2103t lecture", "2022-09-02T16:00"});

        TaskList tasks = new TaskList(tmp);
        assertEquals(tasks.getLength(), 2);
        assertEquals(tasks.getTask(1).toString(), "[T][ ] do cs2100 tutorial");
        assertEquals(tasks.getTask(2).toString(), "[E][ ] cs2103t lecture (at: Sep 2 2022 1600H)");
    }

    @Test
    public void markAndUnmarkTask() {
        ArrayList<String[]> tmp = new ArrayList<>();
        tmp.add(new String[]{"T", "0", "do cs2100 tutorial"});
        TaskList tasks = new TaskList(tmp);
        assertEquals(tasks.getTask(1).toString(), "[T][ ] do cs2100 tutorial");

        tasks.mark(1);
        assertEquals(tasks.getTask(1).toString(), "[T][X] do cs2100 tutorial");

        tasks.unmark(1);
        assertEquals(tasks.getTask(1).toString(), "[T][ ] do cs2100 tutorial");
    }

    @Test
    public void deleteTask() {
        ArrayList<String[]> tmp = new ArrayList<>();
        tmp.add(new String[]{"T", "0", "do cs2100 tutorial"});
        TaskList tasks = new TaskList(tmp);
        assertEquals(tasks.getTask(1).toString(), "[T][ ] do cs2100 tutorial");

        tasks.delete(1);
        assertEquals(tasks.getLength(), 0);
    }

}
