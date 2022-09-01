package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoCommandTest {
    @Test
    public void isEndTest() {
        TodoCommand todoCommand = new TodoCommand();
        assertEquals(false, todoCommand.isEnd());
    }

}
