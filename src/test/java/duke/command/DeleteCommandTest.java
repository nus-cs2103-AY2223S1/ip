package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.task.ToDo;

public class DeleteCommandTest {
    private TaskList tasks;
    private Storage storage;

    public DeleteCommandTest() {
        this.tasks = new TaskList();
        try {
            this.storage = new Storage();
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void deleteTest() {
        tasks.add(new ToDo("test test"));
        try {
            Command command = new DeleteCommand("1");
            command.execute(tasks, storage);
            assertEquals(0, tasks.size());
        } catch (DukeException e) {
            fail(e);
        }
        tasks = new TaskList();
    }

    @Test
    public void deleteTest_incorrectIndex() {
        try {
            Command command = new DeleteCommand("1");
            command.execute(tasks, storage);
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Please input a correct number").toString(), e.toString());
        }
    }

    @Test
    public void deleteTest_notANumber() {
        try {
            Command command = new DeleteCommand("a");
            command.execute(tasks, storage);
            fail("An exception should be thrown");
        } catch (DukeException e) {
            assertEquals(new InvalidInput("Input is not a number").toString(), e.toString());
        }
    }

}
