package bloop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    public void addTask_emptyCommand_exceptionThrown() {
        Ui ui = new Ui();
        Storage storage = new Storage("BloopDataTest.txt", ui);
        TaskList taskList = new TaskList(ui, storage);
        try {
            taskList.addTask("todo", 'T');
            fail();
        } catch(BloopException e) {
            assertEquals("There is no task to do", e.getMessage());
        }
    }
}