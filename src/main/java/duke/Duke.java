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
     * Runs the duke chatbot,
     * starts the chatbot up, interacts with user commands, and ends until user enters 'bye' command.
     */
    /*
    public void run() {
        ui.printStartUpUi();
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                String line = input.nextLine().trim();
                String[] parsedCommand = parser.parseCommand(line, tasks).split(" ");
                if (parsedCommand[0].equals("mark") || parsedCommand[0].equals("unmark")
                        || parsedCommand[0].equals("to do") || parsedCommand[0].equals("event")
                        || parsedCommand[0].equals("deadline") || parsedCommand[0].equals("delete")) {
                    ui.printAddCommandUi(parsedCommand[0], parsedCommand[1], tasks);
                } else if (parsedCommand[0].equals("list")) {
                    ui.printListCommandUi(parsedCommand[0], parsedCommand[1], tasks);
                } else if (parsedCommand[0].equals("help")) {
                    ui.printDukeInfo();
                } else {
                    ui.printEndingUi();
                    break;
                }
            } catch (Exception e) {
                ui.printException(e);
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
    */

    public List<String> getResponse(String input) {
        try {
            String line = input.trim();
            String[] parsedCommand = parser.parseCommand(line, tasks).split(" ");
            if (parsedCommand[0].equals("mark") || parsedCommand[0].equals("unmark")
                    || parsedCommand[0].equals("todo") || parsedCommand[0].equals("event")
                    || parsedCommand[0].equals("deadline") || parsedCommand[0].equals("delete")) {
                return ui.printAddCommandUi(parsedCommand[0], parsedCommand[1], tasks);
            } else if (parsedCommand[0].equals("list")) {
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