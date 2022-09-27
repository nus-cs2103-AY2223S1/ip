package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import duke.util.Parser;

public class TodoTest {
    @Test
    public void test1() {
        final Task expected = Parser.parseTask("T } 0 } compileJava UP-TO-DATE");
        Task actual = new Todo("compileJava UP-TO-DATE");
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        final Task expected = Parser.parseTask("T } 0 } compileJava UP-TO-DATE");
        Task actual = new Todo("compileJava Not UP-TO-DATE");
        assertNotEquals(expected, actual);
    }

    @Test
    public void test3() {
        final Task expected = Parser.parseTask("T } 1 } compileJava UP-TO-DATE");
        Task actual = new Todo("compileJava UP-TO-DATE");
        actual.markAsDone();
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        final Task expected = Parser.parseTask("E } 1 } compileJava UP-TO-DATE } 2022-01-22 10:00");
        Task actual = new Todo("compileJava UP-TO-DATE");
        actual.markAsDone();
        assertEquals(expected.equals(actual), false);
    }
}
