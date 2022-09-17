package duke;

import java.util.ArrayList;

import duke.tasks.Task;

public class Duke {

    private TaskList items;
    private Ui ui;
    private Storage storage;

    /**
     * Default constructor cos Java is dumb.
     */
    public Duke() {
        ui = new Ui("tasks.txt");
    }

    /**
     * Creates a Duke instance.
     * @param filePath the fileName.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = storage.loadFile();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            items = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Gets a response from Ui.
     * @param input the command.
     * @return the output.
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }

    /**
     * Runs a Duke instance.
     */
    public void run() {
        ui.printWelcome();
        String[] input;
        boolean done = false;

        while (!done) {
            String output = ui.getResponse(ui.getInput());
            if (output.equals("Bye. Hope to see you again soon!")) {
                done = true;
            }
            ui.printMsg(output);

        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
