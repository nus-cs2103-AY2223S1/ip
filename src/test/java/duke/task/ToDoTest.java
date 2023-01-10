package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toStringTest() {
        ToDo toDo = new ToDo();

        toDo.setText("Test toString");
        assertEquals(toDo.toString(), "[T][ ] Test toString");

        toDo.setComplete(true);
        assertEquals(toDo.toString(), "[T][X] Test toString");
    }

    @Test
    public void taskTypeTest() {
        ToDo toDo = new ToDo();
        assertEquals(toDo.getTaskType(), Task.TaskType.ToDo);
    }

    @Test
    public void serializeTest() {
        ToDo toDo = new ToDo();
        toDo.setText("Test toString");
        toDo.setComplete(true);
        System.out.println(toDo.serialize());
        assertEquals(toDo, Task.deserialize(toDo.serialize()));
    }
}
