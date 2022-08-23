package duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import duke.commands.*;

/**
 * The Duke class encapsulates a chatbot that helps users to keep track of their tasks.
 */
public class Duke {
    private static final String pathName = "text/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.storage = new Storage(pathName);
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();

        File file = new File(Duke.pathName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        duke.storage.loadTasks(duke.taskList);
        duke.ui.printWelcomeMsg();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    duke.ui.print("Bye. Hope to see you again soon!");
                    break;
                }
                Command c = Parser.parseInput(input);
                c.execute(duke.taskList, duke.ui, duke.storage);
            } catch (DukeException d) {
                duke.ui.print(d.getMessage());
            } catch (DateTimeParseException e) {
                duke.ui.print("Do format your time in yyyy-MM-dd HH:mm");
            }
        }
        sc.close();
    }
}
