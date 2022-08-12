package main.java;

import java.util.Scanner;

public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static DukeList dukeList = new DukeList();

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
                    echo("Bye. Hope to see you again soon!");
                    return;
                default:
                    echo(userInput);
                    dukeList.add(userInput);
            }
        }
    }
}
