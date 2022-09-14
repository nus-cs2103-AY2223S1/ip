package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.note.NoteList;

/**
 * Represents a command to exit Duke.
 */
public class ByeCommand extends Command {
    /**
     * Create a new ByeCommand.
     */
    public ByeCommand() {
        super();
        isExit = true;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) {
        assert isExit : "ByeCommand is malfunctioning.";
        ui.wrapPrint("Bye. Hope to see you again soon! ☺");
        storage.save(tasks, notes);
        return "Bye. Hope to see you again soon! ☺";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ByeCommand;
    }
}
