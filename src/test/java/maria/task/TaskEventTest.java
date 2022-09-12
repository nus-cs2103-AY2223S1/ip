package maria.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class TaskEventTest {

    @Test
    public void addTaskEventTest() {

        try {
            TaskEvent te = new TaskEvent("Birthday", false,
                    LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-01"));
            assertEquals("[Event] Birthday [Not Done] (from 1 January 2022 to 1 January 2022)", te.toString());
        } catch (TaskNoNameException e) {
            fail();
        }

    }

}
