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
     *
     * @param command user's inputted command
     */
    public Parser(String command) {
        this.command = command;
        this.ui = new Ui();
    }

    private void checkParameters(String command, String[] commandList) {
        if (commandList.length == 1) {
            throw new EmptyDescriptionException(command);
        }
    }

    private String parseDate(String date) {
        LocalDate dateTimeStringParsed = LocalDate.parse(date);
        return dateTimeStringParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Parses the command and matches it with the corresponding action to be taken
     * Executes the action on the task list given
     *
     * @param tasks ArrayList of current tasks stored on the chatbot
     * @return boolean true if bye command entered and chatbot should be stopped, false otherwise
     */
    public String executeCommand(TaskList tasks, Storage storage) throws IOException {
        String[] commandList = command.strip()
                                      .split(" ", 2);
        String command = commandList[0];
        try {
            switch (command) {
            case "bye":
                storage.saveTasks(tasks);
                return ui.showBye();

            case "list":
                return ui.getCorrectMessage(Ui.Commands.LIST, tasks, 0);

            case "mark":
                checkParameters("mark", commandList);

                int indexMark = Integer.parseInt(commandList[1]) - 1;
                if (indexMark < 0 || indexMark >= tasks.getSize()) {
                    throw new InvalidParameterException();
                }

                tasks.markIndex(indexMark);
                return ui.getCorrectMessage(Ui.Commands.MARK, tasks, indexMark);

            case "unmark":
                checkParameters("unmark", commandList);

                int indexUnmark = Integer.parseInt(commandList[1]) - 1;
                if (indexUnmark < 0 || indexUnmark >= tasks.getSize()) {
                    throw new InvalidParameterException();
                }

                tasks.unmarkIndex(indexUnmark);
                return ui.getCorrectMessage(Ui.Commands.UNMARK, tasks, indexUnmark);

            case "todo":
                checkParameters("todo", commandList);
                tasks.add(new Todo(commandList[1]));
                return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);

            case "event":
                checkParameters("event", commandList);
                String[] eventCommands = commandList[1].split(" /at ", 2);
                checkParameters("event", eventCommands);

                String eventDescription = eventCommands[0];
                String event = parseDate(eventCommands[1]);

                tasks.add(new Deadline(eventDescription, event));
                return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);

            case "deadline":
                checkParameters("deadline", commandList);
                String[] deadlineCommands = commandList[1].split(" /by ", 2);
                checkParameters("deadline", deadlineCommands);

                String deadlineDescription = deadlineCommands[0];
                String deadline = parseDate(deadlineCommands[1]);

                tasks.add(new Deadline(deadlineDescription, deadline));
                return ui.getCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);

            case "delete":
                checkParameters("delete", commandList);

                int index = Integer.parseInt(commandList[1]) - 1;
                if (index < 0 || index >= tasks.getSize()) {
                    throw new InvalidParameterException();
                }

                String response = ui.getCorrectMessage(Ui.Commands.DELETE, tasks, index);
                tasks.delete(index);
                return response;

            case "find":
                checkParameters("find", commandList);
                String keyword = commandList[1];
                return ui.getCorrectMessage(Ui.Commands.FIND, tasks.find(keyword), 0);
            default:
                throw new UnknownCommandException();
            }

        } catch (UnknownCommandException | EmptyDescriptionException | InvalidParameterException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.getErrorMessage("datetime");
        }
    }
}
