package duke.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataParserTest {

    @Test
    public void stringToDateTest() {
        String dateString = "2022-01-02";
        LocalDate date = DateParser.stringToDate(dateString);
        assertEquals(date, LocalDate.of(2022, 01, 02));
    }

}
