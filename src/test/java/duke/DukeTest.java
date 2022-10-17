package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void TODOtoStringTest() {
        assertEquals("[T][ ] watch show", new Todo("watch show").toString());
    }

    @Test
    public void TODOsaveStringTest() {
        assertEquals("T | 0 | watch show", new Todo("watch show").saveString());
    }

    @Test
    public void DDLtoStringTest() {
        assertEquals("[D][ ] watch lecture (by: Jul 24 2022)",
                new Deadline("watch lecture", LocalDate.parse("2022-07-24")).toString());
    }

    @Test
    public void DDLsaveStringTest() {
        assertEquals("D | 0 | watch lecture | 2022-07-24",
                new Deadline("watch lecture", LocalDate.parse("2022-07-24")).saveString() );
    }
}