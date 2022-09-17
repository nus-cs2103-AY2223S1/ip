package duke;

import java.io.FileNotFoundException;

/**
 * Represents the Duke bot.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        ui = new Ui();
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        String pathToDuke = workingDir + "/out/duke.txt";
        storage = new Storage(pathToDuke);
        try {
            tasklist = storage.load();
        } catch (DukeException de) {
            tasklist = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets user input.
     *
     * @param input User input into Duke bot.
     * @return String of response to user.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(ui, tasklist, storage);
        try {
            return parser.parse(input);
        } catch (DukeException de) {
            int dukeExceptionWords = 19;
            return ui.printErrorMsg(de.toString().substring(dukeExceptionWords));
        }
    }

}
