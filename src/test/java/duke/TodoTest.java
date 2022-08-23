package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void Test1() {
        try {
            Task todo = new Todo("test");
            assertEquals("[T][ ]test", todo.toString());
        } catch (MissingDescriptionException e) {
            System.out.println("Missing name for test");
        }
    }
}