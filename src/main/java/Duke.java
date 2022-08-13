package main.java;

import java.util.HashMap;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.function.Consumer;

public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static TaskList taskList = new TaskList();

    /**
     * Boolean attribute to know if Duke is running.
     */
    private static Boolean runDuke = false;

    /**
     * 'java.util.function' to exit program.
     */
    private static Consumer<String> quit = (input) -> {
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
        runDuke = false;
    };

    /**
     * 'java.util.function' to list out all tasks in 'taskList'.
     */
    private static Consumer<String> list = (input) -> {
        // List inputs in 'userInput' list.
        System.out.println(taskList);
    };

    /**
     * 'java.util.function' to mark task as done.
     * @param input Full String input from user.
     */
    private static Consumer<String> mark = (input) -> {
        try {
            int i = Integer.valueOf(input.split(" ")[1]);
            taskList.markDone(i);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify task to be marked " +
                    "done!");
        } catch(NumberFormatException e) {
            System.out.println("Task index must be a number!");
        }
    };

    /**
     * 'java.util.function' to mark task as undone.
     * @param input Full String input from user.
     */
    private static Consumer<String> unmark = (input) -> {
        try {
            int i = Integer.valueOf(input.split(" ")[1]);
            taskList.markUnDone(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify task to be marked " +
                    "undone!");
        } catch(NumberFormatException e) {
            System.out.println("Task index must be a number!");
        }
    };

    /**
     * 'java.util.function' to add task to 'taskList'.
     * @param input Full String input from user.
     */
    private static Consumer<String> addTask = (String input) -> {
        taskList.add(input);
    };


    /**
     * Add a HashMap of commands that maps to their respective functions.
     */
    private static HashMap<String, Consumer<String>> commands = new HashMap<>();
    static {
        commands.put("bye", quit);
        commands.put("deadline", addTask);
        commands.put("event", addTask);
        commands.put("list", list);
        commands.put("mark", mark);
        commands.put("todo", addTask);
        commands.put("unmark", unmark);
    }

    /**
     * Function to handle user inputs and check for errors.
     * @param userInput Full String input from user.
     * @throws DukeException
     */
    private static void handleUserInputs(String userInput) throws DukeException {
        String command = userInput.split(" ")[0];
        // To add error checking
        commands.get(command).accept(userInput);
    }

    /**
     * Main function with program logic.
     * @param args
     */
    public static void main(String[] args) {
        runDuke = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Create Scanner object for user inputs.
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

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