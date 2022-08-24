import java.util.Scanner;

public class InputParser {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    static final String initialMessage = indentedMessage(
            "\n      Hello! I'm Duke\n      What can I do for you?\n");
    private static String indentedMessage(String message) {
        return indentedLine + message + indentedLine;
    }

    private static String generateEmptyDescMessage(String taskType) {
        return "\n      ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

    private static String generateEmptyActionMessage(String action) {
        return "\n      ☹ OOPS!!! The action to " + action + " must have the index as an argument.\n";
    }

    private String generateTasksNumberMessage() {
        return "Now you have " + taskList.sizeOfList() + " task" + (taskList.sizeOfList() == 1 ? "" : "s")
                + " in the list" + ".\n";
    }

    final BreakLoopIndicator breakLoopIndicator = new BreakLoopIndicator();

    private final TaskList taskList;

    public InputParser(TaskList taskList) {
        this.taskList = taskList;
    }

    private static final String byeMessage = indentedMessage("\n      Bye. This doesn't have to be the end!\n");

    public class BreakLoopIndicator {
        private boolean isScannerDone = false;

        boolean getIsScannerDone() {
            return isScannerDone;
        }

        private void setIsScannerDoneTrue() {
            isScannerDone = true;
        }
    }

    public String parseScannerLineInput(Scanner scanner, BreakLoopIndicator breakLoopIndicator) {
        String userInput;
        try {
            userInput = scanner.nextLine();
            String[] commands = userInput.split("\\s+");
            Command taskType;
            try {
                taskType = Command.valueOf(commands[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CustomMessageException(indentedMessage("\n      ☹ OOPS!!! I'm sorry, but I " +
                        "don't know what that means :-(\n"));
            }
            commands[0] = "";
            int index;
            String toPrint;
            switch (taskType) {
                case BYE:
                    breakLoopIndicator.setIsScannerDoneTrue();
                    toPrint = byeMessage;
                    break;
                case LIST:
                    toPrint = (indentedMessage("\n     Here are the tasks in your list:"
                            + taskList.getTasksListsForUser() + "\n"));
                    break;
                case MARK:
                    if (commands.length == 1) {
                        throw new CustomMessageException(indentedMessage(generateEmptyActionMessage("mark")));
                    }
                    index = Integer.parseInt(commands[1]) - 1;
                    taskList.markTaskAsDone(index);
                    toPrint = "\n     Nice! I've marked this task as done:\n       " + taskList.getTaskString(index) + "\n";
                    toPrint = (indentedMessage(toPrint));
                    break;
                case UNMARK:
                    if (commands.length == 1) {
                        throw new CustomMessageException(
                                indentedMessage(generateEmptyActionMessage("unmark")));
                    }
                    index = Integer.parseInt(commands[1]) - 1;
                    taskList.markTaskAsNotDone(index);
                    toPrint = "\n     OK, I've marked this task as not done yet:\n       "
                            + taskList.getTaskString(index) + "\n";
                    toPrint = (indentedMessage(toPrint));
                    break;
                case DELETE:
                    if (commands.length == 1) {
                        throw new CustomMessageException(
                                indentedMessage(generateEmptyActionMessage("delete")));
                    }
                    index = Integer.parseInt(commands[1]) - 1;
                    String deletedTaskDescription = taskList.getTaskString(index);
                    taskList.removeTask(index);
                    toPrint = "\n     Noted. I've removed this task:\n       "
                            + deletedTaskDescription + "\n     " + generateTasksNumberMessage();
                    toPrint = (indentedMessage(toPrint));
                    break;
                case TODO:
                    toPrint = parseNewTaskCommand(userInput, commands.length, Command.TODO, "");
                    break;
                case DEADLINE:
                    toPrint = parseNewTaskCommand(userInput, commands.length, Command.DEADLINE, " /by ");
                    break;
                case EVENT:
                    toPrint = parseNewTaskCommand(userInput, commands.length, Command.EVENT, " /at ");
                    break;
                default:
                    throw new CustomMessageException(indentedMessage(
                            "\n      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
            }
            return toPrint;
        } catch (CustomMessageException e) {
            return e.getMessage();
        }
    }

    private String parseNewTaskCommand(String userInput, int commandsLen, Command taskCommand, String toSplitBy)
            throws CustomMessageException {
        if (commandsLen == 1) {
            throw new CustomMessageException(indentedMessage(generateEmptyDescMessage(taskCommand.getString())));
        }
        if (taskCommand == Command.TODO) {
            taskList.addToTaskList(new ToDo(userInput.substring(5).strip(), Command.TODO));
        } else if (taskCommand == Command.EVENT || taskCommand == Command.DEADLINE) {
            String[] splitString = userInput.split(toSplitBy);
            String taskDescription = splitString[0].substring(taskCommand.getString().length() + 1).strip();
            if (taskDescription.isEmpty() || taskDescription.isBlank()) {
                throw new CustomMessageException(indentedMessage(generateEmptyDescMessage(taskCommand.getString())));
            }
            String userRequirement = splitString[1].strip();
            Task newTask;
            if (taskCommand == Command.EVENT) {
                newTask = new Event(taskDescription, Command.EVENT, userRequirement);
            } else {
                newTask = new Deadline(taskDescription, Command.DEADLINE, userRequirement);
            }
            taskList.addToTaskList(newTask);
        }
        return (indentedMessage("\n     Got it. I've added this task:\n       "
                + taskList.getTaskString(taskList.sizeOfList() - 1) + "\n     " + generateTasksNumberMessage()));
    }
}
