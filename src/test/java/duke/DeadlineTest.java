package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    private Deadline dl = new Deadline("Borrow Book", LocalDate.parse("2022-02-02"));

    @Test
    public void toStringTest(){
        String expected = "[D][ ] Borrow Book (by: Feb 2 2022)";
        assertEquals(expected, dl.toString());
    }

    @Test
    public void toFileStringTest(){
        String expected = "D | 0 | Borrow Book | Feb 2 2022";
        assertEquals(expected, dl.toFileString());
    }

}
