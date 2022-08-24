import org.junit.jupiter.api.Test;
import duke.tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {


    @Test
    public void newDeadlineTest() {
        assertEquals(new Deadline(
                        "Party", LocalDate.parse("2022-12-13").
                        format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).toString(),
                "[D][ ] Party (by: Dec 13 2022)");
        assertEquals(new Deadline(
                        "Wedding Ceremony", LocalDate.parse("2012-06-01").
                        format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).toString(),
                "[D][ ] Wedding Ceremony (by: Jun 01 2012)");
    }


    @Test
    public void markDeadlineTest() {
        String date = LocalDate.parse("2022-12-13").format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        Deadline Party = new Deadline("Birthday Party", date);
        Party.mark();

        assertEquals(Party.toString(), "[D][X] Birthday Party (by: Dec 13 2022)");
    }

    @Test
    public void unmarkDeadlineTest() {
        String date = LocalDate.parse("2022-12-13").format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        Deadline Party = new Deadline("Birthday Party", date);
        Party.mark();
        Party.unmark();

        assertEquals(Party.toString(), "[D][ ] Birthday Party (by: Dec 13 2022)");
    }
}
