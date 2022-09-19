package pixel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import pixel.util.DateValidator;
import pixel.util.Parser;
import pixel.util.Storage;
import pixel.util.TaskList;

public class ValidateDueDateTest {

    private final String filePath = "./data/pixel.txt";
    private final TaskList taskList = new TaskList(filePath);

    @Test
    public void TestDateTimeFormatterWorking() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
            .withResolverStyle(ResolverStyle.SMART);
        DateValidator validator = new DateValidator(dateFormatter);
        // Correct date format
        assertTrue(validator.isValid("2019-02-28"));
        // Incorrect date format
        assertFalse(validator.isValid("2019-22-30"));
    }

    @Test
    public void testTaskWithDueDate() throws IOException {
        try {
            Storage.resetFile(this.filePath);
            // task with date and time, with date in wrong format
            // current programme is still not strong enough to detect instances where the user intends
            // to input a date but inputs in the wrong format (due to typo/ human error), and correct the user
            taskList.handleNewTask("todo Submit CS2103 project /at 2022-26-08 1850", Parser.TaskType.DEADLINE);
            assertEquals("[D][ ] it CS2103 project (at: 2022-26-08 1850)", Storage.INPUT_TASKS.get(0).toString());

            // when both date and time are in invalid format
            // the invalid date will override the invalid time -- no exceptions will be thrown
            // the task will just take the invalid date and time as a normal string
            taskList.handleNewTask("todo Submit CS2103 project /at 2022-26-08 9999", Parser.TaskType.DEADLINE);
            assertEquals("[D][ ] it CS2103 project (at: 2022-26-08 9999)", Storage.INPUT_TASKS.get(1).toString());

            // When date is in correct format but time is in incorrect format
            taskList.handleNewTask("todo Submit CS2103 project /at 2022-06-08 9999", Parser.TaskType.DEADLINE);
        } catch (ParseException e) {
            assertEquals("java.text.ParseException: Unparseable date: \"9999\"", e.toString());
        }
    }

}
