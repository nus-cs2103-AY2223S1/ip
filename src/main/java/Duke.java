package main.java;

import java.util.Scanner;

public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static TaskList taskList = new TaskList();

    /**
     * Function to echo an input string onto stdout.
     * @param input Input to be printed on stdout.
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    /**
     * Main function with program logic.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Create Scanner object for user inputs.
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

        while (true) {
            userInput = myScanner.nextLine();
            switch(userInput) {
                case "bye":
                    // Exit
                    echo("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    // List inputs in 'userInput' list.
                    taskList.print();
                    break;
                case "":
                    // Do nothing if no input is given before newline.
                    break;
                default:
                    // Echo and add to 'userInput' list.
                    echo("Added: " + userInput);
                    taskList.add(userInput);
            }
        }
    }
}
