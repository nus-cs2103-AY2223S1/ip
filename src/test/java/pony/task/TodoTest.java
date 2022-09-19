package pony.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void createTaskTest() {
        ToDo task = new ToDo("Math Homework");
        assertEquals("[T][ ] Math Homework", task.toString());
    }
    
}
