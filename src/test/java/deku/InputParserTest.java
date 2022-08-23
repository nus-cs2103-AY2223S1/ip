package deku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InputParserTest {

    @Test
    public void parseTask_taskListEmpty_exceptionThrown() {
        List<String> testInput = new ArrayList<>();
        try {
            assertEquals( "",
                    new InputParser().parseTask(testInput),
                    "Empty list as input");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("begin 0, end -1, length 0", e.getMessage());
        }

    }

    @Test
    public void parseTask_taskListToDo_String() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        assertEquals("test test test",
                new InputParser().parseTask(testInput),
                "Basic todo command as input");
    }

    @Test
    public void parseTask_taskListDate_String() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("12/12/2022");
        assertEquals("test test test (by:  12 Dec 2022)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with only date");
    }

    @Test
    public void parseTask_taskListDateTime_String() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("12/12/2022");
        testInput.add("1800");
        assertEquals("test test test (by:  12 Dec 2022 18:00)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with date and time");
    }

    @Test
    public void parseTask_taskListTimeOnly_String() {
        List<String> testInput = new ArrayList<>();
        testInput.add("test test test");
        testInput.add("/by");
        testInput.add("1800");
        assertEquals("test test test (by: 1800)",
                new InputParser().parseTask(testInput),
                "deadline command as input, with only time");
    }

    @Test
    public void getDate_void_LocalDate() {
        assertEquals(null,
                new InputParser().getDate(),
                "get empty date");
    }

    @Test
    public void getTime_void_LocalDate() {
        assertEquals(null,
                new InputParser().getTime(),
                "get empty time");
    }
}
