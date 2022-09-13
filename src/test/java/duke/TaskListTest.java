package duke;

import duke.exceptions.ImproperDeadlineFormatException;
import duke.exceptions.ImproperEventFormatException;
import duke.exceptions.NoMatchingKeywordException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    // sample task
    ToDo toDo1 = new ToDo("test1");
    ToDo toDo2 = new ToDo("test2");
    ToDo toDo3 = new ToDo("test3");
    Deadline deadline1;
    Deadline deadline2;
    Deadline deadline3;

    Event event1;
    Event event2;
    Event event3;

    {
        try {
            deadline1 = new Deadline("test1", " 2000-10-10 23:00");
            deadline2 = new Deadline("test2", " 2000-10-10 23:00");
            deadline3 = new Deadline("test3", " 2000-10-10 23:00");
            event1 = new Event("test1", " 2000-10-10 23:00");
            event2 = new Event("test2", " 2000-10-10 23:00");
            event3 = new Event("test3", " 2000-10-10 23:00");
        } catch (ImproperDeadlineFormatException e) {
            throw new RuntimeException(e);
        } catch (ImproperEventFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testToString() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(toDo1);
        testTaskList.addTask(toDo2);
        testTaskList.addTask(toDo3);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(event1);
        testTaskList.addTask(event2);
        testTaskList.addTask(event3);
        String actual = testTaskList.toString();

        String expected = "";
        expected += "1. " + toDo1.toString() + "\n";
        expected += "2. " + toDo2.toString() + "\n";
        expected += "3. " + toDo3.toString() + "\n";
        expected += "4. " + deadline1.toString() + "\n";
        expected += "5. " + deadline2.toString() + "\n";
        expected += "6. " + deadline3.toString() + "\n";
        expected += "7. " + event1.toString() + "\n";
        expected += "8. " + event2.toString() + "\n";
        expected += "9. " + event3.toString() + "\n";

        assertEquals(actual, expected, "toString a taskList");
    }
    @Test
    void testFindTask() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(toDo1);
        testTaskList.addTask(toDo2);
        testTaskList.addTask(toDo3);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(event1);
        testTaskList.addTask(event2);
        testTaskList.addTask(event3);
        TaskList output;
        try {
            output = testTaskList.findTask("test1");
        } catch (NoMatchingKeywordException e) {
            throw new RuntimeException(e);
        }
        String actual = output.toString();
        String expected = "";
        expected += "1. " + toDo1.toString() + "\n";
        expected += "2. " + deadline1.toString() + "\n";
        expected += "3. " + event1.toString() + "\n";
        assertEquals(actual, expected);
    }
    @Test
    void testGenerateSave() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(toDo1);
        testTaskList.addTask(toDo2);
        testTaskList.addTask(toDo3);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(event1);
        testTaskList.addTask(event2);
        testTaskList.addTask(event3);
        String actual = testTaskList.generateSave();
        String expected = toDo1.toSaveVersion()
                + toDo2.toSaveVersion()
                + toDo3.toSaveVersion()
                + deadline1.toSaveVersion()
                + deadline2.toSaveVersion()
                + deadline3.toSaveVersion()
                + event1.toSaveVersion()
                + event2.toSaveVersion()
                + event3.toSaveVersion();
        assertEquals(actual, expected);
    }
}
