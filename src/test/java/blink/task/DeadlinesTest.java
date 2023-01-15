package blink.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {

    @Test
    public void addDeadline() {
        Task deadline = new Deadlines("Study", "2022-08-23");
        assertEquals("[D][ ] Study (by: AUGUST 23 2022 TUESDAY)", deadline.toString());
    }

    @Test
    public void markDeadline() {
        Task deadline = new Deadlines("Study 2", "2022-08-23");
        deadline.mark();
        assertEquals("[D][X] Study 2 (by: AUGUST 23 2022 TUESDAY)", deadline.toString());
    }

    @Test
    public void unMarkDeadline() {
        Task deadline = new Deadlines("Study 3", "2022-08-23");
        deadline.mark();
        deadline.unMark();
        assertEquals("[D][ ] Study 3 (by: AUGUST 23 2022 TUESDAY)", deadline.toString());
    }

    @Test
    public void checkDeadlineDate() {
        Task deadline = new Deadlines("Study 4", "2022-08-23");
        LocalDate date = LocalDate.parse("2022-08-23");
        assertEquals(true, deadline.checkDate(date));
    }

    @Test
    public void saveDeadline() {
        Task deadline = new Deadlines("Study 5", "2022-08-23");
        String expected = "D |0| Study 5 | 2022-08-23 | \n";
        assertEquals(expected, deadline.saveString());
    }

    @Test
    public void addTagTest() {
        Task deadline = new Deadlines("Study 6", "2022-08-23");
        deadline.addTag("sleep");
        deadline.addTag("!!!!");
        String expected = "[D][ ] Study 6 (by: AUGUST 23 2022 TUESDAY) #sleep #!!!!";
        assertEquals(expected, deadline.toString());
    }
}
