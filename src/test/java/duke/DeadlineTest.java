package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest1() {
        Deadline deadline1 = new Deadline("project1", "2022-08-25", false);
        assertEquals("[D][ ] project1 (by: Aug 25 2022)", deadline1.toString());
    }

    @Test
    public void toStringTest2() {
        Deadline deadline2 = new Deadline("project2", "2023-12-12", true);
        assertEquals("[D][X] project2 (by: Dec 12 2023)", deadline2.toString());
    }

    @Test
    public void getCsvStringTest1() {
        Deadline deadline3 = new Deadline("project3", "2022-08-25", false);
        assertEquals("deadline false project3 /by 2022-08-25", deadline3.getTxtString());
    }

    @Test
    public void getCsvStringTest2() {
        Deadline deadline4 = new Deadline("project4", "2023-12-12", true);
        assertEquals("deadline true project4 /by 2023-12-12", deadline4.getTxtString());
    }
}
