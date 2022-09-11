package gina.commands;
import gina.GinaException;
import gina.Storage;
import gina.TaskList;
import gina.Ui;
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
            Storage storage = new Storage("../data/gina.txt");
            toDoCommand.execute(taskList, ui, storage);
            assertEquals(1, taskList.size());
        } catch (GinaException e) {
            fail();
        }
    }

    @Test
    public void todoCommandTest2(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("../data/gina.txt");
            toDoCommand.execute(taskList, ui, storage);
            fail();
        } catch (GinaException e) {
            assertEquals("Hold up! Description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void todoCommandTest3(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("   ");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("./data/gina.txt");
            toDoCommand.execute(taskList, ui, storage);
            fail();
        } catch (GinaException e) {
            assertEquals("Hold up! Description cannot be empty!", e.getMessage());
        }
    }
}
