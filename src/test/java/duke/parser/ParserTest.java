package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.MarkCommand;
import duke.exceptions.ParseInputException;

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

        try {
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats("something"));
            fail();
        } catch (Exception e) {
            assertEquals("☹ Invalid date format!", e.getMessage());
        }

        try {
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats(""));
            fail();
        } catch (Exception e) {
            assertEquals("☹ Invalid date format!", e.getMessage());
        }

        try {
            assertEquals(LocalDate.parse("2021-11-23"),
                    Parser.parseDateFormats("2021 11 23"));
            fail();
        } catch (Exception e) {
            assertEquals("☹ Invalid date format!", e.getMessage());
        }
    }

    @Test
    public void parseIndex_correctInput_success() throws ParseInputException {
        Command commandA = new MarkCommand(1);
        Command commandB = Parser.parseIndex("mark", "2");
        assertEquals(commandA, commandB);
    }
}
