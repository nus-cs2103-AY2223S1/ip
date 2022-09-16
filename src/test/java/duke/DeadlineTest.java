package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void testDeadline() {
        Deadline testDeadline = new Deadline("Return book",
                LocalDate.parse("2022-08-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        assertEquals(testDeadline.taskInfo(), "[D] [ ] [LOW] Return book (by:Aug 25 2022)");
    }

    @Test
    public void testMarkDeadline() {
        Deadline testDeadline = new Deadline("Return book",
                LocalDate.parse("2022-08-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        testDeadline.markAsDone();
        assertEquals(testDeadline.taskInfo(), "[D] [X] [LOW] Return book (by:Aug 25 2022 18:00)");
    }

    @Test
    public void testChangeDeadlinePriority() {
        Deadline testDeadline = new Deadline("Return book",
                LocalDate.parse("2022-08-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse("1800", DateTimeFormatter.ofPattern("HHmm")));
        testDeadline.setTaskPriority("medium");
        assertEquals(testDeadline.getTaskPriority(), "MEDIUM");
    }

}
