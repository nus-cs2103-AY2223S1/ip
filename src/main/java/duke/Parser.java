package duke;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Parser {
    private boolean isExit;

    public Parser() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    private static boolean isInteger(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private static int getDateIndex(String[] splitInput) throws DukeException {
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                return i;
            }
        }
        throw new DukeException("Duke: OOPS!!! The task is missing a date property.");
    }

    private String getTaskField(String[] splitInput, int start, int end) throws DukeException {
        String field = String.join(" ", Arrays.copyOfRange(splitInput, start, end));
        if (field.equals("")) {
            throw new DukeException("Duke: OOPS!!! The task description cannot be empty.");
        }
        return field;
    }

    private void parseNewTask(String[] splitInput, TaskList tasks) throws DukeException {
        int len = splitInput.length;
        if (len == 0) {
            throw new DukeException("No input!");
        } else if (splitInput[0].equals("todo")) {
            tasks.editTaskList("todo", getTaskField(splitInput, 1, len), "");
        } else if (splitInput[0].equals("deadline")) {
            int index = getDateIndex(splitInput);
            String taskDescription = getTaskField(splitInput, 1, index);
            String date = getTaskField(splitInput, index + 1, len);
            tasks.editTaskList("deadline", taskDescription, date);
        } else if (splitInput[0].equals("event")) {
            int index = getDateIndex(splitInput);
            String taskDescription = getTaskField(splitInput, 1, index);
            String date = getTaskField(splitInput, index + 1, len);
            tasks.editTaskList("event", taskDescription, date);
        } else {
            throw new DukeException("Duke: OH NO!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void parseFind(String[] splitInput, TaskList tasks) throws DukeException {
        int len = splitInput.length;
        if (len == 1) {
            throw new DukeException("Duke: To search for tasks, enter a keyword");
        }
        String keyword = String.join(" ", Arrays.copyOfRange(splitInput, 1, len));
        tasks.find(keyword);
    }

    /**
     * Interprets and executes the input command from the user.
     *
     * @param userInput input from user
     * @param tasks list of stored tasks
     * @throws DukeException if command does not follow the format
     */
    public void parse(String userInput, TaskList tasks) throws DukeException {
        String[] splitInput = userInput.split(" ");
        int len = splitInput.length;
        if (userInput.equals("bye")) {
            isExit = true;
        } else if (userInput.equals("list")) {
            Ui.printTaskList(tasks);
        } else if(splitInput[0].equals("find")) {
            parseFind(splitInput, tasks);
        } else if (len == 2 && splitInput[0].equals("mark") && isInteger(splitInput[1])) {
            tasks.editTaskList("mark", parseInt(splitInput[1]) - 1);
        } else if (len == 2 && splitInput[0].equals("unmark") && isInteger(splitInput[1])) {
            tasks.editTaskList("unmark", parseInt(splitInput[1]) - 1);
        } else if (splitInput[0].equals("mark") || splitInput[0].equals("unmark")) {
            throw new DukeException("Duke: To check off tasks, indicate the index of the task using an integer!");
        } else if (len == 2 && splitInput[0].equals("delete") && isInteger(splitInput[1])) {
            tasks.editTaskList("delete", parseInt(splitInput[1]) - 1);
        } else if (splitInput[0].equals("delete")) {
            throw new DukeException("Duke: To delete tasks, indicate the index of the task using an integer!");
        } else {
            parseNewTask(splitInput, tasks);
        }
    }
}
