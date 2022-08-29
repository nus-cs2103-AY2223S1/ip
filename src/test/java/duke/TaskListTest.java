package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList tasks = new TaskList();
    @Test
    public void testAddingItem() {
        tasks.add(new Todo("todo testing", false));
        assertEquals(1, tasks.getSize());
        }

    @Test
    public void testDeleteItem(){
        tasks.add(new Todo("todo testing", false));
        tasks.delete(0);
        assertEquals(0, tasks.getSize());
    }
}

