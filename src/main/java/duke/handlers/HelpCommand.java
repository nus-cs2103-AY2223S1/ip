package duke.handlers;

import duke.models.DukeResponse;
import duke.models.TaskList;

public class HelpCommand implements DukeCommand {
    @Override
    public DukeResponse run(TaskList taskList, String content) {
        return new DukeResponse("list -- list all tasks\n" +
                "todo <description> -- add a todo\n" +
                "deadline <description> /by <dd/MM/yyyy> -- add a deadline\n" +
                "event <description> /at <dd/MM/yyyy> -- add an event\n" +
                "revent <description> /at <StartDate> <EndDate> -- add a weekly recurring event" +
                "mark <index> -- mark a task as done\n" +
                "unmark <index> -- mark a task as undone\n" +
                "delete <index> -- delete a task\n" +
                "find <keyword> -- find a task that match the keyword\n" +
                "save -- save all the tasks\n" +
                "bye -- exit the app\n");
    }
}
