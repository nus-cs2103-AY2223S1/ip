import java.io.IOException;

import technical.Parser;
import technical.TaskList;
import technical.Ui;

/**
 * The main class
 * @author Nicholas Patrick
 */
public class Duke {
    /**
     * The main runner program.
     *
     * @param args arguments passed from running the program. Ignored.
     * @throws IOException if there is a lack of edit permissions granted.
     */
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        TaskList.loadFromSaveFile();
        Ui.reply("What can I do for you?");
        while (Parser.parseExecute(Ui.readLine()));
    }
}
