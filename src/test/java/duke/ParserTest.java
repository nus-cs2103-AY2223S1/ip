package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ParserTest {

    @Test
    public void addTaskCommandTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>(), new Storage("data/dummy.txt"));
        Parser parser = new Parser(new Ui(), tasks);
        parser.processCommand("todo todo");
        parser.processCommand("deadline deadline /by 2021-03-03");
        parser.processCommand("event event /at 2021-07-02");
        assertEquals(tasks.size(), 3);
    }

}
