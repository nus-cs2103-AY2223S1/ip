package jude;

import java.io.IOException;

/**
 * Jude is a task tracker which appears like a command line interface. The name is a Beatles
 * reference, referring to the hit song Hey Jude.
 * There are two modes, GUI mode (default).
 * GUI mode shows a GUI while console mode shows a command-line interface.
 * In console mode, when > shows up, you can type a command.
 * <br>
 *
 * There are three types of tasks, namely the todo, deadline and event.
 * Todo tasks are tasks without an associated date/time.
 * Deadline tasks have a specific deadline by which the task must be completed.
 * Event tasks have a start time and an end time.
 * <br>
 * Here are the list of commands:
 * todo (description) - adds a todo task with some description
 * deadline (description) /by (deadline) - adds a deadline task with the specified description and
 *   deadline. The deadline needs to be a valid date (e.g., 21 Aug 2022, Aug 21 2022 or
 *   2022-08-21), with time optional. If time is provided, it must be provided after the date,
 *   with exactly one space in between.
 * event (description) /from (start date/time) /to (end date/time) - adds an event task with start
 *   time and end time
 * list - lists all tasks
 * mark - mark the task with a specified index (from list command) as done
 *   e.g. mark 2 marks second task as done
 * unmark - mark the task with a specified index (from list command) as undone,
 *   e.g. unmark 2 marks second task as undone
 * find (keyword) - searches for tasks whose description matches the provided keyword (can be
 *   multiple words, case-insensitive match)
 * delete - delete the task corresponding to a specified index (from list command)
 *   e.g. delete 2 deletes second task
 * remindme - reminds the user of any event about to begin and any deadlines of tasks, which are
 *   not marked as complete, coming soon (default: within the next 24 hours)
 * bye - exits the program
 * <br>
 *
 * If the command does not have these prefixes, an error will be thrown saying that the bot does
 * not understand.
 */
public class Jude {
    private Storage storage;
    private final Ui ui = new Ui();
    private Parser parser;
    private TaskList tasks;

    /**
     * Creates an instance of Jude the chatbot.
     *
     * @param filePath The file path in which the list of tasks is stored.
     * @throws IOException When system I/O fails.
     */
    public Jude(String filePath) throws IOException {
        storage = new Storage(filePath);
        tasks = storage.load();
        parser = new Parser(tasks, storage);
    }

    /**
     * Returns the UI associated with this Jude the chatbot instance.
     *
     * @return The UI associated with this Jude the chatbot instance.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns whether the command terminates the chatbot.
     *
     * @param command The user input command.
     * @return true if the command terminates the chatbot, i.e. bye command, false otherwise.
     */
    public boolean isTerminationCommand(String command) {
        return parser.isTerminationCommand(command);
    }

    /**
     * Returns the reply from the chatbot.
     *
     * @param input The user command input.
     * @return The reply from the chatbot.
     * @throws IOException When system I/O fails.
     */
    public String getResponse(String input) throws IOException {
        return parser.parse(input);
    }

    /**
     * Runs the chatbot in console mode.
     *
     * @throws IOException When system I/O fails.
     */
    public void run() throws IOException {
        // Overall project structure code for main class adapted from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        TaskList tasks = storage.load();
        Parser parser = new Parser(tasks, storage);
        tasks = storage.load();
        parser = new Parser(tasks, storage);
        System.out.println(ui.getWelcomeMessage());
        System.out.println(parser.remindMe());

        while (true) {
            ui.showCommandReadReady();
            String str = ui.readCommand();
            boolean isTerminationCommand = parser.isTerminationCommand(str);
            if (isTerminationCommand) {
                ui.showByeMessage();
                break;
            }
            String response = parser.parse(str);
            System.out.println(response);
        }
    }

    /**
     * Returns the parser object associated with this instance of Jude the chatbot.
     *
     * @return The parser object associated with this instance of Jude the chatbot.
     */
    public Parser getParser() {
        return this.parser;
    }
}
