package duke.task;

import duke.duke.ui.DukeException;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    public void DeadlineTest() {
        Deadline test = new Deadline(" test1", LocalDate.parse("1990-12-11"));
        assertEquals("[D][ ] test1 (by: Dec 11 1990)", test.toString());
        assertEquals(test.getIsDone(), false);
        test.markDone();
        assertEquals("[D][X] test1 (by: Dec 11 1990)", test.toString());
    }

    @Test
    public void TodoTest() throws DukeException {
        Todo test = new Todo(" test2");
        assertEquals(test.toString(), "[T][ ] test2");
        assertEquals(test.getIsDone(), false);
        test.markDone();
        assertEquals(test.toString(), "[T][X] test2");
    }

    @Test
    public void EventTest() {
        Event test = new Event(" test3", LocalDate.parse("1980-09-01"));
        assertEquals(test.toString(), "[E][ ] test3 (at: Sep 01 1980)");
        assertEquals(test.getIsDone(), false);
        test.markDone();
        assertEquals(test.toString(), "[E][X] test3 (at: Sep 01 1980)");
    }
}