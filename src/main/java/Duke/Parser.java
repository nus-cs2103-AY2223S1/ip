package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Parser class object.
 */
public class Parser {
    public static boolean stopRunning = false;

    /**
     * Creates a Parser object by parsing the commands from user.
     *
     * @param command     is the response from user.
     * @param listOfTasks is the list of tasks stored.
     * @param ui          is the output text from Duke to console.
     * @param storage     deals with saving and loading tasks in Duke.txt.
     * @param undo        deals with undoing the last command from user.
     * @throws IOException if file does not open.
     */
    public static String parse(String command, TaskList listOfTasks, Ui ui, Storage storage, Undo undo)
            throws IOException {
        String reply = "";
        String[] response = command.split(" ");
        String firstWord = response[0].toUpperCase();

        switch (firstWord) {
        case "BYE":
            stopRunning = true;
            reply = ui.showBye();
            break;

        case "LIST":
            reply = ui.showTaskList(listOfTasks);
            break;

        case "MARK":
            try {
                int taskIndex = Integer.parseInt(response[1]) - 1;
                listOfTasks.markAsDone(taskIndex);
                reply = ui.showMarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("MARK");
                undo.addLastIndex(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! You're missing an index for mark.");
            }
            break;

        case "UNMARK":
            try {
                int taskIndex = Integer.parseInt(response[1]) - 1;
                listOfTasks.markAsNotDone(taskIndex);
                reply = ui.showUnmarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("UNMARK");
                undo.addLastIndex(taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! You're missing an index for unmark.");
            }
            break;

        case "TODO":
            try {
                String toDoTaskDescription = command.substring(5);
                Todo toDoTask = new Todo(toDoTaskDescription);
                listOfTasks.add(toDoTask);
                reply = ui.showToDoTask(toDoTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("TODO");
            } catch (StringIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! The description of a todo cannot be empty.");
            }
            break;

        case "DEADLINE":
            try {
                String deadlineDescriptionWithDate = command.substring(9);
                String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                listOfTasks.add(deadlineTask);
                reply = ui.showDeadlineTask(deadlineTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("DEADLINE");
            } catch (StringIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! You're missing some descriptions for your deadline.");
            } catch (DateTimeParseException e) {
                ui.showError(":( OOPS!!! You need to use yyyy-mm-dd for date format.");
            }
            break;

        case "EVENT":
            try {
                String eventDescriptionWithDate = command.substring(6);
                String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                Event eventTask = new Event(eventDescription, eventDate);
                listOfTasks.add(eventTask);
                reply = ui.showEventTask(eventTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("EVENT");
            } catch (StringIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! You're missing some descriptions for your event.");
            } catch (DateTimeParseException e) {
                ui.showError(":( OOPS!!! You need to use yyyy-mm-dd for date format.");
            }
            break;

        case "DELETE":
            try {
                int deleteIndex = Integer.parseInt(response[1]) - 1;
                Task deletedTask = listOfTasks.getTask(deleteIndex);
                listOfTasks.remove(deleteIndex + 1);
                reply = ui.showDeletedTask(deletedTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                undo.addLastCommand("DELETE");
                undo.addLastIndex(deleteIndex);
                undo.addLastTask(deletedTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! You're missing an index for delete.");
            }
            break;

        case "FIND":
            try {
                String keyword = response[1];
                TaskList matchingTasks = new TaskList();
                for (Task task : listOfTasks.getListOfTasks()) {
                    if (task.getDescription().contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                reply = ui.showFindTask(matchingTasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(":( OOPS!!! The description of a find cannot be empty.");
            }
            break;

        case "UNDO":
            if (undo.isCommandStackEmpty()) {
                reply = ui.showError("There are no previous commands.");
                break;
            }
            String lastCommand = undo.popLastCommand();
            switch (lastCommand) {
            case "MARK":
                int taskIndex = undo.popLastIndex();
                listOfTasks.markAsNotDone(taskIndex);
                reply = ui.showUnmarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            case "UNMARK":
                taskIndex = undo.popLastIndex();
                listOfTasks.markAsDone(taskIndex);
                reply = ui.showMarkedTask(taskIndex, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            case "TODO":
                Todo lastToDoTask = (Todo) listOfTasks.popLastTask();
                reply = ui.showDeletedTask(lastToDoTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            case "DEADLINE":
                Deadline lastDeadlineTask = (Deadline) listOfTasks.popLastTask();
                reply = ui.showDeletedTask(lastDeadlineTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            case "EVENT":
                Event lastEventTask = (Event) listOfTasks.popLastTask();
                reply = ui.showDeletedTask(lastEventTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            case "DELETE":
                taskIndex = undo.popLastIndex();
                Task deletedTask = undo.popLastTask();
                listOfTasks.addTaskToIndex(taskIndex, deletedTask);
                reply = ui.showUndoDeletedTask(deletedTask, listOfTasks);
                storage.writeToTextFile(listOfTasks);
                break;

            default:
                break;
            }
            break;

        default:
            reply = ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        return reply;
    }
}
