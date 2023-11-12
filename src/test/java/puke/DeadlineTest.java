package puke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Task d = new Deadline("Bring in clothes", LocalDate.parse("2030-12-12"));
        assertEquals("[D][ ] Bring in clothes(by: Dec 12 2030)", d.toString());
    }
}
