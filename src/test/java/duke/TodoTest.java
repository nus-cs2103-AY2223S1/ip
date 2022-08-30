package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void add_todo_todoAdded() {
        try {
            Task todo = new Todo("test");
            assertEquals("[T][ ]test", todo.toString());
        } catch (MissingDescriptionException e) {
            System.out.println("Missing name for test");
        }
    }
}
