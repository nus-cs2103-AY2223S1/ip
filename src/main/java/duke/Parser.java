package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


/**
 * Parses in the user input and executes the corresponding actions
 */
public class Parser {
    private final String command;
    private final Ui ui;

    /**
     * Constructor for parser
     * @param command user's inputted command
     */
    public Parser(String command) {
        this.command = command;
        this.ui = new Ui();
    }

    /**
     * Parses the command and matches it with the corresponding action to be taken
     * Executes the action on the task list given
     * @param tasks ArrayList of current tasks stored on the chatbot
     * @return boolean true if bye command entered and chatbot should be stopped, false otherwise
     */
    public String executeCommand(TaskList tasks, Storage storage) throws IOException {
        String[] commands = command.strip().split(" ", 2);
        try {
            switch (commands[0]) {
            case "bye":
                storage.saveTasks(tasks);
                return ui.showBye();
            case "list":
                return ui.getCorrectMessage(Ui.Commands.LIST, tasks, 0);
            case "mark":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("mark");
                }
                int indexMark = Integer.parseInt(commands[1]) - 1;
                tasks.markIndex(indexMark);
                return ui.getCorrectMessage(Ui.Commands.MARK, tasks, indexMark);
            case "unmark":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("unmark");
                }
                int indexUnmark = Integer.parseInt(commands[1]) - 1;
                tasks.unmarkIndex(indexUnmark);
                return ui.getCorrectMessage(Ui.Commands.UNMARK, tasks, indexUnmark);
            case "todo":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("todo");
                }
                tasks.add(new Todo(commands[1]));
                return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
            case "event":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("event");
                }
                String[] eventCommands = commands[1].split(" /at ", 2);
                if (eventCommands.length == 1) {
                    return ui.getErrorMessage("datetime");
                }
                try {
                    LocalDate timeStringParsed = LocalDate.parse(eventCommands[1]);
                    tasks.add(new Event(eventCommands[0],
                            timeStringParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                    return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
                } catch (DateTimeParseException error) {
                    return ui.getErrorMessage("datetime");
                }
            case "deadline":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("deadline");
                }
                String[] deadlineCommands = commands[1].split(" /by ", 2);
                if (deadlineCommands.length == 1) {
                    return ui.getErrorMessage("datetime");
                }
                try {
                    LocalDate timeStringParsed = LocalDate.parse(deadlineCommands[1]);
                    tasks.add(new Deadline(deadlineCommands[0],
                            timeStringParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                    return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
                } catch (DateTimeParseException error) {
                    return ui.getErrorMessage("datetime");
                }
            case "delete":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("delete");
                }
                int index = Integer.parseInt(commands[1]) - 1;
                String response = ui.getCorrectMessage(Ui.Commands.DELETE, tasks, index);
                tasks.delete(index);
                return response;
            case "find":
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("find");
                }
                return ui.getCorrectMessage(Ui.Commands.FIND, tasks.find(commands[1]), 0);
            default:
                throw new UnknownCommandException();
            }

        } catch (UnknownCommandException | EmptyDescriptionException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
