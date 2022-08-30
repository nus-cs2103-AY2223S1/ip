package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getStringToSaveTest() {
        assertEquals("D | 0 | return book | 2022-08-06",
                new Deadline("return book", "2022-08-06").getStringToSave());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] return book (by: Aug 6 2022)",
                new Deadline("return book", "2022-08-06").toString());
    }
}
