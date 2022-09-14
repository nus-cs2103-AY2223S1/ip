package utils;

import bobthebot.exceptions.BobException;
import bobthebot.tasks.Deadline;
import bobthebot.tasks.Event;
import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.utils.Storage;
import bobthebot.utils.Parser;
import bobthebot.utils.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDeadlineTest() throws BobException {
        Parser parser = new Parser();
        Storage actualStorage = new Storage("./../data/test.txt");
        ArrayList<Task> actualTaskList = new ArrayList<>();
        ToDoList actualTodoList = new ToDoList(actualTaskList, actualStorage);

        String command = "deadline d1 /by 2022-12-12 0000";
        String actual = parser.parseCommand(command, actualTodoList);

        Deadline deadline = new Deadline("d1", "2022-12-12 0000");
        Storage expectedStorage = new Storage("./data.txt");
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(deadline);
        ToDoList expectedTodoList = new ToDoList(expectedTaskList, expectedStorage);
        String expected = Ui.taskAddedMessage(deadline, expectedTodoList);

        assertEquals(actual, expected);
    }

    @Test
    public void parseEventTest() throws BobException {
        Parser parser = new Parser();
        Storage actualStorage = new Storage("./");
        ArrayList<Task> actualTaskList = new ArrayList<>();
        ToDoList actualTodoList = new ToDoList(actualTaskList, actualStorage);

        String command = "event e1 /at 2022-12-12 0000";
        String actual = parser.parseCommand(command, actualTodoList);

        Event event = new Event("e1", "2022-12-12 0000");
        Storage expectedStorage = new Storage("./../data/test.txt");
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(event);
        ToDoList expectedTodoList = new ToDoList(expectedTaskList, expectedStorage);
        String expected = Ui.taskAddedMessage(event, expectedTodoList);

        assertEquals(actual, expected);
    }
}
