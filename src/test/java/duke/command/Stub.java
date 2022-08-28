package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

class TaskListStub extends TaskList {
    public TaskListStub() {
        super();
    }
}

class UiStub extends Ui {
    public UiStub() {
        super();
    }
}

class StorageStub extends Storage {
    public StorageStub(String fileName) {
        super(fileName);
    }
}

class CommandStub extends Command {
    public CommandStub() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return;
    };
}