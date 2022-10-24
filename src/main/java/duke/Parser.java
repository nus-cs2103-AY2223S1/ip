package duke;

import java.util.ArrayList;
//import java.util.Scanner;

/**
 * Reads in user input and processes user input.
 *
 * @author Liu Han
 */
public class Parser {
    private static final String BREAK_LINE = "    ____________________________________________________________";
    private TaskList tasks;

    /**
     * Parser Constructor
     * @param tasks list of tasks to add user input on.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks inputted by user to the task list.
     */
    public String parser(String input) {
        String[] inputArr = input.split(" ");
        String action = inputArr[0];

        int number;
        Task task;
        String[] params;
        try {
            switch (action) {
            case "list":
                return printList();
            case "mark":
                if (inputArr.length > 2 || inputArr.length == 1) {
                    throw new DukeException("The format should be: mark <number>");
                }
                number = Integer.parseInt(inputArr[1]);
                if (number > tasks.getSize()) {
                    throw new DukeException("The index is invalid!");
                }
                task = tasks.getTask(number - 1);
                task.markAsDone();
                return "     Nice! I've marked this task as done:\n       "
                        + task;
            case "unmark":
                if (inputArr.length > 2 || inputArr.length == 1) {
                    throw new DukeException("The format should be: unmark <number>");
                }
                number = Integer.parseInt(inputArr[1]);
                if (number > tasks.getSize()) {
                    throw new DukeException("The index is invalid!");
                }
                task = tasks.getTask(number - 1);
                task.markAsNotDone();
                return "     Nice! I've marked this task as not done yet:\n       "
                        + task;
            case "todo":
                if (input.substring(4).replaceAll("\\s+", "").equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                task = new Todo(input.substring(5));
                tasks.addTask(task);
                return printTask(task);
            case "deadline":
                if (input.substring(8).replaceAll("\\s+", "").equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("The timing of a deadline cannot be omitted.");
                }
                params = input.substring(9).split(" /by ");
                task = new Deadline(params[0], params[1]);
                tasks.addTask(task);
                return printTask(task);
            case "event":
                if (input.substring(5).replaceAll("\\s+", "").equals("")) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                if (!input.contains("/at")) {
                    throw new DukeException("The timing of an event cannot be omitted.");
                }
                params = input.substring(6).split(" /at ");
                task = new Event(params[0], params[1]);
                tasks.addTask(task);
                return printTask(task);
            case "delete":
                if (inputArr.length > 2 || inputArr.length == 1) {
                    throw new DukeException("The format should be: delete <number>");
                }
                number = Integer.parseInt(inputArr[1]);
                if (number > tasks.getSize()) {
                    throw new DukeException("The index is invalid!");
                }
                task = tasks.getTask(number - 1);
                tasks.deleteTask(number - 1);
                return "     Okay! I've removed this task from the list:\n       "
                        + task;
            case "find":
                String keyWord = input.substring(5);
                if (keyWord.replaceAll("\\s+", "").equals("")) {
                    throw new DukeException("The description of a find query cannot be empty.");
                }

                ArrayList<Task> matches = new ArrayList<Task>();
                for (int i = 0; i < tasks.getSize(); i++) {
                    task = tasks.getTask(i);
                    if (task.getDescription().contains(keyWord)) {
                        matches.add(task);
                    }
                }

                return printMatch(matches);
            case "update":
                if (input.substring(6).replaceAll("\\s+", "").equals("")) {
                    throw new DukeException("The description of an update cannot be empty.");
                }
                for (int i = 0; i < tasks.getSize(); i++) {
                    task = tasks.getTask(i);
                    if (task.getDescription().contains(inputArr[1])) {
                        tasks.deleteTask(i);
                        if (task instanceof Deadline) {
                            tasks.addTask(new Deadline(inputArr[1], inputArr[2]));
                        } else if (task instanceof Event) {
                            tasks.addTask(new Event(inputArr[1], inputArr[2]));
                        } else {
                            throw new DukeException("No more to update for this task");
                        }
                        return printTask(task);
                    }
                }
                throw new DukeException("Cannot find an existing task matching the update");
            case "bye":
                return "     A happy moment is always transient.";
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException err) {
            return "     ☹ OOPS!!! " + err;
        }
    }

    private String printTask(Task task) {
        String output = "     Got it. I've added this task:\n       "
                + task;
        output += String.format("     Now you have %d tasks in the list.", tasks.getSize());
        return output;
    }

    private String printList() {
        if (tasks.getSize() == 0) {
            return "     There is no pending task for you.";
        } else {
            assert tasks.getSize() > 0 : "The size of the task list should be larger than 0";
            StringBuilder output = new StringBuilder("     Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append(String.format("     %d.%s\n", i + 1, tasks.getTask(i)));
            }
            return output.toString();
        }
    }

    private String printMatch(ArrayList<Task> matches) {
        if(matches.isEmpty()) {
            return "     There is no task matching this key word.";
        } else {
            StringBuilder output = new StringBuilder("     Okay! I've found these matches from the list:\n       ");
            for (int i = 0; i < matches.size(); i++) {
                output.append(String.format("     %d.%s\n", i + 1, matches.get(i)));
            }
            return output.toString();
        }
    }
}