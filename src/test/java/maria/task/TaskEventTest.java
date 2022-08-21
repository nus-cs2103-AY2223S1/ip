package maria.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEventTest {

    @Test
    public void addTaskEventTest() {

        try {
            TaskEvent te = new TaskEvent("Birthday", false,
                    LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-01"));
            assertEquals(te.toString(), "[E][ ] Birthday (from 1 January 2022 to 1 January 2022)");
        } catch (TaskNoNameException e) {
            fail();
        }

    }

}
