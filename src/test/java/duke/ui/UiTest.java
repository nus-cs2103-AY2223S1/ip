package duke.ui;

import duke.tasklist.TaskList;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    TaskList taskList = new TaskList(new ArrayList<>());

    @Test
    public void testGetEmptyList() {
        assertEquals("", ui.getList(taskList));
    }

    @Test
    public void testGetList() {
        taskList.addEvent("watch TF2 i69 /at 2022-08-27");
        assertEquals("1.[E][ ] watch TF2 i69 (at: 2022-08-27)",
                ui.getList(taskList));
    }

    @Test
    public void testGetMarkedTaskMessage() {
        assertEquals("I have marked this task as done:\n",
                ui.getMarkedTaskMessage());
    }
}
