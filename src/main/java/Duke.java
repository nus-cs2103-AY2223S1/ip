package main.java;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;


public class Duke {
    /**
     * Available commands
     */
    private static final String AVAILABLE_COMMANDS =
            "Available commands:\n" +
            "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n" +
            "   event    [TASK DESCRIPTION] /at [VENUE]\n" +
            "   todo     [TASK DESCRIPTION]\n" +
            "   delete   [TASK NUMBER]\n" +
            "   mark     [TASK NUMBER]\n" +
            "   unmark   [TASK NUMBER]\n" +
            "   list\n" +
            "   bye\n";

    /**
     * 'List' attribute to store inputs.
     */
    private static final TaskList taskList = new TaskList();

    /**
     * Boolean attribute to know if Duke is running.
     */
    private static Boolean runDuke = false;

    /**
     * 'java.util.function' to add task to 'taskList'.
     */
    private static final Consumer<String> addTask = taskList::add;

    /**
     * 'java.util.function' to delete task from 'taskList'.
     */
    private static final Consumer<String> deleteTask = taskList::delete;

    /**
     * 'java.util.function' to mark task as done.
     * input: Full String input from user.
     */
    private static final Consumer<String> mark = (input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        taskList.markDone(i);
    };

    /**
     * 'java.util.function' to mark task as undone.
     * input: Full String input from user.
     */
    private static final Consumer<String> unmark = (input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        taskList.markUnDone(i);
    };

    /**
     * 'java.util.function' to list out all tasks in 'taskList'.
     */
    private static final Consumer<String> list = (input) -> {
        // List inputs in 'userInput' list.
        System.out.println(taskList);
    };

    /**
     * 'java.util.function' to exit program.
     * input: Full String input from user.
     */
    private static final Consumer<String> quit = (input) -> {
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
        runDuke = false;
    };

    /**
     * Add a HashMap of commands that maps to their respective functions.
     */
    private static final HashMap<String, Consumer<String>> commands
            = new HashMap<>();
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
    private static void handleUserInputs(String userInput) throws DukeException {
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
                if (index <= 0 || index > size) {
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
                if (index <= 0 || index > size) {
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
                if (index <= 0 || index > size) {
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
                        String.format("(%s)\n%s",
                                userInput, AVAILABLE_COMMANDS));
            }

        }
        // Call respective 'Consumer' object on input once it has been verified.
        commands.get(command).accept(userInput);
    }

    /**
     * Main function with program logic.
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        runDuke = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + AVAILABLE_COMMANDS);

        // Create Scanner object for user inputs.
        Scanner myScanner = new Scanner(System.in);
        String userInput;

        while (runDuke && myScanner.hasNextLine()) {
            userInput = myScanner.nextLine();
            try {
                handleUserInputs(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}