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

    @Test
    void testSortDeadlineChronologicallyIncreasing() {
        Deadline deadline1;
        Deadline deadline2;
        Deadline deadline3;
        Deadline deadline4;
        Deadline deadline5;
        try {
            deadline1 = new Deadline("test1", " 2000-10-10 23:00");
            deadline2 = new Deadline("test2", " 2000-10-11 23:00");
            deadline3 = new Deadline("test3", " 2000-10-12 23:00");
            deadline4 = new Deadline("test4", " 2000-10-13 23:00");
            deadline5 = new Deadline("test5", " 2000-10-14 23:00");
        } catch (ImproperDeadlineFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline5);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(deadline4);

        String expected = "";
        expected += "1. " + deadline1.toString() + "\n";
        expected += "2. " + deadline2.toString() + "\n";
        expected += "3. " + deadline3.toString() + "\n";
        expected += "4. " + deadline4.toString() + "\n";
        expected += "5. " + deadline5.toString() + "\n";
        assertEquals(testTaskList.sortDeadlineChronologically(Order.increasing).toString()
                , expected);
    }

    @Test
    void testSortDeadlineChronologicallyDecreasing() {
        Deadline deadline1;
        Deadline deadline2;
        Deadline deadline3;
        Deadline deadline4;
        Deadline deadline5;
        try {
            deadline1 = new Deadline("test1", " 2000-10-10 23:00");
            deadline2 = new Deadline("test2", " 2000-10-11 23:00");
            deadline3 = new Deadline("test3", " 2000-10-12 23:00");
            deadline4 = new Deadline("test4", " 2000-10-13 23:00");
            deadline5 = new Deadline("test5", " 2000-10-14 23:00");
        } catch (ImproperDeadlineFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline5);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(deadline4);

        String expected = "";
        expected += "1. " + deadline5.toString() + "\n";
        expected += "2. " + deadline4.toString() + "\n";
        expected += "3. " + deadline3.toString() + "\n";
        expected += "4. " + deadline2.toString() + "\n";
        expected += "5. " + deadline1.toString() + "\n";
        assertEquals(testTaskList
                        .sortDeadlineChronologically(Order.decreasing)
                        .toString()
                , expected);
    }
    @Test
    void testSortEventChronologicallyIncreasing() {
        Event event1;
        Event event2;
        Event event3;
        Event event4;
        Event event5;
        try {
            event1 = new Event("test1", " 2000-10-10 23:00");
            event2 = new Event("test2", " 2000-10-11 23:00");
            event3 = new Event("test3", " 2000-10-12 23:00");
            event4 = new Event("test4", " 2000-10-13 23:00");
            event5 = new Event("test5", " 2000-10-14 23:00");
        } catch (ImproperEventFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(event2);
        testTaskList.addTask(event1);
        testTaskList.addTask(event3);
        testTaskList.addTask(event5);
        testTaskList.addTask(event4);

        String expected = "";
        expected += "1. " + event1.toString() + "\n";
        expected += "2. " + event2.toString() + "\n";
        expected += "3. " + event3.toString() + "\n";
        expected += "4. " + event4.toString() + "\n";
        expected += "5. " + event5.toString() + "\n";
        assertEquals(testTaskList.sortEventChronologically(Order.increasing).toString()
                , expected);
    }

    @Test
    void testSortEventChronologicallyDecreasing() {
        Event event1;
        Event event2;
        Event event3;
        Event event4;
        Event event5;
        try {
            event1 = new Event("test1", " 2000-10-10 23:00");
            event2 = new Event("test2", " 2000-10-11 23:00");
            event3 = new Event("test3", " 2000-10-12 23:00");
            event4 = new Event("test4", " 2000-10-13 23:00");
            event5 = new Event("test5", " 2000-10-14 23:00");
        } catch (ImproperEventFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(event2);
        testTaskList.addTask(event1);
        testTaskList.addTask(event3);
        testTaskList.addTask(event5);
        testTaskList.addTask(event4);

        String expected = "";
        expected += "1. " + event5.toString() + "\n";
        expected += "2. " + event4.toString() + "\n";
        expected += "3. " + event3.toString() + "\n";
        expected += "4. " + event2.toString() + "\n";
        expected += "5. " + event1.toString() + "\n";
        assertEquals(testTaskList.sortEventChronologically(Order.decreasing).toString()
                , expected);
    }

    @Test
    void testSortDeadlineLexicographicallyDecreasing() {
        Deadline deadline1;
        Deadline deadline2;
        Deadline deadline3;
        Deadline deadline4;
        Deadline deadline5;
        try {
            deadline1 = new Deadline("a", " 2000-10-10 23:00");
            deadline2 = new Deadline("b", " 2000-10-11 23:00");
            deadline3 = new Deadline("c", " 2000-10-12 23:00");
            deadline4 = new Deadline("d", " 2000-10-13 23:00");
            deadline5 = new Deadline("e", " 2000-10-14 23:00");
        } catch (ImproperDeadlineFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline5);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(deadline4);

        String expected = "";
        expected += "1. " + deadline1.toString() + "\n";
        expected += "2. " + deadline2.toString() + "\n";
        expected += "3. " + deadline3.toString() + "\n";
        expected += "4. " + deadline4.toString() + "\n";
        expected += "5. " + deadline5.toString() + "\n";
        assertEquals(testTaskList
                        .sortDeadlineLexicographically(Order.decreasing)
                        .toString()
                , expected);
    }

    @Test
    void testSortDeadlineLexicographicallyIncreasing() {
        Deadline deadline1;
        Deadline deadline2;
        Deadline deadline3;
        Deadline deadline4;
        Deadline deadline5;
        try {
            deadline1 = new Deadline("a", " 2000-10-10 23:00");
            deadline2 = new Deadline("b", " 2000-10-11 23:00");
            deadline3 = new Deadline("c", " 2000-10-12 23:00");
            deadline4 = new Deadline("d", " 2000-10-13 23:00");
            deadline5 = new Deadline("e", " 2000-10-14 23:00");
        } catch (ImproperDeadlineFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(deadline2);
        testTaskList.addTask(deadline5);
        testTaskList.addTask(deadline1);
        testTaskList.addTask(deadline3);
        testTaskList.addTask(deadline4);

        String expected = "";
        expected += "1. " + deadline5.toString() + "\n";
        expected += "2. " + deadline4.toString() + "\n";
        expected += "3. " + deadline3.toString() + "\n";
        expected += "4. " + deadline2.toString() + "\n";
        expected += "5. " + deadline1.toString() + "\n";
        assertEquals(testTaskList
                        .sortDeadlineLexicographically(Order.increasing)
                        .toString()
                , expected);
    }
    @Test
    void testSortEventLexicographicallyDecreasing() {
        Event event1;
        Event event2;
        Event event3;
        Event event4;
        Event event5;
        try {
            event1 = new Event("a", " 2000-10-10 23:00");
            event2 = new Event("b", " 2000-10-11 23:00");
            event3 = new Event("c", " 2000-10-12 23:00");
            event4 = new Event("d", " 2000-10-13 23:00");
            event5 = new Event("e", " 2000-10-14 23:00");
        } catch (ImproperEventFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(event2);
        testTaskList.addTask(event1);
        testTaskList.addTask(event3);
        testTaskList.addTask(event5);
        testTaskList.addTask(event4);

        String expected = "";
        expected += "1. " + event1.toString() + "\n";
        expected += "2. " + event2.toString() + "\n";
        expected += "3. " + event3.toString() + "\n";
        expected += "4. " + event4.toString() + "\n";
        expected += "5. " + event5.toString() + "\n";
        assertEquals(testTaskList
                        .sortEventLexicographically(Order.decreasing)
                        .toString()
                , expected);
    }

    @Test
    void testSortEventLexicographicallyIncreasing() {
        Event event1;
        Event event2;
        Event event3;
        Event event4;
        Event event5;
        try {
            event1 = new Event("a", " 2000-10-10 23:00");
            event2 = new Event("b", " 2000-10-11 23:00");
            event3 = new Event("c", " 2000-10-12 23:00");
            event4 = new Event("d", " 2000-10-13 23:00");
            event5 = new Event("e", " 2000-10-14 23:00");
        } catch (ImproperEventFormatException e) {
            throw new RuntimeException(e);
        }
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(event2);
        testTaskList.addTask(event1);
        testTaskList.addTask(event3);
        testTaskList.addTask(event5);
        testTaskList.addTask(event4);

        String expected = "";
        expected += "1. " + event5.toString() + "\n";
        expected += "2. " + event4.toString() + "\n";
        expected += "3. " + event3.toString() + "\n";
        expected += "4. " + event2.toString() + "\n";
        expected += "5. " + event1.toString() + "\n";
        assertEquals(testTaskList
                        .sortEventLexicographically(Order.increasing)
                        .toString()
                , expected);
    }
}
