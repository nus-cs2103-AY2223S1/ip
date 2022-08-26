package duke;

import duke.command.*;
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * This class returns a command based on the user input
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.taskList = tasks;
    }

    /**
     * Requirement: deals with making sense of the user command
     *
     * Takes the input given by the user, extracts the verb
     * and translates it into a command
     *
     * @param command
     * @return
     */
    public Command parse(String command) {
        // Idea from : https://stackoverflow.com/questions/70683058/using-startswith-in-switch-case-in-java
        String verb = command.split(Constants.EMPTY_SPACE)[0];
        switch (verb) {
            case Constants.EVENT_STRING:
                String eventDescription = command.split(Constants.EMPTY_SPACE)[1];
                LocalDate eventDate = LocalDate.parse(command.split(Constants.EMPTY_SPACE)[3]);
                Task event = new Event(eventDescription, eventDate);
                return new AddCommand(event);
            case Constants.TODO_STRING:
                String todoDescription = command.split(Constants.EMPTY_SPACE)[1];
                Task todo = new Todo(todoDescription);
                return new AddCommand(todo);
            case Constants.DEADLINE_STRING:
                String deadlineDescription = command.split(Constants.EMPTY_SPACE)[1];
                String dateString = command.split(Constants.EMPTY_SPACE)[3];
                LocalDate parsedDate = DateParser.parseDate(dateString);
                Task deadline = new Deadline(deadlineDescription, parsedDate);
                return new AddCommand(deadline);
            case Constants.DELETE_STRING:
                int deleteIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
                return new DeleteCommand(deleteIndex);
            case Constants.LIST_STRING:
                this.ui.listAllTasks(this.taskList);
                break;
            case Constants.MARK_STRING:
                int markIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
                return new MarkCommand(markIndex);
            case Constants.UNMARK_STRING:
                int unmarkIndex = Integer.parseInt(command.split(Constants.EMPTY_SPACE)[1]);
                return new UnmarkCommand(unmarkIndex);
            case Constants.BYE_STRING:
                this.ui.showByeMessage();
                break;
            default:
                return new InvalidCommand();
        }
        return new InvalidCommand();
    }
}
