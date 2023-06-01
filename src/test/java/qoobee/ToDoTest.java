package qoobee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] do CS2103T iP", new ToDo("do CS2103T iP").toString());
    }


}
