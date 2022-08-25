package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskTest(){
        TaskList list = new TaskList();
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }

    }

}
