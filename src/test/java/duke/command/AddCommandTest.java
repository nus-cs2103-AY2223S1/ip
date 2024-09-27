package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class AddCommandTest {
    private Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Command cmd;
    @Test
    public void addTodoTask_todoTask_returnTodoTask() throws DukeException {
        cmd = new AddCommand("todo task1");
        cmd.execute(ui, taskList);

        String output;
        output = "[T][ ] task1";
        assertEquals(taskList.getTask(0).toString(), output);
    }

    @Test
    public void addTwoDeadline_deadline_returnTwoDeadline() throws DukeException {
        cmd = new AddCommand("deadline task1 /by 1500, 12/01/2022");
        cmd.execute(ui, taskList);
        cmd = new AddCommand("deadline task2 /by 0530, 12/12/2022");
        cmd.execute(ui, taskList);

        String output;
        output = "[D][ ] task1 (by: 03:00 PM, Wed, 12 Jan 2022)";
        assertEquals(taskList.getTask(0).toString(), output);
        output = "[D][ ] task2 (by: 05:30 AM, Mon, 12 Dec 2022)";
        assertEquals(taskList.getTask(1).toString(), output);
    }

    @Test
    public void addMultipleTasks_tasks_returnTasks() throws DukeException {
        cmd = new AddCommand("todo task1");
        cmd.execute(ui, taskList);
        cmd = new AddCommand("deadline task2 /by 0530, 12/12/2022");
        cmd.execute(ui, taskList);
        cmd = new AddCommand("event task3 /at 1230, 31/01/2023");
        cmd.execute(ui, taskList);

        String output;
        output = "[T][ ] task1";
        assertEquals(taskList.getTask(0).toString(), output);
        output = "[D][ ] task2 (by: 05:30 AM, Mon, 12 Dec 2022)";
        assertEquals(taskList.getTask(1).toString(), output);
        output = "[E][ ] task3 (at: 12:30 PM, Tue, 31 Jan 2023)";
        assertEquals(taskList.getTask(2).toString(), output);
    }
}
