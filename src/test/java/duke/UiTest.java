package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void welcomeMsgTest() {
        Ui ui = new Ui();
        String welcomeMsg = ("Hello! I'm Duke\n" +
                "What can I do for you?");
        assertEquals(welcomeMsg, ui.welcomeMessage());
    }

    @Test
    public void taskAddMsgTest() {
        Ui ui = new Ui();
        Task task = new ToDo("read book", false, 0);
        String msg = ("\tGot it. I've added this task:\n" + "\t" + task.toString() +
                "\n\tNow you have 3 tasks in the list.");
        assertEquals(msg, ui.taskAddMsg(task, 3));
    }

    @Test
    public void taskDoneMsgTest() {
        Ui ui = new Ui();
        Task task = new ToDo("read book", false, 0);
        String msg = ("\tNice! I've marked this task as done:\n" +
                "\t\t" + "[T][ ] read book");
        assertEquals(msg, ui.taskDoneMsg(task));
    }
}
