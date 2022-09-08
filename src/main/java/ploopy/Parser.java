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
        if (input.isBlank() || input.isEmpty()) {
            throw new PloopyException("blank");
        }
        String[] inputSequence = input.split(" ");
        String command = inputSequence[0].toLowerCase();
        switch (command) {
        case "mark":
            if (!isIncompleteCommand(input, "mark".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                return taskList.markTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("mark");
            }
        case "unmark":
            if (!isIncompleteCommand(input, "unmark".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                return taskList.unmarkTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("unmark");
            }
        case "list":
            return taskList.displayList();
        case "delete":
            if (!isIncompleteCommand(input, "delete".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                return taskList.deleteTask(Integer.parseInt(inputSequence[1]));
            } else {
                throw new PloopyException("delete");
            }
        case "todo":
            if (!isIncompleteCommand(input, "todo".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                return taskList.createToDo(input.split("todo ")[1]);
            } else {
                throw new PloopyException("todo");
            }
        case "deadline":
            if (!isIncompleteCommand(input, "deadline".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                String date = getDate(input);
                String name = input.split("deadline ")[1].split(" /")[0];
                return taskList.createDeadline(name, date);
            } else {
                throw new PloopyException("deadline");
            }
        case "event":
            if (!isIncompleteCommand(input, "mark".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
                String date = getDate(input);
                System.out.println(date);
                String name = input.split("event ")[1].split(" /")[0];
                return taskList.createEvent(name, date);
            } else {
                throw new PloopyException("event");
            }
        case "find":
            if (!isIncompleteCommand(input, "mark".length())) {
                assert inputSequence.length > 1 : "isIncompleteCommand is not working";
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
        String result = separate[1] + " " + separate[2];
        assert result.split("/").length == 3 : "Date not correctly extracted";
        return result;
    }

    private static boolean isIncompleteCommand(String command, int size) {
        assert !(command.isEmpty() || command.isBlank()) : "Input is empty";
        return command.trim().length() == size;
    }
}
