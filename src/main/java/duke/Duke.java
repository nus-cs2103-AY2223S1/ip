package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a robot which can react to different commands.
 */
public class Duke {
    private TaskList tasks;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Storage storage;
    private int num = 1;

    public String hello = "Hello from\n" + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String command) {
        try {
            if (command.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            }
            if (command.equals("list")) {
                return getList();
            } else {
                storage.push(tasks.get());
                return printCommand(command);
            }
        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

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