package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.util.Parser;
import duke.util.TaskList;

public class TaskListTest {
    @Test
    public void test1() {
        TaskList actual = new TaskList();
        TaskList expected = Parser.parseTaskList("E } 0 } cs1010s } 2022-01-01 12:34"
                + System.lineSeparator()
                + "D } 0 } cs1231s } 2022-02-01 13:34"
                + System.lineSeparator()
                + "E } 0 } cs2030s } 2022-01-21 02:34");
        actual.add(new Event("cs1010s", Parser.parseStringToDateTime("2022-01-01 12:34")));
        actual.add(new Deadline("cs1231s", Parser.parseStringToDateTime("2022-02-01 13:34")));
        actual.add(new Event("cs2030s", Parser.parseStringToDateTime("2022-01-21 02:34")));
        assertEquals(actual, expected);
    }

    @Test
    public void test2() {
        TaskList actual = new TaskList();
        TaskList expected = Parser.parseTaskList("E } 0 } cs1010s } 2022-01-01 12:34"
                + System.lineSeparator()
                + "E } 0 } cs2030s } 2022-01-21 02:34");
        actual.add(new Event("cs1010s", Parser.parseStringToDateTime("2022-01-01 12:34")));
        actual.add(new Deadline("cs1231s", Parser.parseStringToDateTime("2022-02-01 13:34")));
        actual.add(new Event("cs2030s", Parser.parseStringToDateTime("2022-01-21 02:34")));
        actual.remove(1);
        assertEquals(actual, expected);
    }

    @Test
    public void test3() {
        Task actual;
        TaskList actualList = new TaskList();
        Task expected = Parser.parseTask("E } 0 } cs2030s } 2022-01-21 02:34");
        actualList.add(new Event("cs1010s", Parser.parseStringToDateTime("2022-01-01 12:34")));
        actualList.add(new Deadline("cs1231s", Parser.parseStringToDateTime("2022-02-01 13:34")));
        actualList.add(new Event("cs2030s", Parser.parseStringToDateTime("2022-01-21 02:34")));

        actual = actualList.get(2);
        assertEquals(actual, expected);
    }

}
