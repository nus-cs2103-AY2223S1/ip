package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.task.TaskList;

public class ParserTest {

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

    @Test
    public void parserInputTest2() {
        TaskList tl = new TaskList();
        Parser parser = new Parser();
        parser.parse("todo something", tl);
        parser.parse("mark 1", tl);
        String result = String.format("%s", tl.get(0));
        assertEquals(result, "[T] [x] something");
    }

    @Test
    public void parserInputTest3() {
        TaskList tl = new TaskList();
        Parser parser = new Parser();
        parser.parse("todo something", tl);
        parser.parse("todo something else", tl);
        parser.parse("mark 1", tl);
        parser.parse("delete 1", tl);
        String result = String.format("%s", tl.get(0));
        assertEquals(result, "[T] [ ] something else");
    }

    @Test
    public void parserInputTest4() {
        TaskList tl = new TaskList();
        Parser parser = new Parser();
        parser.parse("todo something", tl);
        parser.parse("todo something else", tl);
        parser.parse("mark 1", tl);
        parser.parse("delete 1", tl);
        parser.parse("list", tl);
        String result = String.format("%s", tl.get(0));
        assertEquals(result, "[T] [ ] something else");
    }

    @Test
    public void parserInputTest5() {
        TaskList tl = new TaskList();
        Parser parser = new Parser();
        parser.parse("todo something", tl);
        parser.parse("todo something else", tl);
        parser.parse("mark 1", tl);
        parser.parse("delete 1", tl);
        parser.parse("list", tl);
        parser.parse("bye", tl);
        String result = String.format("%s", tl.get(0));
        assertEquals(result, "[T] [ ] something else");
    }
}
