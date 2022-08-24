package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    String wordToFind;
    public FindCommand(String wordToFind) {
        this.wordToFind = wordToFind;
    }

    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printAnyOtherMessage(tasks.findTasks(wordToFind));
    }
}
