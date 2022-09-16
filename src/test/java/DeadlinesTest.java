import duke.Deadlines;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DeadlinesTest {
    @Test
    public void deadlinesTest() {
        String time = "1971-05-29";
        LocalDate date = LocalDate.parse(time);
        String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        ArrayList<String> arrayList = new ArrayList<>();
        Deadlines deadlines = new Deadlines("deadline return book /by 1971-05-29", 1, arrayList);
        assertEquals(deadlines.toString(), "Got it. I've added this task:\n" + "[D][ ] " + "return book "
                + "(by: " + tranTime + ")\n" + "Now you have " + 1 + " tasks in the list.");
    }
}
