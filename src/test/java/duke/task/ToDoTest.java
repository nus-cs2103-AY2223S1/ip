package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createToDo_validInput_getCorrectStringRepresentation() {
        ToDo sampleToDo = new ToDo("cs2103t ip", "assignment");
        assertEquals(sampleToDo.toString(), "[T][ ] cs2103t ip [assignment]");
    }

    @Test
    public void createToDo_validInput_getCorrectStorageRepresentation() {
        ToDo sampleToDo = new ToDo("cs2101 op1", "presentation");
        assertEquals(sampleToDo.toStorageRepresentation(), "T| |cs2101 op1#presentation");
    }

    @Test
    public void checkIsOnGivenDate_shouldReturnFalse() {
        ToDo sampleToDo = new ToDo("wash clothes");
        assertFalse(sampleToDo.isOnGivenDate(LocalDate.parse("2022-01-01")));
    }
}
