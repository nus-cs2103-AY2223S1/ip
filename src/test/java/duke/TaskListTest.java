package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void addToTaskList_correct() {
        Chick chick = new Chick();
        chick.parseCommand("deadline return book /by 2022-08-28");
        assertEquals(chick.tasks.getSize(), 1);
    }

    @Test
    public void markTask_correct() {
        Chick chick = new Chick();
        chick.parseCommand("deadline return book /by 2022-08-28");
        chick.parseCommand("mark 1");
        assertEquals(chick.tasks.tasks.get(0).getStatus(), "mark");
        chick.parseCommand("unmark 1");
        assertEquals(chick.tasks.tasks.get(0).getStatus(), "unmark");
    }
}
