package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {
    Todo testTodo = new Todo("test");


    @Test
    public void dummyTest() throws DukeException {
        assertFalse(testTodo.getStatus());
    }

    @Test
    public void anotherDummyTest(){
        testTodo.setDone();
        assertFalse(!testTodo.getStatus());
    }
}
