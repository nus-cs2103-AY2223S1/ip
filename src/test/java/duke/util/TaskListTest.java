package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void getListInfo_emptyInput_specialHintOutput() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getListInfo(), "The list is empty.");
    }
}
