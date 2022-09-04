package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class DeadlineTest {
    @Test
    public void encodeTest() {
        Task task = null;
        try {
            task = new Deadline("Description", LocalDate.parse("2022-08-28"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(task.encode(), "D | 0 | Description | 2022-08-28");
    }

    @Test
    public void toStringTest() {
        Task task = null;
        try {
            task = new Deadline("Description", LocalDate.parse("2022-08-28"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(task.toString(), "[D][ ] Description (by: Aug 28 2022)");
    }
}
