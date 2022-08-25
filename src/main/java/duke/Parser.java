package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

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
                this.ui.newItemAdded(event, this.taskList.getSize());
                return new AddCommand(event);
            case Constants.TODO_STRING:
                String todoDescription = command.split(Constants.EMPTY_SPACE)[1];
                Task todo = new Todo(todoDescription);
                this.ui.newItemAdded(todo, this.taskList.getSize());
                return new AddCommand(todo);
            case Constants.DEADLINE_STRING:
                String deadlineDescription = command.split(Constants.EMPTY_SPACE)[1];
                String dateString = command.split(Constants.EMPTY_SPACE)[3];
                LocalDate parsedDate = DateParser.parseDate(dateString);
                Task deadline = new Deadline(deadlineDescription, parsedDate);
                this.ui.newItemAdded(deadline, this.taskList.getSize());
                return new AddCommand(deadline);
            case Constants.LIST_STRING:
                ui.listAllTasks(this.taskList);
            case Constants.BYE_STRING:
                this.ui.showByeMessage();
            default:
                System.out.println("INVALID");
        }
    }
}
