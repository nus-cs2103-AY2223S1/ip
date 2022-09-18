package jarvis;

import jarvis.task.Task;

import java.io.IOException;

/**
 * Parser parse the users' input from CMI
 * and do run corresponding code
 */
public class Parser {
    private TaskList taskList;

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
                return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                return taskList.printTasks();
            } else if (input.startsWith("mark")) {
                if (input.length() <= 5) {
                    return "You need to specify the index of the task to mark";
                }
                // -1 to match 0-based index
                return taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);

            } else if (input.startsWith("unmark")) {
                if (input.length() <= 7) {
                    return "You need to specify the index of the task to unmark";
                }
                return taskList.markTaskAsUnDone(Integer.parseInt(input.substring(7)) - 1);

            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    return "☹ OOPS!!! The description of a todo cannot be empty.";
                }
                return taskList.addTask(input.substring(5), Task.TaskType.ToDo, false);
            } else if (input.startsWith("event")) {
                if (input.length() <= 6) {
                    return "☹ OOPS!!! The description of a event cannot be empty.";
                } else if (!input.contains("/at")) {
                    return "☹ OOPS!!! The event should have a time.\n" +
                            "e.g. event {name} /at {time}";
                }
                return taskList.addTask(input.substring(6), Task.TaskType.Event, false);
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    return "☹ OOPS!!! The description of a deadline cannot be empty.";
                } else if (!input.contains("/by")) {
                    return "☹ OOPS!!! The deadline should have a due date.\n"
                            + "e.g. deadline {name} /by {time}";
                }
                return taskList.addTask(input.substring(9), Task.TaskType.Deadline, false);
            } else if (input.startsWith("delete")) {
                if (input.length() <= 7) {
                    return "You should specify the index of task to delete";
                }
                return taskList.deleteTask(Integer.parseInt(input.substring(7)) - 1);

            } else if (input.startsWith("find")) {
                if (input.length() <= 5) {
                    return "☹ OOPS!!! Please enter a keyword for searching.";
                }
                return taskList.find(input.substring(5));
            } else {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (IOException e) {
            return "Error when trying to add task: " + e;
        }
    }

    public boolean getByeStatus() {
        return this.isBye;
    }
}
