package duke;

import duke.Storage.DukeDecoder;
import duke.Parser.ProcessUserInput;
import Ui.Constants;
import duke.TaskList.*;

import java.util.ArrayList;

public class Duke {

    // List to store text entered by the user and display them back to the user when requested
    private static ArrayList<Task> workList = DukeDecoder.loadDataFromList();
    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Constants.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    public static void main(String[] args){
        // Greeting
        Duke.greet();

        // Process
        ProcessUserInput.process(workList);

        // Bye
        Duke.exit();
    }
}
