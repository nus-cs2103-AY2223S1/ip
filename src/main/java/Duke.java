package main.java;

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

    private static void handleUserInputs(String userInput){
        if (userInput.equals("bye")) {
            // Exit
            System.out.println("Bye. Hope to see you again soon!");
            runDuke = false;
            return;
        } else if (userInput.equals("list")) {
            // List inputs in 'userInput' list.
            System.out.println(taskList);
        } else if (userInput.equals("")) {
            // Do nothing if no input is given before newline.
            return;
        } else if (userInput.startsWith("mark")) {
            try {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                taskList.markDone(i);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify task to be marked " +
                        "done!");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                taskList.markUnDone(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify task to be marked " +
                        "undone!");
            }
        } else {
            // Add to 'userInput' list.
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

        while (runDuke) {
            userInput = myScanner.nextLine();
            handleUserInputs(userInput);
        }
    }
}