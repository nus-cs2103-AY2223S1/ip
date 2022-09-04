import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void ToDoTest() {
        TaskList taskList = new TaskList(new UI());
        Command command = new Command(CommandsList.TODO, "Hello");
        assertEquals("    Alright! I've added it to our list:\n      " +
                "[T][ ] Hello\n    Now we have 3 tasks in our list Dattebayo!", taskList.executeTask(command));
    }
}
