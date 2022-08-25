package duke.task;

import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseDateFormats_correctInput_success() throws Exception {
        assertEquals(LocalDate.parse("2021-11-23"),
                Parser.parseDateFormats("2021-11-23"));
        assertEquals(LocalDate.parse("2021-11-23"),
                Parser.parseDateFormats("2021/11/23"));
        assertEquals(LocalDate.parse("2021-11-23"),
                Parser.parseDateFormats("Nov 23 2021"));
    }

    @Test
    public void parseDateFormats_wrongInput_exceptionThrown() {
        try{
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats("something"));
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats(""));
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats(null));
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats("2021 11 23"));
            fail();
        } catch (Exception e) {
            assertEquals("☹ Invalid date format!", e.getMessage());
        }
    }

    @Test
    public void parse_correctInput_success() {

    }
}
