package DukeBot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test ToDo Class.
 */
public class TodoTest {

    /**
     * Test to test the getTaskType method of ToDo.
     *
     * @throws DukeException
     */
    @Test
    public void taskTypeTest() {
        assertEquals("T", new ToDo("").getTaskType());
    }

    /**
     * Test to test the toString method of ToDo.
     *
     * @throws DukeException
     */
    @Test
    public void stringConversionTest() {
        assertEquals("[T][ ] go to fair", new ToDo("go to fair").toString());
    }
}
