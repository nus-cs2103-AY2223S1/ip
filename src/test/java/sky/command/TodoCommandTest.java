package sky.command;

import org.junit.jupiter.api.Test;
import sky.Storage;
import sky.TaskList;
import sky.Ui;
import sky.exception.TextNoMeaningException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoCommandTest {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage("data/sky.txt");

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void TodoTestOnEmptyList() {
        try {
            String fullCommand = "todo read book";
            String expected = "  Got it. I've added this task: \n" +
                    "    " + "[T][ ] read book" +
                    "\n  Now you have 1 task in the list.";
            assertEquals(expected, new TodoCommand(fullCommand).execute(this.taskList, this.ui, this.storage));
        } catch (TextNoMeaningException e) {
            System.out.println(e);
            fail("Got TextNoMeaningException.");
        }
    }
}
