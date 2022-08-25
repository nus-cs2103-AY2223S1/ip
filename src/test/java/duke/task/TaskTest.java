package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void TaskTestOne(){
        Task test = new Event("test event", LocalDate.parse("2002-02-02"));
        test.markAsDone();
        assertEquals(test.toString(), "[E][X] test event (at: Feb 2 2002)");
    }

    @Test
    public void TaskTestTwo(){
        Task test = new Deadline("task", LocalDate.parse("2015-03-01"));
        test.markAsDone();
        assertEquals(test.toString(), "[D][X] task (by: Mar 1 2015)");
    }

    @Test
    public void TaskTestThree() throws DukeException {
        Task test = new Todo("task");
        test.markAsNotDone();
        assertEquals(test.toString(), "[T][ ] task");
    }
}