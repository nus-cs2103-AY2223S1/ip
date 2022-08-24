package duke;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.testng.AssertJUnit.assertEquals;


public class JUnitTests {
    @Test
    public void checkNumeric() throws IOException, ParseException {
        SavedTaskHandler storage = new SavedTaskHandler();
        Parser parser = new Parser(storage);
        assertEquals(parser.isNumeric("i am not numeric"), false);
        assertEquals(parser.isNumeric("i am not numeric 2"), false);
        assertEquals(parser.isNumeric("i am not numeric"), false);
        assertEquals(parser.isNumeric("111111"), true);
    }

    @Test
    public void addAddsTasks() {
        TaskList taskList = new TaskList();
        ToDos todo = new ToDos("homework");
        assertEquals(taskList.size(), 0);
        taskList.add(todo);
        assertEquals(taskList.size(), 1);
        taskList.add(todo);
        taskList.add(todo);
        taskList.add(todo);
        assertEquals(taskList.size(), 4);

    }
    @Test
    public void removeRemovesTasks(){
        TaskList taskList = new TaskList();
        ToDos todo = new ToDos("homework");
        assertEquals(taskList.size(), 0);
        taskList.add(todo);
        assertEquals(taskList.size(), 1);
        taskList.add(todo);
        taskList.add(todo);
        taskList.add(todo);
        taskList.remove(3);
        taskList.remove(2);
        assertEquals(taskList.size(), 2);

    }
}
