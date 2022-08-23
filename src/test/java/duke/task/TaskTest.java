package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task t = new Task("This is a dummy task");

    @Test
    public void getDoneStatus_initialStatus_notDone() {
        assertEquals(" ", t.getDoneStatus());
    }

    @Test
    public void getDoneStatus_finalStatus_done() {
        t.setDoneStatus(true);
        assertEquals("X", t.getDoneStatus());
    }

    @Test
    public void getDescription_description_correct() {
        assertEquals("This is a dummy task", t.getDescription());
    }

    @Test
    public void toString_stringRepresentation_correct() {
        assertEquals("[ ] This is a dummy task", t.toString());
    }
}
