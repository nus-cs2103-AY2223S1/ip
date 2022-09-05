package duke;


import Command.Command;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {

    }

    /**
     * Initializes the Duke chatbot.
     *
     * @param filePath Specifies the path to save the content.
     */

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the program
     */
    public void run() {
       
    }
    public static void main(String[] args) {
        // new Duke("src/main/java/duke.txt").run();
    }
}
