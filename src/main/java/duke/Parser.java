package duke;

import duke.task.TaskList;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * Interprets user commands.
 */
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

    /**
     * Returns a task field.
     *
     * @param splitInput array of user input words
     * @param start leftmost index of task field
     * @param end rightmost index of task field
     * @return task field
     * @throws DukeException if task description or date field is empty
     */
    private String getTaskField(String[] splitInput, int start, int end) throws DukeException {
        String field = String.join(" ", Arrays.copyOfRange(splitInput, start, end));
        if (field.equals("")) {
            throw new DukeException("Duke: OOPS!!! The task description/date cannot be empty.");
        }
        return field;
    }

    /**
     * Edit <code>Task</code> and <code>TaskList</code> based on user input.
     *
     * @param cmd command to be executed
     * @param splitInput array of user input words
     * @param tasks list of stored <code>Task</code>
     * @throws DukeException if the index is out of range of the <code>TaskList</code>
     */
    private String parseEdit(String cmd, String[] splitInput, TaskList tasks) throws DukeException {
        int len = splitInput.length;
        if (len != 2 || !isInteger(splitInput[1])) {
            throw new DukeException("Duke: To edit tasks, indicate the index of the task using an integer!");
        }
        return tasks.editTaskList(cmd, parseInt(splitInput[1]) - 1);
    }

    /**
     * Creates a new task from user input and adds it to the <code>TaskList</code>.
     *
     * @param splitInput array of user input words
     * @param tasks list of stored tasks
     * @throws DukeException if user input is invalid
     */
    private String parseCreateTask(String[] splitInput, TaskList tasks) throws DukeException {
        int len = splitInput.length;
        String createdTaskMessage = "";
        if (len == 0) {
            throw new DukeException("No input!");
        } else if (splitInput[0].equals("todo")) {
            createdTaskMessage = tasks.editTaskList("todo", getTaskField(splitInput, 1, len), "");
        } else if (splitInput[0].equals("deadline")) {
            int index = getDateIndex(splitInput);
            String taskDescription = getTaskField(splitInput, 1, index);
            String date = getTaskField(splitInput, index + 1, len);
            createdTaskMessage = tasks.editTaskList("deadline", taskDescription, date);
        } else if (splitInput[0].equals("event")) {
            int index = getDateIndex(splitInput);
            String taskDescription = getTaskField(splitInput, 1, index);
            String date = getTaskField(splitInput, index + 1, len);
            createdTaskMessage = tasks.editTaskList("event", taskDescription, date);
        } else {
            throw new DukeException("Duke: OH NO!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println(createdTaskMessage);
        return createdTaskMessage;
    }

    /**
     * Searches for tasks in the <code>TaskList</code> with the input keyword.
     *
     * @param splitInput array of user input words
     * @param tasks list of stored tasks
     * @throws DukeException if user input does not have a keyword
     */
    private String parseFind(String[] splitInput, TaskList tasks) throws DukeException {
        int len = splitInput.length;
        if (len == 1) {
            throw new DukeException("Duke: To search for tasks, enter a keyword");
        }
        String keyword = String.join(" ", Arrays.copyOfRange(splitInput, 1, len));
        return tasks.find(keyword);
    }

    /**
     * Interprets and executes the input command from the user.
     *
     * @param userInput input from user
     * @param tasks list of stored tasks
     * @throws DukeException if command does not follow the format
     */
    public String parse(String userInput, TaskList tasks) throws DukeException {
        String[] splitInput = userInput.split(" ");
        String output = "";
        if (userInput.equals("bye")) {
            isExit = true;
        } else if (userInput.equals("list")) {
            output = Ui.printTaskList(tasks);
        } else if (splitInput[0].equals("find")) {
            output = parseFind(splitInput, tasks);
        } else if (splitInput[0].equals("mark")) {
            output = parseEdit("mark", splitInput, tasks);
        } else if (splitInput[0].equals("unmark")) {
            output = parseEdit("unmark", splitInput, tasks);
        } else if (splitInput[0].equals("delete")) {
            output = parseEdit("delete", splitInput, tasks);
        } else if (splitInput[0].equals("delete")) {
            throw new DukeException("Duke: To delete tasks, indicate the index of the task using an integer!");
        } else {
            output = parseCreateTask(splitInput, tasks);
        }
        return output;
    }
}
