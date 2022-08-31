package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoCommandTest {
    @Test
    public void todoCommandTest1(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("eat ramen");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("../data/duke.txt");
            toDoCommand.execute(taskList, ui, storage);
            assertEquals(1, taskList.size());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void todoCommandTest2(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("../data/duke.txt");
            toDoCommand.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Hold up! Description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void todoCommandTest3(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("   ");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("./data/duke.txt");
            toDoCommand.execute(taskList, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Hold up! Description cannot be empty!", e.getMessage());
        }
    }
}
