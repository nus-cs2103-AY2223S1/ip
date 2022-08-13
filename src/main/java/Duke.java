package main.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;

public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static TaskList taskList = new TaskList();

    /**
     * Boolean attribute to know if Duke is running.
     */
    private static Boolean runDuke = false;

    private static void handleUserInputs(String userInput) throws DukeException {
        String command = userInput.split(" ")[0];
        if (command.equals("bye")) {
            // Exit
            System.out.println("Bye. Hope to see you again soon!");
            runDuke = false;
            return;
        } else if (command.equals("list")) {
            // List inputs in 'userInput' list.
            System.out.println(taskList);
        } else if (command.equals("")) {
            // Do nothing if no input is given before newline.
            return;
        } else if (command.equals("mark")) {
            try {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                taskList.markDone(i);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify task to be marked " +
                        "done!");
            } catch(NumberFormatException e) {
                System.out.println("Task index must be a number!");
            }
        } else if (command.equals("unmark")) {
            try {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                taskList.markUnDone(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify task to be marked " +
                        "undone!");
            } catch(NumberFormatException e) {
                System.out.println("Task index must be a number!");
            }
        } else if (Arrays.asList("deadline", "event", "todo").contains(command)){
            taskList.add(userInput);
        }
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