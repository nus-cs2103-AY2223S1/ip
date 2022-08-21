package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;


public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static final TaskList taskList = new TaskList();

    /**
     * Boolean attribute to know if Duke is running.
     */
    private static Boolean runDuke = false;

    /**
     * `Ui` object to handle user-interface.
     */
    private static Ui ui;

    /**
     * `Storage` object to handle reading and writing task list to disk.
     */
    private static Storage storage;

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
        commands.get(command).accept(userInput);
    }




    /**
     * Main function with program logic.
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        String OUTPUT_DIRECTORY = "data";
        String OUTPUT_FILENAME = "list.txt";

        runDuke = true;
        ui = new Ui();
        storage = new Storage(OUTPUT_DIRECTORY, OUTPUT_FILENAME, taskList);

        // Create Scanner object for user inputs.
        Scanner sc = new Scanner(System.in);
        String userInput;

        // Run Duke.
        while (runDuke && sc.hasNextLine()) {
            userInput = sc.nextLine();
            try {
                handleUserInputs(userInput);
                storage.writeToFile(taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
        sc.close();
    }
}