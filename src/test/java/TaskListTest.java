import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.UI;
import duke.commands.Command;
import duke.commands.CommandsList;


public class TaskListTest {
    @Test
    public void toDoTest() {
        TaskList taskList = new TaskList();
        Command command = new Command(CommandsList.TODO, "Hello");
        assertEquals("    Alright! I've added it to our list:\n      "
                + "[T][ ] Hello\n    Now we have 3 tasks in our list Dattebayo!", taskList.executeTask(command));
    }
}
