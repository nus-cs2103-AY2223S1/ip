package gina.commands;
import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoCommandTest {
    @Test
    public void todoCommandTest1(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("eat ramen");
            TaskAndContactList taskAndContactList = new TaskAndContactList();
            Ui ui = new Ui();
            Storage storage = new Storage("../data/gina.txt");
            toDoCommand.execute(taskAndContactList, ui, storage);
            assertEquals(1, taskAndContactList.tasksSize());
        } catch (GinaException e) {
            fail();
        }
    }

    @Test
    public void todoCommandTest2(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("");
            TaskAndContactList taskAndContactList = new TaskAndContactList();
            Ui ui = new Ui();
            Storage storage = new Storage("../data/gina.txt");
            toDoCommand.execute(taskAndContactList, ui, storage);
            fail();
        } catch (GinaException e) {
            assertEquals("Hold your horses! The description can't be empty!", e.getMessage());
        }
    }

    @Test
    public void todoCommandTest3(){
        try {
            ToDoCommand toDoCommand = new ToDoCommand("   ");
            TaskAndContactList taskAndContactList = new TaskAndContactList();
            Ui ui = new Ui();
            Storage storage = new Storage("./data/gina.txt");
            toDoCommand.execute(taskAndContactList, ui, storage);
            fail();
        } catch (GinaException e) {
            assertEquals("Hold your horses! qThe description can't be empty!", e.getMessage());
        }
    }
}
