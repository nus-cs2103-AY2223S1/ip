import java.util.Scanner;

import duke.AllTasksList;
import duke.Command;
import duke.Storage;

/**
 * The main driver class for duke
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main driver method for duke
     *
     * @param args
     */
    public static void main(String[] args) {
        Storage.initStorage();
        AllTasksList allTasks = Storage.loadTasks();
        Command.greet();
        Command.chat(scanner, allTasks);
    }
}
