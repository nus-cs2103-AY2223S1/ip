package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    public void deleteOutOfArrayTest() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(1);
            deleteCommand.execute(new Storage(), new UI(), new TaskList(new ArrayList<>()));
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: please input a valid task number",e.toString());
        }
    }

    @Test
    public void deleteNegativeNumberTest() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(-1);
            deleteCommand.execute(new Storage(), new UI(), new TaskList(new ArrayList<>()));
        } catch (DukeException e) {
            assertEquals("( • ᴖ • ｡) Error encountered: please input a valid task number",e.toString());
        }
    }

    @Test
    public void successfulDeleteTest() {
        TaskList list = new TaskList(new ArrayList<>());
        try {
            DeleteCommand deleteCommand = new DeleteCommand(1);
            list.add(new ToDo("1"));
            deleteCommand.execute(new Storage(), new UI(), list);

        } catch (DukeException e) {
            e.toString();
        }
        assertEquals(list.size(),0);

    }
}
