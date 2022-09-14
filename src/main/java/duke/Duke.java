package duke;

import java.io.FileNotFoundException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks_outstanding.txt");
        try {
            tasks = storage.loadExistingTasks();
        } catch (FileNotFoundException err) {
            tasks = new TaskList();
        }
    }

    /**
     * Provides Duke's response to a given user input
     * @param input Input from user
     * @return Response from Duke
     */
    protected String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        } catch (Exception ex) {
            return "Invalid user input ~ Type 'help' to view available commands";
        }
    }

    /**
     * Welcome message to users along with the total number of outstanding tasks
     * @return Greeting and number of outstanding tasks to users in String format
     */
    protected String greet() {
        return ui.getGreetingMessage() + String.format("\n\nYou have %d outstanding tasks!", tasks.getSize());
    }
}
