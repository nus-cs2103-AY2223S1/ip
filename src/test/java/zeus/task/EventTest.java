package zeus.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testDateString(){
        Event e = new Event("birthday", "22 Aug 2022");
        assertEquals("[E][ ] birthday (at: 22 Aug 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[E][X] birthday (at: 22 Aug 2022)", e.toString(), "markAsDone() method works");
    }

    @Test
    void testLocalDate() {
        LocalDate ld = LocalDate.parse("2022-08-22");
        Event e = new Event("birthday", ld);
        assertEquals("[E][ ] birthday (at: Aug 22 2022)", e.toString(), "toString() method works");

        e.markAsDone();
        assertEquals("[E][X] birthday (at: Aug 22 2022)", e.toString(), "markAsDone() method works");
    }
}
