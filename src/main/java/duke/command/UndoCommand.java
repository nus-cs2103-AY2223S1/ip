package duke.command;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.CustomMessageException;
import duke.Parser;
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
        try {
            Scanner fileScanner = storage.getScannerForPreviousTasksFile();
            while (fileScanner.hasNextLine()) {
                Command parsedCommand = Parser.parseUserCommand(fileScanner.nextLine());
                parsedCommand.execute(storage, taskList);
            }
            fileScanner.close();
        } catch (FileNotFoundException | CustomMessageException e) {
            return "Unable to undo!";
        }
        return "Previous command successfully undone";
    }
}
