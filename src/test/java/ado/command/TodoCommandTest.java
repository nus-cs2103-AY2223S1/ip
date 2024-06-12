package ado.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoCommandTest {

    @Test
    public void isExitTest() {
        TodoCommand command = new TodoCommand("task1");
        assertEquals(command.isExit(), false);
    }
}
