package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void dummyTest1() {
        Deadline task = new Deadline("homework", new String[]{" ", "2022-02-20", "23:59"});
        assertEquals("[D][ ] homework (by: Feb 20 2022 11:59PM)", task.toString());
    }

    @Test
    public void dummyTest2() {
        Deadline task = new Deadline("homework", new String[]{" ", "2022-02-20", "23:59"});
        task.mark();
        assertEquals("[D][X] homework (by: Feb 20 2022 11:59PM)", task.toString());
    }

    @Test
    public void dummyTest3() {
        Deadline task = new Deadline("coding assignment", new String[]{" ", "2022-04-22", "22:00"});
        task.mark();
        task.unmark();
        assertEquals("[D][ ] coding assignment (by: Apr 22 2022 10:00PM)", task.toString());
    }
}
