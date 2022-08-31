package sky.command;

import org.junit.jupiter.api.Test;
import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventCommandTest {
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage("data/sky.txt");

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void EventTestOnEmptyList() {
        try {
            String fullCommand = "event join friends for hotpot /at 2022/09/23 1700-1900";
            String expected = "Got it. I've added this task: \n" +
                    "    " + "[E][ ] join friends for hotpot (at: Sep 23 2022, 5:00PM-7:00PM)" +
                    "\nNow you have 1 task in the list.";
            assertEquals(expected, new EventCommand(fullCommand).execute(this.taskList, this.storage));
        } catch (TextNoMeaningException e) {
            System.out.println(e.getMessage());
            fail("Got TextNoMeaningException.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}