package duke;

import main.java.duke.Duke;
import main.java.duke.Parser;
import main.java.duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    public void dukeLoadTest() {
        new Duke("./data/duke.txt");
    }

    @Test
    public void parserInputTest() {
        TaskList tl = new TaskList();
        Parser parser = new Parser();
        parser.parse("todo something", tl);
        String result = String.format("%s", tl.get(0));
        assertEquals(result, "[T] [ ] something");
    }
}