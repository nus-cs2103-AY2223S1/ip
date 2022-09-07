package ploopy;

import ploopy.ui.TextUI;

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
    public static String parseInput(String input, TaskList taskList) throws PloopyException {
        String[] inputSequence = input.split(" ");
        if (input.isBlank() || input.isEmpty()) {
            throw new PloopyException("blank");
        }
        String command = inputSequence[0].toLowerCase();
        switch (command) {
        case "mark":
            if (!isEmptyCommand(input, "mark".length())) {
                return taskList.markTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("mark");
            }
        case "unmark":
            if (!isEmptyCommand(input, "unmark".length())) {
                return taskList.unmarkTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("unmark");
            }
        case "list":
            return taskList.displayList();
        case "delete":
            if (!isEmptyCommand(input, "delete".length())) {
                return taskList.deleteTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("delete");
            }
        case "todo":
            if (!isEmptyCommand(input, "todo".length())) {
                return taskList.createToDo(input.split("todo ")[1]);
            } else {
                throw new PloopyException("todo");
            }
        case "deadline":
            if (!isEmptyCommand(input, "deadline".length())) {
                String date = getDate(input);
                String name = input.split("deadline ")[1].split(" /")[0];
                return taskList.createDeadline(name, date);
            } else {
                throw new PloopyException("deadline");
            }
        case "event":
            if (!isEmptyCommand(input, "mark".length())) {
                String date = getDate(input);
                String name = input.split("event ")[1].split(" /")[0];
                return taskList.createEvent(name, date);
            } else {
                throw new PloopyException("event");
            }
        case "find":
            if (!isEmptyCommand(input, "mark".length())) {
                return taskList.findTasks(inputSequence[1]);
            } else {
                throw new PloopyException("find");
            }
        case "bye":
            return TextUI.bye();
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
