package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.function.BiFunction;

public class Parser {
    /**
     * Point to current taskList. Used to keep track of size.
     */
    private TaskList taskList;

    /**
     * Constructor for `Parser` object.
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * `BiFunction` to add task to `taskList`.
     */
    private static final BiFunction<TaskList, String, String> addTask
            = TaskList::add;

    /**
     * `BiFunction` to delete task from `taskList`.
     */
    private static final BiFunction<TaskList, String, String> deleteTask
            = TaskList::delete;

    /**
     * `BiFunction` to mark task as done.
     * input: Full String input from user.
     */
    private static final BiFunction<TaskList, String, String> mark =
            (taskList, input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        return taskList.markDone(i);
    };

    /**
     * `BiFunction` to mark task as undone.
     * input: Full String input from user.
     */
    private static final BiFunction<TaskList, String, String> unmark =
            (taskList, input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        return taskList.markUnDone(i);
    };

    /**
     * `BiFunction` to list out all tasks in 'taskList'.
     */
    private static final BiFunction<TaskList, String, String> list =
            (taskList, input) -> {
        // List inputs in 'userInput' list.
        return taskList.toString();
    };

    /**
     * `BiFunction` to exit program.
     * input2: Full String input from user.
     */
    private static final BiFunction<TaskList, String, String> quit =
            (taskList, input) -> {
        // Exit
        return "exit sequence initiated";
    };

    /**
     * Add a HashMap of commands that maps to their respective functions.
     */
    private static final HashMap<String,
            BiFunction<TaskList, String, String>> commands = new HashMap<>();
    static {
        commands.put("deadline", addTask);
        commands.put("event", addTask);
        commands.put("todo", addTask);
        commands.put("delete", deleteTask);
        commands.put("mark", mark);
        commands.put("unmark", unmark);
        commands.put("list", list);
        commands.put("bye", quit);

    }

    /**
     * Function to handle user inputs and check for errors.
     * @param userInput      Full String input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    public BiFunction<TaskList, String, String> handleUserInputs(
            String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        String command = args[0];
        // Error checking based on command.
        switch(command) {
            case "deadline": {
                if ((userInput.indexOf(" ") != 8) ||
                        (userInput.indexOf("/by ") <= 9)) {
                    // Should have a " " delimiter followed by "/by:"
                    throw new DukeException("Wrong format! To add a new " +
                            "deadline, please enter the following:\n" +
                            "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n");
                }
                String dateString
                        = userInput.substring(userInput.indexOf("/by") + 4);
                try {
                    DateTimeFormatter formatter
                            = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate.parse(dateString, formatter);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Wrong date format! To add a new " +
                            "deadline, please enter the following:\n" +
                            "   deadline [TASK DESCRIPTION] /by " +
                            "[YYYY/MM/DD]\n");
                }
                break;
            }

            case "event": {
                if ((userInput.indexOf(" ") != 5) ||
                        (userInput.indexOf("/at ") <= 6)) {
                    // Should have a " " delimiter followed by "/at"
                    throw new DukeException("Wrong format! To add a new " +
                            "event, please enter the following:\n" +
                            "   event [TASK DESCRIPTION] /at [VENUE]\n");
                }
                break;
            }

            case "todo": {
                if ((args.length < 2)) {
                    throw new DukeException("Wrong format! To create a " +
                            "'todo' task, type:\n   todo [DESCRIPTION]\n");
                }
                break;
            }

            case "delete": {
                int index;
                int size = taskList.getSize();
                if ((args.length != 2)) {
                    throw new DukeException("Wrong format! To delete a task, " +
                            "type:\n   delete [TASK NUMBER]\n");
                }

                try {
                    index = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Task number must be an integer!" +
                            "\n   delete [TASK NUMBER]\n");
                }
                if (index < 0 || index >= size) {
                    throw new DukeException("Task number is invalid." +
                            String.format("You have %d tasks!", size) +
                            "\n   delete [TASK NUMBER]\n");
                }
                break;
            }

            case "mark": {
                int index;
                int size = taskList.getSize();
                if ((args.length != 2)) {
                    throw new DukeException("Wrong format! To mark a task as " +
                            "done, type:\n   mark [TASK NUMBER]\n");
                }

                try {
                    index = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Task number must be an integer!" +
                            "\n   mark [TASK NUMBER]\n");
                }
                if (index < 0 || index >= size) {
                    throw new DukeException("Task number is invalid." +
                            String.format("You have %d tasks!", size) +
                            "\n   mark [TASK NUMBER]\n");
                }
                break;
            }

            case "unmark": {
                int index;
                int size = taskList.getSize();
                if ((args.length != 2)) {
                    throw new DukeException("Wrong format! To unmark a task " +
                            "as done, type:\n   unmark [TASK NUMBER]\n");
                }

                try {
                    index = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Task number must be an integer!" +
                            "\n   unmark [TASK NUMBER]\n");
                }
                if (index < 0 || index >= size) {
                    throw new DukeException("Task number is invalid." +
                            String.format("You have %d tasks!", size) +
                            "\n   mark [TASK NUMBER]\n");
                }
                break;
            }

            case "list": {
                if (!userInput.equals("list")) {
                    // Whole input should only be "list"
                    throw new DukeException("Wrong format! To list tasks, " +
                            "type:\n   list\n");
                }
                break;
            }

            case "bye": {
                if (!userInput.equals("bye")) {
                    // Whole input should only be "bye"
                    throw new DukeException("Wrong format! To exit, type:\n" +
                            "   bye\n");
                }
                break;
            }

            default: {
                // Case where no commands are matched.
                throw new DukeException("Sorry, I did not quite get that! " +
                        String.format("(%s)\n",
                                userInput));
            }

        }
        // Call respective 'Consumer' object on input once it has been verified.
        return commands.get(command);
    }
}
