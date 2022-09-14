package pluto.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toFile_returnsTasktoFileFormat_success() {
        LocalDateTime date = LocalDateTime.parse("04-05-2022 1800", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        Event e = new Event("join meeting", date);
        e.markAsDone();
        assertEquals(e.toFile(), "E | 1 | join meeting | 04-05-2022 1800");
    }

    @Test
    public void getDateMaybe_returnsLocalDate_success() {
        LocalDateTime date = LocalDateTime.parse("04-05-2022 1800", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        Deadline d = new Deadline("join meeting", date);
        assertEquals(d.getDateMaybe(), LocalDate.of(2022, 5, 4));
    }
}
