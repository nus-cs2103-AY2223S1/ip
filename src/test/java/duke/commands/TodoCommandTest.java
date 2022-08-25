package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void isEndTest() {
        TodoCommand todoCommand = new TodoCommand();
        assertEquals(false, todoCommand.isEnd());
    }

}
