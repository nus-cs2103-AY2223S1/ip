package duke;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class for testing Duke's functionalities.
 */
public class DukeTest {
    /**
     * Tests whether TaskList handles invalid task creation correctly.
     */
    @Test
    public void addInvalidTaskTest() {
        DukeException noTaskNameException = Assertions.assertThrows(DukeException.class, () -> {
            TaskList.addToDo("");
        });

        Assertions.assertEquals(
                "Failed to create todo: No task name given\nCommand format: todo [taskName]",
                noTaskNameException.getMessage());

        DukeException invalidDateException = Assertions.assertThrows(DukeException.class, () -> {
            TaskList.addDeadline("test /by abc");
        });

        Assertions.assertEquals(
                "Failed to create deadline: Invalid date given\nCommand format: deadline [taskName] /by [date]",
                invalidDateException.getMessage());

        DukeException invalidArgumentsException = Assertions.assertThrows(DukeException.class, () -> {
            TaskList.addEvent("123456789/aabsdbhu");
        });

        Assertions.assertEquals(
                "Failed to create event: Invalid number of arguments\nCommand format: event [taskName] /at [date]",
                invalidArgumentsException.getMessage());
    }

    /**
     * Tests whether Storage loads and saves tasks correctly (no duplicate/lost data between consecutive saves/loads).
     */
    @Test
    public void loadAndSaveTest() {
        Assertions.assertDoesNotThrow(Storage::loadData);
        List<String> tasksStringsAfterFirstLoad = TaskList.getTasksStrings();

        Assertions.assertDoesNotThrow(Storage::loadData);
        List<String> tasksStringsAfterSecondLoad = TaskList.getTasksStrings();

        Assertions.assertEquals(tasksStringsAfterFirstLoad, tasksStringsAfterSecondLoad);

        Assertions.assertDoesNotThrow(Storage::saveData);
        Assertions.assertDoesNotThrow(Storage::saveData);

        Assertions.assertDoesNotThrow(Storage::loadData);
        List<String> tasksStringsAfterThirdLoad = TaskList.getTasksStrings();

        Assertions.assertEquals(tasksStringsAfterSecondLoad, tasksStringsAfterThirdLoad);
    }

    /**
     * Tests whether Parser handles invalid input, and uncommon but valid input correctly.
     */
    @Test
    public void parseInputTest() {
        DukeException invalidCommandException = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parseInput("asdfg");
        });

        Assertions.assertEquals("Command not recognised", invalidCommandException.getMessage());

        DukeException blankCommandException = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parseInput("");
        });

        Assertions.assertEquals("Command not recognised", blankCommandException.getMessage());

        Assertions.assertDoesNotThrow(() -> Parser.parseInput("deadline test1 /by 2020-01-01"));
        Assertions.assertDoesNotThrow(() -> Parser.parseInput("event test2/at2020-01-01"));
    }
}
