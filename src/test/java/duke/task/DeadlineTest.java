package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class DeadlineTest {

    @Test
    public void stringFormatting_unmarkedStringFormatting_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Deadline deadline = new Deadline("complete CS2103t ip",
                LocalDate.parse("2025-09-25", dtf));
        Assertions.assertEquals("D # F # complete CS2103t ip # 2025-09-25",
                deadline.stringFormatting());
    }

    @Test
    public void stringFormatting_markedStringFormatting_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Deadline deadline = new Deadline("complete CS2103t ip",
                LocalDate.parse("2025-09-25", dtf));
        deadline.setCompleted(true);
        Assertions.assertEquals("D # T # complete CS2103t ip # 2025-09-25",
                deadline.stringFormatting());
    }
}
