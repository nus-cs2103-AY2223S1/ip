package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void newDeadlineTest() {
        String name = "test1";
        String by = "testing";
        Deadline newDeadline = new Deadline(name, by);
        assertEquals(String.format("[D][ ] %s (by: %s)", name, by), newDeadline.toString());
    }

    @Test
    public void newDeadlineTest_markTask() {
        String name = "test1";
        String by = "testing";
        Deadline newDeadline = new Deadline(name, by);
        newDeadline.markComplete();
        assertEquals(String.format("[D][X] %s (by: %s)", name, by), newDeadline.toString());
    }

    @Test
    public void newDeadlineTest_validDateForm1() {
        String name = "test1";
        String by = "2022-08-21";
        Deadline newDeadline = new Deadline(name, by);
        assertEquals(String.format("[D][ ] %s (by: 21 Aug 2022)", name), newDeadline.toString());
    }

    @Test
    public void newDeadlineTest_validDateForm2() {
        String name = "test1";
        String by = "21-08-2022";
        Deadline newDeadline = new Deadline(name, by);
        assertEquals(String.format("[D][ ] %s (by: 21 Aug 2022)", name), newDeadline.toString());
    }

    @Test
    public void newDeadlineTest_validDateTimeForm2() {
        String name = "test1";
        String by = "21-08-2022 0151";
        Deadline newDeadline = new Deadline(name, by);
        assertEquals(String.format("[D][ ] %s (by: 21 Aug 2022 01:51)", name), newDeadline.toString());
    }

    @Test
    public void newDeadlineTest_validDateTimeForm1() {
        String name = "test1";
        String by = "2022-08-21 0151";
        Deadline newDeadline = new Deadline(name, by);
        assertEquals(String.format("[D][ ] %s (by: 21 Aug 2022 01:51)", name, by), newDeadline.toString());
    }
}
