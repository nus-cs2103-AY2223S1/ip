package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toStringTest() {
        Event event = new Event();

        event.setText("Test toString");
        event.setDetails(LocalDate.of(2022, 8, 24));
        assertEquals(event.toString(), "[E][ ] Test toString (at: Wednesday, 24 August 2022)");

        event.setComplete(true);
        assertEquals(event.toString(), "[E][X] Test toString (at: Wednesday, 24 August 2022)");
    }

    @Test
    public void taskTypeTest() {
        Event event = new Event();
        assertEquals(event.getTaskType(), Task.TaskType.Event);
    }

    @Test
    public void serializeTest() {
        Event event = new Event();
        event.setText("Test toString");
        event.setComplete(true);
        event.setDetails(LocalDate.of(3000, 1, 1));
        System.out.println(event.serialize());
        assertEquals(event, Task.deserialize(event.serialize()));
    }

}
