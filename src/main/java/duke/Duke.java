package duke;

import java.util.List;


/**
 * Represents a duke chatbot object that stores a task list,
 * users can add / delete / mark / unmark / list tasks using this chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates Duke chatbot object,
     * creates ui, parser, storage and tasklist objects
     */
    public Duke() {}
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load()); //exception handling ?
    }

    /**
     * Get response from duke chatbot,
     * @param input
     * @return list of strings
     */

    public List<String> getResponse(String input) {
        try {
            String line = input.trim();
            String[] parsedCommand = parser.parseCommand(line, tasks).split(" ", 2);
            assert parsedCommand.length >= 1 : "Parsed command length is incorrect.";
            if (parsedCommand[0].equals("mark") || parsedCommand[0].equals("unmark")
                    || parsedCommand[0].equals("todo") || parsedCommand[0].equals("event")
                    || parsedCommand[0].equals("deadline") || parsedCommand[0].equals("delete")) {
                storage.save(tasks);
                return ui.printAddCommandUi(parsedCommand[0], parsedCommand[1], tasks);
            } else if (parsedCommand[0].equals("tag") || parsedCommand[0].equals("untag")) {
                storage.save(tasks);
                return ui.printTagCommandUi(parsedCommand[0], parsedCommand[1].split(" ")[0],
                        parsedCommand[1].split(" ")[1], tasks);
            }else if (parsedCommand[0].equals("list")) {
                return ui.printListCommandUi(parsedCommand[0], parsedCommand[1], tasks);
            } else if (parsedCommand[0].equals("help")) {
                return ui.printDukeInfo();
            } else {
                return ui.printEndingUi();
            }

        } catch (Exception e) {
            return ui.showException(e);
        }
    }
}