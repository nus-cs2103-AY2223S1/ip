package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;



public class UiTest {
    private Ui ui = new Ui();
    private TaskList taskList = new TaskList(new ArrayList<>());

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
