package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.utilities.DukeException;

public class StorageTest {

    private Todo todo;
    private Deadline deadline;
    private Event event;
    private Storage storage;

    {
        try {
            DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            storage = new Storage("data/taskstest.txt");
            todo = new Todo("This is a dummy todo task.");
            deadline = new Deadline(
                    "This is a dummy deadline task.",
                    LocalDateTime.parse("2022-09-21 18:00", d)
            );
            event = new Event(
                    "This is a dummy event task.",
                    LocalDateTime.parse("2022-09-21 18:00", d),
                    LocalDateTime.parse("2022-09-21 19:00", d)
            );
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void convertTodoToStorageFormat_stringRepresentation_correct() {
        assertEquals("T| |This is a dummy todo task.", storage.convertTodoToStorageFormat(todo));
    }

    @Test
    public void convertDeadlineToStorageFormat_stringRepresentation_correct() {
        assertEquals(
                "D| |This is a dummy deadline task.|2022-09-21 18:00",
                storage.convertDeadlineToStorageFormat(deadline)
        );
    }

    @Test
    public void convertEventToStorageFormat_stringRepresentation_correct() {
        assertEquals(
                "E| |This is a dummy event task.|2022-09-21 18:00|2022-09-21 19:00",
                storage.convertEventToStorageFormat(event)
        );
    }
}
