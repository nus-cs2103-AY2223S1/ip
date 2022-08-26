package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("picnic");
       assertEquals("[T][ ] picnic" + "\n", todo.toString());
    }
}
