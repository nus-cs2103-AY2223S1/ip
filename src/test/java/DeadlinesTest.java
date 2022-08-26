import duke.Deadlines;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    Deadlines deadlines = new Deadlines("return book", 1, "1971-5-29");

    @Test
    public void deadlinesTest() {
        assertEquals(deadlines.toString(), "Got it. I've added this task:\n" + "[D][ ] " + "return book"
                + "(by: " + "1971-5-29" + ")\n" + "Now you have " + 1 + " tasks in the list.");
    }
}
