package jduke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jduke.data.TaskList;

public class DeleteCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello world");
        DeleteCommand cmd = new DeleteCommand("1");
        cmd.setData(tl);
        assertEquals("|  deleted task:\n|    [T][ ] hello world\n", cmd.execute());
    }
}
