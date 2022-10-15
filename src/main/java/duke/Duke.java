package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a robot which can react to different commands.
 */
public class Duke {
    private static String hello = "Hello from\n" + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private TaskList tasks;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Storage storage;
    private int num = 1;

    /**
     * Creates a object of Duke.
     *
     * @param storage
     */
    public Duke(Storage storage) {
        Ui ui = new Ui();
        this.storage = storage;
        try {
            tasks = new TaskList(arrayList);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Prints out the response.
     *
     * @param command
     * @return String
     */
    public String getResponse(String command) {
        try {
            if (command.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            }
            if (command.equals("list")) {
                return storage.load();
            } else {
                String result = printCommand(command);
                storage.push(tasks.get());
                return result;
            }
        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Prints out the current command.
     *
     * @param command String command.
     * @return String
     */
    public String printCommand(String command) {
        Command commands = Parser.of(command, arrayList, num);
        commands.execute();
        if (commands.addToList()) {
            num++;
        }
        if (command.split(" ")[0].equals("delete")) {
            num--;
        }
        return commands.toString();
    }

    /**
     * Returns a String representation of the hello greeting command.
     *
     * @return String
     */
    public static String greet() {
        return hello;
    }

    /**
     * Returns a String representation of the command list.
     *
     * @return String
     */
    public String getList() {
        String list = "";
        for (int k = 1; k < arrayList.size() + 1; k++) {
            list += k + "." + arrayList.get(k - 1) + "\n";
        }
        return list;
    }
}
