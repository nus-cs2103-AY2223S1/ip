package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for an Undo Command when a user wishes to restore
 * to the previous state (before execution of command).
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class UndoCommand extends Command {
    private String commandToUndo;
    private String taskDescription;
    private int taskNumber;

    // Messages to return for the various Commands
    private final String undoMarkMessage = "________________________________________\n"
            + "Undo Complete! I unmarked the task\n"
            + "________________________________________";;
    private final String undoUnmarkMessage = "________________________________________\n"
            + "Undo Complete! I marked the task\n"
            + "________________________________________";;
    private final String undoTodoMessage = "________________________________________\n"
            + "Undo Complete! I deleted the todo task\n"
            + "________________________________________";;
    private final String undoDeadlineMessage = "________________________________________\n"
            + "Undo Complete! I deleted the deadline task\n"
            + "________________________________________";;
    private final String undoEventMessage = "________________________________________\n"
            + "Undo Complete! I deleted the event task\n"
            + "________________________________________";
    private final String undoDeleteMessage = "________________________________________\n"
            + "Undo Complete! I added back the deleted task\n"
            + "________________________________________";
    private final String undoDefaultMessage = "________________________________________\n"
            + "Error! Cannot undo the previous command >.<\n"
            + "________________________________________";

    /**
     * Creates an instance of UndoCommand.
     * @param commandToUndo Command type that the user wishes to undo
     * @param taskDescription Description of the task (Add Commands)
     * @param taskNumber Task number that the user indicated (Mark, Unmark commands)
     */
    public UndoCommand(String commandToUndo, String taskDescription, int taskNumber) {
        this.commandToUndo = commandToUndo;
        this.taskDescription = taskDescription;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Undo Command which restores the ChatBot to the version before
     * the command has been executed. This only works for (Add, Delete, Mark, Unmark commands).
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String representation indicating the success/failure of the undo command
     * @throws DukeException if an error occurs in the execution of the commands
     * @throws IOException if an error occurs in the execution of the commands when reading
     *     or writing to the txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Command c;
        switch (commandToUndo) {
        case "mark":
            c = new UnmarkCommand(this.taskNumber);
            c.execute(tasks, ui, storage);
            return undoMarkMessage;
        case "unmark":
            c = new MarkCommand(this.taskNumber);
            c.execute(tasks, ui, storage);
            return undoUnmarkMessage;
        case "todo":
            c = new DeleteCommand(tasks.size());
            c.execute(tasks, ui, storage);
            return undoTodoMessage;
        case "deadline":
            c = new DeleteCommand(tasks.size());
            c.execute(tasks, ui, storage);
            return undoDeadlineMessage;
        case "event":
            c = new DeleteCommand(tasks.size());
            c.execute(tasks, ui, storage);
            return undoEventMessage;
        case "delete":
            Task taskToUndo = storage.formatString(this.taskDescription);
            tasks.add(taskToUndo);
            return undoDeleteMessage;
        default:
            return undoDefaultMessage;
        }
    }

    /**
     * Checks if two UndoCommands are the same.
     *
     * @param o Object to be compared against an instance of UndoCommand
     * @return true if the Object is an instance of UndoCommand with the same parameters
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof UndoCommand) {
            UndoCommand u = (UndoCommand) o;
            if (!this.commandToUndo.equals(u.commandToUndo)) {
                return false;
            }

            if (this.taskNumber != u.taskNumber) {
                return false;
            }

            if (this.taskDescription != null) {
                return this.taskDescription.equals(u.taskDescription);
            } else {
                return u.taskDescription == null;
            }

        }
        return false;
    }
}
