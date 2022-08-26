package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    public void testToString() {
        assertEquals("[D][ ] test1 (by: Dec 12 2022)",
                new Deadline("test1", false, LocalDate.of(2022, 12, 12)).toString());
        assertEquals("[D][X] test2 (by: Jan 25 2023)",
                new Deadline("test2", true, LocalDate.of(2023, 1, 25)).toString());

    }

    @Test
    public void testTaskToDataString() {
        Deadline test3 = new Deadline("test3", false, LocalDate.of(2022, 11, 28));
        assertEquals("D | X | test3 | 28/11/2022\n", test3.taskToDataString());

        Deadline test4 = new Deadline("test4", true, LocalDate.of(2023, 2, 19));
        assertEquals("D | O | test4 | 19/02/2023\n", test4.taskToDataString());
    }
}