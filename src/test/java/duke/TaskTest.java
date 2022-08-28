package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void stringToTaskTest() {
        String taskString = " D | 0 | homework | 08/09/2022 1200";
        Deadline dl = (Deadline) Task.fileStringToTask(taskString);
        assert dl != null;
        assertEquals(dl.taskToFileString(), " D | 0 | homework | 08/09/2022 1200" );
    }

}
