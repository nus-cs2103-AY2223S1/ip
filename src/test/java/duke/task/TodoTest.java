package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    public void toDoTest() {
        assertEquals("[T][ ] Read book", new Todos("Read book").toString());
    }

    @Test
    void testStringifyTask() {
        Todos test1 = new Todos("test1");
        Todos test2 = new Todos("test2");
        test2.markAsDone();
        assertEquals("T|0|test1", test1.savedTaskString());
        assertEquals("T|1|test2", test2.savedTaskString());
    }


    @Test
    void testToString() {
        Todos test1 = new Todos("test1");
        Todos test2 = new Todos("test2");
        test2.markAsDone();
        assertEquals("[T][ ] test1", test1.toString());
        assertEquals("[T][X] test2", test2.toString());
    }
}

