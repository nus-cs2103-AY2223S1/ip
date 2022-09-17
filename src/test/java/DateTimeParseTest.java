import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.date.DateTimeParse;
import duke.exception.DukeException;

public class DateTimeParseTest {
    @Test
    public void parseDateTime_sixDigitDdMmYyHhMm_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 21, 8, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("21/06/22 08:00"));
    }

    @Test
    public void parseDateTime_eightDigitDdMmYyyyHhMm_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 21, 8, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("21/06/2022 08:00"));
    }

    @Test
    public void parseDateTime_sixDigitDmYyyyHhMm_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 1, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("01/06/2022 13:00"));
    }

    @Test
    public void parseDateTime_sixDigitYyyyDmHhMmA_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 21, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("2022/06/21 1:00PM"));
    }

    @Test
    public void parseDateTime_sixDigitYyyyMdHhMm_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 1, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("2022/6/1 13:00"));
    }

    @Test
    public void parseDateTime_fourDigitYyMdHhMm_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 1, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("22/6/1 13:00"));
    }

    @Test
    public void parseDateTime_fourDigitDdMmHhMm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 6, 22, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("22/06 13:00"));
    }

    @Test
    public void parseDateTime_fourDigitMmDdHhMm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 6, 21, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("06/21 13:00"));
    }

    @Test
    public void parseDateTime_fourDigitMmYyHhMm_success() throws DukeException {
        int dateNow = LocalDate.now().getDayOfMonth();
        LocalDateTime localDateTime = LocalDateTime.of(2032, 12, dateNow, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("1232 13:00"));
    }

    @Test
    public void parseDateTime_threeDigitddMHhMm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 6, 21, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("21 6 13:00"));
    }

    @Test
    public void parseDateTime_threeDigitMddHhMm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 6, 21, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("6-21 13:00"));
    }

    @Test
    public void parseDateTime_textDdMmmmYyyy_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 21, 13, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("21 June 2022 1:00 pm"));
    }

    @Test
    public void parseDateTime_textDdMmmYyyy_success() throws DukeException {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 21, 3, 50);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("21 Jun 2022 3:50am"));
    }

    @Test
    public void parseDateTime_textMmmYyyy_success() throws DukeException {
        int dateNow = LocalDate.now().getDayOfMonth();
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, dateNow, 15, 50);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("Mar 2022 3:50pm"));
    }

    @Test
    public void parseDateTime_textDdMmm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 3, 22, 20, 20);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("22 Mar 20-20"));
    }

    @Test
    public void parseDateTime_textMmm_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        int dateNow = LocalDate.now().getDayOfMonth();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, 3, dateNow, 20, 20);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("March 20:20"));
    }

    @Test
    public void parseDateTime_timeHhMmA_success() throws DukeException {
        int yearNow = LocalDate.now().getYear();
        int monthNow = LocalDate.now().getMonthValue();
        int dateNow = LocalDate.now().getDayOfMonth();
        LocalDateTime localDateTime = LocalDateTime.of(yearNow, monthNow, dateNow, 20, 20);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("8.20Pm"));
    }

    @Test
    public void parseDateTime_naturalDateToday_success() throws DukeException {
        LocalDateTime localDateTime = LocalDate.now().atTime(23, 59);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("tdy"));
        assertEquals(localDateTime, DateTimeParse.parseDateTime("today"));
    }

    @Test
    public void parseDateTime_naturalDateMorning_success() throws DukeException {
        LocalDateTime localDateTime = LocalDate.now().atTime(8, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("morning"));
    }

    @Test
    public void parseDateTime_naturalDateEvening_success() throws DukeException {
        LocalDateTime localDateTime = LocalDate.now().atTime(18, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("evening"));
    }

    @Test
    public void parseDateTime_naturalDateNight_success() throws DukeException {
        LocalDateTime localDateTime = LocalDate.now().atTime(20, 0);
        assertEquals(localDateTime, DateTimeParse.parseDateTime("night"));
    }
}
