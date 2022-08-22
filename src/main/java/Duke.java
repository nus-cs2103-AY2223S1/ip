import exceptions.DukeException;
import tasks.Task;
import parser.DukeParser;
import tasklist.TaskList;

import java.util.Scanner;


public class Duke {

    // String array used to store tasks
    private static final TaskList taskList = new TaskList();

    // Currently, the main function takes in user input and echoes it to the user
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello, I am Duke. \nWhat can I do for you? :)\n=======================");

        // Init variables to use
        Scanner sc = new Scanner(System.in);
        DukeParser parser = new DukeParser(taskList);

        while(!parser.exitDuke()) {
            String input = sc.nextLine();
            parser.parseInput(input);
            try {
                parser.execute();
                continue;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("=================================");
                continue;
            }
        }



    }



}

/* TODO
Add error checking for user input after the /: Should be "at" and "by" for Event and Deadline
Help command

Possible code refactor:
while (true) {
    fetch user input
    sanitise user input
    pass sanitised input to parser, including ArrayList
    parser will parse the code, hand it to individual handler classes
    individual handler classes will modify the List accordingly
}
 */