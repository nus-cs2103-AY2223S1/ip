import Duke.Duke;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private final Duke duke = new Duke();

    @Test
    public void testTodoClass() {
        assertEquals("[T][ ] hello", duke.new Todo("hello").toString());

        assertEquals("[T][ ] todotask", duke.new Todo("todotask").toString());
    }

    @Test
    public void testEventClass() {
        assertEquals("[E][ ] hello (at: Jan 1 2022)",
                duke.new Event("hello", LocalDate.parse("2022-01-01")).toString());

        assertEquals("[E][ ] evento (at: Aug 22 2022)",
                duke.new Event("evento", LocalDate.parse("2022-08-22")).toString());
    }

    @Test
    public void testDeadlineClass() {
        assertEquals("[D][ ] hello (by: Dec 1 2022)",
                duke.new Deadline("hello", LocalDate.parse("2022-12-01")).toString());
    }


}