package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createToDo_validInput_getCorrectStringRepresentation() {
        ToDo sampleToDo = new ToDo("cs2103t ip");
        assertEquals(sampleToDo.toString(), "[T][ ] cs2103t ip");
    }

    @Test
    public void createToDo_validInput_getCorrectStorageRepresentation() {
        ToDo sampleToDo = new ToDo("cs2101 op1");
        assertEquals(sampleToDo.toStorageRepresentation(), "T| |cs2101 op1");
    }
}
