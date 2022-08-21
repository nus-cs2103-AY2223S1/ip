package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String word;
    private TaskList tasks;
    private Ui ui;

    public FindCommand(String word, TaskList tasks, Ui ui) {
        this.word = word;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        ui.readList(tasks.find(word));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
