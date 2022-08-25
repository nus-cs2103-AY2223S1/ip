package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to parse user input
 */
public class Parser {
    /**
     * An instance of the {@code BreakLoopIndicator}
     */
    final BreakLoopIndicator breakLoopIndicator = new BreakLoopIndicator();
    private final TaskList taskList;

    /**
     * Takes in a list of the tasks where the parsed tasks will be stored.
     * @param taskList A list of the tasks
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    private static String generateEmptyDescMessage(String taskType) {
        return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

    private static String generateEmptyActionMessage(String action) {
        return " ☹ OOPS!!! The action to " + action + " must have the index as an argument.\n";
    }

    private String generateTasksNumberMessage() {
        return "Now you have " + taskList.sizeOfList() + " task" + (taskList.sizeOfList() == 1 ? "" : "s")
                + " in the list.";
    }

    /**
     * Returns a {@code String} representing the parsed input
     * @param command The user input
     * @param breakLoopIndicator The {@code BreakLoopIndicator} instance
     * @return A {@code String} representing the input
     * @throws CustomMessageException if invalid input is given
     */
    public String parseUserCommand(String command, BreakLoopIndicator breakLoopIndicator) throws CustomMessageException {
        String[] commands = command.split("\\s+");
        Command taskType;
        try {
            taskType = Command.valueOf(commands[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomMessageException((" ☹ OOPS!!! I'm sorry, but I " +
                    "don't know what that means :-(\n"));
        }
        commands[0] = "";
        int index;
        String toPrint;
        switch (taskType) {
            case BYE:
                toPrint = "";
                breakLoopIndicator.setIsByeCommand();
                break;
            case LIST:
                toPrint = (Ui.indentedMessage("Here are the tasks in your list:"
                        + taskList.getTasksListsForUser()));
                break;
            case MARK:
                if (commands.length == 1) {
                    throw new CustomMessageException((generateEmptyActionMessage("mark")));
                }
                index = Integer.parseInt(commands[1]) - 1;
                taskList.markTaskAsDone(index);
                toPrint = "Nice! I've marked this task as done:\n       " + taskList.getTaskString(index);
                toPrint = (Ui.indentedMessage(toPrint));
                break;
            case UNMARK:
                if (commands.length == 1) {
                    throw new CustomMessageException(generateEmptyActionMessage("unmark"));
                }
                index = Integer.parseInt(commands[1]) - 1;
                taskList.markTaskAsNotDone(index);
                toPrint = "OK, I've marked this task as not done yet:\n       "
                        + taskList.getTaskString(index);
                toPrint = (Ui.indentedMessage(toPrint));
                break;
            case DELETE:
                if (commands.length == 1) {
                    throw new CustomMessageException(
                            Ui.indentedMessage(generateEmptyActionMessage("delete")));
                }
                index = Integer.parseInt(commands[1]) - 1;
                String deletedTaskDescription = taskList.getTaskString(index);
                taskList.removeTask(index);
                toPrint = "Noted. I've removed this task:\n       "
                        + deletedTaskDescription + "\n     " + generateTasksNumberMessage();
                toPrint = (Ui.indentedMessage(toPrint));
                break;
            case TODO:
                toPrint = parseNewTaskCommand(command, commands.length, Command.TODO, "");
                break;
            case DEADLINE:
                toPrint = parseNewTaskCommand(command, commands.length, Command.DEADLINE, " /by ");
                break;
            case EVENT:
                toPrint = parseNewTaskCommand(command, commands.length, Command.EVENT, " /at ");
                break;
            default:
                throw new CustomMessageException((
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
        }
        return toPrint.equals("") ? "" : toPrint + "\n";
    }

    private String parseNewTaskCommand(String command, int commandsLen, Command taskCommand, String toSplitBy)
            throws CustomMessageException {
        if (commandsLen == 1) {
            throw new CustomMessageException((generateEmptyDescMessage(taskCommand.getString())));
        }
        if (taskCommand == Command.TODO) {
            taskList.addToTaskList(new ToDo(command.substring(5).strip(), duke.Command.TODO));
        } else if (taskCommand == Command.EVENT || taskCommand == Command.DEADLINE) {
            String[] splitString = command.split(toSplitBy);
            String taskDescription = splitString[0].substring(taskCommand.getString().length() + 1).strip();
            if (taskDescription.isEmpty() || taskDescription.isBlank()) {
                throw new CustomMessageException((generateEmptyDescMessage(taskCommand.getString())));
            }
            String userRequirement = splitString[1].strip();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(userRequirement, formatter);
            Task newTask;
            if (taskCommand == Command.EVENT) {
                newTask = new Event(taskDescription, Command.EVENT, dateTime);
            } else {
                newTask = new Deadline(taskDescription, Command.DEADLINE, dateTime);
            }
            taskList.addToTaskList(newTask);
        }
        return (Ui.indentedMessage("Got it. I've added this task:\n       "
                + taskList.getTaskString(taskList.sizeOfList() - 1) + "\n     " + generateTasksNumberMessage()));
    }

    /**
     * A class to track whether it is time to break the loop.
     */
    public static class BreakLoopIndicator {
        private boolean isExitCommand = false;

        boolean getIsExitCommand() {
            return isExitCommand;
        }

        private void setIsByeCommand() {
            isExitCommand = true;
        }
    }
}
