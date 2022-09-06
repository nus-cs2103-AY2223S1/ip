package sky.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import sky.Sky;
import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;

public class TodoCommandTest {
    private Sky sky;
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage("data/sky.txt");
    private History history = new History(this.sky, this.taskList);

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void todoTestOnEmptyList() {
        try {
            String fullCommand = "todo read book";
            String expected = "Got it. I've added this task: \n"
                    + "    " + "[T][ ] read book"
                    + "\nNow you have 1 task in the list.";
            assertEquals(expected, new TodoCommand(fullCommand).execute(this.taskList, this.storage, this.history));
        } catch (TextNoMeaningException e) {
            System.out.println(e.getMessage());
            fail("Got TextNoMeaningException.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
