package duke;

import duke.command.SnoozeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Contains JUnit tests
*/
public class DukeTest {

    @Test
    public void snooze_changeDate_success() {
        TaskList testList = new TaskList();
        testList.addTask(new Task("hw", "deadline", "2022-12-20"));
        SnoozeCommand command = new SnoozeCommand("hw", "2022-12-21");
        try {
            command.exec(testList, new Storage(), new Ui());
            Task task = testList.getTaskList().get(0);
            assertEquals(task.onDate("2022-12-21"), true);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getTaskNameTest(){
        String result = Parser.getTaskName("event graduation /at 2024-03-20");
        assertEquals("graduation", result);
    }

    @Test
    public void toStringTest(){
        Task testTask = new Task("ip", "deadline", "2022-08-25");
        assertEquals("[D][ ] ip 2022-08-25", testTask.toString());
    }
}
