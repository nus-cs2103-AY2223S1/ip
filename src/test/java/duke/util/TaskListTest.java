package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    TaskList testSubject = new TaskList();

    @Test
    public void getListInfo_emptyInput_specialHintOutput() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getListInfo(), "The list is empty.");
    }
}
