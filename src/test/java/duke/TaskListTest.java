package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void displayListTest() {
        TaskList t = new TaskList(new ArrayList<>(List.of("[T][ ] wake up",
                "[D][ ] homework (by: Aug 24 2022)",
                "[E][ ] lab (at: Jul 30 2023)")));
        String desiredOutput = "1. [T][ ] wake up\n" +
                "2. [D][ ] homework (by: Aug 24 2022)\n" +
                "3. [E][ ] lab (at: Jul 30 2023)\n";
        assertEquals(desiredOutput, t.displayList());
    }

}
