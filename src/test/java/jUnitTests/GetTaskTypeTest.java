package jUnitTests;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTaskTypeTest {

    @Test
    void todoShouldReturnT() {
        Todo todo = new Todo("dummy task");
        assertEquals('T', todo.getTaskType());
    }

    @Test
    void deadlineShouldReturnT() {
        Deadline deadline = new Deadline("dummy task", "Monday");
        assertEquals('D', deadline.getTaskType());
    }

    @Test
    void eventShouldReturnT() {
        Event event = new Event("dummy task", "Monday 2-4pm");
        assertEquals('E', event.getTaskType());
    }
}
