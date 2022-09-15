package duke.task;

import duke.logic.task.Deadline;
import duke.logic.task.Task;
import duke.logic.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessUserInputTest {
    @Test
    public void processTodoInputTest(){
        String actual = duke.logic.ProcessUserInput.process(new ArrayList<>(), "todo testing");
        ToDo todo = new ToDo("testing");
        ArrayList<Task> workList = new ArrayList<>();
        workList.add(todo);
        String expected = Task.add(workList, "todo testing") + todo + "\n"
                + Task.updateNumOfTask(workList);
        assertEquals(expected, actual);
    }

    @Test
    public void processDeadlineInputTest(){
        String actual = duke.logic.ProcessUserInput.process(new ArrayList<>(), "deadline testing /by 2022-09-15");
        Deadline deadline = new Deadline("testing", "2022-09-15");
        ArrayList<Task> workList = new ArrayList<>();
        workList.add(deadline);
        String expected = Task.add(workList, "deadline testing /by 2022-09-15") + deadline + "\n"
                + Task.updateNumOfTask(workList);
        assertEquals(expected, actual);
    }

    @Test
    public void processMarkInputTest(){
        Deadline deadline = new Deadline("testing", "2022-09-15");
        ArrayList<Task> workList = new ArrayList<>();
        workList.add(deadline);
        duke.logic.ProcessUserInput.process(workList, "mark 1");
        assertEquals(deadline.getStatusIcon(), "[X] ");
    }

    @Test
    public void processFindTest(){
        Deadline deadline = new Deadline("testing", "2022-09-15");
        ArrayList<Task> workList = new ArrayList<>();
        workList.add(deadline);
        String actual = duke.logic.ProcessUserInput.process(workList, "find test");
        String expected = "Here are the tasks that match at least 3 characters of your key word in your list:\n" +
                "tes\n" +
                "[D][ ]  testing (by: Sep 15 2022)\n";
        assertEquals(expected, actual);
    }
}
