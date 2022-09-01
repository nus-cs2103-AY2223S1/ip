package anya;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserTest {
    @Test
    public void parseCommandTest() {
        String userInput = "todo test";
        String command = Parser.parseCommand(userInput);

        String expected = "todo";

        assertEquals(expected, command);
    }

    @Test
    public void parseMarkIndexTest() {
        String userInput = "mark 3";
        int index = Parser.parseCommandIndex(userInput);

        int expected = 3;

        assertEquals(expected, index);
    }

    @Test
    public void parseAddIndexTest() {
        String userInput = "add 5";
        int index = Parser.parseCommandIndex(userInput);

        int expected = 5;

        assertEquals(expected, index);
    }

    @Test
    public void parseTodoTaskName() {
        try {
            String userInput = "todo test1 test2";
            String taskName = Parser.parseTaskName(userInput);

            String expected = "test1 test2";

            assertEquals(expected, taskName);
        } catch (AnyaException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseDeadlineTaskName() {
        try {
            String userInput = "deadline task1 task2 /by 05/05/2022 1935";
            String taskName = Parser.parseTaskName(userInput);

            String expected = "task1 task2";

            assertEquals(expected, taskName);
        } catch (AnyaException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseEventTaskName() {
        String userInput = "event task1 /at task2 task 3";
        try {
            String taskName = Parser.parseTaskName(userInput);

            String expected = "task1";

            assertEquals(expected, taskName);
        } catch (AnyaException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseLocalDateTimeTest() {
        String userInput = "deadline /by 05/05/2022 1935";
        try {
            LocalDateTime dateTime = Parser.parseDateTime(userInput);

            LocalDateTime expected = LocalDateTime.parse("05/05/2022 1935",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

            assertEquals(expected, dateTime);
        } catch (AnyaException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void parseEventDetailsTest() {
        String userInput = "event /at test1 test2";
        try {
            String details = Parser.parseEventDetails(userInput);

            String expected = "test1 test2";

            assertEquals(expected, details);
        } catch (AnyaException e) {
            fail("Exception should not be thrown");
        }
    }
}
