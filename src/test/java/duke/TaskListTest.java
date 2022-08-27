package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getTest() {
        ToDos test = new ToDos("Read diary");
        TaskList lst = new TaskList();
        lst.add(test);
        assertEquals(test,lst.get(0));
    }
}
