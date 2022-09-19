package jarvis;

import jarvis.task.Task;

import java.io.IOException;

/**
 * Parser parse the users' input from CMI
 * and do run corresponding code
 */
public class Parser {
    private TaskList taskList;


    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MARK_NO_INDEX_MSG = "You need to specify the index of the task to mark";
    private static final String UNMARK_NO_INDEX_MSG = "You need to specify the index of the task to unmark";
    private static final String TODO_EMPTY_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty.";
    private static final String EVENT_EMPTY_DESCRIPTION = "OOPS!!! The description of a event cannot be empty.";
    private static final String DEADLINE_EMPTY_DESCRIPTION = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String EVENT_FORMAT = "OOPS!!! The event should have a time.\n" +
            "e.g. event {name} /at {time}";
    private static final String DEADLINE_FORMAT = "OOPS!!! The deadline should have a due date.\n"
            + "e.g. deadline {name} /by {time}";
    private static final String DELETE_NO_INDEX = "You should specify the index of task to delete";
    private static final String FIND_NO_KEYWORD = "OOPS!!! Please enter a keyword for searching.";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    private boolean isBye = false;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse the user input as different actions
     * @param input The whole line of users' input
     */
    public String parse(String input) {
        try {
            if (input.equals("bye")) {
                this.isBye = true;
                return GOODBYE_MESSAGE;
            } else if (input.equals("list")) {
                return taskList.printTasks();
            } else if (input.startsWith("mark")) {
                if (input.length() <= 5) {
                    return MARK_NO_INDEX_MSG;
                }
                // -1 to match 0-based index
                return taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);

            } else if (input.startsWith("unmark")) {
                if (input.length() <= 7) {
                    return UNMARK_NO_INDEX_MSG;
                }
                return taskList.markTaskAsUnDone(Integer.parseInt(input.substring(7)) - 1);

            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    return TODO_EMPTY_DESCRIPTION;
                }
                return taskList.addTask(input.substring(5), Task.TaskType.ToDo, false);
            } else if (input.startsWith("event")) {
                if (input.length() <= 6) {
                    return EVENT_EMPTY_DESCRIPTION;
                } else if (!input.contains("/at")) {
                    return EVENT_FORMAT;
                }
                return taskList.addTask(input.substring(6), Task.TaskType.Event, false);
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    return DEADLINE_EMPTY_DESCRIPTION;
                } else if (!input.contains("/by")) {
                    return DEADLINE_FORMAT;
                }
                return taskList.addTask(input.substring(9), Task.TaskType.Deadline, false);
            } else if (input.startsWith("delete")) {
                if (input.length() <= 7) {
                    return DELETE_NO_INDEX;
                }
                return taskList.deleteTask(Integer.parseInt(input.substring(7)) - 1);

            } else if (input.startsWith("find")) {
                if (input.length() <= 5) {
                    return FIND_NO_KEYWORD;
                }
                return taskList.find(input.substring(5));
            } else {
                return INVALID_COMMAND_MESSAGE;
            }
        } catch (IOException e) {
            return "Error when trying to add task: " + e;
        }
    }

    public boolean getByeStatus() {
        return this.isBye;
    }
}
