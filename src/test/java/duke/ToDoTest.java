package duke; 

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest { 

    @Test 
    public void toStringTest() { 
        assertEquals("[T][ ] CS9999 Assignment", new ToDo("CS9999 Assignment").
                toString());
    }
}


