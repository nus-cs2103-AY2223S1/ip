package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.response.DukeResponse;
import duke.response.EventResponse;

public class ParserTest {
    @Test
    public void getResponse_validInput_returnCorrectResponse() {
        DukeList list = new DukeList();
        String input = "event bookfair /at 27 aug 4.30pm";
        try {
            DukeResponse response = Parser.getResponse(list, input);
            assertEquals(EventResponse.class, response.getClass());
            assertFalse(response.isExit());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void strToDate_validDateStr_returnCorrectDateTime() {
        String dateTimeStr = "27 Aug 2025 11:59pm";
        LocalDateTime correctDateTime = LocalDateTime.of(2025, 8, 27, 23, 59);

        try {
            assertEquals(correctDateTime, Parser.strToDate(dateTimeStr));
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void strToDateFromStorage_validDateStr_returnCorrectDateTime() {
        String dateTimeStr = "2025-08-27T23:59";
        LocalDateTime correctDateTime = LocalDateTime.of(2025, 8, 27, 23, 59);
        assertEquals(correctDateTime, Parser.strToDateFromStorage(dateTimeStr));
    }
}
