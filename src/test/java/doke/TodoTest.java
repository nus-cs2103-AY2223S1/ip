package doke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void getType_emptyString_stringTReturned() {
        ToDo todo = new ToDo("");
        assertEquals("T", "T");
    }

    @Test
    public void getString_stringDasd_properToStringReturned() {
        ToDo todo = new ToDo("dasd");
        assertEquals("[T][ ]   dasd", todo.toString());
    }
}
