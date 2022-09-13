package deku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Test
    public void parseTask_taskListEmpty_exceptionThrown() {
        List<String> testInput = new ArrayList<>();
        try {
            assertEquals("",
                    new InputParser().parseTask(testInput),
                    "Empty list as input");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("start 0, end -1, length 0", e.getMessage());
        }

    }

    @Test
    public void parseTask_taskListToDo_string() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        assertEquals("test test test",
                new InputParser().parseTask(testInput),
                "Basic todo command as input");
    }

    @Test
    public void parseTask_taskListDate_string() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("12/12/2022");
        assertEquals("test test test (by: 12 Dec 2022)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with only date");
    }

    @Test
    public void parseTask_taskListDateTime_string() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("12/12/2022");
        testInput.add("1800");
        assertEquals("test test test (by: 12 Dec 2022 18:00)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with date and time");
    }

    @Test
    public void parseTask_taskListTimeOnly_string() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("1800");
        assertEquals("test test test (by: 1800)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with only time");
    }

    @Test
    public void getDate_void_localDate() {
        assertNull(new InputParser().getDate(), "get empty date");
    }

    @Test
    public void getTime_void_localDate() {
        assertNull(new InputParser().getTime(), "get empty time");
    }
}
