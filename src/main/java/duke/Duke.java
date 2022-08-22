package duke;

import duke.exceptions.DukeException;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.Scanner;


public class Duke {

    // String array used to store duke.tasks
    private static TaskList taskList = new TaskList();

    // Currently, the main function takes in user input and echoes it to the user
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello, I am Duke.");

        // Init variables to use
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage("src/main/java/duke/data.txt");
        try {
            taskList = st.load();
        } catch (Exception e) {
            System.out.println("I found some previously entered data on your disk, but " +
                    "encountered the following error when trying to load it:\n" +
                    e.getMessage());
        }
        DukeParser parser = new DukeParser(taskList);
        System.out.println("What can I do for you? :)\n=================================");

        while(!parser.exitDuke()) {
            String input = sc.nextLine();
            parser.parseInput(input);
            try {
                parser.execute(st);
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
    pass sanitised input to duke.parser, including ArrayList
    duke.parser will parse the code, hand it to individual handler classes
    individual handler classes will modify the List accordingly
}
 */