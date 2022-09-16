package jarvis;

import jarvis.task.Task;

import java.io.IOException;

/**
 * Parser parse the users' input from CMI
 * and do run corresponding code
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parse the user input as different actions
     * @param input The whole line of users' input
     */
    public void parse(String input) {
        try {
            if (input.equals("list")) {
                taskList.printTasks();
            } else if (input.startsWith("mark")) {
                if (input.length() <= 5) {
                    System.out.println("You need to specify the index of the task to mark");
                    return;
                }
                // -1 to match 0-based index
                taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);
                // Initially I want to just change the line of the task instead of rewrite the whole
                // data file as it's more efficient, but I haven't found a way to implement it.
                storage.saveTaskList(taskList);
            } else if (input.startsWith("unmark")) {
                if (input.length() <= 7) {
                    System.out.println("You need to specify the index of the task to unmark");
                    return;
                }
                taskList.markTaskAsUnDone(Integer.parseInt(input.substring(7)) - 1);
                storage.saveTaskList(taskList);
            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    return;
                }
                taskList.addTask(input.substring(5), Task.TaskType.ToDo, false);
            } else if (input.startsWith("event")) {
                if (input.length() <= 6) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    return;
                } else if (!input.contains("/at")) {
                    System.out.println("☹ OOPS!!! The event should have a time.\n" +
                            "e.g. event {name} /at {time}");
                    return;
                }
                taskList.addTask(input.substring(6), Task.TaskType.Event, false);
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    return;
                } else if (!input.contains("/by")) {
                    System.out.println("☹ OOPS!!! The deadline should have a due date.\n" +
                            "e.g. deadline {name} /by {time}");
                    return;
                }
                taskList.addTask(input.substring(9), Task.TaskType.Deadline, false);
            } else if (input.startsWith("delete")) {
                if (input.length() <= 7) {
                    System.out.println("You should specify the index of task to delete");
                    return;
                }
                taskList.deleteTask(Integer.parseInt(input.substring(7)) - 1);

            } else if (input.startsWith("find")) {
                if (input.length() <= 5) {
                    System.out.println("☹ OOPS!!! Please enter a keyword for searching.");
                    return;
                }
                taskList.find(input.substring(5));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IOException e) {
            System.out.println("Error when trying to add task: " + e);
        }
    }
}
