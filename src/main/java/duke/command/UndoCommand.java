package duke.command;

import java.util.Optional;
import java.util.Scanner;

import duke.Duke;
import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class for the UNDO command.
 */
public class UndoCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList) {
        if (isFirstUndoableCommandRun) {
            return "Nothing to undo!";
        }
        taskList.removeAllTasks();
        Optional<Scanner> fileScanner = storage.getScannerForPreviousTasksFile();
        if (fileScanner.isPresent()) {
            fileScanner.ifPresent((scanner) -> Duke.handleScanner(scanner, storage, taskList));
            return "Previous command successfully undone";
        } else {
            return "Nothing to undo!";
        }
    }
}
