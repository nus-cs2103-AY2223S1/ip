package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseTimeTest() {
        String output = "6:00PM";
        String input = "1800";
        Parser p = new Parser(new TaskList(new ArrayList<>(), new Ui()), new Ui());
        assertEquals(p.parseTime(input), output);
    }

    @Test
    public void parseTimeTest2() {
        String output = "0:50AM";
        String input = "0050";
        Parser p = new Parser(new TaskList(new ArrayList<>(), new Ui()), new Ui());
        assertEquals(p.parseTime(input), output);
    }

    @Test
    public void parseTimeTest3() {
        String output = "7:50AM";
        String input = "0750";
        Parser p = new Parser(new TaskList(new ArrayList<>(), new Ui()), new Ui());
        assertEquals(p.parseTime(input), output);
    }




}
