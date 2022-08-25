package duke;


import duke.task.Deadline;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toDoDeadline() {
        Deadline testDeadline = new Deadline("Return book",
                LocalDate.parse("2022-08-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        assertEquals(testDeadline.taskInfo(), "[D] [ ] Return book (by:Aug 25 2022)");
    }


}
