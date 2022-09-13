package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class HelpCommandTest {
    @Test
    public void executeTest() {
        HelpCommand cmd = new HelpCommand();
        cmd.setData(new TaskList(), new Ui());
        String sep = System.lineSeparator();
        assertEquals("Here's the list of commands that I know! The commands are not case-sensitive."
                        + sep + sep + "Creating Tasks" + sep + "1. todo <description>: Add a todo task" + sep
                        + "2. deadline <description> /by <dd/mm/yyyy> <hhmm | optional>: Add a deadline task" + sep
                        + "3. event <description> /at <dd/mm/yyyy> <hhmm | optional>: Add a event task" + sep + sep
                        + "Editing Tasks" + sep + "1. mark <positive integer>: Mark task as done" + sep
                        + "2. unmark <positive integer>: Unmark task" + sep + sep
                        + "Deleting Tasks" + sep + "1. delete <positive integer>: Delete specified task" + sep + sep
                        + "Showing Tasks" + sep + "1. list <dd/mm/yyyy | optional>: Lists tasks (on a specific date)"
                        + sep
                        + "2. find <keyword>: Find tasks by keyword" + sep + sep
                        + "Exiting" + sep + "1. bye: Exiting the application",
                cmd.execute());
    }
}
