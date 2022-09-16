package duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a robot which can react to different commands.
 */
public class Duke {
    private TaskList tasks;
    ArrayList<String> arrayList = new ArrayList<>();
    int num = 1;

    public String hello = "Hello from\n" + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String command, Duke duke, Storage storage) {
            try {
                if (command.equals("bye")) {
                    return "Bye. Hope to see you again soon!";
                }
                if (command.equals("list")) {
                    return duke.getList();
                } else {
                    storage.push(duke.getList());
                    duke.tasks.set(storage.load());
                    return duke.printCommand(command);
                }
            } catch (DukeException | IOException e) {
                return e.toString();
            }
    }

    public Duke() {
        Ui ui = new Ui();
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * PrintCommand function to print out the current command.
     *
     * @param command String command.
     * @return String
     */
    public String printCommand(String command) {
        if (command.split(" ")[0].equals("delete")) {
            int number = Integer.parseInt(command.split(" ")[1]) - 1;
            num--;
            Delete task = new Delete(arrayList.get(number), num);
            arrayList.remove(number);
            return task.toString();
        } else {
            Parser parser = Parser.of(command, arrayList, num);
            if (parser.AddToList()) {
                num++;
            }
            return parser.toString();
        }
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