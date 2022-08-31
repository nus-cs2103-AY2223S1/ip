package sky.command;

import org.junit.jupiter.api.Test;
import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineCommandTest {
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage("data/sky.txt");

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void DeadlineTestOnEmptyList() {
        try {
            String fullCommand = "deadline submit Math assignment /by 2022/09/22 1800";
            String expected = "Got it. I've added this task: \n" +
                    "    " + "[D][ ] submit Math assignment (by: Sep 22 2022, 6:00PM)" +
                    "\nNow you have 1 task in the list.";
            assertEquals(expected, new DeadlineCommand(fullCommand).execute(this.taskList, this.storage));
        } catch (TextNoMeaningException e) {
            System.out.println(e.getMessage());
            fail("Got TextNoMeaningException.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
