package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.BotUI;

public class ParserTest {
    private static final BotUI UI = new BotUI();

    @Test
    public void testExtractDate_invalidDateFormat_exceptionThrown() {
        try {
            Parser.extractDateTime("dummyDetail /at 2022-14-32 2300", " /at ");
            Parser.extractDateTime("dummyDetail /at 2022-02-11 2500", " /at ");
            Parser.extractDateTime("dummyDetail /at 2022", " /at ");
            Parser.extractDateTime("dummyDetail /at 1800", " /at ");
        } catch (DukeException ex) {
            assertEquals(UI.invalidDateFormat(), ex.getMessage());
        }
    }

    @Test
    public void testExtractDate_validFilteredInput_dateExtracted() {
        try {
            assertEquals(LocalDateTime.of(2022, 5, 1, 23, 0),
                    Parser.extractDateTime("dummyDetail /at 2022-05-01 2300", " /at "));
        } catch (DukeException ex) {
            System.out.println("fail");
        }
    }

    @Test
    public void testExtractDetail_validFilteredInput_detailExtracted() {
        assertEquals("dummyDetail",
                    Parser.extractDetail("dummyDetail /by 2022-05-01 2300", " /by "));
    }

    @Test
    public void testConvertTime_validInput_convertSuccess() {
        assertEquals(LocalDateTime.of(2011, 1, 1, 19, 0),
                Parser.convertTime("Jan 01 2011 19:00"));
    }



}
