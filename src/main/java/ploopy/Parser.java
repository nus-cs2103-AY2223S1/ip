package ploopy;

/**
 * Understands the user's input.
 *
 */
public class Parser {

    /**
     * Interprets the user's input and calls the correct TaskList method.
     *
     * @param input Input provided by User
     * @param taskList Associated taskList for subsequent methods.
     * @throws PloopyException if input is invalid.
     */
    public static void parseInput(String input, TaskList taskList) throws PloopyException {
        String[] inputSequence = input.split(" ");
        if (input.isBlank() || input.isEmpty()) {
            throw new PloopyException("blank");
        }
        String command = inputSequence[0];
        switch (command) {
        case "mark":
            if (!isEmptyCommand(input, "mark".length())) {
                taskList.markTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("mark");
            }
            break;
        case "unmark":
            if (!isEmptyCommand(input, "unmark".length())) {
                taskList.unmarkTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("unmark");
            }
            break;
        case "list":
            taskList.displayList();
            break;
        case "delete":
            if (!isEmptyCommand(input, "delete".length())) {
                taskList.deleteTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("delete");
            }
            break;
        case "todo":
            if (!isEmptyCommand(input, "todo".length())) {
                taskList.createToDo(input.split("todo ")[1]);
            } else {
                throw new PloopyException("todo");
            }
            break;
        case "deadline":
            if (!isEmptyCommand(input, "deadline".length())) {
                String date = getDate(input);
                String name = input.split("deadline ")[1].split(" /")[0];
                taskList.createDeadline(name, date);
            } else {
                throw new PloopyException("deadline");
            }
            break;
        case "event":
            if (!isEmptyCommand(input, "mark".length())) {
                String date = getDate(input);
                String name = input.split("event ")[1].split(" /")[0];
                taskList.createEvent(name, date);
            } else {
                throw new PloopyException("event");
            }
            break;
        case "find":
            if (!isEmptyCommand(input, "mark".length())) {
                taskList.findTasks(inputSequence[1]);
            } else {
                throw new PloopyException("find");
            }
            break;
        default:
            throw new PloopyException("nonsense");
        }

    }

    private static String getDate(String input) {
        String dateString = input.split(" /")[1];
        String[] separate = dateString.split(" ");
        return separate[1] + " " + separate[2];
    }

    private static boolean isEmptyCommand(String command, int size) {
        return command.trim().length() == size;
    }
}
