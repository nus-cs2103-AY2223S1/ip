package duke.core;

import duke.commands.*;
import duke.task.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private final Ui ui = new Ui();
    private final Parser parser;
    private final TaskList taskList = new TaskList();

    public Duke() {
        this.parser = new Parser(new ArrayList<>(Arrays.asList(
            new Exit("bye", this.ui),
            new AddTaskCommand<>("todo", taskList, null, ToDo::new),
            new AddTaskCommand<>("deadline", taskList, " /by ", Deadline::new),
            new AddTaskCommand<>("event", taskList, " /at ", Event::new),
            new ListCommand("list", taskList),
            new MarkCommand("mark", taskList, true),
            new MarkCommand("unmark", taskList, false),
            new DeleteTaskCommand("delete", taskList)
        )));
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                ui.showMessage(parser.parseInput(fullCommand));
                isExit = ui.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
