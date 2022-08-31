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
        duke.run();

    }

    public void run() throws IOException {
        File file = new File(Duke.pathName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        this.storage.loadTasks(this.taskList);
        this.ui.printWelcomeMsg();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    this.ui.print("Bye. Hope to see you again soon!");
                    break;
                }
                Command c = Parser.parseInput(input);
                c.execute(this.taskList, this.ui, this.storage);
            } catch (DukeException d) {
                this.ui.print(d.getMessage());
            } catch (DateTimeParseException e) {
                this.ui.print("Do format your time in yyyy-MM-dd HH:mm");
            }
        }
        sc.close();
    }
}
