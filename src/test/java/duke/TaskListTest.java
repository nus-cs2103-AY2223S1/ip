package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private final TaskList taskList = new TaskList();
    @Test
    public void addTest() {
        String commandString = "deadline test /by 10/02/22 1800";
        Deadline deadline = new Deadline(false,
                "test",
                LocalDateTime.parse("10/02/22 1800", DateTimeFormatter.ofPattern("dd/MM/yy HHmm")));
        try {
            taskList.add(deadline,
                    commandString,
                    new Storage(new Ui(), "data.txt", "data"));
        } catch (IOException e) {
            fail();
        }

        assertEquals(deadline, taskList.getTask(0));
    }
}
