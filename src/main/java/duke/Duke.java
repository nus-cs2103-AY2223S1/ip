package duke;

import java.util.List;


/**
 * Represents a TaskDive chatbot object that stores a task list,
 * users can add / delete / mark / unmark / list tasks using this chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isExit;

    /**
     * This method is Duke object constructor.
     *
     * A Duke object contains ui, parser, storage and tasklist objects and a boolean attribute isExit.
     * isExit represents the chatbot exit status (true: ready to exit; false: running)
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load()); //exception handling ?
        isExit = false;
    }

    /**
     * This method is a getter function, it returns the chatbot exit status (isExit attribute).
     *
     * @return chatbot exit status (isExit attribute).
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Returns a list of String containing chatbot response ui.
     * getResponse function make sense of user input command string, perform command and get response ui texts.
     *
     * @param input
     * @return list of strings containing chatbot response ui texts.
     */
    public List<String> getResponse(String input) {
        if (!input.equals("yes")) isExit = false;
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
                isExit = true;
                return ui.printEndingUi();
            }
        } catch (Exception e) {
            return ui.showException(e);
        }
    }
}