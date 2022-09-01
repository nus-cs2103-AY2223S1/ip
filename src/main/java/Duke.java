import duke.AllTasksList;
import duke.Command;
import duke.Storage;

import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Storage.initStorage();
        AllTasksList allTasks = Storage.loadTasks();
        Command.greet();
        Command.chat(scanner, allTasks);
    }
}
