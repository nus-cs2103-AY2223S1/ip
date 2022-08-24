package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void convertToDate_validDate_localDateReturned() {
        try {
            LocalDate date = Parser.convertToDateObj("2022-02-02");
            assertEquals(date, LocalDate.parse("2022-02-02"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void convertToDate_invalidString_dukeExceptionThrown() {
        try {
            Parser.convertToDateObj("someRandomString");
            fail();
        } catch (DukeException e) {
            //Pass
        }
    }

    @Test
    public void printDateTest() {
        LocalDate date = LocalDate.parse("2022-02-02");
        String expected = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String actual = Parser.printDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void printSaveFileDateTest() {
        LocalDate date = LocalDate.parse("2022-02-02");
        String expected = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String actual = Parser.printSaveFileDate(date);
        assertEquals(expected, actual);
    }
}
